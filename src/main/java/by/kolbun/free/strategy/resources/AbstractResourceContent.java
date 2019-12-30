package by.kolbun.free.strategy.resources;

import lombok.Getter;

@Getter
public abstract class AbstractResourceContent implements Resource {

	private final ResourceType type;

	private int maxCapacity;

	private volatile int curVolume;

	public AbstractResourceContent(ResourceType type, int maxCapacity) {

		this(type, maxCapacity, 0);
	}

	public AbstractResourceContent(ResourceType type, int maxCapacity, int curVolume) {

		this.type = type;
		this.maxCapacity = maxCapacity;
		this.curVolume = curVolume;
	}

	public void updateMaxCapacity(int delta) {

		this.maxCapacity += delta;
	}

	public synchronized void updateCurVolume(int delta) {

		this.curVolume += delta;
	}
}
