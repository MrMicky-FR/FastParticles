package fr.mrmicky.fastparticle;

import org.bukkit.Color;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

/**
 * All the supported particles types.
 * Depending on the server version some particles may not be available.
 *
 * @author MrMicky
 */
@SuppressWarnings("deprecation")
public enum ParticleType {

    // 1.7+
    EXPLOSION_NORMAL("explode", "poof"),
    EXPLOSION_LARGE("largeexplode", "explosion"),
    EXPLOSION_HUGE("hugeexplosion", "explosion_emitter"),
    FIREWORKS_SPARK("fireworksSpark", "firework"),
    WATER_BUBBLE("bubble", "bubble"),
    WATER_SPLASH("splash", "splash"),
    WATER_WAKE("wake", "fishing"),
    SUSPENDED("suspended", "underwater"),
    SUSPENDED_DEPTH("depthsuspend", "underwater"),
    CRIT("crit", "crit"),
    CRIT_MAGIC("magicCrit", "enchanted_hit"),
    SMOKE_NORMAL("smoke", "smoke"),
    SMOKE_LARGE("largesmoke", "large_smoke"),
    SPELL("spell", "effect"),
    SPELL_INSTANT("instantSpell", "instant_effect"),
    SPELL_MOB("mobSpell", "entity_effect"),
    SPELL_MOB_AMBIENT("mobSpellAmbient", "ambient_entity_effect"),
    SPELL_WITCH("witchMagic", "witch"),
    DRIP_WATER("dripWater", "dripping_water"),
    DRIP_LAVA("dripLava", "dripping_lava"),
    VILLAGER_ANGRY("angryVillager", "angry_villager"),
    VILLAGER_HAPPY("happyVillager", "happy_villager"),
    TOWN_AURA("townaura", "mycelium"),
    NOTE("note", "note"),
    PORTAL("portal", "portal"),
    ENCHANTMENT_TABLE("enchantmenttable", "enchant"),
    FLAME("flame", "flame"),
    LAVA("lava", "lava"),
    CLOUD("cloud", "cloud"),
    REDSTONE("reddust", "dust"),
    SNOWBALL("snowballpoof", "item_snowball"),
    SNOW_SHOVEL("snowshovel", "item_snowball"),
    SLIME("slime", "item_slime"),
    HEART("heart", "heart"),
    ITEM_CRACK("iconcrack", "item"),
    BLOCK_CRACK("blockcrack", "block"),
    BLOCK_DUST("blockdust", "block"),

    // 1.8+
    BARRIER("barrier", "barrier", 8),
    WATER_DROP("droplet", "rain", 8),
    MOB_APPEARANCE("mobappearance", "elder_guardian", 8),

    // 1.9+
    DRAGON_BREATH("dragonbreath", "dragon_breath", 9),
    END_ROD("endRod", "end_rod", 9),
    DAMAGE_INDICATOR("damageIndicator", "damage_indicator", 9),
    SWEEP_ATTACK("sweepAttack", "sweep_attack", 9),

    // 1.10+
    FALLING_DUST("fallingdust", "falling_dust", 10),

    // 1.11+
    TOTEM("totem", "totem_of_undying", 11),
    SPIT("spit", "spit", 11),

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
    FALLING_WATER(14),

    // 1.15+
    DRIPPING_HONEY(15),
    FALLING_HONEY(15),
    LANDING_HONEY(15),
    FALLING_NECTAR(15),

    // 1.16+
    SOUL_FIRE_FLAME(16),
    ASH(16),
    CRIMSON_SPORE(16),
    WARPED_SPORE(16),
    SOUL(16),
    DRIPPING_OBSIDIAN_TEAR(16),
    FALLING_OBSIDIAN_TEAR(16),
    LANDING_OBSIDIAN_TEAR(16),
    REVERSE_PORTAL(16),
    WHITE_ASH(16),

    // --- Removed particles ---
    /**
     * @deprecated This particle has been removed in Minecraft 1.13 and can't be spawned
     * on 1.13+ servers and will not be visible to 1.13+ players.
     */
    @Deprecated
    FOOTSTEP("footstep", null), // 1.7+
    /**
     * @deprecated This particle has been removed in Minecraft 1.13 and can't be spawned
     * on 1.13+ servers and will not be visible to 1.13+ players.
     */
    @Deprecated
    ITEM_TAKE("take", null, 8); // 1.8+

    private static final int SERVER_VERSION_ID;

    static {
        String ver = FastReflection.VERSION;
        SERVER_VERSION_ID = ver.charAt(4) == '_' ? Character.getNumericValue(ver.charAt(3)) : Integer.parseInt(ver.substring(3, 5));
    }

    private final String legacyName;
    private final String name;
    private final int minimumVersion;

    // 1.7 particles
    ParticleType(String legacyName, String name) {
        this(legacyName, name, -1);
    }

    // 1.13+ particles
    ParticleType(int minimumVersion) {
        this.legacyName = null;
        this.name = name().toLowerCase();
        this.minimumVersion = minimumVersion;
    }

    // 1.8-1.12 particles
    ParticleType(String legacyName, String name, int minimumVersion) {
        this.legacyName = legacyName;
        this.name = name;
        this.minimumVersion = minimumVersion;
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

    public String getName() {
        return name != null ? name : legacyName;
    }

    public boolean isSupported() {
        // Legacy particles
        if (minimumVersion <= 0) {
            return name != null || SERVER_VERSION_ID < 13;
        }

        return SERVER_VERSION_ID >= minimumVersion;
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

    public static ParticleType getParticle(String particleName) {
        try {
            return ParticleType.valueOf(particleName.toUpperCase());
        } catch (IllegalArgumentException e) {
            for (ParticleType particle : values()) {
                if (particle.getName().equalsIgnoreCase(particleName)) {
                    return particle;
                }

                if (particle.hasLegacyName() && particle.getLegacyName().equalsIgnoreCase(particleName)) {
                    return particle;
                }
            }
        }
        return null;
    }
}
