package cc.abbie.amap.client.worldmap;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class WorldMapScreen extends Screen {
    public WorldMapScreen() {
        super(Component.translatable("screen.amap.worldmap"));
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        renderBackground(gui);
        super.render(gui, mouseX, mouseY, partialTick);
    }
}
