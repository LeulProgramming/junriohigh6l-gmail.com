package coach.randle.meme;

import net.dv8tion.jda.api.JDA;

import java.util.concurrent.TimeUnit;

public class StupidLoopCommand implements ICommands{

    @Override
    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        jda.getRestPing().queue(
                (ping) -> ctx.getChannel()
                        .sendMessageFormat("Reset ping: %sms\nWS ping: %sms", ping, jda.getGatewayPing()).queue()
        );
    }

    @Override
    public String getName() {
        return "ping";
    }


}
