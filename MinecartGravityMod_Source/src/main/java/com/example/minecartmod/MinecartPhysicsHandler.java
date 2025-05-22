package com.example.minecartmod;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class MinecartPhysicsHandler {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(MinecartPhysicsHandler::onServerTick);
    }

    private static void onServerTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            for (MinecartEntity minecart : world.getEntitiesByClass(MinecartEntity.class, minecart -> true)) {
                applyCustomPhysics(minecart);
            }
        }
    }

    private static void applyCustomPhysics(MinecartEntity minecart) {
        if (minecart.isOnGround()) return;

        minecart.setVelocity(
            minecart.getVelocity().x * 0.99,
            minecart.getVelocity().y - 0.04 * GravityManager.getGravity(),
            minecart.getVelocity().z * 0.99
        );
    }
}