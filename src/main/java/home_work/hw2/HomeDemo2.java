package home_work.hw2;

import home_work.hw2.service.Flow;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;


@ComponentScan
@PropertySource("classpath:application.properties")
@EnableAspectJAutoProxy
public class HomeDemo2 {

    public static void main(String[] args) throws Exception {

        var context = new AnnotationConfigApplicationContext(HomeDemo2.class);

        var flow = context.getBean(Flow.class);
        flow.run(10);
        flow.run(1);
        flow.run(2);
        flow.run(2);
        flow.run(3);
        flow.run(4);
        flow.run(4);
        flow.run(4);
        flow.run(4);
        flow.run(4);
        flow.run(4);
        flow.run(5);

        context.close();

    }

}
