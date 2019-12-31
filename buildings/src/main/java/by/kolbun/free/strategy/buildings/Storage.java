package by.kolbun.free.strategy.buildings;

import by.kolbun.free.strategy.StorageInfoLogger;
import by.kolbun.free.strategy.resources.handler.ResourceReceiver;

public class Storage implements Buildable {

	private final String title;

	private final ResourceReceiver storage;

	public Storage(String title, ResourceReceiver storage) {

		this.title = title;
		this.storage = storage;
	}

	public void build() {

		StorageInfoLogger logger = new StorageInfoLogger(storage);
		logger.start();
	}
}
