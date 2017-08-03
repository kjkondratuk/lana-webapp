package org.kondrak.archer.listener;

import org.kondrak.shared.channel.ChannelDao;
import org.kondrak.shared.guild.GuildDao;
import org.kondrak.shared.user.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2/24/2017.
 */
@Component
public class ReadyListener {

    private static final Logger LOG = LoggerFactory.getLogger(ReadyListener.class);

    @Autowired
    private Properties applicationProperties;

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private GuildDao guildDao;

    @Autowired
    private UserDao userDao;

    @EventSubscriber
    public void onReady(ReadyEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
        List<IUser> u = e.getClient().getUsers();

        for(int i = 0; i <u.size(); i++) {
            boolean saved = userDao.userIsSaved(u.get(i).getStringID());
            if(!saved) {
                LOG.info("Saved!: " + u.get(i).getName());
                userDao.insertUser(u.get(i));
            }
        }

        List<IGuild> guilds = e.getClient().getGuilds();

        for(IGuild g : guilds) {
            if(!guildDao.guildIsSaved(g)) {
                LOG.info("Adding " + g.getName());
                guildDao.addGuild(g);
            }
        }

        List<IChannel> channels = e.getClient().getChannels();

        for(IChannel c : channels) {
            if(!channelDao.channelIsSaved(c)) {
                LOG.info("Adding " + c.getName());
                channelDao.addChannel(c);
            }
        }
    }
}
