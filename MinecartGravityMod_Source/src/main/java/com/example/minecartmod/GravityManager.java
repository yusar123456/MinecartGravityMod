package com.example.minecartmod;

public class GravityManager {
    private static float gravity = 1.0f;

    public static void setGravity(float g) {
        gravity = g;
    }

    public static float getGravity() {
        return gravity;
    }
}