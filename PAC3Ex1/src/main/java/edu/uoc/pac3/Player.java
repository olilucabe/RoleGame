package edu.uoc.pac3;

import java.time.LocalDate;

/**
 * Represents a player in a game, encapsulating attributes such as name, level, creation date,
 * experience, and others, along with a pet.
 */
public class Player {
    public static final String INVALID_NAME = "[ERROR] The name cannot be null, empty, consist solely of spaces, and it must be within the predefined minimum and maximum character limits.";
    public static final String INVALID_LEVEL = "[ERROR] The level must be between 1 and the predefined maximum.";
    public static final String INVALID_CREATION_DATE = "[ERROR] The creation date cannot be null or in the future.";
    public static final String INVALID_EXPERIENCE = "[ERROR] The experience must be greater than or equal to 0.";
    public static final String INVALID_GOLD = "[ERROR] The gold must be greater than or equal to 0.";
    public static final String INVALID_HEALTH_REGEN_PER_SEC = "[ERROR] The health regeneration per second must be greater than or equal to 0.";
    public static final String INVALID_CRITICAL_PCT = "[ERROR] The critical percentage must be between 0.0 and 100.0.";
    public static final String INVALID_DODGE_PCT = "[ERROR] The dodge percentage must be between 0.0 and 100.0.";
    public static final String INVALID_HONOR_TITLE = "[ERROR] The honor title cannot be null, empty, consist solely of spaces, cannot exceed the predefined maximum character limit, and can only contain characters from the English alphabet and whitespaces.";
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_LEVEL = 99;
    private static final int MAX_HONOR_TITLE_LENGTH = 30;

    private String name;
    private int level;
    private LocalDate creationDate;
    private int experience;
    private int gold;
    private double healthRegenPerSec;
    private double criticalPct;
    private double dodgePct;
    private String honorTitle;
    private Pet pet;

    /**
     * Constructs a new Player with initial attributes, excluding pet.
     *
     * @param name              the player's name
     * @param level             the player's level
     * @param creationDate      the date the player was created
     * @param experience        the player's experience points
     * @param gold              the amount of gold the player owns
     * @param healthRegenPerSec the health regeneration rate per second
     * @param criticalPct       the critical hit percentage
     * @param dodgePct          the dodge percentage
     * @param honorTitle        the honorific title of the player
     */
    public Player(String name, int level, LocalDate creationDate, int experience, int gold, double healthRegenPerSec, double criticalPct, double dodgePct, String honorTitle) {
        setName(name);
        setLevel(level);
        setCreationDate(creationDate);
        setExperience(experience);
        setGold(gold);
        setHealthRegenPerSec(healthRegenPerSec);
        setCriticalPct(criticalPct);
        setDodgePct(dodgePct);
        setHonorTitle(honorTitle);
        this.pet = null;
    }

    /**
     * Constructs a new Player with initial attributes, including a pet.
     *
     * @param name              the player's name
     * @param level             the player's level
     * @param creationDate      the date the player was created
     * @param experience        the player's experience points
     * @param gold              the amount of gold the player owns
     * @param healthRegenPerSec the health regeneration rate per second
     * @param criticalPct       the critical hit percentage
     * @param dodgePct          the dodge percentage
     * @param honorTitle        the honorific title of the player
     * @param petName           the pet's name
     * @param petLevel          the pet's level
     * @param petBirthdate      the pet's birthdate
     * @param petLoyalty        the pet's loyalty level
     * @param petStamina        the pet's stamina
     * @param petIsAggressive   whether the pet is aggressive
     */
    public Player(String name, int level, LocalDate creationDate, int experience, int gold, double healthRegenPerSec, double criticalPct, double dodgePct, String honorTitle, String petName, int petLevel, LocalDate petBirthdate, int petLoyalty, int petStamina, boolean petIsAggressive) {
        this(name, level, creationDate, experience, gold, healthRegenPerSec, criticalPct, dodgePct, honorTitle);
        setPet(petName, petLevel, petBirthdate, petLoyalty, petStamina, petIsAggressive);
    }

    /**
     * Returns the player's name.
     *
     * @return the current name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's name, ensuring it meets specified conditions such as minimum and maximum length.
     * Throws IllegalArgumentException if conditions are not met.
     *
     * @param name the new name for the player
     */
    private void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        name = name.trim();
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    /**
     * Returns the player's level.
     *
     * @return the current level of the player
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the player's level, ensuring it is within a valid range.
     * Throws IllegalArgumentException if the level is not within the valid range [1, MAX_LEVEL].
     *
     * @param level the new level for the player
     */
    private void setLevel(int level) {
        if (level < 1 || level > MAX_LEVEL) {
            throw new IllegalArgumentException(INVALID_LEVEL);
        }
        this.level = level;
    }

