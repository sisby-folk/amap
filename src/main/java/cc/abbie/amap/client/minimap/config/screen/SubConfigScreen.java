package cc.abbie.amap.client.minimap.config.screen;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SubConfigScreen extends Screen {
    private final Screen parent;
    
    protected SubConfigScreen(Screen parent) {
        super(Component.empty());
        
        this.parent = parent;
    }

    @Override
    public void onClose() {
        minecraft.setScreen(parent);
    }
}
