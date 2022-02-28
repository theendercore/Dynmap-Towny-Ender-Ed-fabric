package com.theendercore.endersdynamptowny;

import org.dynmap.markers.AreaMarker;

public class PP {
    public static void init(){
        if (EndersDynampTowny.markerSet == null) {
            EndersDynampTowny.LOGGER.info("it's null bruh");
        }
        String htmlLabel = "pp man big pp<br>pp Penis";
        EndersDynampTowny.LOGGER.info(EndersDynampTowny.pp + " work?");
        AreaMarker areaMarker = EndersDynampTowny.markerSet.createAreaMarker("sln-ld", htmlLabel, true, "1.13.1 fast world eater ilmango", new double[]{6, 60}, new double[]{50, 5}, true);

    }

}
