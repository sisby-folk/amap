package cc.abbie.amap.client;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import folk.sisby.surveyor.terrain.LayerSummary;
import folk.sisby.surveyor.terrain.RegionSummary;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;

public class MinimapHud implements HudRenderCallback {
    public static int scale = 0;
    public static final int minScale = -2;
    public static final int maxScale = 2;

    @Override
    public void onHudRender(GuiGraphics gui, float tickDelta) {
        PoseStack pose = gui.pose();
        Minecraft client = Minecraft.getInstance();
        Window window = client.getWindow();
        int windowHeight = window.getGuiScaledHeight();
        int windowWidth = window.getGuiScaledWidth();

        int mapWidth = 100;
        int mapHeight = 100;

        int offsetX = 5;
        int offsetY = 5;

        int minX = windowWidth - mapWidth - offsetX;
        int minY = offsetY;
        int maxX = windowWidth - offsetX;
        int maxY = mapHeight + offsetY;

        int mapCentreX = minX + mapWidth / 2;
        int mapCentreY = minY + mapHeight / 2;


        gui.enableScissor(minX, minY, maxX, maxY);
        pose.pushPose();
            pose.translate(minX, minY, 0);

            gui.fill(0, 0, mapWidth, mapHeight, 0xff000000);

            float rot;
            ChunkPos playerChunkPos;
            Vec3 playerPos;
            if (client.player != null) {
                rot = client.player.getViewYRot(tickDelta);
                playerPos = client.player.getEyePosition(tickDelta);
                playerChunkPos = new ChunkPos((int) (playerPos.x / 16), (int) (playerPos.z / 16));
            } else {
                rot = 0f;
                playerChunkPos = ChunkPos.ZERO;
                playerPos = Vec3.ZERO;
            }

            pose.pushPose();
                pose.translate(mapWidth / 2f, mapHeight / 2f, 0);
                pose.scale((float) Math.pow(2, scale), (float) Math.pow(2, scale), 1);
                pose.translate(-playerPos.x % 16, -playerPos.z % 16, 0);
                for (int x = -4; x < 4; x++) {
                    for (int y = -4; y < 4; y++) {
                        pose.pushPose();
                            pose.translate(x * 16, y * 16, 0);
                            renderChunk(gui, new ChunkPos(playerChunkPos.x + x, playerChunkPos.z + y));
                        pose.popPose();
                    }
                }
            pose.popPose();

            pose.pushPose();
                pose.translate(mapWidth / 2f, mapHeight / 2f, 0);
                pose.rotateAround(Axis.ZP.rotationDegrees(rot + 180f), 0, 0, 0);
                pose.translate(-0.5f, 0, 0);
                gui.blit(new ResourceLocation("textures/map/map_icons.png"), -4, -4, 16, 0, 8, 8, 128, 128);
            pose.popPose();


        pose.popPose();
        gui.disableScissor();
    }

    public static void zoomOut() {
        if (scale > minScale) scale--;
    }

    public static void zoomIn() {
        if (scale < maxScale) scale++;
    }

    private void renderChunk(GuiGraphics gui, ChunkPos chunkPos) {
        ChunkPos regionPos = new ChunkPos(RegionSummary.chunkToRegion(chunkPos.x), RegionSummary.chunkToRegion(chunkPos.z));
        ChunkPos regionRelativePos = new ChunkPos(RegionSummary.regionRelative(chunkPos.x), RegionSummary.regionRelative(chunkPos.z));

        LayerSummary.Raw[][] terr = MapStorage.INSTANCE.terrain.get(regionPos);
        var palette = MapStorage.INSTANCE.blockPalettes.get(chunkPos);
        if (terr != null && palette != null) {
            var summ = terr[regionRelativePos.x][regionRelativePos.z];
            if (summ != null) {
                var blocks = summ.blocks();
                if (blocks != null) {
                    for (int x = 0; x < 16; x++) {
                        for (int y = 0; y < 16; y++) {
                            Block block = palette.byIdOrThrow(blocks[16 * x + y]);
                            MapColor mapColor = block.defaultMapColor();
                            int colour = mapColor.col | 0xff000000;
                            gui.fill(x, y, x + 1, y + 1, colour);
                        }
                    }
                }
            }
        }
    }
}
