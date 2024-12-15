package cc.abbie.amap;

public class GuiUtil {
    public static int abgrToArgb(int abgr) {
        int a = (abgr >> 24) & 0xff;
        int b = (abgr >> 16) & 0xff;
        int g = (abgr >> 8) & 0xff;
        int r = abgr & 0xff;
        return a << 24 | r << 16 | g << 8 | b;
    }

}
