# MorniCraft

@STAND.md

## Regeln für dieses Projekt

- Repo ist **öffentlich**: keine Geheimnisse, Tokens oder privaten Daten committen.
- „Verfasst mit Claude 🤖“ als letzte Zeile in Commits, PRs, Issues und Release-Notes; einmal im README.
- Texte im Spiel freundlich, Deutsch (Standard) und Englisch.
- Das Plugin steckt auch in MorniHosting (`plugins/mornicraft/`): Änderungen in beiden Repos gleich halten.
- Neue Version = `<version>` in `pom.xml` erhöhen und `docs/releases/v<version>.md` anlegen; der Workflow baut das Release beim Push auf `main`.
- Läuft ab Paper 1.17 (Java 17, API 1.17.1) – keine neueren APIs ohne Rückfall (siehe `Sidebar.hideNumbers`).
