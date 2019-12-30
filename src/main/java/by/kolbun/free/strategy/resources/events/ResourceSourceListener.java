package by.kolbun.free.strategy.resources.events;

import java.util.EventObject;

public class ResourceSourceListener implements ResourceEventListener {

	public void actionPerformed(EventObject e) {

		((ResourceSetDeltaDto) e.getSource()).getResources().forEach((key, value) -> System.out.println(key + ":" + value));
		System.out.println();
	}
}
