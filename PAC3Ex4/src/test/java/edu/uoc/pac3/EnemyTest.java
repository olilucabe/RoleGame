package edu.uoc.pac3;

import org.junit.jupiter.api.*;
import java.lang.reflect.*;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EnemyTest {

    @Test
    @Order(1)
    @DisplayName("Enemy creation - Valid parameters")
    void testCreateEnemy() {
        Enemy enemy = new Enemy("Goblin", 100, 10, 20, 0, 0);

        assertNotNull(enemy);
        assertEquals("Goblin", enemy.getName());
        assertEquals(100, enemy.getHealth());
        assertEquals(10, enemy.getMinDamage());
        assertEquals(20, enemy.getMaxDamage());
        assertFalse(enemy.isDead());
    }

    @Test
    @Order(2)
    @DisplayName("Enemy creation - Invalid Name")
    void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> new Enemy("", 100, 10, 20, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Enemy(null, 100, 10, 20, 0, 0));
        assertThrows(IllegalArgumentException.class, () -> new Enemy("A".repeat(51), 100, 10, 20, 0, 0));
    }

    @Test
    @Order(3)
    @DisplayName("Enemy health adjustment - Set to zero")
    void testHealthAdjustment() {
        Enemy enemy = new Enemy("Goblin", 100, 10, 20, 0, 0);
        enemy.setHealth(0);
        assertTrue(enemy.isDead());
        assertEquals(0, enemy.getHealth());
    }

    @Test
    @Order(4)
    @DisplayName("Enemy damage range - Invalid settings")
    void testDamageRange() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Enemy("Goblin", 100, 0, 20, 0, 0));
        assertEquals(Enemy.INVALID_MIN_DAMAGE, e.getMessage());

        e = assertThrows(IllegalArgumentException.class, () -> new Enemy("Goblin", 100, 20, 10, 0, 0));
        assertEquals(Enemy.INVALID_MAX_DAMAGE, e.getMessage());
    }

    @Test
    @Order(5)
    @DisplayName("Enemy movement - Valid and invalid moves")
    void testMovement() {
        Enemy enemy = new Enemy("Goblin", 100, 10, 20, 0, 0);
        assertTrue(enemy.move(5, 5));
        assertFalse(enemy.move(20, 20));
    }

    @Test
    @Order(6)
    @DisplayName("Enemy attack and receive damage")
    void testAttackAndDamage() {
        Enemy enemy = new Enemy("Goblin", 100, 10, 20, 0, 0);
        int damage = enemy.attack();
        assertTrue(damage >= 10 && damage <= 20);

        enemy.receiveDamage(50);
        assertEquals(50, enemy.getHealth());
    }

    @Test
    @Order(7)
    @DisplayName("Initial Position and Movement")
    void testInitialPositionAndMovement() {
        Enemy enemy = new Enemy("Goblin", 100, 10, 20, 10, 15);
        Position initialPosition = enemy.getPosition();
        assertNotNull(initialPosition);
        assertEquals(10, initialPosition.getX());
        assertEquals(15, initialPosition.getY());

        enemy.move(15, 15);
        Position newPosition = enemy.getPosition();
        assertNotNull(newPosition);
        assertEquals(15, newPosition.getX());
        assertEquals(15, newPosition.getY());
    }

    @Test
    @Order(8)
    @DisplayName("Enemy receive damage and death interaction")
    void testReceiveDamageAndDeath() {
        Enemy enemy = new Enemy("Goblin", 50, 10, 20, 0, 0);
        enemy.receiveDamage(30);
        assertFalse(enemy.isDead());

        enemy.receiveDamage(20);
        assertTrue(enemy.isDead());
        assertEquals(0, enemy.getHealth());
    }

    @Test
    @Order(9)
    @DisplayName("Enemy attack with equal min and max damage")
    void testAttackWithEqualMinMaxDamage() {
        Enemy enemy = new Enemy("Goblin", 100, 15, 15, 0, 0);
        for (int i = 0; i < 10; i++) {
            assertEquals(15, enemy.attack());
        }
    }

    @Test
    @Order(10)
    @DisplayName("Enemy successive movements")
    void testSuccessiveMovements() {
        Enemy enemy = new Enemy("Goblin", 100, 10, 20, 0, 0);
        assertTrue(enemy.move(5, 0));
        assertTrue(enemy.move(10, 0));
        assertFalse(enemy.move(25, 0));
    }

    @Test
    @Order(11)
    @DisplayName("Enemy health not set to negative")
    void testHealthNotNegative() {
        Enemy enemy = new Enemy("Goblin", 100, 10, 20, 0, 0);
        enemy.receiveDamage(150);
        assertEquals(0, enemy.getHealth());
    }

    @Test
    @Order(12)
    @DisplayName("Sanity - Fields definition")
    void checkFieldsSanity() {
        Field[] fields = Enemy.class.getDeclaredFields();
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("name") && Modifier.isPrivate(f.getModifiers()) && f.getType().equals(String.class)));
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("health") && Modifier.isPrivate(f.getModifiers()) && f.getType().equals(int.class)));
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("minDamage") && Modifier.isPrivate(f.getModifiers()) && f.getType().equals(int.class)));
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("maxDamage") && Modifier.isPrivate(f.getModifiers()) && f.getType().equals(int.class)));
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("position") && Modifier.isPrivate(f.getModifiers()) && f.getType().equals(Position.class)));
        assertTrue(Arrays.stream(fields).anyMatch(f -> f.getName().equals("isDead") && Modifier.isPrivate(f.getModifiers()) && f.getType().equals(boolean.class)));
    }

    @Test
    @Order(13)
    @DisplayName("Sanity - Methods definition")
    void checkMethodsSanity() {
        Method[] methods = Enemy.class.getDeclaredMethods();
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getName") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(String.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("setName") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(void.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getHealth") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(int.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("setHealth") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(void.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getMinDamage") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(int.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getMaxDamage") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(int.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("setDamage") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(void.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("getPosition") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(Position.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("setPosition") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(void.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("isDead") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(boolean.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("move") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(boolean.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("attack") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(int.class)));
        assertTrue(Arrays.stream(methods).anyMatch(m -> m.getName().equals("receiveDamage") && Modifier.isPublic(m.getModifiers()) && m.getReturnType().equals(void.class)));
    }



}


