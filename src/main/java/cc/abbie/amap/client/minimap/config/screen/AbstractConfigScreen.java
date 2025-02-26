package cc.abbie.amap.client.minimap.config.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import org.jetbrains.annotations.Nullable;

import cc.abbie.amap.client.minimap.config.widget.BooleanConfigButton;
import cc.abbie.amap.client.minimap.config.widget.ConfigButton;
import cc.abbie.amap.client.minimap.config.widget.SimpleButton;
import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

import java.util.List;

public abstract class AbstractConfigScreen extends Screen {
    private final Screen parent;
    
    protected AbstractConfigScreen(Component title, @Nullable Screen parent) {
        super(title);
        
        this.parent = parent;
    }

    @Override
    protected void init() {
        GridLayout layout = new GridLayout().columnSpacing(5).rowSpacing(6);
        layout.defaultCellSetting().alignHorizontallyCenter();
        GridLayout.RowHelper rows = layout.createRowHelper(1);

        int titleWidth = font.width(title);
        rows.addChild(new SimpleButton(title, titleWidth + 4));

        GridLayout innerContainer = new GridLayout();
        innerContainer.defaultCellSetting().paddingHorizontal(2).paddingTop(2).paddingBottom(1);

        GridLayout inner = new GridLayout();
        GridLayout.RowHelper innerRows = inner.createRowHelper(1);

        for (ConfigButton button : getButtons()) {
            innerRows.addChild(button);
        }
        
        innerContainer.addChild(inner, 0, 0);

        rows.addChild(innerContainer);

        rows.addChild(new SimpleButton(Component.translatable("config.amap.category.back"), b -> onClose()));

        layout.arrangeElements();
        FrameLayout.alignInRectangle(layout, 0, 0, this.width, this.height, 0.5f, 0.5f);
        this.addRenderableOnly((gui, mouseX, mouseY, partialTick) -> {
            int x = innerContainer.getX();
            int y = innerContainer.getY();
            int maxX = x + innerContainer.getWidth();
            int maxY = y + innerContainer.getHeight();
            gui.fill(x, y, maxX, maxY, 0xa0000000);
        });
        layout.visitWidgets(this::addRenderableWidget);

    }
    
    protected abstract List<ConfigButton> getButtons();
    
    public static Component createComponent(TrackedValue<?> trackedValue) {
        return Component.translatable("config.amap.option." + String.join(".", trackedValue.key()));
    }
    
    public static BooleanConfigButton booleanButton(TrackedValue<Boolean> trackedValue) {
        return new BooleanConfigButton(
                createComponent(trackedValue),
                trackedValue
        );
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if (minecraft.level == null) {
            renderPanorama(guiGraphics, partialTick);
            renderBlurredBackground(partialTick);
            renderMenuBackground(guiGraphics);
        }
    }
    
    @Override
    public void onClose() {
        minecraft.setScreen(parent);
    }
}
