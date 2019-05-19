package fr.mrmicky.fastparticle;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * @author MrMicky
 */
public enum ParticleType {

    // 1.7+
    EXPLOSION_NORMAL("explode"),
    EXPLOSION_LARGE("largeexplode"),
    EXPLOSION_HUGE("hugeexplosion"),
    FIREWORKS_SPARK("fireworksSpark"),
    WATER_BUBBLE("bubble"),
    WATER_SPLASH("splash"),
    WATER_WAKE("wake"),
    SUSPENDED("suspended"),
    SUSPENDED_DEPTH("depthsuspend"),
    CRIT("crit"),
    CRIT_MAGIC("magicCrit"),
    SMOKE_NORMAL("smoke"),
    SMOKE_LARGE("largesmoke"),
    SPELL("spell"),
    SPELL_INSTANT("instantSpell"),
    SPELL_MOB("mobSpell"),
    SPELL_MOB_AMBIENT("mobSpellAmbient"),
    SPELL_WITCH("witchMagic"),
    DRIP_WATER("dripWater"),
    DRIP_LAVA("dripLava"),
    VILLAGER_ANGRY("angryVillager"),
    VILLAGER_HAPPY("happyVillager"),
    TOWN_AURA("townaura"),
    NOTE("note"),
    PORTAL("portal"),
    ENCHANTMENT_TABLE("enchantmenttable"),
    FLAME("flame"),
    LAVA("lava"),
    FOOTSTEP("footstep"),
    CLOUD("cloud"),
    REDSTONE("reddust"),
    SNOWBALL("snowballpoof"),
    SNOW_SHOVEL("snowshovel"),
    SLIME("slime"),
    HEART("heart"),
    ITEM_CRACK("iconcrack"),
    BLOCK_CRACK("blockcrack"),
    BLOCK_DUST("blockdust"),

    // 1.8+
    BARRIER("barrier", 8),
    WATER_DROP("droplet", 8),
    MOB_APPEARANCE("mobappearance", 8),
    ITEM_TAKE("take", 8),

    // 1.9+
    DRAGON_BREATH("dragonbreath", 9),
    END_ROD("endRod", 9),
    DAMAGE_INDICATOR("damageIndicator", 9),
    SWEEP_ATTACK("sweepAttack", 9),

    // 1.10+
    FALLING_DUST("fallingdust", 10),

    // 1.11+
    TOTEM("totem", 11),
    SPIT("spit", 11),

    // 1.13+
    SQUID_INK(13),
    BUBBLE_POP(13),
    CURRENT_DOWN(13),
    BUBBLE_COLUMN_UP(13),
    NAUTILUS(13),
    DOLPHIN(13),

    // 1.14+
    SNEEZE(14),
    CAMPFIRE_COSY_SMOKE(14),
    CAMPFIRE_SIGNAL_SMOKE(14),
    COMPOSTER(14),
    FLASH(14),
    FALLING_LAVA(14),
    LANDING_LAVA(14),
    FALLING_WATER(14);

    private static final int SERVER_VERSION_ID;

    static {
        String ver = FastReflection.VERSION;
        SERVER_VERSION_ID = ver.charAt(4) == '_' ? Character.getNumericValue(ver.charAt(3)) : Integer.parseInt(ver.substring(3, 5));
    }

    private final String legacyName;
    private final int minimalVersion;

    // 1.7 particles
    ParticleType(String name) {
        this(name, -1);
    }

    // 1.13+ particles
    ParticleType(int minimalVersion) {
        this(null, minimalVersion);
    }

    // 1.8-1.12 particles
    ParticleType(String legacyName, int minimalVersion) {
        this.legacyName = legacyName;
        this.minimalVersion = minimalVersion;
    }

    public boolean hasLegacyName() {
        return legacyName != null;
    }

    public String getLegacyName() {
        if (!hasLegacyName()) {
            throw new IllegalStateException("Particle " + name() + " don't have legacy name");
        }
        return legacyName;
    }

    public boolean isSupported() {
        return minimalVersion <= 0 || SERVER_VERSION_ID >= minimalVersion;
    }

    public Class<?> getDataType() {
        switch (this) {
            case ITEM_CRACK:
                return ItemStack.class;
            case BLOCK_CRACK:
            case BLOCK_DUST:
            case FALLING_DUST:
                //noinspection deprecation
                return MaterialData.class;
            case REDSTONE:
                return Color.class;
            default:
                return Void.class;
        }
    }

    public static ParticleType getParticle(String particleName) {
        try {
            return ParticleType.valueOf(particleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            for (ParticleType particle : values()) {
                if (particle.getLegacyName().equalsIgnoreCase(particleName)) {
                    return particle;
                }
            }
        }
        return null;
    }
}
