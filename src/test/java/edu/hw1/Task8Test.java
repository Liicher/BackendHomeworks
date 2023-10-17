package edu.hw1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task8Test {
    Task8 task8 = new Task8();

    @Test
    void knightBoardCaptureTrue() {
        int[][] input = {{0, 0, 0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 1, 0, 1, 0},
                        {0, 1, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 1, 0, 0, 0}};
        boolean response = task8.knightBoardCapture(input);
        Assertions.assertThat(response).isEqualTo(true);
    }

    @Test
    void knightBoardCaptureFalse() {
        int[][] input = {{1, 0, 1, 0, 1, 0, 1, 0},
                        {0, 1, 0, 1, 0, 1, 0, 1},
                        {0, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 1, 0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 1, 0, 1, 0},
                        {0, 0, 0, 1, 0, 1, 0, 1}};
        boolean response = task8.knightBoardCapture(input);
        Assertions.assertThat(response).isEqualTo(false);
    }

    @Test
    void knightBoardCaptureZeros() {
        int[][] input = {{1, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0}};
        boolean response = task8.knightBoardCapture(input);
        Assertions.assertThat(response).isEqualTo(false);
    }

    @Test
    void invalidNullInput() {
        int[][] input = null;
        assertThrows(IllegalArgumentException.class, () -> {
            task8.knightBoardCapture(input);
        });
    }

    @Test
    void invalidBoardSizeInput() {
        int[][] input = {{1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 4, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 5, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0,-5, 0, 0, 0, 0, 0}};
        assertThrows(IllegalArgumentException.class, () -> {
            task8.knightBoardCapture(input);
        });
    }

    @Test
    void invalidNumInput() {
        int[][] input = {{1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0}};
        assertThrows(IllegalArgumentException.class, () -> {
            task8.knightBoardCapture(input);
        });
    }
}
