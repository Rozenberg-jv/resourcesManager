package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.Resource;
import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.events.ResourceSetDeltaDto;

import java.util.*;

public class ResourceSource extends Thread {

	private final Map<ResourceType, Integer> resources = new HashMap<>();

	public ResourceSource() {

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

			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
