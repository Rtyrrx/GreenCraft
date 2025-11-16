# Greencraft - 3D Minecraft-Inspired Design Patterns Demo

**Academic Project - Software Design Patterns Course**

## Team Members

- **Tamerlan Yessimov** - SE-2420
- **Ruslan Tsoy** - SE-2420
- **Sanzhar Baiserikov** - SE-2420
- **Madias Bek** - SE-2430

---

## Project Overview

Greencraft is a 3D block-building game developed in JavaFX that demonstrates the practical implementation of 7 fundamental design patterns. Inspired by Minecraft's mechanics, this educational project showcases how proper architectural patterns improve code maintainability, scalability, and clarity.

---

## Implemented Design Patterns

### 1. **Singleton Pattern**

- **Class:** `ResourceManager`
- **Purpose:** Ensures single instance for texture and sound caching
- **Benefit:** Prevents memory waste by loading resources once

### 2. **Strategy Pattern**

- **Classes:** `MovementStrategy` (Walk/Fly/Slide), `BlockSoundStrategy` (Stone/Grass/Water sounds)
- **Purpose:** Encapsulates interchangeable behaviors
- **Benefit:** Easy to add new movement types or block sounds without modifying existing code

### 3. **Factory Pattern**

- **Classes:** `BlockFactory`, `StoneFactory`, `GrassFactory`, `WaterFactory`
- **Purpose:** Encapsulates block creation logic
- **Benefit:** Centralizes object creation, easy to add new block types

### 4. **Facade Pattern**

- **Class:** `GameFacade`
- **Purpose:** Simplifies complex subsystem interactions (BlockManager, Player, Camera)
- **Benefit:** Clean API for game actions, hides implementation complexity

### 5. **Decorator Pattern**

- **Class:** `GlowingBlockDecorator`
- **Purpose:** Adds glowing effect to blocks dynamically without modifying original classes
- **Benefit:** Flexible feature addition, stackable decorators

### 6. **Observer Pattern**

- **Classes:** `Observer`, `EventLogger`
- **Purpose:** Publishes game events to subscribers
- **Benefit:** Decoupled event handling, easy to add logging or UI updates

### 7. **Adapter Pattern**

- **Classes:** `AudioClipAdapter`, `MediaPlayerAdapter`
- **Purpose:** Adapts incompatible JavaFX audio APIs to unified interface
- **Benefit:** Flexibility to swap audio libraries without changing client code

---

## Technical Stack

- **Language:** Java 17
- **Framework:** JavaFX 20
- **Build Tool:** Maven
- **3D Graphics:** JavaFX 3D Scene Graph
- **Audio:** JavaFX Media API

---

## How to Run

### Prerequisites

- JDK 17 or higher
- Maven 3.6+
- IntelliJ IDEA (recommended) or any Java IDE

### Installation Steps

1. Clone the repository
2. Open project in IntelliJ IDEA (open `pom.xml`)
3. Maven will automatically download dependencies
4. Run with Maven:
   ```bash
   mvn javafx:run
   ```

### Alternative: Run from IDE

- Right-click `GameApp.java`
- Select "Run 'GameApp.main()'"

---

## Controls

| Key         | Action                                 |
| ----------- | -------------------------------------- |
| **W/A/S/D** | Move player                            |
| **SPACE**   | Jump                                   |
| **1**       | Place Stone block                      |
| **2**       | Place Grass block                      |
| **3**       | Place Water block                      |
| **G**       | Hit/Break block (uses Visitor pattern) |
| **T**       | Toggle glowing effect on nearest block |
| **H**       | Switch to Walk movement                |
| **F**       | Switch to Fly movement                 |
| **J**       | Switch to Slide movement               |

---

## Project Structure

