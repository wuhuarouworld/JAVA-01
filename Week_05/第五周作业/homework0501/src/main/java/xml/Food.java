package xml;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Food {

    private String name;

    public Food create() {
        return new Food("白菜炒五花肉");
    }
}
