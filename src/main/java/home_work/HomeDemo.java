package home_work;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@ComponentScan
@PropertySource("classpath:application.properties")
public class HomeDemo {

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(HomeDemo.class);

        var flow = context.getBean(Flow.class);
        flow.run(1);
        flow.run(2);
        flow.run(2);
        flow.run(3);
        flow.run(4);

    }

}
