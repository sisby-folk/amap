package cc.abbie.amap.client.minimap.config.screen;

import net.minecraft.SharedConstants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.layouts.FrameLayout;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.controls.KeyBindsScreen;
import net.minecraft.network.chat.Component;

import cc.abbie.amap.AMap;
import cc.abbie.amap.client.AMapKeybinds;
import cc.abbie.amap.client.minimap.config.MinimapConfig;
import cc.abbie.amap.client.minimap.config.widget.BooleanConfigButton;
import cc.abbie.amap.client.minimap.config.widget.ConfigButton;
import cc.abbie.amap.client.minimap.config.widget.EnumConfigButton;
import cc.abbie.amap.client.minimap.config.widget.SimpleButton;

public class MinimapConfigScreen extends Screen {
    public MinimapConfigScreen() {
        super(Component.translatable("screen.amap.minimap.config"));
    }

    private static int boopCounter = 0;

    @Override
    protected void init() {
        GridLayout layout = new GridLayout().columnSpacing(5).rowSpacing(6);
        layout.defaultCellSetting().alignHorizontallyCenter();
        GridLayout.RowHelper rows = layout.createRowHelper(3);

        String titleText = String.format("%s v%s [%s]", AMap.MOD_NAME, AMap.MOD_VERSION, SharedConstants.getCurrentVersion().getName());
        int titleWidth = font.width(titleText);
        rows.addChild(new SimpleButton(Component.literal(titleText), titleWidth + 4, b -> {
            boopCounter++;
            if (boopCounter > 10) {
                b.active = false;
                b.setMessage(Component.literal(">:("));
            }
        }), 3);

        GridLayout innerContainer = new GridLayout();
        innerContainer.defaultCellSetting().paddingHorizontal(2).paddingTop(2).paddingBottom(1);

        GridLayout inner = new GridLayout();
        GridLayout.RowHelper innerRows = inner.createRowHelper(1);

        innerRows.addChild(new BooleanConfigButton(
                Component.translatable("config.amap.option.enableMinimap"),
                MinimapConfig.INSTANCE.enable
        ));
        innerRows.addChild(new EnumConfigButton<>(
                Component.translatable("config.amap.option.renderType"),
                MinimapConfig.INSTANCE.renderType,
                MinimapConfig.RenderType.values()
        ));
        innerRows.addChild(new BooleanConfigButton(
                Component.translatable("config.amap.option.deathPoint"),
                MinimapConfig.INSTANCE.deathPoint
        ));
        innerRows.addChild(new ConfigButton(
                Component.translatable("config.amap.category.minimap"),
                b -> minecraft.setScreen(new SubConfigScreen(this))
        ));
        innerRows.addChild(new ConfigButton(
                Component.translatable("config.amap.category.surfaceMap"),
                b -> minecraft.setScreen(new SubConfigScreen(this))
        ));
        innerRows.addChild(new ConfigButton(
                Component.translatable("config.amap.category.entitiesRadar"),
                b -> minecraft.setScreen(new SubConfigScreen(this))
        ));
        innerRows.addChild(new ConfigButton(
                Component.translatable("config.amap.category.marker"),
                b -> minecraft.setScreen(new SubConfigScreen(this))
        ));
        innerRows.addChild(new ConfigButton(
                Component.translatable("config.amap.category.about"),
                b -> minecraft.setScreen(new SubConfigScreen(this))
        ));
        innerRows.addChild(new ConfigButton(Component.translatable("config.amap.option.updateCheck")));
        innerRows.addChild(new BooleanConfigButton(
                Component.translatable("config.amap.option.autoUpdateCheck"),
                MinimapConfig.INSTANCE.autoUpdateCheck
        ));

        innerContainer.addChild(inner, 0, 0);

        rows.addChild(innerContainer, 3);

        rows.addChild(new SimpleButton(Component.translatable("config.amap.option.exit"), b -> onClose()));
        rows.addChild(new SimpleButton(Component.translatable("config.amap.category.waypoints")));
        rows.addChild(new SimpleButton(Component.translatable("config.amap.category.keyconfig"), b -> {
            this.minecraft.setScreen(new KeyBindsScreen(this, this.minecraft.options));
        }));

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

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // no-op
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (AMapKeybinds.OPEN_MINIMAP_CONFIG.matches(keyCode, scanCode)) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
