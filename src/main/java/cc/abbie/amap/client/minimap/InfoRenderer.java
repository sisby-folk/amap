package cc.abbie.amap.client.minimap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.phys.Vec3;

public class InfoRenderer {
    public static void renderInfo(GuiGraphics gui, int x, int y, float partialTick) {
        Minecraft client = Minecraft.getInstance();
        LocalPlayer player = client.player;
        if (player == null) return;

        Vec3 eyePos = player.getEyePosition(partialTick);
        int footPos = (int) player.getPosition(partialTick).y;

        gui.drawCenteredString(Minecraft.getInstance().font, String.format("%.2f, %.2f", eyePos.x, eyePos.z), x, y, -1);
        gui.drawCenteredString(Minecraft.getInstance().font, String.format("%.2f (%d)", eyePos.y, footPos), x, y + 10, -1);
    }
}
