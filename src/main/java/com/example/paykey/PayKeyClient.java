package com.example.paykey;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class PayKeyClient implements ClientModInitializer {

    private static KeyBinding payKey;

    @Override
    public void onInitializeClient() {
        KeyBinding.Category category = KeyBinding.Category.create(Identifier.of("paykey", "main"));

        payKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.paykey.pay",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_I,
                category
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (payKey.wasPressed()) {
                if (client.player != null) {
                    // ohne fuehrenden Slash
                    client.player.networkHandler.sendChatCommand("pay g0ggY3909 8m");
                }
            }
        });
    }
}
