# Snake Game in Java

Welcome to the Snake Game project! This is a simple, classic Snake game implemented in Java using JavaFX for the user interface. It's designed to be beginner-friendly, so even if you're new to Java, you can understand and run this project.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites)
- [Setting Up the Project](#setting-up-the-project)
- [Building and Running the Game](#building-and-running-the-game)
- [How to Play](#how-to-play)
- [Project Structure](#project-structure)
- [Code Explanation](#code-explanation)
    - [Main.java](#mainjava)
    - [GameController.java](#gamecontrollerjava)
    - [Snake.java](#snakejava)
    - [Food.java](#foodjava)
    - [Direction.java](#directionjava)
- [Understanding the Build Script](#understanding-the-build-script)
- [Further Customizations](#further-customizations)
- [Troubleshooting](#troubleshooting)
- [Resources](#resources)
- [License](#license)

---

## Project Overview

This Snake Game is a desktop application where you control a green snake that moves around the screen, eating red food squares. Each time the snake eats food, it grows longer. The game ends if the snake collides with the walls or itself. The game also displays your current score and the time elapsed.

---

## Prerequisites

Before you begin, make sure you have the following installed on your system:

- **Java Development Kit (JDK) 22 or above**

    - Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or [OpenJDK](https://jdk.java.net/17/).

- **Gradle Build Tool**

    - Gradle is used to build and run the project.
    - You can install Gradle from [Gradle Installation Guide](https://gradle.org/install/).

- **IntelliJ IDEA (Optional but Recommended)**

    - An Integrated Development Environment (IDE) for Java development.
    - Download from [JetBrains](https://www.jetbrains.com/idea/download/).

---

## Setting Up the Project

Follow these steps to set up the project on your local machine.

### 1. Clone the Repository

If you're using Git, you can clone the repository. Otherwise, download the project ZIP file and extract it.

```bash
git clone https://github.com/yourusername/SnakeGame.git
```

### 2. Navigate to the Project Directory

```bash
cd SnakeGame
```

### 3. Verify Java Installation

Ensure that Java is installed and the version is 17 or above.

```bash
java -version
```

Expected output:

```
java version "17.0.x"
```

### 4. Set `JAVA_HOME` Environment Variable

Set the `JAVA_HOME` environment variable to point to your JDK installation.

- **On macOS/Linux:**

  ```bash
  export JAVA_HOME=/path/to/your/jdk
  ```

- **On Windows:**

  ```cmd
  set JAVA_HOME=C:\Path\To\Your\JDK
  ```

### 5. Verify Gradle Installation

Check if Gradle is installed.

```bash
gradle -v
```

Expected output includes Gradle version information.

---

## Building and Running the Game

### 1. Clean the Project (Optional)

It's good practice to clean the project before building.

```bash
./gradlew clean
```

### 2. Build the Project

```bash
./gradlew build
```

If the build is successful, you will see a `BUILD SUCCESSFUL` message.

### 3. Run the Game

```bash
./gradlew run
```

This command will start the game. A window titled "Snake Game" should appear.

---

## How to Play

- **Objective:** Eat as much red food as possible without colliding with the walls or yourself.
- **Controls:**
    - **Up:** Up Arrow or 'W'
    - **Down:** Down Arrow or 'S'
    - **Left:** Left Arrow or 'A'
    - **Right:** Right Arrow or 'D'
- **Gameplay:**
    - The snake moves continuously in the current direction.
    - Use the controls to change the snake's direction.
    - Each time the snake eats food, it grows longer, and your score increases.
    - The game displays your current score and the time elapsed.
- **Game Over:**
    - Occurs when the snake collides with the walls or itself.
    - Press **Enter** to restart the game.

---

## Project Structure

The project follows a standard Gradle Java project structure:

```
SnakeGame
├── build.gradle
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
└── src
    └── main
        ├── java
        │   └── com
        │       └── example
        │           ├── Direction.java
        │           ├── Food.java
        │           ├── GameController.java
        │           ├── Main.java
        │           └── Snake.java
        └── resources
            └── (Optional resource files)
```

---

## Code Explanation

### Main Classes and Their Roles:

- **Main.java**: Entry point of the application. Sets up the game window and handles user input.
- **GameController.java**: Manages the game logic, including the game loop, drawing, and updates.
- **Snake.java**: Represents the snake, handling movement, growth, and collision detection.
- **Food.java**: Manages the food's position and ensures it doesn't spawn on the snake.
- **Direction.java**: An enumeration that defines possible movement directions and helper methods.

---

### Main.java

**Path:** `src/main/java/com/example/Main.java`

**Purpose:**

- Initializes the game.
- Sets up the main window (stage) and the scene.
- Handles user input for controlling the snake.

**Key Points:**

- **Scene and Stage Setup:**

  ```java
  Group root = new Group();
  root.getChildren().add(game.getCanvas());
  Scene scene = new Scene(root, GameController.WIDTH, GameController.HEIGHT);
  primaryStage.setScene(scene);
  ```

- **User Input Handling:**

  ```java
  scene.setOnKeyPressed(event -> {
      switch (event.getCode()) {
          case UP:
          case W:
              game.setDirection(Direction.UP);
              break;
          // ... other cases
      }
  });
  ```

---

### GameController.java

**Path:** `src/main/java/com/example/GameController.java`

**Purpose:**

- Manages game state and logic.
- Contains the game loop using `AnimationTimer`.
- Handles drawing the game components on the canvas.

**Key Points:**

- **Game Loop:**

  ```java
  timer = new AnimationTimer() {
      private long lastUpdate = 0;
      @Override
      public void handle(long now) {
          if (now - lastUpdate >= 200_000_000) { // Update every 200ms
              update(now);
              lastUpdate = now;
          }
      }
  };
  ```

- **Updating Game State:**

  ```java
  private void update(long now) {
      snake.move(currentDirection);
      // Collision detection and scoring
  }
  ```

- **Drawing on Canvas:**

  ```java
  private void draw() {
      // Clear canvas and redraw snake, food, score, and timer
  }
  ```

---

### Snake.java

**Path:** `src/main/java/com/example/Snake.java`

**Purpose:**

- Represents the snake's body and handles movement and growth.
- Checks for collisions with itself.

**Key Points:**

- **Movement:**

  ```java
  public void move(Direction direction) {
      // Calculate new head position and update segments
  }
  ```

- **Growth:**

  ```java
  public void grow() {
      // Add a new segment to the snake
  }
  ```

- **Collision Detection:**

  ```java
  public boolean isCollidingWithSelf() {
      // Check if the head collides with any other segment
  }
  ```

---

### Food.java

**Path:** `src/main/java/com/example/Food.java`

**Purpose:**

- Manages the food's position.
- Ensures food doesn't spawn on the snake.

**Key Points:**

- **Relocation:**

  ```java
  public void relocate(int width, int height, int size, List<Snake.Segment> snakeSegments) {
      // Randomly place food on the grid, avoiding the snake
  }
  ```

---

### Direction.java

**Path:** `src/main/java/com/example/Direction.java`

**Purpose:**

- Enumerates possible movement directions.
- Provides utility methods for direction handling.

**Key Points:**

- **Enum Definition:**

  ```java
  public enum Direction {
      UP(0, -1), DOWN(0, 1), LEFT(-1, 0), RIGHT(1, 0);
      // ...
  }
  ```

- **Checking Opposite Directions:**

  ```java
  public boolean isOpposite(Direction other) {
      return this.dx == -other.dx && this.dy == -other.dy;
  }
  ```

---

## Understanding the Build Script

**File:** `build.gradle`

**Purpose:**

- Configures the project build settings.
- Manages dependencies and plugins.

**Key Sections:**

- **Plugins:**

  ```groovy
  plugins {
      id 'application'
      id 'org.openjfx.javafxplugin' version '0.0.13'
  }
  ```

    - `application`: Allows the project to be run as an application.
    - `org.openjfx.javafxplugin`: Simplifies JavaFX setup.

- **Dependencies:**

  ```groovy
  dependencies {
      implementation 'org.openjfx:javafx-controls:17'
  }
  ```

    - Includes JavaFX controls library.

- **JavaFX Configuration:**

  ```groovy
  javafx {
      version = "17"
      modules = [ 'javafx.controls' ]
  }
  ```

- **Application Settings:**

  ```groovy
  application {
      mainClass = 'com.example.Main'
  }
  ```

---

## Further Customizations

Feel free to enhance the game with additional features:

- **Add Obstacles:** Introduce obstacles that the snake must avoid.
- **Increase Difficulty:** Gradually increase the snake's speed as the score increases.
- **Change Appearance:** Customize colors, add images, or change the snake's look.
- **Sound Effects:** Add sounds for eating food or when the game ends.
- **Persistent High Scores:** Save high scores to a file or database.

---

## Troubleshooting

### Game Doesn't Start

- **Check Console Output:** Look for error messages in the terminal.
- **Verify Java and Gradle Versions:** Ensure you have the correct versions installed.
- **Ensure JavaFX is Included:** Make sure the JavaFX dependencies are correctly specified.

### Key Inputs Not Working

- **Focus on Game Window:** Click on the game window to ensure it has focus.
- **Check Key Event Handling:** Ensure the `setOnKeyPressed` method is correctly implemented.

### Snake Collides Immediately

- **Adjust Starting Position:** Modify the snake's initial position in `GameController.java`.
- **Check Collision Logic:** Ensure the collision detection methods are working properly.

---

## Resources

- **JavaFX Documentation:** [OpenJFX Documentation](https://openjfx.io/)
- **Gradle User Guide:** [Gradle Documentation](https://docs.gradle.org/current/userguide/userguide.html)
- **Java Tutorials:** [Oracle Java Tutorials](https://docs.oracle.com/javase/tutorial/)

---

## License

This project is open-source and free to use under the MIT License.

---

Enjoy your journey in learning Java and game development! If you have any questions or need further assistance, feel free to reach out.