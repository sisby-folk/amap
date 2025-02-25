package cc.abbie.amap.client.minimap.config;

import net.minecraft.network.chat.Component;

import folk.sisby.kaleido.lib.quiltconfig.api.values.TrackedValue;

public class EnumConfigButton<E extends Enum<E>> extends ConfigButton {
    private final TrackedValue<E> trackedValue;
    private final E[] values;
    
    public EnumConfigButton(Component message, TrackedValue<E> trackedValue, E[] values) {
        super(message);
        
        this.trackedValue = trackedValue;
        this.values = values;
    }

    @Override
    protected Component getText() {
        return Component.literal(trackedValue.value().name());
    }

    @Override
    public void onPress() {
        E oldValue = trackedValue.value();
        int ord = oldValue.ordinal();
        E newValue = values[(ord + 1) % values.length];
        trackedValue.setValue(newValue);
    }
}
