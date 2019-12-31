package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.events.ResourceUpdateEvent;
import by.kolbun.free.strategy.resources.events.ResourceUpgradeEvent;
import by.kolbun.free.strategy.resources.events.ResourceUpgradeEventListener;
import by.kolbun.free.strategy.resources.storage.Resource;
import by.kolbun.free.strategy.resources.events.ResourceIncomeEventListener;
import by.kolbun.free.strategy.resources.dto.ResourceSetDeltaDto;
import by.kolbun.free.strategy.resources.storage.SimpleResourceStorage;

import java.util.*;

public class ResourceReceiver implements ResourceIncomeEventListener, ResourceUpgradeEventListener {

  private final Map<ResourceType, SimpleResourceStorage> storages = new HashMap<>();

  public ResourceReceiver() {

  }

  public void addStorage(ResourceType type, int maxCapacity) {

    storages.putIfAbsent(type, new SimpleResourceStorage(type, maxCapacity));
  }

  /*public void upgradeCapacity(ResourceType type, int capacityDelta) {
    storages.compute(type, (t, storage) -> {
      SimpleResourceStorage st;

      if (storage == null) {
        st = new SimpleResourceStorage(t, 0);
      } else
        st = storage;

      st.updateMaxCapacity(capacityDelta);
      return st;
    });
  }*/


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
  public void actionPerformed(EventObject event) {

    if (event instanceof ResourceUpdateEvent) {

      ResourceSetDeltaDto income = ((ResourceSetDeltaDto) event.getSource());

      income.getResources().forEach(
          (type, delta) -> {
            Resource storage = storages.get(type);
            if (storage != null)
              storage.updateCurValue(delta);
          });
    } else if (event instanceof ResourceUpgradeEvent) {

      ResourceSetDeltaDto capacityDelta = ((ResourceSetDeltaDto) event.getSource());

      capacityDelta.getResources().forEach(
          (type, delta) -> {
            Resource storage = storages.get(type);
            if (storage != null)
              storage.updateMaxCapacity(delta);
          });
    }

  }
}
