package edu.uoc.pac3;

import java.time.LocalDate;

/**
 * Represents a pet associated with a player in a game. This class encapsulates attributes such as
 * name, level, birthdate, loyalty, stamina, and aggression status.
 */
public class Pet {
    public static final String INVALID_NAME = "[ERROR] The name cannot be null or empty, and it must be within the predefined minimum and maximum character limits.";
    public static final String INVALID_LEVEL = "[ERROR] The level must be between 1 and the predefined maximum.";
    public static final String INVALID_BIRTHDATE = "[ERROR] The birthdate cannot be null or in the future.";
    public static final String INVALID_LOYALTY = "[ERROR] Loyalty must be within 0 and 100.";
    public static final String INVALID_STAMINA = "[ERROR] Stamina must be within 0 and 100.";
    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MAX_LEVEL = 60;

    private String name;
    private int level;
    private LocalDate birthdate;
    private int loyalty;
    private int stamina;
    private boolean aggressive;

    /**
     * Constructs a new Pet with initial attributes.
     *
     * @param name       the name of the pet
     * @param level      the level of the pet
     * @param birthdate  the birthdate of the pet
     * @param loyalty    the loyalty level of the pet
     * @param stamina    the stamina of the pet
     * @param aggressive whether the pet is aggressive
     */

    public Pet(String name, int level, LocalDate birthdate, int loyalty, int stamina, boolean aggressive) {
        setName(name);
        setLevel(level);
        setBirthdate(birthdate);
        setLoyalty(loyalty);
        setStamina(stamina);
        setAggressive(aggressive);
    }

    /**
     * Returns the name of the pet.
     *
     * @return the current name of the pet
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the pet, ensuring it adheres to specified conditions such as not being null or empty and fitting within the character limits.
     * Throws IllegalArgumentException if conditions are not met.
     *
     * @param name the new name for the pet
     */
    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        name = name.trim();
        if (name.length() > MAX_NAME_LENGTH || name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    /**
     * Returns the level of the pet.
     *
     * @return the current level of the pet
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the pet, ensuring it is within a valid range.
     * Throws IllegalArgumentException if the level is not within the valid range [1, MAX_LEVEL].
     *
     * @param level the new level for the pet
     */
    private void setLevel(int level) {
        if (level < 1 || level > MAX_LEVEL) {
            throw new IllegalArgumentException(INVALID_LEVEL);
        }
        this.level = level;
    }

    /**
     * Returns the birthdate of the pet.
     *
     * @return the birthdate of the pet
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }

    /**
     * Sets the birthdate of the pet, ensuring it is not in the future or null.
     * Throws IllegalArgumentException if the birthdate is invalid.
     *
     * @param birthdate the new birthdate for the pet
     */
    private void setBirthdate(LocalDate birthdate) {
        if (birthdate == null || birthdate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(INVALID_BIRTHDATE);
        }
        this.birthdate = birthdate;
    }

    /**
     * Returns the loyalty level of the pet.
     *
     * @return the current loyalty level of the pet
     */
    public int getLoyalty() {
        return loyalty;
    }

    /**
     * Sets the loyalty level of the pet, ensuring it is within the specified range [0, 100].
     * Throws IllegalArgumentException if the loyalty is out of range.
     *
     * @param loyalty the new loyalty level for the pet
     */
    private void setLoyalty(int loyalty) {
        if (loyalty < 0 || loyalty > 100) {
            throw new IllegalArgumentException(INVALID_LOYALTY);
        }
        this.loyalty = loyalty;
    }

    /**
     * Returns the stamina level of the pet.
     *
     * @return the current stamina level of the pet
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * Sets the stamina level of the pet, ensuring it is within the specified range [0, 100].
     * Throws IllegalArgumentException if the stamina is out of range.
     *
     * @param stamina the new stamina level for the pet
     */
    private void setStamina(int stamina) {
        if (stamina < 0 || stamina > 100) {
            throw new IllegalArgumentException(INVALID_STAMINA);
        }
        this.stamina = stamina;
    }

    /**
     * Returns the aggression status of the pet.
     *
     * @return whether the pet is aggressive
     */
    public boolean isAggressive() {
        return aggressive;
    }

    /**
     * Sets the aggression status of the pet.
     *
     * @param aggressive the new aggression status for the pet
     */
    private void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }
}
