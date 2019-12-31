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

  public void addStorage(ResourceType type, int maxCapacity) {

    storages.putIfAbsent(type, new SimpleResourceStorage(type, maxCapacity));
  }

  public void printStorageInfo() {
    storages.forEach(
        (type, storage) -> System.out.printf(
            "%s: %d / %d\n",
            storage.getResourceType(),
            storage.getCurValue(),
            storage.getMaxCapacity()));
    System.out.println();
  }

  @Override
  public void actionPerformed(EventObject e) {

    ResourceSetDeltaDto income = ((ResourceSetDeltaDto) e.getSource());

    income.getResources().forEach(
        (type, delta) -> {
          Resource res = storages.get(type);
//          System.out.println(type + " " + (delta > 0 ? "+" : "") + delta);
//          printStorageInfo();
          if (res != null)
            res.updateCurValue(delta);
        });


//    storage.forEach((key, value) -> System.out.println(key + ":" + value.getCurValue()));
  }
}
