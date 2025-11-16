# Minecraft Theme Textures

This project now uses Minecraft-style texture naming conventions through the **Adapter Pattern**.

## Required Texture Files

Place the following Minecraft texture files in this directory:

### Block Textures
- `grass_block.png` - Grass block texture
- `stone.png` - Stone block texture
- `water_still.png` - Water block texture
- `dirt.png` - Dirt block texture (optional)
- `cobblestone.png` - Cobblestone texture (optional)
- `oak_planks.png` - Wood planks texture (optional)

### Character/UI Textures
- `player.png` - Player character texture

## Adapter Pattern Implementation

The `MinecraftTextureAdapter` automatically translates simple texture names to Minecraft conventions:

- "grass" → "grass_block.png"
- "stone" → "stone.png"
- "water" → "water_still.png"
- "character" → "player.png"
- "floor" → "grass_block.png"

## Where to Get Textures

You can extract textures from:
1. Minecraft resource packs
2. Create your own Minecraft-style textures
3. Use free Minecraft texture resources (check licensing)

## Image Specifications

- Format: PNG with transparency support
- Recommended size: 16x16, 32x32, or 64x64 pixels
- Should tile seamlessly for blocks
