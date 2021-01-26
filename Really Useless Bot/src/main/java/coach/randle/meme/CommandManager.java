package coach.randle.meme;

import com.jagrosh.jdautilities.examples.command.PingCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class CommandManager {
    private final List<ICommands> command = new ArrayList<>();

    public CommandManager() {

        addCommand(new StupidLoopCommand());
    }
    private void addCommand(ICommands cmd) {

        boolean nameFound = this.command.stream().anyMatch((it ->it.getName().equalsIgnoreCase(cmd.getName())));

        if(nameFound) {

            throw new IllegalArgumentException("A command with this name is already present!");

        }

        command.add(cmd);
    }
    @Nullable
    private ICommands getCommand(String search) {

        String searchLower = search.toLowerCase();
        for(ICommands cmd : this.command) {

            if (cmd.getName().equals(searchLower) || cmd.getAliases().contains(searchLower)) {

                return cmd;
            }

        }
        return null;
    }

    void handle(GuildMessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(Config.get("PREFIX")), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommands cmd = this.getCommand(invoke);

        if(cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1,split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }




}
