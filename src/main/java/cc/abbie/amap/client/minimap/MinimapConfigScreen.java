package cc.abbie.amap.client.minimap;

import net.minecraft.SharedConstants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import cc.abbie.amap.AMap;

public class MinimapConfigScreen extends Screen {
    public MinimapConfigScreen() {
        super(Component.translatable("screen.amap.minimap.config"));
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float partialTick) {
        String titleText = String.format("%s v%s [%s]", AMap.MOD_NAME, AMap.MOD_VERSION, SharedConstants.getCurrentVersion().getName());
        int titleWidth = font.width(titleText);
        gui.fill(width / 2 - titleWidth / 2 - 3, 15 - 3, width / 2 + titleWidth / 2 + 3, 15 + font.lineHeight + 2, 0xa0000000);
        gui.drawCenteredString(font, titleText, width / 2, 15, -1);

        super.render(gui, mouseX, mouseY, partialTick);
    }
}
