package cc.abbie.amap.client;

import cc.abbie.amap.AMap;
import folk.sisby.surveyor.WorldSummary;
import folk.sisby.surveyor.client.SurveyorClientEvents;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class AMapClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new MinimapHud());

        WorldSummary.enableTerrain();

        SurveyorClientEvents.Register.worldLoad(AMap.id("world_load"), MapStorage.INSTANCE);
        SurveyorClientEvents.Register.terrainUpdated(AMap.id("terrain_updated"), MapStorage.INSTANCE);

        AMapKeybinds.register();
    }
}
