package com.theendercore.endersdynamptowny.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.theendercore.endersdynamptowny.DynampEnder;
import com.theendercore.endersdynamptowny.EndersDynampTowny;
import com.theendercore.endersdynamptowny.SendTown;
import net.minecraft.server.command.ServerCommandSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MapClearCommand implements Command<ServerCommandSource> {
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
            EndersDynampTowny.LOGGER.info(EndersDynampTowny.motdLOG + " cant read file?");
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


        //remove old chunk
        for (SendTown.Bloxs bloc : result.size) {
            DynampEnder.removeChunkClaim(result, bloc.x, bloc.z);
        }

        return 1;
    }
}
