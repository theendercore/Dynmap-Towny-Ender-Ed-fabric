package com.theendercore.endersdynamptowny.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.theendercore.endersdynamptowny.DynampEnder;
import com.theendercore.endersdynamptowny.SendTown;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.theendercore.endersdynamptowny.EndersDynampTowny.enderLog;
import static com.theendercore.endersdynamptowny.EndersDynampTowny.toggleLog;

public class ppCommand implements Command<ServerCommandSource> {
    public ppCommand() {
    }

    private static void say(ServerCommandSource source, String text) {
        source.sendFeedback(new LiteralText(text), true);

    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        if (toggleLog)
        {
            toggleLog = false;
        }
        else
        {
            toggleLog = true;
        }
        say(context.getSource(), "toggleLog set to :"+ toggleLog);
        enderLog("pp --command-end");
        return 1;
    }
}
