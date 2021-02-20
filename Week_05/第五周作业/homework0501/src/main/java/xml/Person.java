package xml;

import lombok.Data;

import java.util.List;

@Data
public class Person {

    List<Food> foods;

    public void eating() {
        System.out.println(this.getFoods());
    }
}
