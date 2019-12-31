package by.kolbun.free.strategy.resources.storage;

import by.kolbun.free.strategy.resources.ResourceType;

public interface Resource {

	void updateMaxCapacity(int delta);

	void updateCurValue(int delta);

	ResourceType getResourceType();

	int getCurValue();

	int getMaxCapacity();
}
