package by.kolbun.free.strategy.resources.storage;

import by.kolbun.free.strategy.resources.ResourceType;

public class SimpleResourceStorage extends AbstractResourceStorage {

  public SimpleResourceStorage(ResourceType type, int maxCapacity) {
    super(type, maxCapacity);
  }

  public SimpleResourceStorage(ResourceType type, int maxCapacity, int curValue) {
    super(type, maxCapacity, curValue);
  }
}
