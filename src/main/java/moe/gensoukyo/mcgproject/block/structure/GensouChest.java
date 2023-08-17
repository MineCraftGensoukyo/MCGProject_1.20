package moe.gensoukyo.mcgproject.block.structure;

import moe.gensoukyo.mcgproject.client.gui.GensouChestMenu;
import moe.gensoukyo.mcgproject.mixin.mixinInterface.PlayerAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

public class GensouChest extends Block {
    public GensouChest(Properties properties) {
        super(properties);
    }

    public @NotNull InteractionResult use(@NotNull BlockState blockState, Level level, @NotNull BlockPos blockPos, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull BlockHitResult result) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            player.openMenu(new MenuProvider() {
                @Override
                public @NotNull Component getDisplayName() {
                        return Component.translatable("ui.gensou_storage");
                    }
                    @Override
                    public @NotNull AbstractContainerMenu createMenu(int id, @NotNull Inventory inventory, @NotNull Player player1) {
                    return new GensouChestMenu(id,inventory,((PlayerAccess)player1).getGensoContainer(((PlayerAccess)player1).getGensouchestID()));
                }
            });
        }
        return InteractionResult.CONSUME;
    }
}
