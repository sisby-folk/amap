package cc.abbie.amap.client.minimap.config.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.amap.client.minimap.config.MinimapConfig;
import cc.abbie.amap.client.minimap.config.widget.ConfigButton;
import cc.abbie.amap.client.minimap.config.widget.EnumConfigButton;

import java.util.List;

public class MinimapOptionsScreen extends AbstractConfigScreen {
    protected MinimapOptionsScreen(@Nullable Screen parent) {
        super(Component.translatable("config.amap.category.minimap"), parent);
    }

    @Override
    protected List<ConfigButton> getButtons() {
        MinimapConfig.Minimap section = MinimapConfig.INSTANCE.minimap;
        return List.of(
                new EnumConfigButton<>(
                        section.shape,
                        MinimapConfig.Minimap.Shape.values()
                ),
                new EnumConfigButton<>(
                        section.texture,
                        MinimapConfig.Minimap.Texture.values()
                ),
                new EnumConfigButton<>(
                        section.position,
                        MinimapConfig.Minimap.Position.values()
                ),
                new EnumConfigButton<>(
                        section.scale,
                        MinimapConfig.Minimap.Scale.values()
                ),
                new EnumConfigButton<>(
                        section.opacity,
                        MinimapConfig.Minimap.Opacity.values()
                ),
                new EnumConfigButton<>(
                        section.largeScale,
                        MinimapConfig.Minimap.Scale.values()
                ),
                new EnumConfigButton<>(
                        section.largeOpacity,
                        MinimapConfig.Minimap.Opacity.values()
                ),
                booleanButton(section.largeLabel),
                booleanButton(section.filtering),
                new EnumConfigButton<>(
                        section.showCoordinates,
                        MinimapConfig.Minimap.CoordinatesType.values()
                ),
                booleanButton(section.showMenuKey),
                new EnumConfigButton<>(
                        section.fontScale,
                        MinimapConfig.Minimap.Scale.values()
                ),
                new EnumConfigButton<>(
                        section.defaultZoom,
                        MinimapConfig.Minimap.Zoom.values()
                ),
                new EnumConfigButton<>(
                        section.mapMaskType,
                        MinimapConfig.Minimap.MaskType.values()
                ),
                new EnumConfigButton<>(
                        section.updateFrequency,
                        MinimapConfig.Minimap.Amount.values()
                ),
                booleanButton(section.threading),
                new EnumConfigButton<>(
                        section.threadPriority,
                        MinimapConfig.Minimap.Amount.values()
                ),
                booleanButton(section.preloadedChunks)
        );
    }
}
