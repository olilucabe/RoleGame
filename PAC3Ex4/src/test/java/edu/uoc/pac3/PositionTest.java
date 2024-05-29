package edu.uoc.pac3;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class PositionTest {

    @Test
    @Order(1)
    @DisplayName("Position creation - Valid coordinates")
    void testValidPositionCreation() {
        Position position = new Position(512, 256);
        assertNotNull(position);
        assertEquals(512, position.getX());
        assertEquals(256, position.getY());
    }

    @Test
    @Order(2)
    @DisplayName("Set position - Invalid X coordinate")
    void testInvalidXCoordinate() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Position(-1, 256));
        assertEquals(Position.INVALID_X, ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Position(1025, 256));
        assertEquals(Position.INVALID_X, ex.getMessage());
    }

    @Test
    @Order(3)
    @DisplayName("Set position - Invalid Y coordinate")
    void testInvalidYCoordinate() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Position(512, -1));
        assertEquals(Position.INVALID_Y, ex.getMessage());

        ex = assertThrows(IllegalArgumentException.class, () -> new Position(512, 513));
        assertEquals(Position.INVALID_Y, ex.getMessage());
    }

    @Test
    @Order(4)
    @DisplayName("Calculate distance - Correct calculation")
    void testDistanceCalculation() {
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(3, 4);
        double expectedDistance = 5.0; // Based on the Pythagorean theorem: a^2 + b^2 = c^2
        assertEquals(expectedDistance, Position.distance(pos1, pos2), 0.01);
    }

    @Test
    @Order(5)
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        Field[] fields = Position.class.getDeclaredFields();
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("x") && Modifier.isPrivate(f.getModifiers()) && f.getType().equals(int.class)));
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("y") && Modifier.isPrivate(f.getModifiers()) && f.getType().equals(int.class)));
    }

    @Test
    @Order(6)
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        Method[] methods = Position.class.getDeclaredMethods();
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getX") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(int.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getY") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(int.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("setX") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(void.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("setY") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(void.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("distance") && Modifier.isPublic(m.getModifiers()) && Modifier.isStatic(m.getModifiers()) && m.getReturnType().equals(double.class)));
    }


}


