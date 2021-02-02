import me.jordanplayz158.utils.Initiate;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.apache.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Control {
    public static void main(String[] args) throws LoginException, IOException, InterruptedException {
        Logger logger = Initiate.log();

        JDA jda = JDABuilder.createLight("").build().awaitReady();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Command: ");
        String line = br.readLine();

        while(!line.equals("exit")) {
            String[] command = line.split(" ");

            switch(command[0]) {
                case "listGuilds":
                    logger.info(jda.getGuilds());
                    break;
                case "leaveGuild":
                    Objects.requireNonNull(jda.getGuildById(command[1])).leave().queue(success ->
                            logger.info("Bot has successfully left the guild with the id of " + command[1]));
                    break;
            }

            System.out.print("Enter Command: ");
            line = br.readLine();
        }

        System.exit(0);
    }
}