    /**
     * Returns the creation date of the player.
     *
     * @return the date the player was created
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the player's creation date, ensuring it is not in the future or null.
     * Throws IllegalArgumentException if the creation date is invalid.
     *
     * @param creationDate the new creation date for the player
     */
    private void setCreationDate(LocalDate creationDate) {
        if (creationDate == null || creationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(INVALID_CREATION_DATE);
        }
        this.creationDate = creationDate;
    }

    /**
     * Returns the experience points of the player.
     *
     * @return the current experience points of the player
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets the experience points for the player, ensuring they are non-negative.
     * Throws IllegalArgumentException if the experience points are negative.
     *
     * @param experience the new experience points for the player
     */
    private void setExperience(int experience) {
        if (experience < 0) {
            throw new IllegalArgumentException(INVALID_EXPERIENCE);
        }
        this.experience = experience;
    }

    /**
     * Returns the amount of gold the player possesses.
     *
     * @return the current gold amount of the player
     */
    public int getGold() {
        return gold;
    }

    /**
     * Sets the amount of gold for the player, ensuring it is non-negative.
     * Throws IllegalArgumentException if the gold amount is negative.
     *
     * @param gold the new amount of gold for the player
     */
    private void setGold(int gold) {
        if (gold < 0) {
            throw new IllegalArgumentException(INVALID_GOLD);
        }
        this.gold = gold;
    }

    /**
     * Returns the health regeneration rate per second of the player.
     *
     * @return the current health regeneration rate per second
     */
    public double getHealthRegenPerSec() {
        return healthRegenPerSec;
    }

    /**
     * Sets the health regeneration rate per second for the player, ensuring it is non-negative.
     * Throws IllegalArgumentException if the rate is negative.
     *
     * @param healthRegenPerSec the new health regeneration rate per second for the player
     */
    private void setHealthRegenPerSec(double healthRegenPerSec) {
        if (healthRegenPerSec < 0) {
            throw new IllegalArgumentException(INVALID_HEALTH_REGEN_PER_SEC);
        }
        this.healthRegenPerSec = healthRegenPerSec;
    }

    /**
     * Returns the critical hit percentage of the player.
     *
     * @return the current critical hit percentage
     */
    public double getCriticalPct() {
        return criticalPct;
    }

    /**
     * Sets the critical hit percentage for the player, ensuring it is within the range [0.0, 100.0].
     * Throws IllegalArgumentException if the percentage is out of range.
     *
     * @param criticalPct the new critical hit percentage for the player
     */
    private void setCriticalPct(double criticalPct) {
        if (criticalPct < 0 || criticalPct > 100) {
            throw new IllegalArgumentException(INVALID_CRITICAL_PCT);
        }
        this.criticalPct = criticalPct;
    }

    /**
     * Returns the dodge percentage of the player.
     *
     * @return the current dodge percentage
     */
    public double getDodgePct() {
        return dodgePct;
    }

    /**
     * Sets the dodge percentage for the player, ensuring it is within the range [0.0, 100.0].
     * Throws IllegalArgumentException if the percentage is out of range.
     *
     * @param dodgePct the new dodge percentage for the player
     */
    private void setDodgePct(double dodgePct) {
        if (dodgePct < 0 || dodgePct > 100) {
            throw new IllegalArgumentException(INVALID_DODGE_PCT);
        }
        this.dodgePct = dodgePct;
    }

    /**
     * Returns the honor title of the player.
     *
     * @return the current honor title of the player
     */
    public String getHonorTitle() {
        return honorTitle;
    }

    /**
     * Sets the honor title for the player, ensuring it meets specified conditions.
     * Throws IllegalArgumentException if the title is null, empty, too long, or contains invalid characters.
     *
     * @param honorTitle the new honor title for the player
     */
    private void setHonorTitle(String honorTitle) {
        if (honorTitle == null) {
            throw new IllegalArgumentException(INVALID_HONOR_TITLE);
        }
        honorTitle = honorTitle.trim();
        if (honorTitle.isEmpty() || honorTitle.length() > MAX_HONOR_TITLE_LENGTH || !honorTitle.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException(INVALID_HONOR_TITLE);
        }
        this.honorTitle = honorTitle;
    }

    /**
     * Returns the player's pet.
     *
     * @return the pet of the player
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Sets the pet for the player with specified attributes.
     *
     * @param name       the name of the pet
     * @param level      the level of the pet
     * @param birthdate  the birthdate of the pet
     * @param loyalty    the loyalty level of the pet
     * @param stamina    the stamina of the pet
     * @param aggressive whether the pet is aggressive
     */
    private void setPet(String name, int level, LocalDate birthdate, int loyalty, int stamina, boolean aggressive) {
        this.pet = new Pet(name, level, birthdate, loyalty, stamina, aggressive);
    }
}
