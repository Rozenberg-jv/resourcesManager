package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.ResourceType;
import by.kolbun.free.strategy.resources.events.ResourceUpdateEvent;
import by.kolbun.free.strategy.resources.events.ResourceUpgradeEvent;
import by.kolbun.free.strategy.resources.events.ResourceUpgradeEventListener;
import by.kolbun.free.strategy.resources.exceptions.ResourceUnsupportedByStorageException;
import by.kolbun.free.strategy.resources.storage.MultipleResourcesStorage;
import by.kolbun.free.strategy.resources.storage.Resources;
import by.kolbun.free.strategy.resources.events.ResourceIncomeEventListener;
import by.kolbun.free.strategy.resources.dto.ResourcesDto;

import java.util.*;

public class ResourceReceiver implements ResourceIncomeEventListener, ResourceUpgradeEventListener {

	private final MultipleResourcesStorage storages;

	public ResourceReceiver() {

		this.storages = new MultipleResourcesStorage();
	}

	public void allowStorage(ResourceType type, int maxCapacity) {

		storages.allowStorage(type, maxCapacity);
	}

	public void printStorageInfo() {

		storages.printInfo();
	}

	@Override
	public void actionPerformed(EventObject event) {

		if (event instanceof ResourceUpdateEvent) {

			ResourcesDto income = ((ResourcesDto) event.getSource());

			income.getResources().forEach(
					(type, delta) -> {
						try {
							Resources storage = storages.getStorage(type);
							if (storage != null)
								storage.updateCurValue(delta);
						} catch (ResourceUnsupportedByStorageException e) {
							e.printStackTrace();
						}
					});
		} else if (event instanceof ResourceUpgradeEvent) {

			ResourcesDto capacityDelta = ((ResourcesDto) event.getSource());

			capacityDelta.getResources().forEach(
					(type, delta) -> {
						try {
							Resources storage = storages.getStorage(type);
							if (storage != null)
								storage.updateMaxCapacity(delta);
						} catch (ResourceUnsupportedByStorageException e) {
							e.printStackTrace();
						}
					});
		}
	}
}
