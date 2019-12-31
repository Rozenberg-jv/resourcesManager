package by.kolbun.free.strategy.resources.dto;

import by.kolbun.free.strategy.resources.ResourceType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ResourceSetDeltaDto {

  @Getter
  private final Map<ResourceType, Integer> resources = new HashMap<>();

  public ResourceSetDeltaDto() {
  }

  public ResourceSetDeltaDto(ResourceType resource, int delta) {
    setResourcesDelta(resource, delta);
  }

  public void setResourcesDelta(ResourceType resource, int delta) {

    resources.merge(resource, delta, (k, v) -> v += delta);
  }

}
