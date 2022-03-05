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

public class ppCommand implements Command<ServerCommandSource> {
    public ppCommand() {
    }

    private static void say(ServerCommandSource source, String text) {
        source.sendFeedback(new LiteralText(text), true);

    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = "pp.json";
        BufferedReader br = null;


        SendTown result = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            result = gson.fromJson(br, SendTown.class);
        } catch (FileNotFoundException e) {
            enderLog("cant read file?");
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        SendTown[] penin = new Gson().fromJson(filePath, SendTown[].class);
        say(context.getSource(), penin[0].name);

        assert result != null;
        say(context.getSource(), " --command-active");

        //make new chunk
        for (SendTown.Bloxs bloc : result.size) {
            DynampEnder.removeChunkClaim(result, bloc.x, bloc.z);
            DynampEnder.newChunkClaim(result, bloc);
        }
        enderLog("pp --command-end");
        return 1;
    }
}
