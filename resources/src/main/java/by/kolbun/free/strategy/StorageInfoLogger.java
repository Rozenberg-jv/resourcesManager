package by.kolbun.free.strategy;


import by.kolbun.free.strategy.resources.handler.ResourceReceiver;

public class StorageInfoLogger extends Thread {

  private final ResourceReceiver storage;

  public StorageInfoLogger(ResourceReceiver storage) {
    this.storage = storage;

    this.setDaemon(true);
  }

  @Override
  public void run() {
    while (true) {

      storage.printStorageInfo();

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
