package org.kondrak.lana;

import org.kondrak.shared.config.Configuration;
import org.kondrak.shared.config.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    public ConfigurationService configService;

    @RequestMapping("/guild/{guildId}/all")
    public List<Configuration> getAllGuildConfigurations(@PathVariable final String guildId) {
        return configService.getConfigsForGuild(guildId);
    }
}
