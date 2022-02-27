package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import org.dynmap.DynmapCommonAPI;
import org.dynmap.markers.Marker;
import org.dynmap.markers.MarkerIcon;
import org.dynmap.markers.MarkerSet;
import org.dynmap.DynmapCommonAPIListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dynmap.markers.MarkerAPI;

public class ExampleMod implements ModInitializer {

    DynmapCommonAPIListener.register();
    MarkerSet set;
    MarkerAPI markerAPI = api.getMarkerAPI();

    public static final Logger LOGGER = LoggerFactory.getLogger("modid");


    String htmlLabel = "<div>Hello World</div>";
    private final MarkerIcon icon = markerAPI.getMarkerIcon("building");

    Marker marker = set.createMarker("uniqueMarkerId", htmlLabel, true,
            "world", 10, 20, 30, icon, false);

    @Override
    public void onInitialize() {
        LOGGER.info("pp");
    }
}
