package cc.abbie.amap;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMap implements ModInitializer {

    public static final String MODID = "amap";
    public static String MOD_NAME;
    public static String MOD_VERSION;
    public static Logger LOGGER;

    @Override
    public void onInitialize() {
        ModContainer mod = FabricLoader.getInstance().getModContainer(MODID).orElseThrow();
        ModMetadata metadata = mod.getMetadata();
        MOD_VERSION = metadata.getVersion().getFriendlyString();
        MOD_NAME = metadata.getName();
        LOGGER = LoggerFactory.getLogger(MOD_NAME);
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
