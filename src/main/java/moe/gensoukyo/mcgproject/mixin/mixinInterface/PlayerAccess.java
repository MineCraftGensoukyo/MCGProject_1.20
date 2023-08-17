package moe.gensoukyo.mcgproject.mixin.mixinInterface;

import moe.gensoukyo.mcgproject.client.container.GensouContainer;

import java.util.ArrayList;

public interface PlayerAccess {
    GensouContainer getGensoContainer(int id);
    ArrayList<GensouContainer> getContainerList ();
    int getGensouchestID();
    void setGensouContainer(GensouContainer container,int id);
    void setContainerList (ArrayList<GensouContainer> arrayList);
    void setContainerID (int id);
}
