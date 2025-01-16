package cc.abbie.amap.mixin.client;

import net.minecraft.client.gui.Gui;

import cc.abbie.amap.client.minimap.MinimapHud;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Gui.class)
public abstract class GuiMixin {
    @ModifyExpressionValue(method = "renderEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;guiWidth()I"))
    private int pushEffects(int original) {
        if (!MinimapHud.enable || MinimapHud.position != MinimapHud.Position.TOP_RIGHT) return original;

        return original - MinimapHud.mapWidth - 2 * MinimapHud.offsetX;
    }
}
