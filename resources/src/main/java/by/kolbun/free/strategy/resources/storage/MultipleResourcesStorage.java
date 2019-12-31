package by.kolbun.free.strategy.resources.storage;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.dto.ResourcesDto;
import by.kolbun.free.strategy.resources.exceptions.ResourceUnsupportedByStorageException;

import java.util.HashMap;
import java.util.Map;

public class MultipleResourcesStorage {

	private final Map<ResourceType, SimpleResourcesStorage> storages;

	public MultipleResourcesStorage() {

		this.storages = new HashMap<>();
	}

	public void allowStorage(ResourceType type, int maxCapacity) {

		storages.put(type, new SimpleResourcesStorage(type, maxCapacity));
	}

	public void printInfo() {

		storages.forEach(
				(type, storage) -> System.out.printf(
						"%s: %d / %d\n",
						storage.getResourceType(),
						storage.getCurValue(),
						storage.getMaxCapacity()));
		System.out.println();
	}

	public Resources getStorage(ResourceType type) throws ResourceUnsupportedByStorageException {

		if (!storages.containsKey(type))
			throw new ResourceUnsupportedByStorageException(type);

		return storages.get(type);
	}

	public void changeIncome(ResourceType type, int incomeDelta) throws ResourceUnsupportedByStorageException {

		Resources storage = storages.get(type);

		if (storage == null)
			throw new ResourceUnsupportedByStorageException(type);

		storage.updateCurValue(incomeDelta);
	}

	public ResourcesDto getResourcesDto() {

		ResourcesDto.ResourcesIncomeDtoBuilder builder = ResourcesDto.getBuilder();

		storages.forEach((type, storage) -> builder.addResourcesDelta(type, storage.getCurValue()));

		return builder.build();
	}
}
