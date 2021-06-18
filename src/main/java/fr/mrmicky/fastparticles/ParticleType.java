package fr.mrmicky.fastparticles;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public interface ParticleType {
    /**
     * Attempts to get the ParticleType with the given name. The name must be a valid name from {@link org.bukkit.Particle} enum.
     *
     * @param name Name of the particle to get
     * @return the particle type
     * @throws IllegalArgumentException if the particle type doesn't exists or isn't supported
     */
    static ParticleType of(String name) {
        return ParticleTypes.of(name);
    }

    /**
     * Returns the name of this particle.
     *
     * @return the name
     */
    String getName();

    /**
     * Returns the required Bukkit data type for the particle.
     *
     * @return the required data type
     */
    Class<?> getRawDataType();

    /**
     * Returns the required FastParticles data type for the particle.
     *
     * @return the required data type
     */
    default Class<?> getDataType() {
        Class<?> type = getRawDataType();
        return ParticleTypes.DATA_ADAPTERS.getOrDefault(type, type);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param world    the world to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     */
    default void spawn(World world, Location location, int count) {
        spawn(world, location.getX(), location.getY(), location.getZ(), count);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param world the world to spawn particle to
     * @param x     the position on the x axis to spawn at
     * @param y     the position on the y axis to spawn at
     * @param z     the position on the z axis to spawn at
     * @param count the number of particles
     */
    default void spawn(World world, double x, double y, double z, int count) {
        spawn(world, x, y, z, count, null);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T>      type of particle data (see {@link ParticleType#getDataType()}
     * @param world    the world to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(World world, Location location, int count, T data) {
        spawn(world, location.getX(), location.getY(), location.getZ(), count, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T>   type of particle data (see {@link ParticleType#getDataType()}
     * @param world the world to spawn particle to
     * @param x     the position on the x axis to spawn at
     * @param y     the position on the y axis to spawn at
     * @param z     the position on the z axis to spawn at
     * @param count the number of particles
     * @param data  the data to use for the particle or null,
     *              the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(World world, double x, double y, double z, int count, T data) {
        spawn(world, x, y, z, count, 0, 0, 0, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param world    the world to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     */
    default void spawn(World world, Location location, int count, double offsetX, double offsetY, double offsetZ) {
        spawn(world, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param world   the world to spawn particle to
     * @param x       the position on the x axis to spawn at
     * @param y       the position on the y axis to spawn at
     * @param z       the position on the z axis to spawn at
     * @param count   the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     */
    default void spawn(World world, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        spawn(world, x, y, z, count, offsetX, offsetY, offsetZ, null);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>      type of particle data (see {@link ParticleType#getDataType()}
     * @param world    the world to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(World world, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {
        spawn(world, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>     type of particle data (see {@link ParticleType#getDataType()}
     * @param world   the world to spawn particle to
     * @param x       the position on the x axis to spawn at
     * @param y       the position on the y axis to spawn at
     * @param z       the position on the z axis to spawn at
     * @param count   the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param data    the data to use for the particle or null,
     *                the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(World world, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {
        spawn(world, x, y, z, count, offsetX, offsetY, offsetZ, 1, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param world    the world to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     */
    default void spawn(World world, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        spawn(world, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param world   the world to spawn particle to
     * @param x       the position on the x axis to spawn at
     * @param y       the position on the y axis to spawn at
     * @param z       the position on the z axis to spawn at
     * @param count   the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param extra   the extra data for this particle, depends on the
     *                particle used (normally speed)
     */
    default void spawn(World world, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        spawn(world, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>      type of particle data (see {@link ParticleType#getDataType()}
     * @param world    the world to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(World world, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        spawn(world, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>     type of particle data (see {@link ParticleType#getDataType()}
     * @param world   the world to spawn particle to
     * @param x       the position on the x axis to spawn at
     * @param y       the position on the y axis to spawn at
     * @param z       the position on the z axis to spawn at
     * @param count   the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param extra   the extra data for this particle, depends on the
     *                particle used (normally speed)
     * @param data    the data to use for the particle or null,
     *                the type of this depends on {@link ParticleType#getDataType()}
     */
    <T> void spawn(World world, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data);

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param player   the player to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     */
    default void spawn(Player player, Location location, int count) {
        spawn(player, location.getX(), location.getY(), location.getZ(), count);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param player the player to spawn particle to
     * @param x      the position on the x axis to spawn at
     * @param y      the position on the y axis to spawn at
     * @param z      the position on the z axis to spawn at
     * @param count  the number of particles
     */
    default void spawn(Player player, double x, double y, double z, int count) {
        spawn(player, x, y, z, count, null);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T>      type of particle data (see {@link ParticleType#getDataType()}
     * @param player   the player to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(Player player, Location location, int count, T data) {
        spawn(player, location.getX(), location.getY(), location.getZ(), count, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T>    type of particle data (see {@link ParticleType#getDataType()}
     * @param player the player to spawn particle to
     * @param x      the position on the x axis to spawn at
     * @param y      the position on the y axis to spawn at
     * @param z      the position on the z axis to spawn at
     * @param count  the number of particles
     * @param data   the data to use for the particle or null,
     *               the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(Player player, double x, double y, double z, int count, T data) {
        spawn(player, x, y, z, count, 0, 0, 0, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param player   the player to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     */
    default void spawn(Player player, Location location, int count, double offsetX, double offsetY, double offsetZ) {
        spawn(player, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param player  the player to spawn particle to
     * @param x       the position on the x axis to spawn at
     * @param y       the position on the y axis to spawn at
     * @param z       the position on the z axis to spawn at
     * @param count   the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     */
    default void spawn(Player player, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        spawn(player, x, y, z, count, offsetX, offsetY, offsetZ, null);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>      type of particle data (see {@link ParticleType#getDataType()}
     * @param player   the player to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(Player player, Location location, int count, double offsetX, double offsetY, double offsetZ, T data) {
        spawn(player, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>     type of particle data (see {@link ParticleType#getDataType()}
     * @param player  the player to spawn particle to
     * @param x       the position on the x axis to spawn at
     * @param y       the position on the y axis to spawn at
     * @param z       the position on the z axis to spawn at
     * @param count   the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param data    the data to use for the particle or null,
     *                the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(Player player, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, T data) {
        spawn(player, x, y, z, count, offsetX, offsetY, offsetZ, 1, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param player   the player to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     */
    default void spawn(Player player, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        spawn(player, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param player  the player to spawn particle to
     * @param x       the position on the x axis to spawn at
     * @param y       the position on the y axis to spawn at
     * @param z       the position on the z axis to spawn at
     * @param count   the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param extra   the extra data for this particle, depends on the
     *                particle used (normally speed)
     */
    default void spawn(Player player, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        spawn(player, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>      type of particle data (see {@link ParticleType#getDataType()}
     * @param player   the player to spawn particle to
     * @param location the location to spawn at
     * @param count    the number of particles
     * @param offsetX  the maximum random offset on the X axis
     * @param offsetY  the maximum random offset on the Y axis
     * @param offsetZ  the maximum random offset on the Z axis
     * @param extra    the extra data for this particle, depends on the
     *                 particle used (normally speed)
     * @param data     the data to use for the particle or null,
     *                 the type of this depends on {@link ParticleType#getDataType()}
     */
    default <T> void spawn(Player player, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, T data) {
        spawn(player, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);
    }

    /**
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T>     type of particle data (see {@link ParticleType#getDataType()}
     * @param player  the player to spawn particle to
     * @param x       the position on the x axis to spawn at
     * @param y       the position on the y axis to spawn at
     * @param z       the position on the z axis to spawn at
     * @param count   the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param extra   the extra data for this particle, depends on the
     *                particle used (normally speed)
     * @param data    the data to use for the particle or null,
     *                the type of this depends on {@link ParticleType#getDataType()}
     */
    <T> void spawn(Player player, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, T data);
}
