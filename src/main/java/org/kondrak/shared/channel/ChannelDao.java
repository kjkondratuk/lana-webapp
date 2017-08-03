package org.kondrak.shared.channel;

import org.kondrak.shared.mappers.ChannelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.handle.obj.IChannel;

/**
 * Created by Administrator on 2/24/2017.
 */
@Component
public class ChannelDao {

    @Autowired
    private ChannelMapper channelMapper;

    public boolean channelIsSaved(IChannel channel) {
        Integer result = channelMapper.channelExists(channel.getStringID());
        return null != result && result > 0;
    }

    public void addChannel(IChannel channel) {
        channelMapper.addChannel(channel.getStringID(), channel.getName(),
                channel.isPrivate(), channel.getGuild().getStringID(), channel.getPosition());
    }
}
