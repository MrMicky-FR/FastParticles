package fr.mrmicky.fastparticle.compatibility;

import fr.mrmicky.fastparticle.ParticleEnum;
import org.bukkit.World;
import org.bukkit.entity.Player;

public abstract class AbstractParticleSender {

    public abstract void spawnParticle(Player player, ParticleEnum particle, double x, double y, double z, int count,
                                       double offsetX, double offsetY, double offsetZ, double extra, Object data);

    public abstract void spawnParticle(World world, ParticleEnum particle, double x, double y, double z, int count,
                                       double offsetX, double offsetY, double offsetZ, double extra, Object data);

    public abstract Object getParticle(ParticleEnum particle);

    public abstract boolean isValidData(Object particle, Object data);

    protected double color(double color) {
        if (color <= 0) {
            color = -1;
        }
        return color / 255;
    }
}