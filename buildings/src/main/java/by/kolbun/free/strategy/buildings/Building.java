package by.kolbun.free.strategy.buildings;

import by.kolbun.free.strategy.resources.handler.ResourceSource;

public class Building implements Buildable {

	private final ResourceSource resourceSource;

	private final String title;

	public Building(String title, ResourceSource resourceSource) {

		this.resourceSource = resourceSource;
		this.title = title;
	}

	public void build() {

		resourceSource.start();
	}
}
