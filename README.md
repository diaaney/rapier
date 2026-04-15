<p align=center>
  <img src="assets/icon@64.png"/>
</p>

<h1 align=center>Rapier</h1>
<p align=center>Paper 1.21.11 fork that restores authentic 1.8.9 PvP combat on modern Minecraft. No plugins, no client mods.</p>

## Why?

From 1.9 onward Minecraft combat drifted further and further from its PvP roots: attack cooldowns, sweep attacks, shields replacing sword blocking, softer armor curves. Competitive servers still run ancient Paper 1.8.8 + ViaVersion just to keep the 1.8.9 feel alive — at the cost of an end-of-life server with no chunk updates, no new blocks, no modern client support.

Rapier brings 1.8.9 combat to a current Paper. One jar, no plugins, any 1.21.x client connects natively. Mechanics that existed in 1.8 are surgically restored to their 1.8 form. Content that didn't exist in 1.8 — netherite, crossbow, trident, shields as a real offhand item — is left exactly as vanilla 1.21.11 ships it.

## 📝 To Do

### Timing and damage
- [X] Remove attack cooldown
- [X] Disable sweep attack
- [X] Hide the cooldown bar (ATTACK_SPEED synced via modifier so vanilla clients see it)
- [X] Sword damage restored to 1.8 values
- [X] Axe damage restored to 1.8 values
- [X] `INVULNERABLE_DURATION = 20` with 1.8 cumulative-only first half
- [X] Remove 1.9+ damage-indicator particles and crit star animations

### Blockhit
- [X] Sword right-click 50% damage reduction via `BLOCKS_ATTACKS` component
- [X] Network patch forces the component into the item packet so vanilla clients see it
- [X] Use animation set to `NONE` (no shield-raise pose on swords)
- [X] Movement slowed to 30% while blocking
- [X] Force-update `useItem` so switching swords never leaves a stale blocking state
- [X] Shield in offhand takes priority over sword blockhit

### Enchantments and defense
- [X] Sharpness `+1.25` per level (V = +6.25)
- [X] Protection EPF curve (lvl 1 / 2 / 3 / 4 = 1 / 2 / 3 / 5)
- [X] Armor flat 4% per point, no toughness bypass

### Food, regen, mobility
- [X] Disable saturation-based fast regen (only 1.8 slow regen remains)
- [X] 1.8 exhaustion values (attack 0.3, jump 0.2, sprint-jump 0.8)
- [X] Taking damage cancels sprint (enables W-tap)

### Projectiles
- [X] Fishing rod yoink with 1.8's vertical boost
- [X] Throwables ignore shooter momentum (running doesn't boost pearls/potions)
- [X] Splash potions and XP bottles use player pitch directly (no upward lob)

### Post-1.8 items
- [X] Mace smash attack and fall-damage negation disabled
- [ ] Optional per-world toggle for 1.8 vs modern combat
- [ ] Configurable knockback curve
- [ ] Public Bukkit API for the sword-block component
- [ ] CI regression tests that assert combat values

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
