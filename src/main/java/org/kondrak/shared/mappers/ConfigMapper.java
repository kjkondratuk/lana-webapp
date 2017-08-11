package org.kondrak.shared.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.kondrak.shared.config.ConfigScope;
import org.kondrak.shared.config.ConfigType;
import org.kondrak.shared.config.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigMapper {

    @Select({
            "SELECT configuration_assignment.assignment_id as assignment_id,",
            "configuration_assignment.configuration_id as configuration_id,",
            "configuration_assignment.configuration_scope as configuration_scope,",
            "configuration_assignment.configuration_fkey as configuration_fkey ,",
            "configuration.configuration_name as configuration_name,",
            "configuration.configuration_data_type as configuration_data_type,",
            "configuration.configuration_value as configuration_value",
            "FROM configuration_assignment",
            "JOIN configuration",
            "ON configuration_assignment.configuration_id = configuration.configuration_id",
            "WHERE configuration.configuration_name = #{type}",
            "AND configuration_assignment.configuration_scope = #{scope}",
            "AND configuration_assignment.configuration_fkey = #{fkey}"
    })
    @Results({
            @Result(column = "assignment_id", property = "assignmentId"),
            @Result(column = "configuration_id", property = "configurationId"),
            @Result(column = "configuration_scope", property = "scope"),
            @Result(column = "configuration_fkey", property = "assignedKey"),
            @Result(column = "configuration_name", property = "configType"),
            @Result(column = "configuration_data_type", property = "configDatatype"),
            @Result(column = "configuration_value", property = "configValue")
    })
    List<Configuration> getConfigurationByNameScopeAndType(@Param("type") ConfigType type, @Param("scope") ConfigScope scope,
                                                           @Param("fkey") String fkey);

    @Select({
            "SELECT configuration_assignment.assignment_id as assignment_id,",
            "configuration_assignment.configuration_id as configuration_id,",
            "configuration_assignment.configuration_scope as configuration_scope,",
            "configuration_assignment.configuration_fkey as configuration_fkey ,",
            "configuration.configuration_name as configuration_name,",
            "configuration.configuration_data_type as configuration_data_type,",
            "configuration.configuration_value as configuration_value",
            "FROM configuration_assignment",
            "JOIN configuration",
            "ON configuration_assignment.configuration_id = configuration.configuration_id",
            "WHERE configuration_assignment.configuration_scope = #{scope}",
            "AND configuration_assignment.configuration_fkey = #{fkey}"
    })
    @Results({
            @Result(column = "assignment_id", property = "assignmentId"),
            @Result(column = "configuration_id", property = "configurationId"),
            @Result(column = "configuration_scope", property = "scope"),
            @Result(column = "configuration_fkey", property = "assignedKey"),
            @Result(column = "configuration_name", property = "configType"),
            @Result(column = "configuration_data_type", property = "configDatatype"),
            @Result(column = "configuration_value", property = "configValue")
    })
    List<Configuration> getConfigurationByScopeAndForeignKey(@Param("scope") ConfigScope scope, @Param("fkey") String fkey);

    @Insert({"INSERT INTO configuration_assignment (",
            "configuration_id,",
            "configuration_scope,",
            "configuration_fkey",
            ") VALUES (",
            "(SELECT configuration_id FROM configuration WHERE configuration_name = #{type}),",
            "#{scope},",
            "#{fkey}",
            ")"})
    int addBooleanConfiguration(@Param("type") ConfigType type, @Param("scope") ConfigScope scope, @Param("fkey") String fkey);

    @Delete({"DELETE FROM configuration_assignment",
            "WHERE configuration_id = (SELECT configuration_id FROM configuration WHERE configuration_name = #{type})",
            "AND configuration_scope = #{scope}",
            "AND configuration_fkey = #{fkey}"
    })
    int removeBooleanConfiguration(@Param("type") ConfigType type, @Param("scope") ConfigScope scope, @Param("fkey") String fkey);
}
