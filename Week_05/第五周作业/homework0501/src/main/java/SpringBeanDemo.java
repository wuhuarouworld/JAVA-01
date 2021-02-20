import annotation.Room;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xml.Food;
import xml.Person;

@Slf4j
public class SpringBeanDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Food food01 = (Food) context.getBean("food01");
        System.out.println(food01.toString());

        Food food02 = (Food) context.getBean("food02");
        System.out.println(food02.toString());

        Person person = context.getBean(Person.class);
        System.out.println(person);

        Room room = context.getBean(Room.class);
        System.out.println(room);

        person.eating();

        room.live();

        //System.out.println(" context.getBeanDefinitionNames() ===>> "+ String.join(",", context.getBeanDefinitionNames()));
    }
}
