package cc.abbie.amap.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class AMapKeybinds implements ClientTickEvents.EndTick {

    private static final String worldMapCategory = "key.categories.amap.world_map";
    public static final KeyMapping OPEN_WORLD_MAP = new KeyMapping("key.amap.world_map.open", GLFW.GLFW_KEY_M, worldMapCategory);

    private static final String minimapCategory = "key.categories.amap.minimap";
    public static final KeyMapping ZOOM_OUT_MINIMAP = new KeyMapping("key.amap.minimap.zoom_out", GLFW.GLFW_KEY_MINUS, minimapCategory);
    public static final KeyMapping ZOOM_IN_MINIMAP = new KeyMapping("key.amap.minimap.zoom_in", GLFW.GLFW_KEY_EQUAL, minimapCategory);

    public static void register() {
        KeyBindingHelper.registerKeyBinding(OPEN_WORLD_MAP);
        KeyBindingHelper.registerKeyBinding(ZOOM_OUT_MINIMAP);
        KeyBindingHelper.registerKeyBinding(ZOOM_IN_MINIMAP);

        ClientTickEvents.END_CLIENT_TICK.register(new AMapKeybinds());
    }


    @Override
    public void onEndTick(Minecraft client) {
        if (ZOOM_OUT_MINIMAP.consumeClick()) {
            MinimapHud.zoomOut();
        } else if (ZOOM_IN_MINIMAP.consumeClick()) {
            MinimapHud.zoomIn();
        } else if (OPEN_WORLD_MAP.consumeClick()) {
            client.setScreen(new WorldMapScreen());
        }
    }
}
