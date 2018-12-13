package fr.mrmicky.fastparticle.compatibility;

import fr.mrmicky.fastparticle.ParticleType;

public abstract class AbstractParticleSender {

    public abstract void spawnParticle(Object receiver, ParticleType particle, double x, double y, double z, int count,
                                       double offsetX, double offsetY, double offsetZ, double extra, Object data);

    public abstract Object getParticle(ParticleType particle);

    public abstract boolean isValidData(Object particle, Object data);

    protected double color(double color) {
        return color / 255.0;
    }
}