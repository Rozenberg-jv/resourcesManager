package by.kolbun.free.strategy;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.events.ResourceEventType;
import by.kolbun.free.strategy.resources.handler.ResourceManager;
import by.kolbun.free.strategy.resources.handler.ResourceReceiver;
import by.kolbun.free.strategy.resources.handler.ResourceSource;

public class Main {

  public static void main(String[] args) throws Exception {

    ResourceManager manager = new ResourceManager();
    ResourceReceiver receiver = new ResourceReceiver();
    manager.subscribe(ResourceEventType.UPDATE, receiver);

    StorageInfoLogger logger = new StorageInfoLogger(receiver);
    logger.start();

    ResourceSource sourceGold = new ResourceSource(manager);

    sourceGold.changeIncome(ResourceType.GOLD, 5);
    sourceGold.start();

    Thread.sleep(3000);

    ResourceSource sourceWood = new ResourceSource(manager);
    sourceWood.changeIncome(ResourceType.WOOD, 5);
    sourceWood.changeIncome(ResourceType.GOLD, 5);
    sourceWood.start();

    Thread.sleep(5000);

  }

}
