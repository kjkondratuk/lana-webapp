package org.kondrak.shared.guild;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.kondrak.shared.mappers.GuildMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.handle.obj.IGuild;

/**
 * Created by Administrator on 2/24/2017.
 */
@Component
public class GuildDao {

    @Autowired
    private GuildMapper guildMapper;

    public boolean guildIsSaved(IGuild guild) {
        Integer result = guildMapper.guildExists(guild.getStringID());
        return null != result && result > 0;
    }

    public void addGuild(IGuild guild) {
        guildMapper.insertGuild(guild.getStringID(), guild.getName(),
                guild.getIcon(), guild.getIconURL(), guild.getOwner().getStringID());
    }
}
