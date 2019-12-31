package by.kolbun.free.strategy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class Starter {

  public static void main(String[] args) throws Exception {

    var context = new AnnotationConfigApplicationContext();
    context.scan("by.kolbun.free.strategy");
    context.refresh();
    MainStarter mainStarter = context.getBean(MainStarter.class);
    mainStarter.start(new String[]{"", ""});
  }


}
