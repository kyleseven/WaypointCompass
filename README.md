# WaypointCompass
Spigot plugin that allows players to change their compass heading.

## Disclaimer
This plugin is currently in ALPHA. The plugin's feature set is currently extremely basic.

## Building

WaypointCompass uses Maven to handle dependencies and building.

### Compiling from source

    git clone https://github.com/kyleseven/WaypointCompass.git
    cd WaypointCompass/
    mvn install
    
The jars can be found in the `target` directory.

## Installation

Place the `WaypointCompass.jar` file into your `plugins/` directory and start the server.

## Configuration

As of right now, `config.yml` has no configurable values.
You can edit `messages.yml` to change the messages the plugin displays to users.

## Usage

- `/wc help` displays a help menu that shows all commands the plugin has.
- `/wc current` displays the player's current compass heading.
- `/wc set <x> <y> <z>` sets the player's compass heading to a specified xyz coordinate.
- `/wc reset` resets the player's compass heading back to world spawn.
- `/wc reload` reloads the `config.yml` and `messages.yml`
- `/wc version` displays the plugin version.
