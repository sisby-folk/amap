package cc.abbie.amap.client;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import org.joml.Matrix4f;

// thank you Sisby Folk!
public class FillBatcher implements AutoCloseable {
    private final GuiGraphics gui;
    private final Matrix4f matrix4f;
    private final VertexConsumer vertexConsumer;

    public FillBatcher(GuiGraphics gui) {
        this.gui = gui;
        this.matrix4f = gui.pose().last().pose();
        this.vertexConsumer = gui.bufferSource().getBuffer(RenderType.gui());
    }

    public void add(int x1, int y1, int x2, int y2, int z, int color) {
        vertexConsumer.vertex(matrix4f, x1, y1, z).color(color).endVertex();
        vertexConsumer.vertex(matrix4f, x1, y2, z).color(color).endVertex();
        vertexConsumer.vertex(matrix4f, x2, y2, z).color(color).endVertex();
        vertexConsumer.vertex(matrix4f, x2, y1, z).color(color).endVertex();
    }

    @Override
    public void close() {
        gui.flush();
    }
}
