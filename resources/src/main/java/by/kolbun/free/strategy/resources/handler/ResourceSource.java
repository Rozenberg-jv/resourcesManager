package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.dto.ResourceSetDeltaDto;
import by.kolbun.free.strategy.resources.events.ResourceEventType;
import by.kolbun.free.strategy.resources.events.ResourceUpdateEvent;

import java.util.HashMap;
import java.util.Map;

public class ResourceSource extends Thread {

	private final Map<ResourceType, Integer> resourceIncome;
	private final ResourceManager resourceManager;

	public ResourceSource(ResourceManager resourceManager) {

		this.resourceIncome = new HashMap<>();
		this.resourceManager = resourceManager;
		this.setDaemon(true);
	}

	public void changeIncome(ResourceType type, int incomeDelta) {

		resourceIncome.merge(type, incomeDelta, Integer::sum);
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(1000);

				ResourceSetDeltaDto dto = new ResourceSetDeltaDto();
				resourceIncome.forEach(dto::setResourcesDelta);
				resourceManager.notify(ResourceEventType.UPDATE, new ResourceUpdateEvent(dto));

			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
