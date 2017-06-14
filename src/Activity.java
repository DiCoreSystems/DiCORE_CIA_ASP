import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CSZ on 14.06.2017.
 */
public class Activity extends Vertex {
    // Arguments and return type can be anything. Hence Object.
    private List<Object> arguments;
    private Object returnValue;

    public Activity(UUID id) {
        super(id);
    }
}
