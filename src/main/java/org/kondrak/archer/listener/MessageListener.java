package org.kondrak.archer.listener;

import org.kondrak.archer.command.BotCommand;
import org.kondrak.archer.command.MessageEventCommand;
import org.kondrak.shared.message.MessageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MentionEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageUpdateEvent;
import sx.blah.discord.handle.obj.IMessage;

import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 11/3/2016.
 */
@Component
public class MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private Properties applicationProperties;

    @Autowired
    private List<MessageEventCommand> commands;

    @Autowired
    private MessageDao msgDao;

    @EventSubscriber
    public void onMessage(MessageReceivedEvent e) {
        IMessage msg = e.getMessage();
        LOG.info("Channel: {} Author: {} - {}", msg.getChannel(),  msg.getAuthor(),  msg);
        msgDao.saveMessage(msg);
        commands.forEach(cmd -> {
            if(cmd.shouldExecute(msg)) {
                cmd.execute(msg);
            }
        });
    }

    // TODO: add a mentions table that ties back to messages and/or message history
    @EventSubscriber
    public void onMention(MentionEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }

    // TODO: add deletes and a message history table to preserve message history
    @EventSubscriber
    public void onDelete(MessageDeleteEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format", e.getClass().getName()));
    }

    // TODO: add updates and a message history table to preserve message history
    @EventSubscriber
    public void onUpdate(MessageUpdateEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }
}
