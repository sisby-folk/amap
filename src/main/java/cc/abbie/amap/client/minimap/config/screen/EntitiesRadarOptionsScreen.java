package cc.abbie.amap.client.minimap.config.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.amap.client.minimap.config.MinimapConfig;
import cc.abbie.amap.client.minimap.config.widget.ConfigButton;

import java.util.List;

public class EntitiesRadarOptionsScreen extends AbstractConfigScreen {
    protected EntitiesRadarOptionsScreen(@Nullable Screen parent) {
        super(Component.translatable("config.amap.category.entitiesRadar"), parent);
    }

    @Override
    protected List<ConfigButton> getButtons() {
        MinimapConfig.EntitiesRadar section = MinimapConfig.INSTANCE.entitiesRadar;
        return List.of(
                booleanButton(section.enable),
                booleanButton(section.player),
                booleanButton(section.animal),
                booleanButton(section.monster),
                booleanButton(section.slime),
                booleanButton(section.squid),
                booleanButton(section.other),
                booleanButton(section.lightning),
                booleanButton(section.showDirection)
        );
    }
}
