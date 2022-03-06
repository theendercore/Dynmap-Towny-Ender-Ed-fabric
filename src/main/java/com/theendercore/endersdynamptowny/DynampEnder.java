package com.theendercore.endersdynamptowny;

import com.google.gson.Gson;
import org.dynmap.markers.AreaMarker;
import org.dynmap.markers.MarkerSet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static com.theendercore.endersdynamptowny.EndersDynampTowny.enderLog;

public class DynampEnder {
    public static ArrayList<SendTown> townsToUpdate;

    public static void newChunkClaim(SendTown sendTown, SendTown.Bloxs bloc) {
        MarkerSet markerSet = EndersDynampTowny.markerSet;
        if (markerSet == null) {
            enderLog("markerSet is Null");
        }

        int chunkX1 = bloc.x * 16, chunkZ1 = bloc.z * 16, chunkX2 = (bloc.x + 1) * 16, chunkZ2 = (bloc.z + 1) * 16;

        try {
            String areaMarkerId = sendTown.name + "-" + chunkX1 + "." + chunkZ1 + ";" + chunkX2 + "." + chunkZ2;
            AreaMarker areaMarker = markerSet.createAreaMarker(areaMarkerId, infoGen(sendTown, bloc), true, "world", new double[]{chunkX1, chunkX2}, new double[]{chunkZ1, chunkZ2}, true);
            int color = 7;
//            enderLog(areaMarkerId);
            areaMarker.setFillStyle(areaMarker.getFillOpacity(), color);
            areaMarker.setLineStyle(areaMarker.getLineWeight(), areaMarker.getLineOpacity(), color);
        } catch (NullPointerException e) {
            enderLog("____ is Null");
        }
    }

    public static void removeChunkClaim(SendTown sendTown, int x, int z) {
        MarkerSet markerSet = EndersDynampTowny.markerSet;
        Set<AreaMarker> areaMarkers = markerSet.getAreaMarkers();

        for (AreaMarker areaMarker : areaMarkers) {
            areaMarker.deleteMarker();
            break;
        }

    }


    public static void fancyRemoveChunkClaim(SendTown sendTown, int x, int z) {
        int chunkX1 = x * 16, chunkZ1 = z * 16, chunkX2 = (x + 1) * 16, chunkZ2 = (z + 1) * 16;
        String idToRemove = sendTown.name + "-" + chunkX1 + "." + chunkZ1 + ";" + chunkX2 + "." + chunkZ2;
        MarkerSet markerSet = EndersDynampTowny.markerSet;
        Set<AreaMarker> areaMarkers = markerSet.getAreaMarkers();

        for (AreaMarker areaMarker : areaMarkers) {
//            enderLog("ID : " + areaMarker.getMarkerID());
//            enderLog("ID-ToRemove : " + idToRemove);
//            enderLog("Same Id : " + areaMarker.getMarkerID().equals(idToRemove));
            if (areaMarker.getMarkerID().equals(idToRemove)) {
                areaMarker.deleteMarker();
                enderLog("Removed " + areaMarker.getMarkerID());
                break;
            }
        }
    }

    public static void makeMap(Gson gson) {
        String filePath = "./plugins/EnderTownyDynamp/pp.json";
        BufferedReader br = null;
        SendTown[] towns = new SendTown[0];

        try {
            br = new BufferedReader(new FileReader(filePath));
            towns = gson.fromJson(br, SendTown[].class);
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

        //make new chunk
        for (SendTown town : towns) {
            for (SendTown.Bloxs bloc : town.size) {
                DynampEnder.fancyRemoveChunkClaim(town, bloc.x, bloc.z);
                DynampEnder.newChunkClaim(town, bloc);
            }
        }

    }

    public static String infoGen(SendTown sendTown, SendTown.Bloxs bloc) {

        if (bloc.outpost) {
            return "<span style=\"font-weight:bold;font-size:200%;\">" + sendTown.name + " [Outpost]</span><br>" + "Mayor: <span style=\"font-weight:bold;\">" + sendTown.mayor + " </span><br>" + "Residents: <span style=\"font-weight:bold;\">" + sendTown.residents + " </span><br>" + "Nation: <span style=\"font-weight:bold;\">" + sendTown.nation + " </span><br>" + " ";
        }
        return "<span style=\"font-weight:bold;font-size:200%;\">" + sendTown.name + "</span><br>" + "Mayor: <span style=\"font-weight:bold;\">" + sendTown.mayor + " </span><br>" + "Residents: <span style=\"font-weight:bold;\">" + sendTown.residents + " </span><br>" + "Nation: <span style=\"font-weight:bold;\">" + sendTown.nation + " </span><br>" + " ";
    }

}
