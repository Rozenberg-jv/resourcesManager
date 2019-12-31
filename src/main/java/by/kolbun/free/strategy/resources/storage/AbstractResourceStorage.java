package by.kolbun.free.strategy.resources.storage;

import by.kolbun.free.strategy.resources.ResourceType;
import lombok.Getter;

@Getter
public abstract class AbstractResourceStorage implements Resource {

  private final ResourceType resourceType;

  private int maxCapacity;

  private volatile int curValue;

  public AbstractResourceStorage(ResourceType resourceType, int maxCapacity) {

    this(resourceType, maxCapacity, 0);
  }

  public AbstractResourceStorage(ResourceType resourceType, int maxCapacity, int curValue) {

    this.resourceType = resourceType;
    this.maxCapacity = maxCapacity;
    this.curValue = curValue;
  }

  public void updateMaxCapacity(int delta) {

    if (maxCapacity + delta < 0)
      maxCapacity = 0;
    else
      this.maxCapacity += delta;
  }

  public synchronized void updateCurValue(int delta) {

    int newValue = this.curValue + delta;

    if (newValue < 0)
      curValue = 0;
    else if (newValue > maxCapacity)
      newValue = maxCapacity;
    else
      this.curValue += delta;
  }
}
