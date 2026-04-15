<p align=center>
  <img src="assets/icon@64.png"/>
</p>

<h1 align=center>Rapier</h1>
<p align=center>Paper 1.21.11 fork that restores authentic 1.8.9 PvP combat on modern Minecraft servers.</p>

## Why?

From 1.9 onward Minecraft combat drifted further and further from its PvP roots: attack cooldowns, sweep attacks, shields replacing sword blocking, softer armor curves. Competitive servers still run old 1.8 spigot just to keep the old versions feel alive at the cost of a server with no chunk updates, no new blocks, no modern client support.

Rapier brings 1.8 combat to a current Paper. One jar, no plugins, any 1.21.x client connects natively. Mechanics that existed in 1.8 are restored to their 1.8 form. Content that didn't exist in 1.8 like netherite, crossbow, trident, shields as a real offhand item is left exactly as vanilla 1.21.11 ships it (might change).

## ⚙️ Usage

### 📥 Download

Grab the latest jar from the [Releases page](https://github.com/diaaney/rapier/releases).

### 🛠️ Build from source

Requires JDK 21 (Temurin recommended).

1. Clone this repository.
2. `./gradlew applyAllPatches` — fetches Paper and applies rapier's patches.
3. `./gradlew createMojmapPaperclipJar` — produces the paperclip jar at `rapier-server/build/libs/rapier-paperclip-1.21.11-R0.1-SNAPSHOT-mojmap.jar`.

### ▶️ Run

Drop the jar into any Paper-compatible server directory and launch as usual:

```sh
java -Xms2G -Xmx4G -jar rapier-paperclip-*.jar --nogui
```

Connect with any 1.21.x client.

### 🎛️ Configure

On first launch rapier writes `rapier.properties` in the server working directory. Edit and restart to tune:

```properties
# Multiplier applied to every knockback magnitude (1.0 = authentic 1.8.9).
# Lower for softer knockback, raise for stronger combos.
knockback-scale=1.0

# Horizontal velocity damping applied to the attacker on each sprint/KB-enchant
# hit. This is what makes faster clicking feel like you take less knockback in
# hit trades — each qualifying hit stacks this factor on your own velocity.
# 0.6 = authentic 1.8.9. Lower for stickier trades, higher for floatier combos.
attack-momentum-scale=0.6
```

More options will land here as tuning knobs are added.

## 🤝 Contributing

Contributions, issues and feature requests are welcome.
Open an issue or PR at [github.com/diaaney/rapier](https://github.com/diaaney/rapier).

## ❤️ Show your support

Give a ⭐️ if this project helped you!

## 📝 License

Copyright © 2026 [diaaney](https://github.com/diaaney).
Licensed under [GPL-3.0](LICENSE), inherited from [PaperMC](https://papermc.io/).
