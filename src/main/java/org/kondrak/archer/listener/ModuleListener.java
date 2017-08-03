package org.kondrak.archer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.module.ModuleDisabledEvent;
import sx.blah.discord.handle.impl.events.module.ModuleEnabledEvent;

import java.util.Properties;

/**
 * Created by Administrator on 11/7/2016.
 */
@Component
public class ModuleListener {

    private static final Logger LOG = LoggerFactory.getLogger(ModuleListener.class);

    @Autowired
    private Properties applicationProperties;

    @EventSubscriber
    public void onRegister(ModuleEnabledEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }

    @EventSubscriber
    public void onDeregister(ModuleDisabledEvent e) {
        LOG.info(applicationProperties.getProperty("logger.event.format"), e.getClass().getName());
    }
}
