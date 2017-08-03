package org.kondrak.archer.command.impl;

import org.kondrak.archer.command.AbstractMessageCommand;
import org.kondrak.shared.config.ConfigCommand;
import org.kondrak.shared.config.ConfigScope;
import org.kondrak.shared.config.ConfigType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageBuilder;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.Properties;

/**
 * Created by Administrator on 11/7/2016.
 */
@Component
public class HelpBasicCommand extends AbstractMessageCommand {

    private static final Logger LOG = LoggerFactory.getLogger(HelpBasicCommand.class);

    @Autowired
    private IDiscordClient client;

    @Autowired
    private Properties applicationProperties;

    private static final String LINE_PREFIX = "== **";

    // TODO: add an admin flavor of this command so admin commands don't display alongside user commands
    // TODO: make this command more "man-like" and allow for more detailed help descriptions
    public HelpBasicCommand(@Autowired Properties applicationProperties) {
        super("help", applicationProperties);
    }

    @Override
    public void execute(IMessage input) {
        try {
            new MessageBuilder(client).withChannel(
                    client.getOrCreatePMChannel(input.getAuthor())).withContent(getHelpText()).send();
        } catch(RateLimitException | DiscordException | MissingPermissionsException ex) {
            LOG.error("Could not builder/send help message");
        }
    }

    @Override
    public boolean shouldExecute(IMessage input) {
        return null != input.getContent() && input.getContent().startsWith(getCommand());
    }

    @Override
    public String getFormatErrorMessage(IMessage input) {
        throw new UnsupportedOperationException("getFormatErrorMessage() is not implemented for " + this.getClass().getName());
    }

    private String getHelpText() {
        return  "=============================================\n" +
                "== **Available Commands:**\n" +
                "=============================================\n" +
                LINE_PREFIX + applicationProperties.getProperty("app.prefix") + "archerism** - display a random archer quote\n" +
                LINE_PREFIX + applicationProperties.getProperty("app.prefix") + "help** - display help information on the commands you can use\n" +
                LINE_PREFIX + applicationProperties.getProperty("app.prefix") + "word <word/phrase>** - display the number of word usages by user\n" +
                LINE_PREFIX + applicationProperties.getProperty("app.prefix") + "timer <ms up to 13 characters> <name>** - start a timer\n"+
                LINE_PREFIX + applicationProperties.getProperty("app.prefix") + "roll <number up to 3 characters>d<sides up to 3 characters>** - roll one or more dice\n" +
                LINE_PREFIX + applicationProperties.getProperty("app.prefix") + "config (" + ConfigCommand.pipeDelimited() + ") (" + ConfigScope.pipeDelimited()
                + ") (" + ConfigType.pipeDelimited() + ")** - configure commands\n";
    }
}