```
Greencraft/
├── src/main/java/com/greencraft/
│   ├── GameApp.java                    # Main entry point
│   ├── core/
│   │   └── GameFacade.java             # Facade pattern
│   ├── blocks/
│   │   ├── Block.java                  # Base block class
│   │   ├── BlockDecorator.java         # Decorator pattern
│   │   ├── GlowingBlockDecorator.java  # Concrete decorator
│   │   ├── blockFactory/
│   │   │   ├── BlockFactory.java       # Factory pattern interface
│   │   │   ├── StoneFactory.java
│   │   │   ├── GrassFactory.java
│   │   │   └── WaterFactory.java
│   │   ├── sound/
│   │   │   ├── BlockSoundStrategy.java # Strategy pattern interface
│   │   │   ├── StoneSoundStrategy.java
│   │   │   ├── GrassSoundStrategy.java
│   │   │   └── WaterSoundStrategy.java
│   │   └── visitor/
│   │       ├── BlockVisitor.java       # Visitor pattern
│   │       ├── DamageVisitor.java
│   │       └── SaveVisitor.java
│   ├── entities/
│   │   ├── Player.java
│   │   └── movement/
│   │       ├── MovementStrategy.java   # Strategy pattern interface
│   │       ├── WalkMovement.java
│   │       ├── FlyMovement.java
│   │       └── SlideMovement.java
│   └── utils/
│       ├── ResourceManager.java        # Singleton pattern
│       ├── Observer.java               # Observer pattern
│       ├── EventLogger.java
│       └── audio/
│           ├── AudioPlayer.java        # Adapter target interface
│           ├── AudioClipAdapter.java   # Adapter pattern
│           ├── MediaPlayerAdapter.java # Adapter pattern
│           └── MusicManager.java
├── src/main/resources/assets/
│   ├── textures/                       # PNG textures
│   └── sounds/                         # WAV/MP3 audio files
└── DESIGN_PATTERNS_GUIDE.md           # Detailed pattern documentation

```

---

## Features

### Core Gameplay

- 🧱 **3D Block Building:** Place and destroy blocks in 3D space
- 🎨 **Textured Blocks:** Stone, grass, water with PNG textures
- 🌍 **40x40 Textured Floor:** Large explorable world
- 👤 **Player Character:** Textured 3D avatar

### Technical Features

- 💡 **Dynamic Glowing Effect:** Blocks pulse with light (Decorator pattern)
- 🔊 **Context-Aware Sounds:** Different sounds for placing, digging, breaking each block type
- 🎵 **Background Music System:** Looping game soundtrack with volume control
- 🏃 **Multiple Movement Modes:** Walk, Fly, Slide with different physics
- 🎯 **Block Damage System:** Hit blocks multiple times to break them (Visitor pattern)

---

## Design Pattern Benefits Demonstrated

1. **Maintainability:** Adding new block types requires only creating new factories and strategies
2. **Scalability:** Audio system can support any audio library through adapters
3. **Flexibility:** Movement and sound behaviors can be changed at runtime
4. **Reusability:** Singleton ResourceManager used by all components
5. **Clarity:** Facade simplifies complex interactions into simple method calls
6. **Extensibility:** Decorators can be stacked (GlowingBlock, future: ExplodingBlock, etc.)

---

## Learning Outcomes

This project demonstrates:

- Practical application of Gang of Four (GoF) design patterns
- Real-world problems each pattern solves
- How patterns improve code architecture in game development
- Integration of multiple patterns in a cohesive system
- JavaFX 3D graphics and media capabilities

---

## Documentation

- **`DESIGN_PATTERNS_GUIDE.md`** - Detailed explanation of Singleton, Adapter, and Strategy patterns with exact code locations and line numbers for presentations

---

## Future Enhancements

- Implement Command pattern for undo/redo block placement
- Add Builder pattern for complex block structures
- Implement State pattern for game states (menu, playing, paused)
- Add multiplayer using network protocols

---

## License

Educational project for academic purposes.

---

## Acknowledgments

- Inspired by Minecraft's block-building mechanics
- JavaFX documentation and community
- Gang of Four Design Patterns book
- Course instructor and teaching assistants

---

