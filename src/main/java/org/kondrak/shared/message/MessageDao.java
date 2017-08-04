package org.kondrak.shared.message;

import org.apache.ibatis.session.SqlSession;
import org.kondrak.shared.mappers.MessageMapper;
import org.kondrak.shared.model.Statistic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.handle.obj.IMessage;

import java.util.List;


/**
 * Created by Administrator on 11/6/2016.
 */
@Component
public class MessageDao {

    @Autowired
    private MessageMapper messageMapper;

    // TODO: add handling for when messages are too large to store
    public void saveMessage(IMessage msg) {
        messageMapper.saveMessage(
                msg.getChannel().getStringID(),
                msg.getAuthor().getStringID(),
                msg.getStringID(),
                msg.getContent(),
                msg.getCreationDate(),
                (msg.getEditedTimestamp().isPresent() ? msg.getEditedTimestamp().get() : null),
                msg.mentionsEveryone(),
                msg.isPinned());
    }

    public List<Statistic> getTimesSaidByUser(final String guild, final String word) {
        return messageMapper.getMessageCountsForGuildByUser(guild, word);
    }

    public boolean messageExists(final String messageId) {
        Integer result = messageMapper.messageExists(messageId);
        return null != result && result > 0;
    }
}