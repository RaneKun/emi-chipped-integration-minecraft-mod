# Contributor Guide — EMI Chipped Integration

This guide walks you through setting up the development environment, editing the source code, and compiling the mod from scratch.

---

## Prerequisites

Before you begin, make sure you have the following installed:

- **Java Development Kit (JDK) 21** — Required by Fabric Loom. [Download Adoptium JDK 21](https://adoptium.net/)
- **Git** — For cloning the repository
- **An IDE** (recommended: [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/))

You do **not** need to install Gradle separately — the project includes a Gradle wrapper (`gradlew.bat` on Windows, `./gradlew` on Linux/Mac).

---

## 1. Clone the Repository

```bash
git clone https://github.com/yourusername/emi-chipped-integration.git
cd emi-chipped-integration
```

---

## 2. Project Structure

```
emi-chipped/
├── src/main/
│   ├── java/com/rane/emichipped/
│   │   └── EmiChippedPlugin.java      # Main EMI plugin entrypoint & recipe registration
│   └── resources/
│       ├── fabric.mod.json            # Mod metadata (name, version, dependencies)
│       └── assets/emi_chipped/
│           └── lang/en_us.json        # Translations
├── libs/
│   ├── emi-1.1.22+1.20.1+fabric.jar              # Local EMI dependency
│   └── chipped-fabric-1_20_1-3_0_7.jar           # Local Chipped dependency
├── build.gradle                       # Build configuration
├── gradle.properties                  # Version numbers and mod metadata
└── settings.gradle                    # Project name
```

---

## 3. Open in IntelliJ IDEA

1. Open IntelliJ IDEA
2. Click **File → Open** and select the `emi-chipped` folder
3. IntelliJ will detect the Gradle project automatically — click **Trust Project** if prompted
4. Wait for Gradle to sync and download dependencies (this may take a few minutes the first time)
5. Once synced, run the Fabric Loom generation task:
   - Open the **Gradle** panel (right side) → `Tasks → fabric → genSources`
   - This downloads and decompiles Minecraft sources for code reference

---

## 4. Understanding the Code

### `EmiChippedPlugin.java`
The EMI plugin entrypoint registered in `fabric.mod.json`. It implements `EmiPlugin` and uses `EmiRegistry` to register all Chipped workbench recipes into EMI when the game loads. This is the main file you'll edit if you want to change how recipes are gathered or displayed.

### `fabric.mod.json`
Controls mod metadata. Key fields you might want to change:
- `"version"` — pulled automatically from `gradle.properties` via `${version}`
- `"description"` — shown on Modrinth and in-game mod menus
- `"contact"."sources"` — update this to your actual GitHub URL after creating a repo

### `gradle.properties`
Controls version numbers used across the build:
```properties
mod_version=1.0.0        # Bump this when releasing a new version
minecraft_version=1.20.1
loader_version=0.16.0
```

---

## 5. Making Changes

### Changing the mod version
Edit `gradle.properties`:
```properties
mod_version=1.1.0
```
The new version will automatically be embedded in the compiled jar.

### Modifying recipe registration logic
Edit `EmiChippedPlugin.java`. This is where Chipped's workbench recipe types are iterated and handed to EMI's registry. If Chipped adds new workbench types in future versions, they would be added here.

### Adding new language support
Create a new file under `src/main/resources/assets/emi_chipped/lang/`, e.g. `de_de.json`, following the same key-value format as `en_us.json`.

### Updating dependencies
If you need a newer version of EMI or Chipped, replace the `.jar` files in the `libs/` folder and update the filenames referenced in `build.gradle`.

---

## 6. Compiling the Mod

Run the following command from the project root:

**Windows:**
```bat
gradlew.bat build
```

**Linux / macOS:**
```bash
./gradlew build
```

The compiled mod `.jar` will be placed in:
```
build/libs/emi-chipped-1.0.0.jar
```

Use this file (not the `-sources.jar` or `-dev.jar`) when installing or uploading the mod.

---

## 7. Testing In-Game

To launch a development Minecraft instance with the mod loaded:

**Windows:**
```bat
gradlew.bat runClient
```

**Linux / macOS:**
```bash
./gradlew runClient
```

This opens a Minecraft client with Fabric Loader, EMI, Chipped, and your mod all active. The `run/` folder acts as the game directory for this dev instance — it is excluded from Git via `.gitignore`.

---

## 8. Publishing a Release

### GitHub
1. Commit and push your changes
2. Go to your repository → **Releases → Draft a new release**
3. Create a tag (e.g. `v1.0.0`), write release notes, and attach `build/libs/emi-chipped-1.0.0.jar`

### Modrinth
1. Log into [modrinth.com](https://modrinth.com) and go to your project
2. Click **Create version**
3. Upload the `.jar` from `build/libs/`
4. Set the game version to `1.20.1`, loader to `Fabric`
5. Add **EMI** and **Chipped** as required dependencies
6. Publish

---

## Questions?

Feel free to open an issue on the GitHub repository if you run into any problems building or editing the mod.
