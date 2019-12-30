package by.kolbun.free.strategy.resources.events;

import java.util.*;
import java.util.stream.Stream;

public class ResourceManager {

	private final Map<ResourceEventType, List<ResourceEventListener>> listeners = new HashMap<>();

	public ResourceManager() {

		Stream.of(ResourceEventType.values()).forEach(t -> listeners.put(t, new ArrayList<>()));
	}

	public void subscribe(ResourceEventType type, ResourceEventListener listener) {

		List<ResourceEventListener> users = listeners.get(type);
		users.add(listener);
	}

	public void unsubscribe(ResourceEventType eventType, ResourceEventListener listener) {

		List<ResourceEventListener> users = listeners.get(eventType);
		users.remove(listener);
	}

	public void notify(ResourceEventType eventType, EventObject e) {

		List<ResourceEventListener> users = listeners.get(eventType);
		users.forEach(l -> l.actionPerformed(e));
	}

}
