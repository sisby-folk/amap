package cc.abbie.amap.client.minimap.config.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.amap.client.minimap.config.widget.ConfigButton;

import java.util.List;

public class MarkerOptionsScreen extends AbstractConfigScreen {
    protected MarkerOptionsScreen(@Nullable Screen parent) {
        super(Component.translatable("config.amap.category.marker"), parent);
    }

    @Override
    protected List<ConfigButton> getButtons() {
        return List.of();
    }
}
