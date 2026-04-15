<p align=center>
  <img src="assets/icon@64.png"/>
</p>

<h1 align=center>Rapier</h1>
<p align=center>Paper 1.21.11 fork that restores authentic 1.8.9 PvP combat on modern Minecraft. No plugins, no client mods.</p>

## Why?

From 1.9 onward Minecraft combat drifted further and further from its PvP roots: attack cooldowns, sweep attacks, shields replacing sword blocking, softer armor curves. Competitive servers still run ancient Paper 1.8.8 + ViaVersion just to keep the 1.8.9 feel alive — at the cost of an end-of-life server with no chunk updates, no new blocks, no modern client support.

Rapier brings 1.8.9 combat to a current Paper. One jar, no plugins, any 1.21.x client connects natively. Mechanics that existed in 1.8 are surgically restored to their 1.8 form. Content that didn't exist in 1.8 — netherite, crossbow, trident, shields as a real offhand item — is left exactly as vanilla 1.21.11 ships it.

## ⚙️ Development

1. Clone this repository.
2. Install JDK 21 (Temurin recommended).

### 🧪 Apply patches and iterate

3. Run `./gradlew applyAllPatches` to fetch Paper and materialize the patched source tree under `rapier-server/src/minecraft/java/`.
4. Edit files directly. The source tree is an inner git repo — commit your edits there with a message prefixed `rapier - ...`.
5. Run `./gradlew rebuildMinecraftFeaturePatches` to regenerate the `.patch` files under `rapier-server/minecraft-patches/features/` from your commits.

### 📦 Build the server jar

6. Run `./gradlew createMojmapPaperclipJar`.
7. The jar lands at `rapier-server/build/libs/rapier-paperclip-1.21.11-R0.1-SNAPSHOT-mojmap.jar`. Drop it into any Paper-compatible server directory and launch.

### 🧩 Side-by-side testing

`test-env/` contains two local servers (gitignored):

- `reference-1.8.9/` — Paper 1.8.8 + ViaVersion on `:25566`, the behavior oracle.
- `our-server/` — Rapier on `:25565`, receives the freshly-built jar each iteration.

## 🤝 Contributing

Contributions, issues and feature requests are welcome.
Open an issue or PR at [github.com/diaaney/rapier](https://github.com/diaaney/rapier).

## ❤️ Show your support

Give a ⭐️ if this project helped you!

## 📝 License

Copyright © 2026 [diaaney](https://github.com/diaaney).
Licensed under [GPL-3.0](LICENSE), inherited from [PaperMC](https://papermc.io/).
