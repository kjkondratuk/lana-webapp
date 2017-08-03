package org.kondrak.archer.command;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Created by Administrator on 11/5/2016.
 */
public interface MessageEventCommand extends BotCommand<IMessage> {
    @Override
    boolean shouldExecute(IMessage input);

    @Override
    void execute(IMessage input);

    @Override
    String getFormatErrorMessage(IMessage input);

    void handleFormatError(IMessage input);
}
