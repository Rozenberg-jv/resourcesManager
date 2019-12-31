package by.kolbun.free.strategy.resources.exceptions;

import by.kolbun.free.strategy.resources.ResourceType;

public class ResourceUnsupportedByStorageException extends Exception {

	public ResourceUnsupportedByStorageException(ResourceType type) {

		super("Storage doesn't support resource type: " + type);
	}
}
