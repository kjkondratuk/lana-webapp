package org.kondrak.shared.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.kondrak.shared.model.Statistic;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageMapper {

    @Select("SELECT 1 FROM message WHERE message_id = #{messageId} ")
    Integer messageExists(@Param("messageId") String messageId);

    @Insert({"INSERT INTO message (",
                "channel_id,",
                "author,",
                "message_id,",
                "content,",
                "created,",
                "edited,",
                "everyone,",
                "ispinned",
            ") VALUES (",
                "#{channelId},",
                "#{authorId},",
                "#{messageId},",
                "#{content},",
                "#{createTimestamp},",
                "#{editTimestamp},",
                "#{mentionsEveryone},",
                "#{isPinned})"})
    void saveMessage(@Param("channelId") String channelId, @Param("authorId") String authorId,
                     @Param("messageId") String messageId, @Param("content") String content,
                     @Param("createTimestamp") LocalDateTime createTimestamp, @Param("editTimestamp") LocalDateTime editTimestamp,
                     @Param("mentionsEveryone") boolean mentionsEveryone, @Param("isPinned") boolean isPinned);

    @Select({"SELECT users.username as USERNAME, count(*) as COUNT",
            "FROM message",
                "  JOIN users",
                "    ON message.author = users.user_id",
                "  JOIN channel",
                "    ON message.channel_id = channel.channel_id",
                "  JOIN guild",
                "    ON channel.parent = guild.guild_id",
                "WHERE UPPER(content) LIKE UPPER(CONCAT('%', CONCAT(#{word}, '%')))",
                "AND content NOT LIKE '%!word%'",
                "AND author <> '239471420470591498'",
                "AND guild.guild_id = #{guild}",
                "group by users.username"})
    @Results({
        @Result(column = "USERNAME", property = "key"),
        @Result(column = "COUNT", property = "value")
    })
    List<Statistic> getMessageCountsForGuildByUser(@Param("guild") String guild, @Param("word") String word);
}
