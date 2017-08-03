package org.kondrak.archer.command.impl;

import org.kondrak.archer.command.AbstractMessageCommand;
import org.kondrak.shared.message.MessageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MessageList;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;

import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

/**
 * Created by Administrator on 2/25/2017.
 */
@Component
public class LoadMessagesBasicAdminCommand extends AbstractMessageCommand {

    private static final Logger LOG = LoggerFactory.getLogger(LoadMessagesBasicAdminCommand.class);

    @Autowired
    private Properties applicationProperties;

    @Autowired
    private MessageDao messageDao;

    public LoadMessagesBasicAdminCommand(@Autowired Properties applicationProperties) {
        super("admin load", applicationProperties);
    }

    @Override
    public void execute(IMessage input) {
        List<IChannel> channels = input.getClient().getChannels();

        int channelCount = 0;
        int msgCount = 0;
        for(IChannel c : channels) {
            LOG.info("Loading from channel: {}", c.getName());
            channelCount++;

            MessageList msg = new MessageList(input.getClient(), c);
            msg.setCacheCapacity(20000);

            try {
                msg.load(100);
            } catch (RateLimitException e) {
                LOG.error("Could not reload initial buffer in LoadMessagesBasicAdminCommand: ", e);
            }

            loadBatch(msg, msgCount);
        }

        try {
            input.reply(msgCount + " messages loaded from " + channelCount + " channels");
        } catch(MissingPermissionsException | RateLimitException | DiscordException ex) {
            LOG.error("Could not reply to message: {}", input.getChannel().getName());
            ex.printStackTrace();
        }
    }

    @Override
    public boolean shouldExecute(IMessage msg) {
        return null != msg.getContent() && msg.getContent().startsWith(getCommand())
                && msg.getAuthor().getName().equals(applicationProperties.getProperty("admin.name"))
                && msg.getAuthor().getDiscriminator().equals(applicationProperties.getProperty("admin.discriminator"));
    }

    @Override
    public String getFormatErrorMessage(IMessage input) {
        throw new UnsupportedOperationException("getFormatErrorMessage() is not implemented for " + this.getClass().getName());
    }

    private void loadBatch(MessageList msg, int msgCount) {
        ListIterator<IMessage> iter =  msg.listIterator();
        IMessage m = iter.next();
        if(m != null) {
            while (iter.hasNext()) {
                boolean alreadyExists = messageDao.messageExists(m.getStringID());

                if(!alreadyExists) {
                    LOG.info("Loading message: {}", m.getContent());
                    messageDao.saveMessage(m);
                    msgCount++;
                }

                m = iter.next();
                if (msgCount % 50 == 0) {
                    try {
                        LOG.info("=== Refreshing buffer @ {} ===", msgCount);
                        msg.load(100);
                    } catch (RateLimitException e) {
                        LOG.error("Could not reload subsequent buffer in LoadMessagesBasicAdminCommand: ", e);
                    }
                }
            }
        }
    }
}
