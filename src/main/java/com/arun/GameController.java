package com.arun;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameController {
    public static final int WIDTH = 800;   // Must be multiple of SQUARE_SIZE
    public static final int HEIGHT = 600;  // Must be multiple of SQUARE_SIZE
    private static final int SQUARE_SIZE = 20;

    private Direction currentDirection = Direction.LEFT;
    private boolean running = false;
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Snake snake;
    private final Food food;
    private AnimationTimer timer;

    public GameController() {
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Start snake away from the edges
        snake = new Snake(WIDTH / 2 + SQUARE_SIZE * 5, HEIGHT / 2,
                SQUARE_SIZE);
        food = new Food(WIDTH, HEIGHT, SQUARE_SIZE);
    }

    public void startGame() {
        running = true;
        timer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 300_000_000) { // Update every 100ms
                    update();
                    lastUpdate = now;
                }
            }
        };
        timer.start();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setDirection(Direction direction) {
        if (!direction.isOpposite(currentDirection)) {
            currentDirection = direction;
        }
    }

    private void update() {
        if (!running) return;

        try {
            snake.move(currentDirection);

            // Debug: Print the snake's head position
            var head = snake.getSegments().get(0);
            System.out.println("Snake Head Position: x=" + head.x()
                    + ", y=" + head.y());

            if (snake.isCollidingWithBounds(WIDTH, HEIGHT)
                    || snake.isCollidingWithSelf()) {
                running = false;
                timer.stop();
                System.out.println("Game Over: Collision Detected");
                drawGameOver();
                return;
            }

            if (snake.isCollidingWith(food)) {
                snake.grow();
                food.relocate(WIDTH, HEIGHT, SQUARE_SIZE,
                        snake.getSegments());
            }

            draw();
        } catch (Exception e) {
            e.printStackTrace();
            running = false;
            timer.stop();
        }
    }

    private void draw() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        // Draw food
        gc.setFill(Color.RED);
        gc.fillRect(food.getX(), food.getY(), SQUARE_SIZE, SQUARE_SIZE);

        // Draw snake
        gc.setFill(Color.LIMEGREEN);
        for (var segment : snake.getSegments()) {
            gc.fillRect(segment.x(), segment.y(), SQUARE_SIZE, SQUARE_SIZE);
        }
    }

    private void drawGameOver() {
        gc.setFill(Color.WHITE);
        gc.fillText("Game Over", WIDTH / 2 - 30, HEIGHT / 2);
    }
}
