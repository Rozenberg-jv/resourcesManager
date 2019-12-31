package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.events.ResourceIncomeEventListener;
import by.kolbun.free.strategy.resources.events.ResourceEventType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Component
public class ResourceManager {

	private final Map<ResourceEventType, List<ResourceIncomeEventListener>> listeners;

	public ResourceManager() {

		listeners = new HashMap<>();
		Stream.of(ResourceEventType.values()).forEach(t -> listeners.put(t, new ArrayList<>()));
	}

	public void subscribe(ResourceEventType type, ResourceIncomeEventListener listener) {

		List<ResourceIncomeEventListener> users = listeners.get(type);
		users.add(listener);
	}

	public void unsubscribe(ResourceEventType eventType, ResourceIncomeEventListener listener) {

		List<ResourceIncomeEventListener> users = listeners.get(eventType);
		users.remove(listener);
	}

	public void notify(ResourceEventType eventType, EventObject e) {

		List<ResourceIncomeEventListener> users = listeners.get(eventType);
		users.forEach(l -> l.actionPerformed(e));
	}

}
