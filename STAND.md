# Stand – MorniCraft

**Was es ist:** Paper/Purpur-Plugin für Freunde-Server (Spawn, Zuhause, Teleports mit Wartezeit, Nachrichten, Start-Elytra, Spawn-Schutz, Leiste, Bedrock-Menüs). Auch in MorniHosting eingebaut (`plugins/mornicraft/`, Panel schreibt `config.yml` aus dem Dashboard).

## Aufbau

- `src/main/java/de/mornihosting/mornicraft/`: `MorniCraft` (Start), `Settings` (config.yml), `Texts` (de/en), `Ui` (Karten, zentriert nach Minecraft-Zeichenbreiten, Knöpfe; Bedrock ohne Zentrierung), `Teleporter` (Wartezeit mit Bossbar, /back), `SpawnArea`, `SpawnGuard` (Schutz, Schlafen, PvP), `SpawnElytra`, `Social` (tpa, msg, mail, ignore, afk), `Commands`, `Welcome` (einmal pro Spieler), `Sidebar`, `WorldRules` (TNT, Creeper, Feuer, Endermen), `Bedrock` + `FloodgateMenus` (nur mit Floodgate geladen), `UpdateCheck` (GitHub-Release), `Ranks` + `DefaultRanks` (Ränge), `DefaultCommands` (eingebaute /info-Liste), `PlayerStore` (players/<uuid>.yml, übernimmt EssentialsX-Homes).
- `datapack/MorniCraft-Spawn/`: Spawn-Insel, erzeugt aus MorniHosting `apps/server/src/craftattack/spawn.ts` (`spawnPackFiles()` + Funktion `here`).
- `.github/workflows/build.yml`: baut bei jedem Push, Release bei neuer Version auf `main`.

## Stand (12.11.2026)

- Version 1.3.0: Rang-Abzeichen im Chat über ein Texturpaket (`pack.url` + `pack.sha1` in der config.yml, je Rang `badge` = Zeichen der Schrift): Paket wird beim Betreten angeboten (nie Pflicht, nicht für Bedrock), Chat pro Betrachter mit Abzeichen oder Symbol. Das Paket erzeugt MorniHosting (ohne Panel: selbst bauen, siehe README).
- Version 1.2.0: Spawn 2.0 im Datapack (Turm weiß/rot mit MorniCraft-Flagge, Zeltdach, Maibaum, Tulpen, Himmelsrichtungen, fünf Ballons), `zones` (geschützte Stellen außerhalb des Kreises, `SpawnArea.inZone`), `/team <…>` reicht an Minecraft weiter, Alias `scoreboard` entfernt. Als Nächstes: Rang-Abzeichen (Variante C, Server-Texturpaket).
- Version 1.1.1: Start-Elytra ohne Schub nach oben (Philip) – Flügel an, Gleiten mit eigenem Schwung; Hinweis „Spring ab und drück 2× Leertaste“.
- Version 1.1.0: Ränge (`Ranks`, `DefaultRanks`): Symbol + Farbe im Chat (Renderer), in der Tab-Liste, über dem Kopf (Teams `mcr00…` auch auf den Leisten-Scoreboards) und in der Leiste, Rechte per PermissionAttachment (auch `minecraft.command.*`), `/team`. Standard: Owner, Admin, Moderator, Supporter, Builder, Media, VIP, Spieler (default). Mit Test-Spielern geprüft (siehe unten).
- Version 1.0.2: Start-Elytra mit echten Flügeln (seit 1.21.2 nötig, Bedrock immer), Brustplatte sicher zurück (auch nach Absturz), Hinweis im Kreativmodus; Fehler-Durchsicht (Spawn-Schutz-Lücken, PvP vom Spawn aus, Tpa-Tausch, sichere Spielerdaten, Teleport bricht bei Schaden ab …); Spawn ohne Holzbänke. Elytra mit Test-Spieler auf 1.17.1, 1.20.4, 1.21.1, 1.21.11, 26.1.2 geprüft.
- Version 1.0.1 (Paper 26: `Title.Times.of` gibt es dort nicht mehr → `Ui.title()` sucht `times`/`of` per Reflexion). Auf allen Paper-Versionen 1.17.1, 1.18.2, 1.19.4, 1.20.1, 1.20.4, 1.20.6, 1.21.1, 1.21.4, 1.21.8, 1.21.11, 26.1.2 mit Test-Spieler und 26.2/26.3 per Konsole geprüft (Java 17/21/25). Vorher: Version 1.0.0. Getestet auf echtem Paper 1.21.11 mit Test-Spielern (mineflayer): Begrüßung, /info, /rules, Zuhause, Teleport mit Balken und Abbruch, /tpa mit Knöpfen, /msg, /r, /mail, /near, /afk, /seen, Spawn-Schutz, Leiste, Standard-Konfiguration ohne Panel. Gebaut gegen Paper-API 1.17.1 und 1.21.11.
- Nicht echt getestet: Start-Elytra und Ränge mit echtem Java-Client (nur Test-Spieler mit nachgebauter Physik bzw. Paketen), Bedrock-Menüs und Bedrock-Gleiten (kein Bedrock-Client), Ränge-Symbole im Bedrock-Font.
