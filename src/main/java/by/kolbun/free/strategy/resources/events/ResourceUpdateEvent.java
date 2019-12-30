package by.kolbun.free.strategy.resources.events;

import java.util.EventObject;

public class ResourceUpdateEvent extends EventObject {

	public ResourceUpdateEvent(ResourceSetDeltaDto source) {

		super(source);
	}
}
