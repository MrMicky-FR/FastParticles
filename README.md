# FastParticles
[![JitPack](https://jitpack.io/v/fr.mrmicky/FastParticles.svg)](https://jitpack.io/#fr.mrmicky/FastParticles)
[![Discord](https://img.shields.io/discord/390919659874156560.svg?colorB=7289da&label=discord&logo=discord&logoColor=white)](https://discord.gg/q9UwaBT)

Simple Bukkit Particles API with 1.7.10 to 1.16 support.

**:warning: If you don't need 1.7/1.8 support, this library is not required and you should just use the Bukkit methods: [`Player#spawnParticle`](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/entity/Player.html#spawnParticle-org.bukkit.Particle-double-double-double-int-) and [`World#spawnParticle`](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/World.html#spawnParticle-org.bukkit.Particle-double-double-double-int-). :warning:**

## Features

* Easy to use
* Don't use reflection with compatible Bukkit versions
* Support all particle data on all versions
* Works on 1.13+ with and without legacy particles

## How to use

### Add FastParticles in your plugin
#### Maven
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>3.2.1</version>
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
                        <pattern>fr.mrmicky.fastparticle</pattern>
                        <!-- Replace with the package of your plugin ! -->
                        <shadedPattern>com.yourpackage.fastparticles</shadedPattern>
                    </relocation>
                </relocations>
            </configuration>
        </plugin>
    </plugins>
</build>
```
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
```xml
<dependencies>
    <dependency>
        <groupId>fr.mrmicky</groupId>
        <artifactId>FastParticles</artifactId>
        <version>1.2.4</version>
        <scope>compile</scope>
    </dependency>
</dependencies>
```

#### Gradle
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
```
```groovy
dependencies {
    compile 'fr.mrmicky:FastParticles:1.2.4'
}
```

#### Manual

Just copy all the classes in your plugin.

### Spawn some particles
Just use a method from `FastParticle`:
```java
// Spawn particle to a player
FastParticle.spawnParticle(player, ParticleType.FLAME, loc, 1);

// Spawn particle to all players in a world
FastParticle.spawnParticle(world, ParticleType.FLAME, loc, 1);

// Spawn colored particle to a player
FastParticle.spawnParticle(player, ParticleType.REDSTONE, loc, 1, Color.GREEN);

// See if a ParticleType is compatible with the server version
ParticleType.DOLPHIN.isSupported(); // Return true only on 1.13+ for this ParticleType
```

## TODO
* Add JavaDoc
* Deploy to another maven repo
