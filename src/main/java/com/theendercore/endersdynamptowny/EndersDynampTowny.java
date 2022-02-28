package com.theendercore.endersdynamptowny;

import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.dynmap.DynmapCommonAPI;
import org.dynmap.markers.*;
import org.dynmap.DynmapCommonAPIListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.util.UUID;

public class EndersDynampTowny implements ModInitializer {

    public static DynmapCommonAPI dynmapCommonAPI;
    public static MarkerAPI markerApi;
    public static MarkerSet markerSet;

    public static final Logger LOGGER = LoggerFactory.getLogger("enders-dynamp-towny");
    public static final String pp = "[Ender's Dynamp Towny Mod]";

    @Override
    public void onInitialize() {
        LOGGER.info(pp + " pp");

        DynmapCommonAPIListener.register(new DynmapCommonAPIListener() {
            @Override
            public void apiEnabled(DynmapCommonAPI dCAPI) {
                dynmapCommonAPI = dCAPI;
                markerApi = dynmapCommonAPI.getMarkerAPI();
                markerSet = markerApi.createMarkerSet("ender-dynmap-towny", "YES", null, true);
                if (markerSet == null) {
                    markerSet = markerApi.getMarkerSet("ender-dynmap-towny");
                }
            }
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {

            LiteralCommandNode<ServerCommandSource> pp = CommandManager
                    .literal("pp")
                    .executes(new ppComand())
                    .build();

            dispatcher.getRoot().addChild(pp);

        });


    }

}
