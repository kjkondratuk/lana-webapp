package org.kondrak.shared.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.kondrak.shared.model.Guild;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GuildMapper {
    @Select("SELECT 1 FROM guild WHERE guild_id = #{guildId}")
    Integer guildExists(@Param("guildId") String guildId);

    @Select("SELECT guild_id, name, owner_id FROM guild")
    @Results({
        @Result(column = "guild_id", property = "guildId"),
        @Result(column = "name", property = "guildName")
    })
    List<Guild> getAllGuilds();

    @Insert({"INSERT INTO guild (guild_id, name, icon, iconurl, owner_id) VALUES (",
            "#{guildId}, #{name}, #{icon}, #{iconUrl}, #{ownerId})"})
    void insertGuild(@Param("guildId") String guildId, @Param("name") String name, @Param("icon") String icon,
                     @Param("iconUrl") String iconUrl, @Param("ownerId") String ownerId);
}
