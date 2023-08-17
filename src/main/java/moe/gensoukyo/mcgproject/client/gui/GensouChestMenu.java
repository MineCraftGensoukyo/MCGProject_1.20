package moe.gensoukyo.mcgproject.client.gui;

import moe.gensoukyo.mcgproject.client.container.GensouContainer;
import moe.gensoukyo.mcgproject.mixin.mixinInterface.PlayerAccess;
import moe.gensoukyo.mcgproject.registry.MenuRegistry;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


public class GensouChestMenu extends AbstractContainerMenu {
    private final GensouContainer container;
    public GensouChestMenu(int id, Inventory inventory) {
        this(id, inventory,new GensouContainer(162));
    }
    public GensouChestMenu(int id, Inventory inventory, GensouContainer container) {
        super(MenuRegistry.GENSOU_CHEST.get(),id);
        this.container = container;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 27; j++) {
                this.addSlot(new Slot(container,i * 27 + j, j * 18 - 142, i * 18 - 22));
            }
        }

        for(int l = 0; l < 3; ++l) {
            for(int j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(inventory, j1 + l * 9 + 9, 20 + j1 * 18, 100 + l * 18));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(inventory, i1, 20 + i1 * 18, 158));
        }
    }

    @Override
    public boolean clickMenuButton(@NotNull Player player, int i) {
        int id = ((PlayerAccess)player).getGensouchestID();
        int max = ((PlayerAccess)player).getContainerList().size();
        //TODO:做翻页
        return true;
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int id) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(id);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (id < 162) {
                if (!this.moveItemStackTo(itemstack1, 162, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, 162, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return container.stillValid(player);
    }
}
