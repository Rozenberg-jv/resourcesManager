package by.kolbun.free.strategy;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.events.ResourceEventType;
import by.kolbun.free.strategy.resources.events.ResourceManager;
import by.kolbun.free.strategy.resources.handler.ResourceReceiver;
import by.kolbun.free.strategy.resources.handler.ResourceSource;

public class Main {

	public static void main(String[] args) throws Exception {

		ResourceManager manager = new ResourceManager();
		ResourceReceiver receiver = new ResourceReceiver();
		manager.subscribe(ResourceEventType.UPDATE, receiver);

		ResourceSource source = new ResourceSource(manager);

		source.addIncome(ResourceType.GOLD, 5);
		source.start();

		Thread.sleep(4000);
		source.addIncome(ResourceType.WOOD, 5);
		source.addIncome(ResourceType.GOLD, 4);

	}

}
