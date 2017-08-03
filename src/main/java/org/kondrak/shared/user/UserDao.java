package org.kondrak.shared.user;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.kondrak.shared.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.handle.obj.IUser;

/**
 * Created by Administrator on 11/6/2016.
 */
@Component
public class UserDao {

    @Autowired
    private UserMapper userMapper;

    public void insertUser(IUser user) {
        userMapper.insertUser(user.getStringID(), user.getName(),
                user.getAvatar(), user.getAvatarURL(), user.getDiscriminator(), user.isBot());
    }

    public boolean userIsSaved(String userId) {
        Integer result = userMapper.userExists(userId);
        return null != result && result > 0;
    }
}
