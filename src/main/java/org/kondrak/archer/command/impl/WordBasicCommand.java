package org.kondrak.archer.command.impl;

import org.kondrak.archer.command.AbstractMessageCommand;
import org.kondrak.shared.config.ConfigType;
import org.kondrak.shared.config.ConfigurationService;
import org.kondrak.shared.message.MessageDao;
import org.kondrak.shared.model.Statistic;
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
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2/25/2017.
 */
@Component
public class WordBasicCommand extends AbstractMessageCommand {

    private static final Logger LOG = LoggerFactory.getLogger(WordBasicCommand.class);

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private ConfigurationService configService;

    public WordBasicCommand(@Autowired Properties applicationProperties) {
        super("word", applicationProperties);
    }

    @Override
    public void execute(IMessage input) {
        String content = input.getContent().replace(getCommand()+ " ", "");

        List<Statistic> values = messageDao.getTimesSaidByUser(input.getGuild().getStringID(), content)
                .stream()
                .sorted((v1, v2) -> v2.getValue().compareTo(v1.getValue()))
                .collect(Collectors.toList());

        String response = "The word/phrase '" + content + "' has been used a total of ";
        StringBuilder table = new StringBuilder("");
        Long total = 0L;

        for(int s = 0; s < values.size(); s++) {
            Long ct = Long.valueOf(values.get(s).getValue());
            table.append("- "+ (s + 1) + ": " + values.get(s).getKey() + ": " + ct + "\n");
            total += ct;
        }

        response += total + " times by:\n" + table.toString();

        try {
            input.reply(response);
        } catch (MissingPermissionsException | RateLimitException | DiscordException e) {
            LOG.error("Could not execute WordBasicCommand: ", e);
        }
    }

    @Override
    public boolean shouldExecute(IMessage input) {
        return null != input.getContent() && input.getContent().startsWith(getCommand())
            && configService.isConfiguredForGuild(input.getGuild(), ConfigType.WORD_COMMAND);
    }

    @Override
    public String getFormatErrorMessage(IMessage input) {
        throw new UnsupportedOperationException("getFormatErrorMessage() is not implemented for " + this.getClass().getName());
    }
}
