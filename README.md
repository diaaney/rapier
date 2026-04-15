# Rapier

A Paper 1.21.11 fork that recreates authentic **Minecraft 1.8.9 PvP combat** on a modern server. Post-1.8 blocks and items keep their modern behavior; anything that existed in 1.8.9 behaves exactly as it did back then.

Modern clients connect natively — no resource packs, no client mods.

## What Rapier does

### Timing and damage
- **No attack cooldown.** 1.9 `attack_speed` gating is gone. Every click deals full damage.
- **No sweep attack.** `isSweepAttack()` always returns false.
- **Cooldown bar hidden.** A permanent `ATTACK_SPEED +1020` modifier is attached to every player so the client never draws the bar.
- **Post-hit invulnerability = 20 ticks** with cumulative-only hits in the first half (authentic 1.8 window — the wiki's "10-tick immunity" is that half-window).
- **Sword damage** (wooden +1, stone +1, iron +1, gold +1, diamond +1) restored to 1.8 values. Netherite untouched.
- **Axe damage** reduced to 1.8 values: wood 3 / stone 4 / iron 5 / gold 3 / diamond 6. Netherite and copper untouched.

### Sword right-click blockhit
- Every sword carries a `BLOCKS_ATTACKS` component cloned from ViaVersion's sword translation layer (90° angle, base −0.5, factor 0.5 = ~50% damage reduction).
- Component is force-injected into the network patch so vanilla clients receive it.
- Use animation forced to `NONE` so there is no shield-raise pose.
- Movement slowed to 30% while blocking.
- If a shield is equipped in the offhand, the shield takes priority.

### Enchantments and defense
- **Sharpness** `+1.25` per level (I = +1.25, V = +6.25). Was `0.5 × level + 0.5` since 1.9.
- **Protection** EPF curve: lvl 1 / 2 / 3 / 4 = 1 / 2 / 3 / 5 (1.8 `floor((6 + lvl²) × 0.75 / 3)`). Vanilla 1.9+ is a linear 1 / 2 / 3 / 4.
- **Armor** flat 4% per point, capped at 80%. The toughness-scaled formula and the high-damage bypass added in 1.9 are gone.

### Food, regen, mobility
- **Fast saturation-based regen removed.** Only the 1.8 slow regen (food ≥ 18 → 1 HP every 80 ticks).
- **Exhaustion values** bumped to 1.8: attack 0.3 (was 0.1), jump 0.2 (was 0.05), sprint-jump 0.8 (was 0.2).
- **Sprint cancels on taking damage** (enables W-tap combat).
- **Mace smash attack disabled** (`canSmashAttack` always false — no fall-damage bonus, no fall-negation).

### Projectiles and throwables
- **Throwables ignore shooter momentum.** `Projectile.shootFromRotation` no longer adds the player's velocity. Running while throwing a pearl or potion does not speed up the projectile — matches 1.8 `EntityThrowable.setThrowableHeading`.
- **Splash potions and XP bottles** throw straight out from where you look (−20° pitch lob removed).
- **Fishing rod yoink** restored with 1.8's `sqrt(sqrt(dist)) × 0.08` vertical boost on top of the `(dx, dy, dz) × 0.1` pull.

### Out of scope (intentionally not touched)
- Netherite tools and armor
- Shields as a separate offhand item (still function as modern shields)
- Crossbow, Trident, Mace existence (Mace features are neutered but the item remains)
- Copper tools
- 1.9+ exclusive enchantments (Mending, Frost Walker, Sweeping Edge)

## Build

Requires Java 21.

```sh
./gradlew applyAllPatches
./gradlew createMojmapPaperclipJar
```

Output: `rapier-server/build/libs/rapier-paperclip-1.21.11-R0.1-SNAPSHOT-mojmap.jar`

## Run

Drop the jar into a standard Paper server layout and launch as usual:

```sh
java -Xms2G -Xmx4G -jar rapier-paperclip-*.jar --nogui
```

Connect with any 1.21.x client.

## Test environment

`test-env/` (ignored by git) contains two local servers used during development:

- `reference-1.8.9/` — Paper 1.8.8 + ViaVersion on port `:25566`. The behavior oracle for side-by-side comparison.
- `our-server/` — Rapier test target on port `:25565`. Receives the freshly-built jar each iteration.

Both are linked via junctions so they can live anywhere on disk without polluting the repo.

## Project layout

- `rapier-api/` — Paper API fork
- `rapier-server/` — Paper server fork
- `rapier-server/minecraft-patches/features/` — the source-level feature patches applied on top of upstream Paper
- `.github/workflows/build.yml` — CI that builds the paperclip jar and publishes a GitHub Release on `v*` tags

## Credits

- [PaperMC](https://papermc.io/) — upstream server
- [ViaVersion](https://viaversion.com/) — reference for the `BLOCKS_ATTACKS` sword component
- [OldCombatMechanics](https://www.spigotmc.org/resources/oldcombatmechanics.19510/) — prior art for mechanic restoration

## License

GPL-3.0 (inherited from Paper).
