package fr.mrmicky.fastparticles;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("deprecation")
final class ParticleTypes {

    static final Class<?> BLOCK_DATA_CLASS = FastReflection.optionalClass("org.bukkit.block.data.BlockData").orElse(null);
    static final Class<?> DUST_OPTIONS_CLASS = FastReflection.optionalClass("org.bukkit.Particle$DustOptions").orElse(null);
    static final Class<?> DUST_TRANSITION_CLASS = FastReflection.optionalClass("org.bukkit.Particle$DustTransition").orElse(null);
    static final Map<Class<?>, Class<?>> DATA_ADAPTERS = new HashMap<>();
    static final boolean LEGACY = !FastReflection.optionalClass("org.bukkit.Particle").isPresent();
    static final boolean MODERN = BLOCK_DATA_CLASS != null && Particle.FALLING_DUST.getDataType() == BLOCK_DATA_CLASS;
    static final ParticleData.DustOptions DEFAULT_DUST_OPTIONS = ParticleData.createDustOptions(Color.RED, 1);

    static {
        DATA_ADAPTERS.put(MaterialData.class, ParticleData.BlockData.class);
        DATA_ADAPTERS.put(Color.class, ParticleData.DustOptions.class);
        if (BLOCK_DATA_CLASS != null) {
            DATA_ADAPTERS.put(BLOCK_DATA_CLASS, ParticleData.BlockData.class);
        }
        if (DUST_OPTIONS_CLASS != null) {
            DATA_ADAPTERS.put(DUST_OPTIONS_CLASS, ParticleData.DustOptions.class);
        }
        if (DUST_TRANSITION_CLASS != null) {
            DATA_ADAPTERS.put(DUST_TRANSITION_CLASS, ParticleData.DustTransition.class);
        }
    }

    private ParticleTypes() {
        throw new UnsupportedOperationException();
    }

    static ParticleType of(String name) {
        Objects.requireNonNull(name, "name");

        try {
            if (LEGACY) {
                return LegacyParticleType.of(name.toUpperCase(Locale.ROOT));
            }

            return new DefaultParticleType(Particle.valueOf(name.toUpperCase(Locale.ROOT)));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid or unsupported particle type: " + name);
        }
    }

    static double color(double color) {
        return color / 255.0;
    }

    static final class DefaultParticleType implements ParticleType {

        private final Particle particle;

        public DefaultParticleType(Particle particle) {
            this.particle = Objects.requireNonNull(particle, "particle");
        }

        @Override
        public String getName() {
            return this.particle.name();
        }

        @Override
        public Class<?> getRawDataType() {
            Class<?> type = this.particle.getDataType();

            // Return color for redstone on legacy servers
            if (type == Void.class && !MODERN && this.particle == Particle.REDSTONE) {
                return Color.class;
            }

            return type;
        }

        @Override
        public <T> void spawn(World world, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
            Object newData = mapData(data);

            if (newData instanceof Color) {
                Color color = (Color) newData;
                count = 0;
                offsetX = color(color.getRed());
                offsetY = color(color.getGreen());
                offsetZ = color(color.getBlue());
                extra = 1.0;
                newData = null;
            }

            world.spawnParticle(this.particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, newData);
        }

        @Override
        public <T> void spawn(Player player, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
            Object newData = mapData(data);

            if (newData instanceof Color) {
                Color color = (Color) newData;
                count = 0;
                offsetX = color(color.getRed());
                offsetY = color(color.getGreen());
                offsetZ = color(color.getBlue());
                extra = 1.0;
                newData = null;
            }

            player.spawnParticle(this.particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, newData);
        }

        private Object mapData(Object data) {
            Class<?> dataType = this.particle.getDataType();

            if (data instanceof ParticleData.AbstractParticleData) {
                data = ((ParticleData.AbstractParticleData) data).data;
            }

            if (dataType == Void.class) {
                // Map color to dust options for redstone on legacy servers
                if (!MODERN && data instanceof Color && this.particle == Particle.REDSTONE) {
                    return data;
                }

                return null;
            }

            if (dataType == DUST_OPTIONS_CLASS) {
                if (data instanceof Color) {
                    return new Particle.DustOptions((Color) data, 1);
                }

                if (data == null) {
                    return DEFAULT_DUST_OPTIONS.data;
                }
            }

            if (dataType == BLOCK_DATA_CLASS && data instanceof MaterialData) {
                return Bukkit.createBlockData(((MaterialData) data).getItemType());
            }

            return data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof DefaultParticleType)) {
                return false;
            }
            DefaultParticleType particleType = (DefaultParticleType) o;
            return this.particle == particleType.particle;
        }

        @Override
        public int hashCode() {
            return this.particle.hashCode();
        }

        @Override
        public String toString() {
            return "DefaultParticleType{particle=" + this.particle + '}';
        }
    }
}
