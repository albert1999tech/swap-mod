package com.example.swapmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SwapMod implements ClientModInitializer {

    private static KeyBinding swapKey;

    @Override
    public void onInitializeClient() {
        swapKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.swapmod.swap",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.swapmod"
        ));

        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (swapKey.wasPressed()) {
                if (client.player != null) {
                    int currentSlot = client.player.getInventory().selectedSlot;
                    if (currentSlot == 0) {
                        client.player.getInventory().selectedSlot = 1;
                    } else if (currentSlot == 1) {
                        client.player.getInventory().selectedSlot = 0;
                    }
                }
            }
        });
    }
}
