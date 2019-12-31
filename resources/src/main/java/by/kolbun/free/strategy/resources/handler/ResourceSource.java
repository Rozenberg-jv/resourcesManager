package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.dto.ResourcesDto;
import by.kolbun.free.strategy.resources.events.ResourceEventType;
import by.kolbun.free.strategy.resources.events.ResourceUpdateEvent;
import by.kolbun.free.strategy.resources.exceptions.ResourceUnsupportedByStorageException;
import by.kolbun.free.strategy.resources.storage.MultipleResourcesStorage;

import java.util.Arrays;
import java.util.Collections;

public class ResourceSource extends Thread {

	// источник!
	private final MultipleResourcesStorage resourceIncome;
	private final ResourceManager resourceManager;

	public ResourceSource(ResourceManager resourceManager, ResourceType... types) {

		this.resourceIncome = new MultipleResourcesStorage();
		Arrays.asList(types).forEach(type -> resourceIncome.allowStorage(type, 0));

		this.resourceManager = resourceManager;
		this.setDaemon(true);
	}

	public void changeIncome(ResourceType type, int incomeDelta) {

		try {
			resourceIncome.changeIncome(type, incomeDelta);
		} catch (ResourceUnsupportedByStorageException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(1000);

				ResourcesDto dto = resourceIncome.getResourcesDto();
				resourceManager.notify(ResourceEventType.UPDATE, new ResourceUpdateEvent(dto));

			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
