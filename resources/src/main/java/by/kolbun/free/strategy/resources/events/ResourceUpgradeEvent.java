package by.kolbun.free.strategy.resources.events;

import by.kolbun.free.strategy.resources.dto.ResourcesDto;

import java.util.EventObject;

public class ResourceUpgradeEvent extends EventObject {

  public ResourceUpgradeEvent(ResourcesDto source) {
    super(source);
  }
}
