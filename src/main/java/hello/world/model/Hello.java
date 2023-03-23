package hello.world.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Hello {
    private String team;
    private String name;
}
