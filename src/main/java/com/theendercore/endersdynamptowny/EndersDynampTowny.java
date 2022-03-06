package com.theendercore.endersdynamptowny;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import com.theendercore.endersdynamptowny.commands.ppCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import org.dynmap.DynmapCommonAPI;
import org.dynmap.DynmapCommonAPIListener;
import org.dynmap.markers.AreaMarker;
import org.dynmap.markers.MarkerAPI;
import org.dynmap.markers.MarkerSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;


public class EndersDynampTowny implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("enders-dynamp-towny");
    public static final String motdLOG = "[Ender's Dynamp Towny Mod]";
    public static DynmapCommonAPI dynmapCommonAPI;
    public static MarkerAPI markerApi;
    public static MarkerSet markerSet;
    public static int ticksElapsed = 0;
    public static int ticksNeeded = 500;
    public static boolean toggleLog = false;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void enderLog(String message) {
        LOGGER.info(motdLOG + " " + message);
    }

    @Override
    public void onInitialize() {
        enderLog(" Started");

        DynmapCommonAPIListener.register(new DynmapCommonAPIListener() {
            @Override
            public void apiEnabled(DynmapCommonAPI DynmapCommonAPI) {
                dynmapCommonAPI = DynmapCommonAPI;
                markerApi = dynmapCommonAPI.getMarkerAPI();
                markerSet = markerApi.createMarkerSet("ender-dynmap-towny", "Towny", null, true);
                if (markerSet == null) {
                    markerSet = markerApi.getMarkerSet("ender-dynmap-towny");
                }
            }
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            //Make some new nodes
            LiteralCommandNode<ServerCommandSource> ppNode = CommandManager.literal("pp").executes(new ppCommand()).build();

            dispatcher.getRoot().addChild(ppNode);
        });


        ServerTickEvents.START_SERVER_TICK.register((endTick) -> {
            ticksElapsed++;

            if (ticksElapsed == ticksNeeded) {
                DynampEnder.makeMap(gson);
                enderLog("Active");
                ticksElapsed = 0;
            }
        });

    }

}
