package cc.abbie.amap.client;

import cc.abbie.amap.client.minimap.MinimapConfigScreen;
import cc.abbie.amap.client.minimap.MinimapHud;
import cc.abbie.amap.client.worldmap.WorldMapScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

public class AMapKeybinds implements ClientTickEvents.EndTick {

    private static final String worldMapCategory = "key.categories.amap.worldmap";
    public static final KeyMapping openWorldmap = new KeyMapping("key.amap.worldmap.open", GLFW.GLFW_KEY_M, worldMapCategory);

    private static final String minimapCategory = "key.categories.amap.minimap";
    private static final KeyMapping zoomOutMinimap = new KeyMapping("key.amap.minimap.zoom_out", GLFW.GLFW_KEY_MINUS, minimapCategory);
    private static final KeyMapping zoomInMinimap = new KeyMapping("key.amap.minimap.zoom_in", GLFW.GLFW_KEY_EQUAL, minimapCategory);
    private static final KeyMapping toggleRotation = new KeyMapping("key.amap.minimap.toggle_rotation", GLFW.GLFW_KEY_R, minimapCategory);
    public static final KeyMapping openMinimapConfig = new KeyMapping("key.amap.minimap.config", GLFW.GLFW_KEY_PERIOD, minimapCategory);

    public static void register() {
        KeyBindingHelper.registerKeyBinding(openWorldmap);
        KeyBindingHelper.registerKeyBinding(zoomOutMinimap);
        KeyBindingHelper.registerKeyBinding(zoomInMinimap);
        KeyBindingHelper.registerKeyBinding(toggleRotation);
        KeyBindingHelper.registerKeyBinding(openMinimapConfig);

        ClientTickEvents.END_CLIENT_TICK.register(new AMapKeybinds());
    }


    @Override
    public void onEndTick(Minecraft client) {
        if (zoomOutMinimap.consumeClick()) {
            MinimapHud.zoomOut();
        } else if (zoomInMinimap.consumeClick()) {
            MinimapHud.zoomIn();
        } else if (openWorldmap.consumeClick()) {
            client.setScreen(new WorldMapScreen());
        } else if (toggleRotation.consumeClick()) {
            MinimapHud.rotate = !MinimapHud.rotate;
        } else if (openMinimapConfig.consumeClick()) {
            client.setScreen(new MinimapConfigScreen());
        }
    }
}
