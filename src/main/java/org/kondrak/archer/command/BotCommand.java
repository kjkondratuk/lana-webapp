package org.kondrak.archer.command;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Created by Administrator on 11/4/2016.
 */
public interface BotCommand<Q> {

    boolean shouldExecute(Q input);

    String getFormatErrorMessage(Q input);

    void execute(Q input);

    void handleFormatError(IMessage input);
}
