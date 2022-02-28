package com.theendercore.endersdynamptowny;

import com.google.gson.JsonObject;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

import java.util.Collection;
import java.util.Iterator;

public class ppComand implements Command<ServerCommandSource> {
    public ppComand() {
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        say(context.getSource(), "pp\nPenis");
        System.out.println("pp");
        JsonObject pp = new JsonObject();
        return 1;
    }

    private static void say(ServerCommandSource source, String text) {
        source.sendFeedback(new LiteralText(text), false);

    }
}
