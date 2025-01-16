package cc.abbie.amap.client;

import com.mojang.blaze3d.platform.NativeImage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.FastColor;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;

import folk.sisby.surveyor.terrain.LayerSummary;
import folk.sisby.surveyor.terrain.RegionSummary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChunkRenderer {

    public static Mode mode = Mode.NORMAL;
    public static Map<ChunkPos, ResourceLocation> textures = new HashMap<>();
    public static Set<ChunkPos> dirtyChunks = new HashSet<>();

    /**
     * Renders the specified chunk to the screen at 0,0 (pose translation recommended)
     *
     * @param gui      the {@link GuiGraphics} to render with
     * @param chunkPos chunk position in the current {@link net.minecraft.client.multiplayer.ClientLevel} to render
     */
    public static void renderChunk(GuiGraphics gui, ChunkPos chunkPos) {
        Minecraft client = Minecraft.getInstance();
        ClientLevel level = client.level;
        if (level == null) return;
        RegistryAccess registryAccess = level.registryAccess();
        Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registries.BIOME);

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

        if (terr == null || blockPalette == null || biomePalette == null || northTerr == null || northBlockPalette == null || northBiomePalette == null)
            return;

        var summ = terr[regionRelativePos.x][regionRelativePos.z];
        var northSumm = northTerr[northRegionRelativePos.x][northRegionRelativePos.z];
        if (summ == null || northSumm == null) return;

        var blocks = summ.blocks();
        var biomes = summ.biomes();
        var northBlocks = northSumm.blocks();
        var northBiomes = northSumm.biomes();
        if (blocks == null || biomes == null || northBlocks == null || northBiomes == null) return;

        DynamicTexture texture;
        ResourceLocation textureLocation;
        if (textures.containsKey(regionPos)) {
            textureLocation = textures.get(regionPos);
            texture = (DynamicTexture) client.getTextureManager().getTexture(textureLocation);
        } else {
            texture = new DynamicTexture(512, 512, false);
            // needed otherwise we may get a texture filled with garbage!
            texture.getPixels().fillRect(0, 0, 512, 512, 0);
            textureLocation = client.getTextureManager().register("emi_ores/region/", texture);
            textures.put(regionPos, textureLocation);
        }
        if (dirtyChunks.contains(chunkPos)) {
            dirtyChunks.remove(chunkPos);
            NativeImage pixels = texture.getPixels();
            for (int x = 0; x < 16; x++) {
                for (int y = 0; y < 16; y++) {
                    int idx = 16 * x + y;
                    if (!summ.exists().get(idx)) continue;

                    Block block = blockPalette.byId(blocks[idx]);
                    Biome biome = biomePalette.byId(biomes[idx]);
                    if (block == null || biome == null) continue;

                    int colour = switch (mode) {
                        case NORMAL -> {
                            MapColor.Brightness brightness;
                            int waterDepth = summ.waterDepths()[idx];
                            MapColor mapColor;
                            if (waterDepth > 0) {
//                            colour = biome.getWaterColor() | 0xff000000;
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
                            yield mapColor.calculateRGBColor(brightness);
                        }
                        case BIOME -> FastColor.ABGR32.fromArgb32(getBiomeColourArgb(biomeRegistry.wrapAsHolder(biome)));
                    };
                    pixels.setPixelRGBA(16 * regionRelativePos.x + x, 16 * regionRelativePos.z + y, colour | 0xff000000);
                }
            }
            texture.upload();
        }
        gui.blit(textureLocation, 0, 0, 16 * regionRelativePos.x, 16 * regionRelativePos.z, 16, 16, 512, 512);
    }

    private static int getBiomeColourArgb(Holder<Biome> biomeHolder) {
        Biome biome = biomeHolder.value();
        if (biomeHolder.is(BiomeTags.IS_RIVER) || biomeHolder.is(BiomeTags.IS_OCEAN) || biomeHolder.is(BiomeTags.IS_DEEP_OCEAN)) {
            return biome.getWaterColor();
        } else if (biomeHolder.is(BiomeTags.IS_NETHER)) {
            return biome.getFogColor();
        } else {
            return biome.getGrassColor(0, 0);
        }
    }

    public static void clear() {
        TextureManager textureManager = Minecraft.getInstance().getTextureManager();
        for (ResourceLocation id : textures.values()) {
            textureManager.getTexture(id).close();
        }
        textures.clear();
        dirtyChunks.clear();
    }

    public enum Mode {
        NORMAL,
        BIOME
    }

}
