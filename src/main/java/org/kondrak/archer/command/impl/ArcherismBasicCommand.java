package org.kondrak.archer.command.impl;

import org.kondrak.archer.command.AbstractMessageCommand;
import org.kondrak.shared.archerism.Archerism;
import org.kondrak.shared.archerism.ArcherismDao;
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

import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Administrator on 11/5/2016.
 */
@Component
public class ArcherismBasicCommand extends AbstractMessageCommand {

    private static final Logger LOG = LoggerFactory.getLogger(ArcherismBasicCommand.class);

    @Autowired
    private ArcherismDao dao;

    @Autowired
    private ConfigurationService configService;

    public ArcherismBasicCommand(@Autowired Properties applicationProperties) {
        super("archerism", applicationProperties);
    }

    @Override
    public void execute(IMessage input) {
        List<Archerism> sayings = dao.getArcherisms();
        int rand = new Random().nextInt(sayings.size());
        Archerism randMessage = sayings.get(rand);
        try {
            input.reply(randMessage.getText());
        } catch (MissingPermissionsException | RateLimitException | DiscordException ex) {
            LOG.error("Could not reply to archerism command: ", ex);
        }
    }

    @Override
    public boolean shouldExecute(IMessage input) {
        String content = input.getContent();
        return null != content && content.startsWith(getCommand())
                && configService.isConfiguredForGuild(input.getGuild(), ConfigType.ARCHERISM_COMMAND);
    }

    @Override
    public String getFormatErrorMessage(IMessage input) {
        throw new UnsupportedOperationException("getFormatErrorMessage() is not implemented for " + this.getClass().getName());
    }
}
