package by.kolbun.free.strategy;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.dto.ResourceSetDeltaDto;
import by.kolbun.free.strategy.resources.events.ResourceEventType;
import by.kolbun.free.strategy.resources.events.ResourceUpgradeEvent;
import by.kolbun.free.strategy.resources.handler.ResourceManager;
import by.kolbun.free.strategy.resources.handler.ResourceReceiver;
import by.kolbun.free.strategy.resources.handler.ResourceSource;

public class MainStarter {

	private final ResourceSource resourceSource;
	private final ResourceManager manager;

	public MainStarter(ResourceSource resourceSource, ResourceManager manager) {

		this.resourceSource = resourceSource;
		this.manager = manager;
	}

	public static void main(String[] args) throws Exception {

		ResourceManager manager = new ResourceManager();
		ResourceSource resourceSource = new ResourceSource(manager);
		MainStarter starter = new MainStarter(resourceSource, manager);
		starter.start();
	}

	public void start() throws Exception {

		ResourceReceiver storage = new ResourceReceiver();
		storage.addStorage(ResourceType.GOLD, 40);
		storage.addStorage(ResourceType.WOOD, 40);
		manager.subscribe(ResourceEventType.UPDATE, storage);
		manager.subscribe(ResourceEventType.UPGRADE, storage);

		StorageInfoLogger logger = new StorageInfoLogger(storage);
		logger.start();

		//    ResourceSource resourceSource = new ResourceSource(manager);

		resourceSource.changeIncome(ResourceType.GOLD, 15);
		resourceSource.start();

		Thread.sleep(5000);

		manager.notify(ResourceEventType.UPGRADE, new ResourceUpgradeEvent(new ResourceSetDeltaDto(ResourceType.GOLD, 40)));
		//    resourceSource.changeIncome(ResourceType.GOLD, -10);
		//    storage.upgradeCapacity(ResourceType.GOLD, 50);
		//    ResourceSource sourceWood = new ResourceSource(manager);
		//    sourceWood.changeIncome(ResourceType.WOOD, 10);
		//    sourceWood.changeIncome(ResourceType.GOLD, -20);
		//    sourceWood.start();

		Thread.sleep(7000);

	}

}