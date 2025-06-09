package com.theshadowwarrior.symbiotemod;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.theshadowwarrior.symbiotemod.symbiote_capability.SymbioteProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public class SymbioteCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("symbiote_set_stage")
            .requires(cs -> cs.hasPermission(2))
            .then(Commands.argument("stage", IntegerArgumentType.integer(0, 4))
                .executes(ctx -> {
                    int stage = IntegerArgumentType.getInteger(ctx, "stage");
                    ServerPlayer player = ctx.getSource().getPlayerOrException();

                    SymbioteProvider.get(player).setInfectionStage(stage);
                    ctx.getSource().sendSuccess(() -> Component.literal("Set infection stage to " + stage), true);
                    return 1;
                })
            ));
    }
}
