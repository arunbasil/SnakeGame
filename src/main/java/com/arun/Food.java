package com.arun;

import java.util.List;
import java.util.Random;

public class Food {
    private int x, y;
    private final Random random = new Random();

    public Food(int width, int height, int size) {
        relocate(width, height, size, List.of());
    }

    public void relocate(int width, int height, int size, List<Snake.Segment> snakeSegments) {
        do {
            x = random.nextInt(width / size) * size;
            y = random.nextInt(height / size) * size;
        } while (snakeSegments.stream().anyMatch(segment -> segment.x() == x && segment.y() == y));
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

