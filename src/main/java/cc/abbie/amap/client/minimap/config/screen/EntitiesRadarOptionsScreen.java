package cc.abbie.amap.client.minimap.config.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.amap.client.minimap.config.MinimapConfig;
import cc.abbie.amap.client.minimap.config.widget.BooleanConfigButton;
import cc.abbie.amap.client.minimap.config.widget.ConfigButton;

import java.util.List;

public class EntitiesRadarOptionsScreen extends AbstractConfigScreen {
    protected EntitiesRadarOptionsScreen(@Nullable Screen parent) {
        super(Component.translatable("config.amap.category.entitiesRadar"), parent);
    }

    @Override
    protected List<ConfigButton> getButtons() {
        return List.of(
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.entitiesRadar.enable"),
                        MinimapConfig.INSTANCE.entitiesRadar.enable
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.entitiesRadar.player"),
                        MinimapConfig.INSTANCE.entitiesRadar.player
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.entitiesRadar.animal"),
                        MinimapConfig.INSTANCE.entitiesRadar.animal
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.entitiesRadar.monster"),
                        MinimapConfig.INSTANCE.entitiesRadar.monster
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.entitiesRadar.slime"),
                        MinimapConfig.INSTANCE.entitiesRadar.slime
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.entitiesRadar.squid"),
                        MinimapConfig.INSTANCE.entitiesRadar.squid
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.entitiesRadar.otherLiving"),
                        MinimapConfig.INSTANCE.entitiesRadar.other
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.entitiesRadar.lightning"),
                        MinimapConfig.INSTANCE.entitiesRadar.lightning
                ),
                new BooleanConfigButton(
                        Component.translatable("config.amap.option.entitiesRadar.showDirection"),
                        MinimapConfig.INSTANCE.entitiesRadar.showDirection
                )
        );
    }
}
