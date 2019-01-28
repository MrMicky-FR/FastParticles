package fr.mrmicky.fastparticle.compatibility;

import fr.mrmicky.fastparticle.ParticleType;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.material.MaterialData;

/**
 * Particle sender for 1.13 servers (add support for DustOptions and BlockData)
 *
 * @author MrMicky
 */
@SuppressWarnings("deprecation")
public class ParticleSender1_13 extends ParticleSender {

    @Override
    public void spawnParticle(Object receiver, ParticleType particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, Object data) {
        Particle bukkitParticle = Particle.valueOf(particle.toString());

        if (bukkitParticle.getDataType() == Particle.DustOptions.class && data instanceof Color) {
            data = new Particle.DustOptions((Color) data, 1);
        } else if (bukkitParticle.getDataType() == BlockData.class && data instanceof MaterialData) {
            data = Bukkit.createBlockData(((MaterialData) data).getItemType());
        }

        super.spawnParticle(receiver, particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, data);
    }

    @Override
    public boolean isValidDataBukkit(Particle particle, Object data) {
        if (particle.getDataType() == Particle.DustOptions.class && data instanceof Color) {
            return true;
        }

        if (particle.getDataType() == BlockData.class && data instanceof MaterialData) {
            return true;
        }

        return super.isValidDataBukkit(particle, data);
    }
}
