package com.theendercore.endersdynamptowny;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import java.io.*;

public class ppCommand implements Command<ServerCommandSource> {
    public ppCommand() {
    }

    @Override
    public int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = "pp.json";
        BufferedReader br = null;


        Towny result = null;
        try {
            br = new BufferedReader(new FileReader(filePath));
            result = gson.fromJson(br, Towny.class);
        } catch (FileNotFoundException e) {
            EndersDynampTowny.LOGGER.info(EndersDynampTowny.pp + " cant read file?");
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
//        Towny[] penis = new Gson().fromJson(TOWNY_FILE, Towny[].class);

        assert result != null;
        say(context.getSource(), result.toString());
        say(context.getSource(), gson.toJson(result));
        EndersDynampTowny.LOGGER.info(EndersDynampTowny.pp + " /pp --command-end");
        return 1;
    }

    private static void say(ServerCommandSource source, String text) {
        source.sendFeedback(new LiteralText(text), true);

    }
}
