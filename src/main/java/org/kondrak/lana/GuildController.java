package org.kondrak.lana;

import org.kondrak.shared.guild.GuildDao;
import org.kondrak.shared.model.Guild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GuildController {

    @Autowired
    private GuildDao guildDao;

    @RequestMapping("/guild")
    public List<Guild> getGuilds() {
        return guildDao.getAllGuilds();
    }
}
