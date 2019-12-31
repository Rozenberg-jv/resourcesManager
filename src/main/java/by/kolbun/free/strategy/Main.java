package by.kolbun.free.strategy;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.events.ResourceEventType;
import by.kolbun.free.strategy.resources.handler.ResourceManager;
import by.kolbun.free.strategy.resources.handler.ResourceReceiver;
import by.kolbun.free.strategy.resources.handler.ResourceSource;

public class Main {

  public static void main(String[] args) throws Exception {

    ResourceManager manager = new ResourceManager();
    ResourceReceiver storage = new ResourceReceiver();
    storage.addStorage(ResourceType.GOLD, 40);
    storage.addStorage(ResourceType.WOOD, 40);
    manager.subscribe(ResourceEventType.UPDATE, storage);

    StorageInfoLogger logger = new StorageInfoLogger(storage);
    logger.start();

    ResourceSource sourceGold = new ResourceSource(manager);

    sourceGold.changeIncome(ResourceType.GOLD, 5);
    sourceGold.start();

    Thread.sleep(3000);

    sourceGold.changeIncome(ResourceType.GOLD, -10);
//    ResourceSource sourceWood = new ResourceSource(manager);
//    sourceWood.changeIncome(ResourceType.WOOD, 10);
//    sourceWood.changeIncome(ResourceType.GOLD, -20);
//    sourceWood.start();

    Thread.sleep(7000);

  }

}
