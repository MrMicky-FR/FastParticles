package fr.mrmicky.fastparticle.compatibility;

import fr.mrmicky.fastparticle.ParticleType;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Particle sender without NMS for 1.13 servers
 *
 * @author MrMicky
 */
public class ParticleSender1_13 extends ParticleSender {

    @Override
    public void spawnParticle(Object receiver, ParticleType particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, Object data) {
        Particle bukkitParticle = Particle.valueOf(particle.toString());

        if (bukkitParticle.getDataType() == DustOptions.class && data instanceof Color) {
            data = new DustOptions((Color) data, 1);
        }

        if (receiver instanceof World) {
            ((World) receiver).spawnParticle(bukkitParticle, x, y, z, count, offsetX, offsetY, offsetZ, extra, data);
        } else if (receiver instanceof Player) {
            ((Player) receiver).spawnParticle(bukkitParticle, x, y, z, count, offsetX, offsetY, offsetZ, extra, data);
        }
    }

    @Override
    public boolean isValidDataBukkit(Particle particle, Object data) {
        if (particle.getDataType() == DustOptions.class && data instanceof Color) {
            return true;
        }

        return super.isValidDataBukkit(particle, data);
    }
}
