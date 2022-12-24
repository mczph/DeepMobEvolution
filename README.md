# Deep Mob Evolution 
This mod is a continuation of  mustapelto's rewrite of Deep Mob Learning, adding new features (see below) that would have been impossible to implement as modification of the original.

[![Curseforge](http://cf.way2muchnoise.eu/full_737252_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/dme)
[![Curseforge](http://cf.way2muchnoise.eu/versions/For%20MC_737252_all.svg)](https://www.curseforge.com/minecraft/mc-mods/dme)
<img alt="Modrinth Downloads" src="https://img.shields.io/modrinth/dt/dme?color=g&label=Modrinth"><br><img alt="Discord" src="https://img.shields.io/discord/914926812948234260?label=Pansmith%27s%20Discord&logo=discord">
<img alt="YouTube Video Views" src="https://img.shields.io/youtube/views/iQ6N5RL4qwc?color=green&label=Showcase%20Video">

****
- Fully JSON configurable Data Model types, tiers, trials, and Living Matter types
    * New types, tiers and trials can be added or existing ones changed/removed
    * Refer to the Wiki for more info on how to add them.
    * Data Model, Pristine Matter and Living Matter textures for added types can be supplied by a resource pack or
      through mods like ResourceLoader. Default fallback textures will be used if no matching texture files are found.
    * Recipes for data models, as well as Living Matter products, are defined in the config files
    * Data Model tiers can be added, removed and fully configured
    * See SettingsGuide.txt in the mod's config folder for more info
- Machines are redstone controllable (always on / on with signal / off with signal / always off)
- Machines don't have restrictions on input/output sides
    * Simulation Chamber allows Data Model and Polymer Clay input from any side, and Living/Pristine Matter output
      to any side
    * Loot Fabricator allows Pristine Matter input from any side, and loot item output to any side
    * A config setting is available to revert to the original DML behavior
- Machine blocks change appearance based on the current state of crafting (idle / running / error)
- Several minor QoL improvements
- Under-the-hood performance improvements

---

### Updating from DeepMobLearning
This mod uses the same item and block registry names as the original, so in-place updating is possible as follows:
- Always backup your world before changing mods!
- Add the new mod jar and remove the old one
- Run Minecraft once (**don't load your world!**) to generate the new config files (config/deepmobevolution/*)
- Manually copy any changes you made to the original config (config/deepmoblearning.cfg) into the new files
- The old config file can be deleted, it's not used by this mod
- Now you can restart Minecraft and load your world - you're done!

---

### What this mod does (adapted from the original mod's description)
A server-friendly and peaceful-compatible mod for mob loot acquisition. Originally inspired by the Soul Shards mod,
where you could "collect" mob kills to later use them for mob spawners.

This mod uses **Data Models** that you can train by defeating monsters, either in-world or virtually in a
**Simulation Chamber**. Simulating a model will additionally produce two types of matter:
- **Living Matter** (related to the dimension the mob is from), which can be crafted into various loot items
- **Pristine Matter** (unique to each Data Model type), which can be placed into a **Loot Fabricator** to produce a
  choice of loot items. Higher tier models produce more of this matter type.

Simulation Chambers and Loot Fabricators require **Forge Energy** to operate, which is currently **not** supplied by
this mod. Both machines can be fully automated with either vanilla (hoppers) or mod-added methods, such as Thermal
Expansion itemducts or EnderIO conduits.

Get started by making a **Deep Learner** and at least one **Data Model**. Insert the Data Model into the Deep Learner
and go kill some mobs of the required type to level the Model up to at least Basic tier. Then you can insert it
into a Simulation Chamber to start producing Matter!

---

### Current out-of-the-box mod support
Data Models and Living Matter for the following mods are defined in the default config:
- **Thermal Foundation** (one combined Data Model for Thermal Elementals, i.e. Blizz, Blitz and Basalz)
- **Twilight Forest** (Twilight Matter; four categories of mobs: Forest, Swamp, Darkwood and Glacier)
- **Tinkers' Construct** (Blue Slime)
- **Matter Overdrive** (Rogue Android)

