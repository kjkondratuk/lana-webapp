package org.kondrak.archer.command.impl;

import org.kondrak.archer.command.AbstractMessageCommand;
import org.kondrak.shared.config.ConfigType;
import org.kondrak.shared.config.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.Properties;
import java.util.Random;

/**
 * Created by nosferatu on 7/11/17.
 */
@Component
public class DiceRollParameterizedCommand extends AbstractMessageCommand {

    private static final Logger LOG = LoggerFactory.getLogger(DiceRollParameterizedCommand.class);

    @Autowired
    private ConfigurationService configService;

    public DiceRollParameterizedCommand(@Autowired Properties applicationProperties) {
        super("roll", applicationProperties);
    }

    // TODO : make these types of commands have their own interface and abstract class to avoid repetition regex application
    @Override
    public void execute(IMessage input) {
        String content = input.getContent().replace(getCommand()+ " ", "");

        String[] parts = content.replace(" ", "").split("d");
        int number = Integer.parseInt(parts[0]);
        int sides = Integer.parseInt(parts[1]);
        int total = 0;

        Random r = new Random();
        for(int n = 0; n < number; n++) {
            total += r.nextInt(sides) + 1;
        }

        try {
            input.reply("You rolled: " + total);
        } catch (MissingPermissionsException | RateLimitException | DiscordException e) {
            LOG.error("Could not respond to DiceRollParameterizedCommand: ", e);
        }
    }

    @Override
    public boolean shouldExecute(IMessage input) {
        if(null != input.getContent() && input.getContent().startsWith(getCommand())
                && configService.isConfiguredForGuild(input.getGuild(), ConfigType.DICE_COMMAND)) {
            String content = input.getContent().replace(getCommand()+ " ", "");

            if(!content.matches("[0-9]{1,3}d[0-9]{1,3}")) {
                handleFormatError(input);
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getFormatErrorMessage(IMessage input) {
        return "Your dice roll command was improperly formatted.\n" +
                "Please use the following format:\n" +
                "\t* <prefix>roll [number]d[sides]";
    }
}
