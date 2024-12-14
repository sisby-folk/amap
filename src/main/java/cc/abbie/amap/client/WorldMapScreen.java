package cc.abbie.amap.client;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class WorldMapScreen extends Screen {
    protected WorldMapScreen() {
        super(Component.translatable("screen.amap.world_map"));
    }
}
