package by.kolbun.free.strategy.resources.handler;

import by.kolbun.free.strategy.resources.Resource;
import lombok.Getter;

import java.util.EventObject;

@Getter
public class SingleResourceHandler extends EventObject {

	private final Resource resource;

	private int income;

	public SingleResourceHandler(Resource resource) {

		this(resource, 0);
	}

	public SingleResourceHandler(Resource resource, int income) {

		super(resource);
		this.resource = resource;
		this.income = income;
	}

	public void updateMaxCapacity(int delta) {

		resource.updateMaxCapacity(delta);
	}

	public void updateIncome(int delta) {

		income += delta;
	}

	public void run() {

	}
}
