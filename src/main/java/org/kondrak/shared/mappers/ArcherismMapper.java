package org.kondrak.shared.mappers;

import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Select;
import org.kondrak.shared.archerism.Archerism;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArcherismMapper {
    @Select("SELECT trigger_tx, msg_tx FROM archerisms WHERE trigger_tx = '!archerism'")
    @ConstructorArgs({
            @Arg(column = "trigger_tx", javaType = String.class),
            @Arg(column = "msg_tx", javaType = String.class)
    })
    List<Archerism> getAllArcherisms();
}
