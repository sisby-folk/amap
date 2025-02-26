package cc.abbie.amap.client;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BeaconRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import folk.sisby.surveyor.landmark.Landmark;
import folk.sisby.surveyor.landmark.component.LandmarkComponentTypes;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

import java.util.Map;
import java.util.UUID;

public class AMapWorldRenderer implements WorldRenderEvents.AfterEntities {
    @Override
    public void afterEntities(WorldRenderContext context) {
        ClientLevel level = context.world();
        Camera camera = context.camera();
        PoseStack pose = context.matrixStack();
        float partialTick = context.tickCounter().getGameTimeDeltaPartialTick(false);
        MultiBufferSource consumers = context.consumers();
        long gameTime = level.getGameTime();
        Vec3 cameraPos = camera.getPosition();

        if (consumers == null) return;

        for (Map.Entry<UUID, Map<ResourceLocation, Landmark>> entry : MapStorage.INSTANCE.landmarks.entrySet()) {
            for (Map.Entry<ResourceLocation, Landmark> entry2 : entry.getValue().entrySet()) {
                Landmark landmark = entry2.getValue();
                
                BlockPos pos = landmark.get(LandmarkComponentTypes.POS);
                if (pos == null) continue;
                
                int color = landmark.getOrDefault(LandmarkComponentTypes.COLOR, -1);

                pose.pushPose();
                pose.translate(pos.getX() - cameraPos.x, 0, pos.getZ() - cameraPos.z);
                BeaconRenderer.renderBeaconBeam(
                        pose,
                        consumers,
                        BeaconRenderer.BEAM_LOCATION,
                        partialTick,
                        1,
                        gameTime,
                        -100,
                        200,
                        color,
                        0.15f,
                        0.175f
                );
                pose.popPose();
            }
        }
    }
}
