package com.arun;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    private final LinkedList<Segment> segments = new LinkedList<>();
    private final int segmentSize;

    public Snake(int startX, int startY, int segmentSize) {
        this.segmentSize = segmentSize;
        segments.add(new Segment(startX, startY));
    }

    public void move(Direction direction) {
        Segment head = segments.getFirst();
        int newX = head.x() + direction.dx() * segmentSize;
        int newY = head.y() + direction.dy() * segmentSize;

        segments.addFirst(new Segment(newX, newY));
        segments.removeLast();
    }

    public void grow() {
        // Duplicate the last segment to grow the snake
        Segment tail = segments.getLast();
        segments.addLast(new Segment(tail.x(), tail.y()));
    }

    public boolean isCollidingWithBounds(int width, int height) {
        Segment head = segments.getFirst();
        return head.x() < 0 || head.y() < 0
                || head.x() + segmentSize > width
                || head.y() + segmentSize > height;
    }

    public boolean isCollidingWithSelf() {
        Segment head = segments.getFirst();
        return segments.stream().skip(1)
                .anyMatch(segment -> segment.equals(head));
    }

    public boolean isCollidingWith(Food food) {
        Segment head = segments.getFirst();
        return head.x() == food.getX() && head.y() == food.getY();
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public record Segment(int x, int y) {}
}
