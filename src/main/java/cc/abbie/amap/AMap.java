package cc.abbie.amap;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMap implements ModInitializer {

    public static final String MODID = "amap";
    public static final Logger LOGGER = LoggerFactory.getLogger("AMap");

    @Override
    public void onInitialize() {
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, path);
    }
}
