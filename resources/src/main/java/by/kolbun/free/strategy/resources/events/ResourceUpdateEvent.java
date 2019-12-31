package by.kolbun.free.strategy.resources.events;

import by.kolbun.free.strategy.resources.dto.ResourcesDto;

import java.util.EventObject;

public class ResourceUpdateEvent extends EventObject {

	public ResourceUpdateEvent(ResourcesDto source) {

		super(source);
	}
}
