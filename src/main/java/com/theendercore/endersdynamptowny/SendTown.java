package com.theendercore.endersdynamptowny;

import java.util.Collection;

public class SendTown {
    public String name;
    public String nation;
    public String mayor;
    public String residents;
    public Collection<Bloxs> size;


    public SendTown(String name, String nation, String mayor, String residents, Collection<Bloxs> size) {
        this.name = name;
        this.nation = nation;
        this.mayor = mayor;
        this.residents = residents;
        this.size = size;
    }

    public static class Bloxs {
        public int x, z;
        public boolean outpost = false;


        public Bloxs(int blox_x, int blox_z, boolean outpost) {
            this.x = blox_x;
            this.z = blox_z;
            this.outpost = outpost;
        }

    }

}












