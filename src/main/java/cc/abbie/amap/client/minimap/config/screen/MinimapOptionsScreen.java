package cc.abbie.amap.client.minimap.config.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.amap.client.minimap.config.MinimapConfig;
import cc.abbie.amap.client.minimap.config.widget.BooleanConfigButton;
import cc.abbie.amap.client.minimap.config.widget.ConfigButton;
import cc.abbie.amap.client.minimap.config.widget.EnumConfigButton;

import java.util.List;

public class MinimapOptionsScreen extends AbstractConfigScreen {
    protected MinimapOptionsScreen(@Nullable Screen parent) {
        super(Component.translatable("config.amap.category.minimap"), parent);
    }

    @Override
    protected List<ConfigButton> getButtons() {
        return List.of(
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.shape"),
                        MinimapConfig.INSTANCE.minimap.shape,
                        MinimapConfig.Minimap.Shape.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.texture"),
                        MinimapConfig.INSTANCE.minimap.texture,
                        MinimapConfig.Minimap.Texture.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.position"),
                        MinimapConfig.INSTANCE.minimap.position,
                        MinimapConfig.Minimap.Position.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.scale"),
                        MinimapConfig.INSTANCE.minimap.scale,
                        MinimapConfig.Minimap.Scale.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.opacity"),
                        MinimapConfig.INSTANCE.minimap.opacity,
                        MinimapConfig.Minimap.Opacity.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.largeScale"),
                        MinimapConfig.INSTANCE.minimap.largeScale,
                        MinimapConfig.Minimap.Scale.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.largeOpacity"),
                        MinimapConfig.INSTANCE.minimap.largeOpacity,
                        MinimapConfig.Minimap.Opacity.values()
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.minimap.largeLabel"),
                        MinimapConfig.INSTANCE.minimap.largeLabel
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.minimap.filtering"),
                        MinimapConfig.INSTANCE.minimap.filtering
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.showCoordinates"),
                        MinimapConfig.INSTANCE.minimap.showCoordinates,
                        MinimapConfig.Minimap.CoordinatesType.values()
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.minimap.showMenuKey"),
                        MinimapConfig.INSTANCE.minimap.showMenuKey
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.fontScale"),
                        MinimapConfig.INSTANCE.minimap.fontScale,
                        MinimapConfig.Minimap.Scale.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.defaultZoom"),
                        MinimapConfig.INSTANCE.minimap.defaultZoom,
                        MinimapConfig.Minimap.Zoom.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.maskType"),
                        MinimapConfig.INSTANCE.minimap.mapMaskType,
                        MinimapConfig.Minimap.MaskType.values()
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.updateFrequency"),
                        MinimapConfig.INSTANCE.minimap.updateFrequency,
                        MinimapConfig.Minimap.Amount.values()
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.minimap.threading"),
                        MinimapConfig.INSTANCE.minimap.threading
                ),
                new EnumConfigButton<>(
                        Component.translatable("config.amap.option.minimap.threadPriority"),
                        MinimapConfig.INSTANCE.minimap.threadPriority,
                        MinimapConfig.Minimap.Amount.values()
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.minimap.preloadedChunks"),
                        MinimapConfig.INSTANCE.minimap.preloadedChunks
                )
        );
    }
}
