package by.kolbun.free.strategy.resources;

public interface Resource {

	void updateMaxCapacity(int delta);

	void updateCurVolume(int delta);
}
