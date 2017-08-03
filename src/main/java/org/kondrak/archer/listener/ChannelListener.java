package org.kondrak.archer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelCreateEvent;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.channel.ChannelUpdateEvent;
import sx.blah.discord.handle.impl.events.guild.voice.VoiceChannelCreateEvent;
import sx.blah.discord.handle.impl.events.guild.voice.VoiceChannelDeleteEvent;
import sx.blah.discord.handle.impl.events.guild.voice.VoiceChannelUpdateEvent;

import java.util.Properties;

/**
 * Created by Administrator on 11/7/2016.
 */
@Component
public class ChannelListener {

    @Autowired
    private Properties applicationProperties;

    private static final Logger LOG = LoggerFactory.getLogger(ChannelListener.class);

    @EventSubscriber
    public void onCreate(ChannelCreateEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }

    @EventSubscriber
    public void onDelete(ChannelDeleteEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }

    @EventSubscriber
    public void onUpdate(ChannelUpdateEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }

    @EventSubscriber
    public void onVoiceChannelCreate(VoiceChannelCreateEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }

    @EventSubscriber
    public void onVoiceChannelDelete(VoiceChannelDeleteEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }

    @EventSubscriber
    public void onVoiceChannelUpdate(VoiceChannelUpdateEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }
}
