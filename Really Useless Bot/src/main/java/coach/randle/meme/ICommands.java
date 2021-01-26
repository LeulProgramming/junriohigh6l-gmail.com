package coach.randle.meme;

import java.util.Arrays;
import java.util.List;

public interface ICommands {
    void handle(CommandContext ctx);


    String getName();


    default List<String> getAliases() {

        return Arrays.asList();

    }
}
