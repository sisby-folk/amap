package cc.abbie.amap.client;

import cc.abbie.amap.AMap;
import cc.abbie.amap.client.minimap.MinimapHud;
import folk.sisby.surveyor.WorldSummary;
import folk.sisby.surveyor.client.SurveyorClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class AMapClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new MinimapHud());
        WorldRenderEvents.AFTER_ENTITIES.register(new AMapWorldRenderer());

        WorldSummary.enableTerrain();
        WorldSummary.enableLandmarks();

        SurveyorClientEvents.Register.worldLoad(AMap.id("world_load"), MapStorage.INSTANCE);
        SurveyorClientEvents.Register.terrainUpdated(AMap.id("terrain_updated"), MapStorage.INSTANCE);
        SurveyorClientEvents.Register.landmarksAdded(AMap.id("landmarks_added"), MapStorage.INSTANCE);
        SurveyorClientEvents.Register.landmarksRemoved(AMap.id("landmarks_removed"), MapStorage.INSTANCE);

        AMapKeybinds.register();
    }
}
