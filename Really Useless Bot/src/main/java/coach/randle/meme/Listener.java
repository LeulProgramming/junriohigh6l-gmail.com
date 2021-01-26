package coach.randle.meme;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Listener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Listener.class);
    private final CommandManager manager = new CommandManager();
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        LOGGER.info("{} is ready!", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        User user = event.getAuthor();

        if(user.isBot() || event.isWebhookMessage()) {
            return;
        }

        String prefix = Config.get("PREFIX");
        String raw = event.getMessage().getContentRaw();


        if(raw.equalsIgnoreCase(prefix + "countdown") && (user.getId().equals(Config.get("OWNER_ID")) || user.getId().equals("426922373883887616"))) {
           event.getChannel().sendTyping().queue();
           for(int i = 0; i < 500; i++) {
               event.getChannel().sendMessage("niggas" + i).queue();
           }

            return;
        }

        if(raw.startsWith(prefix)) {
            manager.handle(event);
        }
    }
}
