package org.kondrak.shared.config;

import org.kondrak.shared.mappers.ConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigDao {

    @Autowired
    private ConfigMapper configMapper;

    public List<Configuration> getConfigurationByNameScopeAndType(ConfigType type, ConfigScope scope, String fkey) {
        return configMapper.getConfigurationByNameScopeAndType(type, scope, fkey);
    }

    public int addBooleanConfiguration(ConfigType type, ConfigScope scope, String fkey) {
        return configMapper.addBooleanConfiguration(type, scope, fkey);
    }

    public int removeBooleanConfiguration(ConfigType type, ConfigScope scope, String fkey) {
        return configMapper.removeBooleanConfiguration(type, scope, fkey);
    }
}
