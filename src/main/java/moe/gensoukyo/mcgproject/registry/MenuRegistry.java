package moe.gensoukyo.mcgproject.registry;

import moe.gensoukyo.mcgproject.MCGProject;
import moe.gensoukyo.mcgproject.client.gui.GensouChestMenu;
import moe.gensoukyo.mcgproject.client.gui.GensouChestScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuRegistry {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MCGProject.MOD_ID);
    public static final RegistryObject<MenuType<GensouChestMenu>> GENSOU_CHEST = CONTAINERS.register("gensou_chest",
            () -> new MenuType<>(GensouChestMenu::new, FeatureFlags.REGISTRY.allFlags()));
    @OnlyIn(Dist.CLIENT)
    public static void renderScreens() {
        MenuScreens.register(GENSOU_CHEST.get(), GensouChestScreen::new);
    }
}
