package com.example.minecartmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import com.mojang.brigadier.arguments.FloatArgumentType;
import static net.minecraft.server.command.CommandManager.literal;

public class MinecartMod implements ModInitializer {

    @Override
    public void onInitialize() {
        MinecartPhysicsHandler.register();
        
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(literal("gravity")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.argument("value", FloatArgumentType.floatArg(0.1f, 3.0f))
                    .executes(context -> {
                        float value = FloatArgumentType.getFloat(context, "value");
                        GravityManager.setGravity(value);
                        context.getSource().sendFeedback(() -> 
                            net.minecraft.text.Text.literal("Minecart gravity set to " + value), true);
                        return 1;
                    })
                )
            );
        });
    }
}