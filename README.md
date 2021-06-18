# FastParticles
[![JitPack](https://jitpack.io/v/fr.mrmicky/FastParticles.svg)](https://jitpack.io/#fr.mrmicky/FastParticles)
[![Discord](https://img.shields.io/discord/390919659874156560.svg?colorB=5865f2&label=Discord&logo=discord&logoColor=white)](https://discord.gg/q9UwaBT)

Lightweight particle API for Bukkit plugins, with 1.7.10 to 1.17 support.

**:warning: If you don't need 1.7/1.8 support, this library is not required and you should just use the Bukkit methods [`Player#spawnParticle`](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Player.html#spawnParticle-org.bukkit.Particle-double-double-double-int-) and [`World#spawnParticle`](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/World.html#spawnParticle-org.bukkit.Particle-double-double-double-int-). :warning:**

## Features

* Easy to use
* Doesn't use reflection with compatible Bukkit versions
* Support all particle data on all versions for legacy particles
* Works on 1.13 and higher servers, with and without legacy particles

### Installation

#### Maven
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.2.4</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <relocations>
                    <relocation>
                        <pattern>fr.mrmicky.fastparticles</pattern>
                        <!-- Replace with the package of your plugin ! -->
                        <shadedPattern>com.yourpackage.fastparticles</shadedPattern>
                    </relocation>
                </relocations>
            </configuration>
        </plugin>
    </plugins>
</build>

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>fr.mrmicky</groupId>
        <artifactId>FastParticles</artifactId>
        <version>2.0.0</version>
    </dependency>
</dependencies>
```

### Gradle
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'fr.mrmicky:FastParticles:2.0.0'
}
```

### Manual (not recommended)

Copy all the classes in your plugin.

## Usage

### Spawning particles

Just use a method from `FastParticle` like this:
```java
// Get a ParticleType
ParticleType flame = ParticleType.of("FLAME");
ParticleType redstone = ParticleType.of("REDSTONE");
ParticleType blockCrack = ParticleType.of("BLOCK_CRACK");

// Spawn particle for a player
flame.spawn(player, loc, 1);

// Spawn particle for all players in a world
flame.spawn(world, loc, 1);

// Spawn colored particle to a player
redstone.spawn(player, loc, 1, ParticleData.createDustOptions(Color.BLUE, 1));

// Spawn block crack particle to a player
blockCrack.spawn(player, loc, 1, ParticleData.createBlockData(Material.DIAMOND));
```

When you need to spawn a large amount of particles, you can cache instances of `ParticleType` and `ParticleData` to slightly improve performances.
