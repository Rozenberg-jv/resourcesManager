package by.kolbun.free.strategy.resources.storage;

import by.kolbun.free.strategy.resources.ResourceType;

public class SimpleResourcesStorage extends AbstractResourcesStorage {

  public SimpleResourcesStorage(ResourceType type, int maxCapacity) {
    super(type, maxCapacity);
  }

  public SimpleResourcesStorage(ResourceType type, int maxCapacity, int curValue) {
    super(type, maxCapacity, curValue);
  }
}
