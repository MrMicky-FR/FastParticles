package fr.mrmicky.fastparticle;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * @author MrMicky
 */
@SuppressWarnings("deprecation")
public enum ParticleEnum {

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
    ITEM_CRACK("iconcrack_"),
    BLOCK_CRACK("blockcrack_"),
    BLOCK_DUST("blockdust_"),

    // 1.8+
    BARRIER("barrier", 8),
    WATER_DROP("droplet", 8),
    MOB_APPEARANCE("mobappearance", 8),
    ITEM_TAKE("take", 8),

    // 1.9+
    DRAGON_BREATH(9),
    END_ROD(9),
    DAMAGE_INDICATOR(9),
    SWEEP_ATTACK(9),

    // 1.10+
    FALLING_DUST(10),

    // 1.11+
    TOTEM(11),
    SPIT(11),

    // 1.13+
    SQUID_INK(13),
    BUBBLE_POP(13),
    CURRENT_DOWN(13),
    BUBBLE_COLUMN_UP(13),
    NAUTILUS(13),
    DOLPHIN(13);

    private static final int SERVER_VERSION_ID;

    static {
        String ver = FastParticle.SERVER_VERSION;
        SERVER_VERSION_ID = Integer.parseInt(ver.charAt(4) == '_' ? Character.toString(ver.charAt(3)) : ver.substring(3, 5));
    }

    private String legacyName;
    private int minimalVersion;

    ParticleEnum(String legacyName) {
        this(legacyName, -1);
    }

    ParticleEnum(int minimalVersion) {
        this(null, minimalVersion);
    }

    ParticleEnum(String legacyName, int minimalVersion) {
        this.legacyName = legacyName;
        this.minimalVersion = minimalVersion;
    }

    public ParticleEnum valueOf() {
        System.out.println();
        return null;
    }

    public String getLegacyName() {
        return legacyName;
    }

    public boolean isCompatibleWithServerVersion() {
        return minimalVersion > 0 || SERVER_VERSION_ID >= minimalVersion;
    }

    public Class<?> getDataType() {
        switch (this) {
            case ITEM_CRACK:
                return ItemStack.class;
            case BLOCK_CRACK:
            case BLOCK_DUST:
            case FALLING_DUST:
                return MaterialData.class;
            case REDSTONE:
                return Color.class;
            default:
                return Void.class;
        }
    }

    public static ParticleEnum getParticle(String particleName) {
        try {
            return ParticleEnum.valueOf(particleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
