package moe.gensoukyo.mcgproject.mixin.mixinClass;

import moe.gensoukyo.mcgproject.client.container.GensouContainer;
import moe.gensoukyo.mcgproject.mixin.mixinInterface.PlayerAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Mixin(Player.class)
public abstract class MixinPlayer implements PlayerAccess { // 实现接口
    @Unique
    private int gensouchestID = 0;
    //初始化10个容器
    @Unique
    private ArrayList<GensouContainer> containerList = IntStream.range(0, 10)
            .mapToObj(i -> new GensouContainer(162))
            .collect(Collectors.toCollection(ArrayList::new));

    @Override
    public void setContainerID (int id) {
        this.gensouchestID = id;
    }
    @Override
    public void setGensouContainer (GensouContainer container,int i) {
        this.containerList.set(i,container);
    }

    @Override
    public void setContainerList (ArrayList<GensouContainer> arrayList) {
        this.containerList = arrayList;
    }
    @Override
    public int getGensouchestID() {return this.gensouchestID;}

    @Override
    public GensouContainer getGensoContainer(int i) { // 实现接口中的方法
        return containerList.get(i);
    }

    @Override
    public ArrayList<GensouContainer> getContainerList () {
        return this.containerList;
    }

    //序列化后储存
    @Inject(at = @At(value = "HEAD"),method = "addAdditionalSaveData")
    private void injectAddData(CompoundTag compoundTag, CallbackInfo ci) {
        compoundTag.putByte("gensouchestID", (byte) gensouchestID);
        for (int i = 0; i < containerList.size(); i++) {
            compoundTag.put("GensoItems" + i, containerList.get(i).createTag(i));
        }
    }

    //反序列化加载
    @Inject(at = @At(value = "HEAD"),method = "readAdditionalSaveData")
    private void injectReadData(CompoundTag compoundTag, CallbackInfo ci) {
        this.gensouchestID = compoundTag.getByte("gensouchestID");
        for (int i = 0; i < containerList.size(); i++) {
            if (compoundTag.contains("GensoItems" + i, 9)) {
                this.containerList.get(i).fromTag(compoundTag.getList("GensoItems" + i, 10));
            }
        }
    }
}
