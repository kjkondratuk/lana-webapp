package org.kondrak.shared.mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Insert({"INSERT INTO users (",
                    "user_id,",
                    "username,",
                    "avatar,",
                    "avatarurl,",
                    "discriminator,",
                    "isbot",
                ") VALUES (",
                    "#{userId},",
                    "#{username},",
                    "#{avatar},",
                    "#{avatarUrl},",
                    "#{discriminator},",
                    "#{isBot})"})
    void insertUser(@Param("userId") String userId, @Param("username") String username,
                    @Param("avatar") String avatar, @Param("avatarUrl") String avatarUrl,
                    @Param("discriminator") String discriminator, @Param("isBot") boolean isBot);

    @Select("SELECT 1 FROM users WHERE user_id = #{userId}")
    Integer userExists(@Param("userId") String userId);
}
