package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.events.ResourceEventType;
import by.kolbun.free.strategy.resources.events.ResourceManager;
import by.kolbun.free.strategy.resources.events.ResourceSetDeltaDto;
import by.kolbun.free.strategy.resources.events.ResourceUpdateEvent;

import java.util.HashMap;
import java.util.Map;

public class ResourceSource extends Thread {

	private final Map<ResourceType, Integer> resources = new HashMap<>();
	private final ResourceManager resourceManager;

	public ResourceSource(ResourceManager resourceManager) {

		this.resourceManager = resourceManager;
	}

	public void addIncome(ResourceType resource, int value) {

		resources.merge(resource, value, (r, v) -> v += value);
	}

	@Override
	public void run() {

		while (true) {
			try {
				Thread.sleep(1000);

				ResourceSetDeltaDto dto = new ResourceSetDeltaDto();
				resources.forEach(dto::setResourcesDelta);
				resourceManager.notify(ResourceEventType.UPDATE, new ResourceUpdateEvent(dto));

			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
