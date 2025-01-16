package cc.abbie.amap.client.worldmap;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import cc.abbie.amap.client.AMapKeybinds;

public class WorldMapScreen extends Screen {
    public WorldMapScreen() {
        super(Component.translatable("screen.amap.worldmap"));
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (AMapKeybinds.openWorldmap.matches(keyCode, scanCode)) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
