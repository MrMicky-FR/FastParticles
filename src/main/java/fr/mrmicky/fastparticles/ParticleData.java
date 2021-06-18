package fr.mrmicky.fastparticles;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

import java.util.Objects;

@SuppressWarnings("deprecation")
public interface ParticleData {

    static ParticleData of(ItemStack item) {
        return new AbstractParticleData(Objects.requireNonNull(item, "item"));
    }

    static BlockData createBlockData(Material material) {
        return createBlockData(material, (byte) 0);
    }

    static BlockData createBlockData(Material material, byte legacyData) {
        Objects.requireNonNull(material, "material");
        if (!ParticleTypes.MODERN) {
            return new BlockData(new MaterialData(material, legacyData));
        }

        return new BlockData(Bukkit.createBlockData(material));
    }

    static DustOptions createDustOptions(Color color, float size) {
        Objects.requireNonNull(color, "color");
        if (ParticleTypes.DUST_OPTIONS_CLASS == null) {
            return new DustOptions(color);
        }

        return new DustOptions(new Particle.DustOptions(color, size));
    }

    static DustTransition createDustTransition(Color fromColor, Color toColor, float size) {
        Objects.requireNonNull(fromColor, "fromColor");
        Objects.requireNonNull(toColor, "toColor");
        if (ParticleTypes.DUST_TRANSITION_CLASS == null) {
            return DustTransition.EMPTY;
        }

        return new DustTransition(new Particle.DustTransition(fromColor, toColor, size));
    }

    class AbstractParticleData implements ParticleData {
        final Object data;

        private AbstractParticleData(Object data) {
            this.data = data;
        }
    }

    class BlockData extends AbstractParticleData {
        private BlockData(Object data) {
            super(data);
        }
    }

    class DustOptions extends AbstractParticleData {
        private DustOptions(Object data) {
            super(data);
        }
    }

    class DustTransition extends DustOptions {
        private static final DustTransition EMPTY = new DustTransition(null);

        private DustTransition(Object data) {
            super(data);
        }
    }
}
