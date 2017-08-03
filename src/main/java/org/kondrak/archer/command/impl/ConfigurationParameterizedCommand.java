package org.kondrak.archer.command.impl;

import org.kondrak.archer.command.AbstractMessageCommand;
import org.kondrak.shared.config.ConfigCommand;
import org.kondrak.shared.config.ConfigScope;
import org.kondrak.shared.config.ConfigType;
import org.kondrak.shared.config.Configuration;
import org.kondrak.shared.config.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sx.blah.discord.handle.obj.IMessage;

import java.util.List;
import java.util.Properties;

import static org.kondrak.shared.config.ConfigCommand.ADD;
import static org.kondrak.shared.config.ConfigCommand.REMOVE;
import static org.kondrak.shared.config.ConfigCommand.SHOW;

@Component
public class ConfigurationParameterizedCommand extends AbstractMessageCommand {

    public static final Logger LOG = LoggerFactory.getLogger(ConfigurationParameterizedCommand.class);

    @Autowired
    private Properties applicationProperties;

    @Autowired
    private ConfigurationService configService;

    public ConfigurationParameterizedCommand(@Autowired Properties applicationProperties) {
        super("config", applicationProperties);
    }

    @Override
    public void execute(IMessage input) {
        // TODO: abstract command parsing into its own class
        String content = input.getContent().replace(getCommand()+ " ", "");

        if(content.startsWith(ADD + " ")) {
            String[] p = content.replaceFirst(ADD + " ", "").split(" ");
            if (p.length == 2) {
                ConfigScope scope = ConfigScope.valueOf(p[0]);
                ConfigType type = ConfigType.valueOf(p[1]);
                switch(scope) {
                    case GUILD:
                        addCommand(type, scope, input);
                        break;
                    case CHANNEL:
                        input.reply("Adding channel configurations is not yet supported.");
                        break;
                    case USER:
                        input.reply("Adding user configurations is not yet supported.");
                        break;
                    default:
                        handleFormatError(input);
                }
            } else {
                handleFormatError(input);
            }
        } else if(content.startsWith(REMOVE + " ")) {
            String[] p = content.replaceFirst(REMOVE + " ", "").split(" ");
            if (p.length == 2) {
                ConfigScope scope = ConfigScope.valueOf(p[0]);
                ConfigType type = ConfigType.valueOf(p[1]);
                switch(scope) {
                    case GUILD:
                        removeCommand(type, scope, input);
                        break;
                    case CHANNEL:
                        input.reply("Removing channel configurations is not yet supported.");
                        break;
                    case USER:
                        input.reply("Removing user configurations is not yet supported.");
                        break;
                    default:
                        handleFormatError(input);
                }
            } else {
                handleFormatError(input);
            }
        } else if(content.startsWith(SHOW + " ")) {
            String[] p = content.replaceFirst(SHOW + " ", "").split(" ");
            if(p.length == 2) {
                ConfigScope scope = ConfigScope.valueOf(p[0]);
                ConfigType type = ConfigType.valueOf(p[1]);
                if(p[1] != null) {
                    switch (scope) {
                        case GUILD:
                            showCommand(type, scope, input);
                            break;
                        case CHANNEL:
                            input.reply("Displaying channel configurations is not yet supported.");
                            break;
                        case USER:
                            input.reply("Displaying user configurations is not yet supported.");
                            break;
                        default:
                            handleFormatError(input);
                    }
                } else {
                    handleFormatError(input);
                }
            } else {
                handleFormatError(input);
            }
        } else {
            handleFormatError(input);
        }
    }

    // TODO: split this into one method that handles permissions and another that handles validity
    // TODO: remove admin hard-coding
    @Override
    public boolean shouldExecute(IMessage msg) {
        if(null != msg.getContent() && msg.getContent().startsWith(getCommand())) {
            String content = msg.getContent().replace(getCommand() + " ", "");

            if(content.matches(ConfigCommand.anyOfRegex() + "(?> )" + ConfigScope.anyOfRegex() + "(?> )" +
                    ConfigType.anyOfRegex())) {
                if (!(msg.getAuthor().getStringID().equals(msg.getGuild().getOwner().getStringID())
                        || (msg.getAuthor().getName().equals(applicationProperties.getProperty("admin.name"))
                        && msg.getAuthor().getDiscriminator().equals(applicationProperties.getProperty("admin.discriminator"))))) {
                    handleFormatError(msg);
                } else {
                    return true;
                }
            } else {
                handleFormatError(msg);
            }
        }

        return false;
    }

    @Override
    public String getFormatErrorMessage(IMessage input) {
        return "Your configuration command was malformed, or you do not have permission to execute it.\n" +
                "\t * Please use the format: **" + applicationProperties.getProperty("app.prefix") + "**config (" + ConfigCommand.pipeDelimited() +
                ") (" + ConfigScope.pipeDelimited() + ") (" + ConfigType.pipeDelimited() + ")";
    }

    private void showCommand(ConfigType type, ConfigScope scope, IMessage input) {
        List<Configuration> config = configService.getConfigurationByNameScopeAndType(type, scope,
                input.getGuild().getStringID());
        if(null != config && !config.isEmpty()) {
            StringBuilder reply = new StringBuilder("\n");
            for (Configuration c : config) {
                reply.append("\t * **")
                        .append(c.getConfigType())
                        .append("** is **").append(c.getConfigValue())
                        .append("** for this **").append(c.getScope().toString().toLowerCase())
                        .append("**\n");
            }
            input.reply(reply.toString());
        } else {
            input.reply("No **" + type + "** configurations are configured for this **" + scope + "**.");
        }
    }

    // TODO: use IDiscordObject instead so arbitrary stringIDs can be handled
    private void addCommand(ConfigType type, ConfigScope scope, IMessage input) {
        if(!configService.isConfiguredForGuild(input.getGuild(), type)) {
            int inserts = configService.addBooleanConfiguration(type, scope, input.getGuild().getStringID());
            if(inserts > 0) {
                input.reply("**" + type + "** was configured for this **" + scope + "**.");
            }
        } else {
            input.reply("**" + type + "** is already configured for this **" + scope + "**.");
        }
    }

    private void removeCommand(ConfigType type, ConfigScope scope, IMessage input) {
        if(configService.isConfiguredForGuild(input.getGuild(), type)) {
            int deletions = configService.removeBooleanConfiguration(type, scope, input.getGuild().getStringID());
            if(deletions > 0) {
                input.reply("**" + type + "** was configured for this **" + scope + "**.");
            }
        } else {
            input.reply("No **" + type + "** configurations can be removed for this **" + scope + "** because none are configured.");
        }
    }
}
