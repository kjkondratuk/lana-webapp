package org.kondrak.lana.data;

import org.kondrak.lana.mappers.ConfigMapper;
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
}
