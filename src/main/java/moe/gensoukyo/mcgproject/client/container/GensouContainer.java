package moe.gensoukyo.mcgproject.client.container;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;

public class GensouContainer extends SimpleContainer {
    public GensouContainer(int num) {
        super(num);
    }
    private int id;

    public ListTag createTag(int id) {
        ListTag listtag = new ListTag();
        for(int i = 0; i < this.getContainerSize(); ++i) {
            ItemStack itemstack = this.getItem(i);
            if (!itemstack.isEmpty()) {
                CompoundTag compoundtag = new CompoundTag();
                compoundtag.putShort("Slot", (short)i);
                itemstack.save(compoundtag);
                listtag.add(compoundtag);
            }
        }
        CompoundTag idTag = new CompoundTag();
        idTag.putByte("id",(byte) id);
        listtag.add(idTag);
        return listtag;
    }
    @Override
    public void fromTag(ListTag listTag) {
        this.clearContent();
        for(int i = 0; i < listTag.size() - 1; ++i) {
            ItemStack itemstack = ItemStack.of(listTag.getCompound(i));
            if (!itemstack.isEmpty()) {
                this.setItem(listTag.getCompound(i).getShort("Slot"),itemstack);
            }
        }
        this.id = listTag.getCompound(listTag.size()).getByte("id");
    }
}
