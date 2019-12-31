package by.kolbun.free.strategy;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.dto.ResourceSetDeltaDto;
import by.kolbun.free.strategy.resources.events.ResourceEventType;
import by.kolbun.free.strategy.resources.events.ResourceUpgradeEvent;
import by.kolbun.free.strategy.resources.handler.ResourceManager;
import by.kolbun.free.strategy.resources.handler.ResourceReceiver;
import by.kolbun.free.strategy.resources.handler.ResourceSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class MainStarter {

  private final ResourceSource sourceGold;

  @Autowired
  public MainStarter(ResourceSource sourceGold) {
    this.sourceGold = sourceGold;
  }

  public void start(String[] args) throws Exception {

    ResourceManager manager = new ResourceManager();
    ResourceReceiver storage = new ResourceReceiver();
    storage.addStorage(ResourceType.GOLD, 40);
    storage.addStorage(ResourceType.WOOD, 40);
    manager.subscribe(ResourceEventType.UPDATE, storage);
    manager.subscribe(ResourceEventType.UPGRADE, storage);

    StorageInfoLogger logger = new StorageInfoLogger(storage);
    logger.start();

//    ResourceSource sourceGold = new ResourceSource(manager);

    sourceGold.changeIncome(ResourceType.GOLD, 15);
    sourceGold.start();

    Thread.sleep(5000);

    manager.notify(ResourceEventType.UPGRADE, new ResourceUpgradeEvent(new ResourceSetDeltaDto(ResourceType.GOLD, 40)));
//    sourceGold.changeIncome(ResourceType.GOLD, -10);
//    storage.upgradeCapacity(ResourceType.GOLD, 50);
//    ResourceSource sourceWood = new ResourceSource(manager);
//    sourceWood.changeIncome(ResourceType.WOOD, 10);
//    sourceWood.changeIncome(ResourceType.GOLD, -20);
//    sourceWood.start();

    Thread.sleep(7000);

  }

}
