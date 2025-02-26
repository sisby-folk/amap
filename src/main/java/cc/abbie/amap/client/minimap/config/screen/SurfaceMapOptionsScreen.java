package cc.abbie.amap.client.minimap.config.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.amap.client.minimap.config.MinimapConfig;
import cc.abbie.amap.client.minimap.config.widget.ConfigButton;
import cc.abbie.amap.client.minimap.config.widget.EnumConfigButton;

import java.util.List;

public class SurfaceMapOptionsScreen extends AbstractConfigScreen {
    
    protected SurfaceMapOptionsScreen(@Nullable Screen parent) {
        super(Component.translatable("config.amap.category.surfaceMap"), parent);
    }

    @Override
    protected List<ConfigButton> getButtons() {
        MinimapConfig.SurfaceMap section = MinimapConfig.INSTANCE.surfaceMap;
        return List.of(
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.surfaceMap.lighting"),
                        section.lighting,
                        MinimapConfig.SurfaceMap.Lighting.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.surfaceMap.lightingType"),
                        section.lightingType,
                        MinimapConfig.SurfaceMap.LightingType.values()
                ),
                booleanButton(section.terrainUndulate),
                booleanButton(section.terrainDepth),
                booleanButton(section.transparency),
                booleanButton(section.environmentColor),
                booleanButton(section.omitHeightCalc),
                booleanButton(section.hideSnow),
                booleanButton(section.showChunkGrid),
                booleanButton(section.showSlimeChunk)
        );
    }
}
