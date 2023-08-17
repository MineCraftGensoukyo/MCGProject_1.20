package moe.gensoukyo.mcgproject.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

public class GensouChestScreen extends AbstractContainerScreen<GensouChestMenu> {
    private static final ResourceLocation BACKPACK = new ResourceLocation("mcgproject", "textures/gui/backpack-1.png");
    public GensouChestScreen(GensouChestMenu chestMenu, Inventory inventory, Component component) {
        super(chestMenu, inventory, component);
    }

    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        double d0 = mouseX - (double)(i - 20);
        double d1 = mouseY - (double)(j + 24);

        if (d0 >= -16.0D && d1 >= 99.0D && d0 < 8.0D && d1 < 125.0D ) {
            this.menu.clickMenuButton(this.minecraft.player, 0);
            return true;
        }
        if (d0 >= 233.0D && d1 >= 100.0D && d0 < 257.0D && d1 < 125.0D ) {
            this.menu.clickMenuButton(this.minecraft.player, 1);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float p_97788_, int p_97789_, int p_97790_) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        graphics.blit(BACKPACK, i - 150, j - 40, 0, 0.0F, 0.0F, 512, 512, 512, 512);
    }

    public void render(@NotNull GuiGraphics guiGraphics, int p_282491_, int p_281953_, float p_282182_) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, p_282491_, p_281953_, p_282182_);
    }
    //TODO:多渲染了几个字
}
