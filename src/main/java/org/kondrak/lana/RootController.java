package org.kondrak.lana;

import org.kondrak.lana.data.ConfigDao;
import org.kondrak.lana.data.ConfigScope;
import org.kondrak.lana.data.ConfigType;
import org.kondrak.lana.data.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RootController {

    @Autowired
    ConfigDao configDao;

    @RequestMapping("/")
    String getRoot() {
        List<Configuration> c = configDao.getConfigurationByNameScopeAndType(ConfigType.ARCHERISM_COMMAND, ConfigScope.GUILD, "140624972421922817");
        String response = "";

        for(Configuration x : c) {
            response += c.toString() + "\n";
        }

        return response;
    }
}
