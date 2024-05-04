package fr.mrmicky.fastparticles;

import org.bukkit.Bukkit;

import java.util.Optional;

/**
 * Small reflection class to use CraftBukkit and NMS
 *
 * @author MrMicky
 */
public final class FastReflection {

    private static final String OBC_PACKAGE_BASE = "org.bukkit.craftbukkit";
    private static final String NMS_PACKAGE_BASE = "net.minecraft.server";

    private static final String OBC_PACKAGE = Bukkit.getServer().getClass().getPackage().getName();
    private static final String NMS_PACKAGE = OBC_PACKAGE.replace(OBC_PACKAGE_BASE, NMS_PACKAGE_BASE);

    private FastReflection() {
        throw new UnsupportedOperationException();
    }

    public static String nmsClassName(String className) {
        return NMS_PACKAGE + '.' + className;
    }

    public static Class<?> nmsClass(String className) throws ClassNotFoundException {
        return Class.forName(nmsClassName(className));
    }

    public static Optional<Class<?>> nmsOptionalClass(String className) {
        return optionalClass(nmsClassName(className));
    }

    public static String obcClassName(String className) {
        return OBC_PACKAGE + '.' + className;
    }

    public static Class<?> obcClass(String className) throws ClassNotFoundException {
        return Class.forName(obcClassName(className));
    }

    public static Optional<Class<?>> obcOptionalClass(String className) {
        return optionalClass(obcClassName(className));
    }

    public static Optional<Class<?>> optionalClass(String className) {
        try {
            return Optional.of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    public static Object enumValueOf(Class<?> enumClass, String enumName) {
        return Enum.valueOf(enumClass.asSubclass(Enum.class), enumName);
    }
}
