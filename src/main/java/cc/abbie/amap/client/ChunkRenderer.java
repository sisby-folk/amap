package cc.abbie.amap.client;

import cc.abbie.amap.GuiUtil;
import folk.sisby.surveyor.terrain.LayerSummary;
import folk.sisby.surveyor.terrain.RegionSummary;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

public class ChunkRenderer {

    /**
     * Renders the specified chunk to the screen at 0,0 (pose translation recommended)
     * @param gui the {@link GuiGraphics} to render with
     * @param chunkPos chunk position in the current {@link net.minecraft.client.multiplayer.ClientLevel} to render
     */
    public static void renderChunk(GuiGraphics gui, ChunkPos chunkPos) {
        ChunkPos regionPos = new ChunkPos(RegionSummary.chunkToRegion(chunkPos.x), RegionSummary.chunkToRegion(chunkPos.z));
        ChunkPos regionRelativePos = new ChunkPos(RegionSummary.regionRelative(chunkPos.x), RegionSummary.regionRelative(chunkPos.z));
        LayerSummary.Raw[][] terr = MapStorage.INSTANCE.regions.get(regionPos);
        var blockPalette = MapStorage.INSTANCE.blockPalettes.get(chunkPos);
        var biomePalette = MapStorage.INSTANCE.biomePalettes.get(chunkPos);

        // needed for shading
        ChunkPos northChunkPos = new ChunkPos(chunkPos.x, chunkPos.z - 1);
        ChunkPos northRegionPos = new ChunkPos(RegionSummary.chunkToRegion(northChunkPos.x), RegionSummary.chunkToRegion(northChunkPos.z));
        ChunkPos northRegionRelativePos = new ChunkPos(RegionSummary.regionRelative(northChunkPos.x), RegionSummary.regionRelative(northChunkPos.z));
        LayerSummary.Raw[][] northTerr = MapStorage.INSTANCE.regions.get(northRegionPos);
        var northBlockPalette = MapStorage.INSTANCE.blockPalettes.get(northChunkPos);
        var northBiomePalette = MapStorage.INSTANCE.biomePalettes.get(northChunkPos);

        if (terr == null || blockPalette == null || biomePalette == null || northTerr == null || northBlockPalette == null || northBiomePalette == null) return;

        var summ = terr[regionRelativePos.x][regionRelativePos.z];
        var northSumm = northTerr[northRegionRelativePos.x][northRegionRelativePos.z];
        if (summ == null || northSumm == null) return;

        var blocks = summ.blocks();
        var biomes = summ.biomes();
        var northBlocks = northSumm.blocks();
        var northBiomes = northSumm.biomes();
        if (blocks == null || biomes == null || northBlocks == null || northBiomes == null) return;

        FillBatcher fillBatcher = new FillBatcher(gui);
        for (int x = 0; x < 16; x++) {
            for (int y = 0; y < 16; y++) {
                int idx = 16 * x + y;
                if (!summ.exists().get(idx)) continue;

                Block block = blockPalette.byId(blocks[idx]);
                Biome biome = biomePalette.byId(biomes[idx]);
                if (block == null || biome == null) continue;
                MapColor.Brightness brightness;
                int waterDepth = summ.waterDepths()[idx];
                MapColor mapColor;
                if (waterDepth > 0) {
//                    colour = biome.getWaterColor() | 0xff000000;
                    double f = (double) waterDepth * 0.1 + (double) (x + y & 1) * 0.2;
                    if (f < 0.5) {
                        brightness = MapColor.Brightness.HIGH;
                    } else if (f > 0.9) {
                        brightness = MapColor.Brightness.LOW;
                    } else {
                        brightness = MapColor.Brightness.NORMAL;
                    }
                    mapColor = MapColor.WATER;
                } else {
                    int depth = summ.depths()[idx];
                    int northDepth;
                    if (y > 0) {
                        int northIdx = 16 * x + y - 1;
                        if (!summ.exists().get(northIdx)) {
                            northDepth = Integer.MAX_VALUE;
                        } else {
                            northDepth = summ.depths()[northIdx] - summ.waterDepths()[northIdx];
                        }
                    } else {
                        int northIdx = 16 * x + 15;
                        if (!northSumm.exists().get(northIdx)) {
                            northDepth = Integer.MAX_VALUE;
                        } else {
                            northDepth = northSumm.depths()[northIdx] - northSumm.waterDepths()[northIdx];
                        }
                    }
                    if (depth == northDepth) {
                        brightness = MapColor.Brightness.NORMAL;
                    } else if (depth < northDepth) {
                        brightness = MapColor.Brightness.HIGH;
                    } else {
                        brightness = MapColor.Brightness.LOW;
                    }
                    mapColor = block.defaultMapColor();
                }
                int colour = GuiUtil.abgrToArgb(mapColor.calculateRGBColor(brightness));
                fillBatcher.add(x, y, x + 1, y + 1, 0, colour);
            }
        }
        fillBatcher.close();
    }

}
