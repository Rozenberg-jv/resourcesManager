package by.kolbun.free.strategy.resources.events;

import by.kolbun.free.strategy.resources.dto.ResourceSetDeltaDto;

import java.util.EventObject;

public class ResourceUpgradeEvent extends EventObject {

  public ResourceUpgradeEvent(ResourceSetDeltaDto source) {
    super(source);
  }
}
