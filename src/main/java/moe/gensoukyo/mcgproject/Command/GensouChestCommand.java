package moe.gensoukyo.mcgproject.Command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import moe.gensoukyo.mcgproject.client.gui.GensouChestMenu;
import moe.gensoukyo.mcgproject.mixin.mixinInterface.PlayerAccess;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;

import java.util.Collection;

public class GensouChestCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands
                .literal("gensochest")
                .requires((stack) -> stack.hasPermission(2))
                .then(Commands.literal("get")
                        .then(Commands.argument("targets", EntityArgument.players())
                        .then(Commands.argument("id", IntegerArgumentType.integer())
                                .executes(context -> openMenu(context.getSource(), EntityArgument.getPlayers(context, "targets"), IntegerArgumentType.getInteger(context, "id"))))))
        );}
    public static int openMenu (CommandSourceStack sourceStack, Collection<ServerPlayer> serverPlayers,int id) {
        if (serverPlayers.size() != 1) {
            sourceStack.sendFailure(Component.literal("Too many players"));
            return 0;
        }
        for (ServerPlayer serverPlayer : serverPlayers) {
            if (((PlayerAccess)serverPlayer).getContainerList().size() <= id) {
                sourceStack.sendFailure(Component.literal("No Container Exist"));
                return 0;
            }
            System.out.println(id);
            serverPlayer.openMenu(new SimpleMenuProvider((id1, inventory, player1)
                    -> new GensouChestMenu(id1,inventory,((PlayerAccess)serverPlayer).getGensoContainer(id)),Component.translatable("ui.ender_storage")));
            return 1;
        }
        return 0;
    }
}
