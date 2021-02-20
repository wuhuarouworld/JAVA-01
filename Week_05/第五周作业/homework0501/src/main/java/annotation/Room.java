package annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import xml.Food;
import xml.Person;

import javax.annotation.Resource;

@Data
public class Room {

    @Autowired
    private Person person;

    @Resource(name = "food01")
    private Food food;

    public void live() {
        System.out.println("The room has " + this.person.getFoods().size() + " foods and one is " + this.food);
    }


}
