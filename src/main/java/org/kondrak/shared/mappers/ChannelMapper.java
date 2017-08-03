package org.kondrak.shared.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelMapper {
    @Select("SELECT 1 FROM channel WHERE channel_id = #{channelId}")
    Integer channelExists(@Param("channelId") String channelId);

    @Insert({"INSERT INTO channel (channel_id, name, isprivate, parent, position) VALUES (",
            "#{channelId}, #{name}, #{isPrivate}, #{parent}, #{position})"})
    void addChannel(@Param("channelId") String channelId, @Param("name") String name,
                    @Param("isPrivate") boolean isPrivate, @Param("parent") String parent,
                    @Param("position") Integer position);
}
