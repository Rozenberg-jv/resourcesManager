package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.storage.Resource;
import by.kolbun.free.strategy.resources.events.ResourceEventListener;
import by.kolbun.free.strategy.resources.dto.ResourceSetDeltaDto;
import by.kolbun.free.strategy.resources.storage.SimpleResourceStorage;

import java.util.*;

public class ResourceReceiver implements ResourceEventListener {

  private final Map<ResourceType, SimpleResourceStorage> storages = new HashMap<>();

  public ResourceReceiver() {

  }

  public void printStorageInfo() {
    storages.forEach(
        storage -> System.out.printf(
            "%s: %d / %d",
            storage.getResourceType(),
            storage.getCurValue(),
            storage.getMaxCapacity()));
    System.out.println();
  }

  @Override
  public void actionPerformed(EventObject e) {

    ResourceSetDeltaDto income = ((ResourceSetDeltaDto) e.getSource());

    income.getResources().forEach(
        (resource, value) -> {
          Resource res = storages.get(resource);
          if (res == null)
            storages.put(res, 0);


          res.updateCurValue(value);
        });

//    storage.forEach((key, value) -> System.out.println(key + ":" + value.getCurValue()));
  }
}
