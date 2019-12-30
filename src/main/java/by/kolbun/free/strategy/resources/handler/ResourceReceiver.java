package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.events.ResourceEventListener;
import by.kolbun.free.strategy.resources.events.ResourceSetDeltaDto;

import java.util.EventObject;
import java.util.HashMap;
import java.util.Map;

public class ResourceReceiver implements ResourceEventListener {

	private final Map<ResourceType, Integer> storage = new HashMap<>();

	public ResourceReceiver() {

	}

	@Override
	public void actionPerformed(EventObject e) {

		ResourceSetDeltaDto inc = ((ResourceSetDeltaDto) e.getSource());

		inc.getResources().forEach((k, v) -> storage.merge(k, v, (r, i) -> i += i));

		storage.forEach((key, value) -> System.out.println(key + ":" + value));
	}
}
