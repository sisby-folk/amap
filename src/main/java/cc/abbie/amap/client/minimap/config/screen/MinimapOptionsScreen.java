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
                        Component.translatable("config.amap.option.minimap.shape"),
                        section.shape,
                        MinimapConfig.Minimap.Shape.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.texture"),
                        section.texture,
                        MinimapConfig.Minimap.Texture.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.position"),
                        section.position,
                        MinimapConfig.Minimap.Position.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.scale"),
                        section.scale,
                        MinimapConfig.Minimap.Scale.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.opacity"),
                        section.opacity,
                        MinimapConfig.Minimap.Opacity.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.largeScale"),
                        section.largeScale,
                        MinimapConfig.Minimap.Scale.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.largeOpacity"),
                        section.largeOpacity,
                        MinimapConfig.Minimap.Opacity.values()
                ),
                booleanButton(section.largeLabel),
                booleanButton(section.filtering),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.showCoordinates"),
                        section.showCoordinates,
                        MinimapConfig.Minimap.CoordinatesType.values()
                ),
                booleanButton(section.showMenuKey),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.fontScale"),
                        section.fontScale,
                        MinimapConfig.Minimap.Scale.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.defaultZoom"),
                        section.defaultZoom,
                        MinimapConfig.Minimap.Zoom.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.maskType"),
                        section.mapMaskType,
                        MinimapConfig.Minimap.MaskType.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.updateFrequency"),
                        section.updateFrequency,
                        MinimapConfig.Minimap.Amount.values()
                ),
                booleanButton(section.threading),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.threadPriority"),
                        section.threadPriority,
                        MinimapConfig.Minimap.Amount.values()
                ),
                booleanButton(section.preloadedChunks)
        );
    }
}
