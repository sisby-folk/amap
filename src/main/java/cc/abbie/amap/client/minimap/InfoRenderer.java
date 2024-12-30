package cc.abbie.amap.client.minimap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;

import cc.abbie.amap.client.AMapKeybinds;

public class InfoRenderer {
    public static boolean showCoordinates = true;
    public static boolean showMenuKey = true;

    public static int getHeight() {
        int lineHeight = Minecraft.getInstance().font.lineHeight;
        int height = 0;

        if (showCoordinates) {
            height += 2 * lineHeight;
        }
        if (showMenuKey) {
            height += lineHeight;
        }

        return height;
    }

    public static void renderInfo(GuiGraphics gui, int centreX, int leftX, int rightX, int y, float partialTick) {
        Minecraft client = Minecraft.getInstance();
        Font font = client.font;

        final int lineHeight = font.lineHeight;
        int yOffset = y;

        if (showCoordinates) {
            LocalPlayer player = client.player;
            if (player == null) return;

            Vec3 eyePos = player.getEyePosition(partialTick);
            int footPos = (int) player.getPosition(partialTick).y;

            gui.drawCenteredString(font, String.format("%+.2f, %+.2f", eyePos.x, eyePos.z), centreX, yOffset, -1);
            yOffset += lineHeight;
            gui.drawCenteredString(font, String.format("%.2f (%d)", eyePos.y, footPos), centreX, yOffset, -1);
            yOffset += lineHeight;
        }

        if (showMenuKey) {
            Component infoLine = Component.translatable("info.amap.menuKey", AMapKeybinds.openMinimapConfig.getTranslatedKeyMessage());
            int textWidth = font.width(infoLine.getVisualOrderText());
            gui.drawString(font, infoLine, rightX - textWidth, yOffset, -1);
            yOffset += lineHeight;
        }
    }
}
