# FastParticles
[![JitPack](https://jitpack.io/v/fr.mrmicky/FastParticles.svg)](https://jitpack.io/#fr.mrmicky/FastParticles)
[![Discord](https://img.shields.io/discord/390919659874156560.svg?colorB=7289da&label=discord&logo=discord&logoColor=white)](https://discord.gg/q9UwaBT)

Simple Bukkit Particles API with 1.7 to 1.15 support !

## Features

* Easy to use
* Don't use reflection with compatible Bukkit versions
* Support all particle data on all versions
* Works on 1.13+ with and without legacy particles

## How to use

### Add FastParticles in your plugin
**Maven**
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
            <version>1.2.3</version>
            <scope>compile</scope>
        </dependency>
    </dependencies>
```

**Manual**

Just copy all the class in your plugin

### Spawn some particles
Just use a method from `FastParticle`

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
* Deploy to an other maven repo
* Cache particles
* Color data