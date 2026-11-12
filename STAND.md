# Stand – MorniCraft

**Was es ist:** Paper/Purpur-Plugin für Freunde-Server (Spawn, Zuhause, Teleports mit Wartezeit, Nachrichten, Start-Elytra, Spawn-Schutz, Leiste, Bedrock-Menüs). Auch in MorniHosting eingebaut (`plugins/mornicraft/`, Panel schreibt `config.yml` aus dem Dashboard).

## Aufbau

- `src/main/java/de/mornihosting/mornicraft/`: `MorniCraft` (Start), `Settings` (config.yml), `Texts` (de/en), `Ui` (Karten, zentriert nach Minecraft-Zeichenbreiten, Knöpfe; Bedrock ohne Zentrierung), `Teleporter` (Wartezeit mit Bossbar, /back), `SpawnArea`, `SpawnGuard` (Schutz, Schlafen, PvP), `SpawnElytra`, `Social` (tpa, msg, mail, ignore, afk), `Commands`, `Welcome` (einmal pro Spieler), `Sidebar`, `WorldRules` (TNT, Creeper, Feuer, Endermen), `Bedrock` + `FloodgateMenus` (nur mit Floodgate geladen), `UpdateCheck` (GitHub-Release), `DefaultCommands` (eingebaute /info-Liste), `PlayerStore` (players/<uuid>.yml, übernimmt EssentialsX-Homes).
- `datapack/MorniCraft-Spawn/`: Spawn-Insel, erzeugt aus MorniHosting `apps/server/src/craftattack/spawn.ts` (`spawnPackFiles()` + Funktion `here`).
- `.github/workflows/build.yml`: baut bei jedem Push, Release bei neuer Version auf `main`.

## Stand (12.11.2026)

- Version 1.0.0. Getestet auf echtem Paper 1.21.11 mit Test-Spielern (mineflayer): Begrüßung, /info, /rules, Zuhause, Teleport mit Balken und Abbruch, /tpa mit Knöpfen, /msg, /r, /mail, /near, /afk, /seen, Spawn-Schutz, Leiste, Standard-Konfiguration ohne Panel. Gebaut gegen Paper-API 1.17.1 und 1.21.11.
- Nicht echt getestet: Flug der Start-Elytra mit echtem Client (Bot simuliert keine Gleitphysik; Doppelsprung löst Gleiten aus), Bedrock-Menüs (kein Bedrock-Client), Paper 26.x (braucht Java 25).
