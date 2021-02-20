package annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomConfig {

    @Bean
    public Room createRoom() {
        Room room = new Room();
        System.out.println(room);
        return room;
    }
}
