package edu.uoc.pac3;

import java.time.LocalDate;

/**
 * Represents a guild in a game, managing its members and their attributes.
 * The guild enforces rules for the name, level, description, and creation date,
 * ensuring they meet specified standards.
 * <p>
 * The guild can recruit members up to a maximum limit, and handles their registration,
 * ensuring no duplicates, null entries, or entries without pets, as required.
 */
public class Guild {
    public static final String INVALID_NAME = "[ERROR] The name cannot be null, has less than the minimum number of characters, exceeds the maximum number of characters or contains only whitespaces.";
    public static final String INVALID_LEVEL = "[ERROR] The level must be between 1 and the predefined maximum.";
    public static final String INVALID_DESCRIPTION = "[ERROR] The description cannot be null and cannot exceed the predefined maximum number of characters.";
    public static final String INVALID_CREATION_DATE = "[ERROR] The creation date cannot be null or in the future.";
    public static final String INVALID_MAX_MEMBERS = "[ERROR] The number of members cannot exceed the predefined maximum.";
    public static final String MEMBER_NULL = "[ERROR] The member cannot be null.";
    public static final String MEMBER_ALREADY_EXISTS = "[ERROR] The member already exists in the guild.";
    public static final String MEMBER_NOT_FOUND = "[ERROR] The member does not exist in the guild.";
    public static final String MEMBER_NO_PET = "[ERROR] The member does not have a pet.";
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 25;
    private static final int MAX_LEVEL = 20;
    private static final int MAX_DESCRIPTION_LENGTH = 100;
    private int id;
    private static int nextId = 1;
    private String name;
    private int level;
    private String description;
    private LocalDate creationDate;
    private boolean recruiting;
    private final int NUM_MAX_MEMBERS;
    private int numMembers;
    private Player[] members;
    private int sumLevels;

    /**
     * Constructs a new Guild with specified attributes, validating all input parameters.
     * Sets the guild's ID, name, level, description, recruiting status, and other properties using internal methods.
     *
     * @param name          the name of the guild, must not be null, empty, or outside the length constraints
     * @param level         the operational level of the guild, must be within specified bounds
     * @param description   a brief description of the guild, must not exceed length constraints
     * @param creationDate  the date the guild was created, must not be in the future
     * @param recruiting    a boolean indicating whether the guild is actively recruiting
     * @param numMaxMembers the maximum number of members the guild can have, must be positive
     * @throws IllegalArgumentException if any parameter conditions are not met
     */
    public Guild(String name, int level, String description, LocalDate creationDate, boolean recruiting, int numMaxMembers) {
        setName(name);
        setLevel(level);
        setDescription(description);
        setCreationDate(creationDate);
        setRecruiting(recruiting);
        setId();
        if (numMaxMembers <= 0) {
            throw new IllegalArgumentException(INVALID_MAX_MEMBERS);
        }

        this.NUM_MAX_MEMBERS = numMaxMembers;
        this.members = new Player[numMaxMembers];
    }
    /**
     * Returns the next unique identifier for a new guild instance.
     * This static method provides access to a class-wide counter that increments
     * each time a new guild object is created.
     *
     * @return the next available unique identifier
     */
    public static int getNextId() {
        return nextId;
    }

    /**
     * Increments the static ID counter for guild instances.
     * This method is private and only called within the class to ensure that ID values are managed correctly.
     */
    private static void incNextId() {
        Guild.nextId++;
    }
    /**
     * Returns the name of the guild.
     * The name is validated to ensure it meets specific length and content requirements.
     *
     * @return the name of the guild
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the guild. The name must not be null, must not be only whitespace,
     * and must fall within the specified length constraints.
     *
     * @param name the new name of the guild
     * @throws IllegalArgumentException if the name does not meet the specified criteria
     */
    private void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
        this.name = name;
    }

    /**
     * Gets the unique identifier of the guild.
     * This ID is set when the guild is initially created and remains constant during the object's lifecycle.
     *
     * @return the unique identifier of this guild
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the unique identifier for this guild instance using the next available ID.
     * This method also increments the static ID counter to ensure uniqueness for the next guild instance.
     * This method is private to control how IDs are assigned within the class.
     */
    private void setId() {
        this.id = getNextId();
        incNextId();
    }

    /**
     * Returns the current level of the guild.
     * The level represents the guild's rank or status within the game context.
     *
     * @return the current level of the guild
     */
    public int getLevel() {
        return level;
    }
    /**
     * Sets the level of the guild. The level must be within specified bounds.
     *
     * @param level the new level to set for the guild
     * @throws IllegalArgumentException if the provided level is outside the allowed range (1 to MAX_LEVEL)
     */
    private void setLevel(int level) {
        if (level < 1 || level > MAX_LEVEL) {
            throw new IllegalArgumentException(INVALID_LEVEL);
        }
        this.level = level;
    }

    /**
     * Retrieves the description of the guild.
     * The description provides more detailed information about the guild's purpose or characteristics.
     *
     * @return the description of the guild
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the guild. The description must not exceed a predefined maximum length.
     *
     * @param description the new description of the guild
     * @throws IllegalArgumentException if the description is null or exceeds the maximum length allowed (MAX_DESCRIPTION_LENGTH)
     */
    private void setDescription(String description) {
        if (description == null || description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException(INVALID_DESCRIPTION);
        }
        this.description = description;
    }

    /**
     * Gets the creation date of the guild.
     * This date marks when the guild was officially formed and registered in the system.
     *
     * @return the LocalDate representing the creation date of the guild
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the creation date of the guild. The date must not be in the future.
     *
     * @param creationDate the date the guild was or will be established
     * @throws IllegalArgumentException if the creation date is null or in the future
     */
    private void setCreationDate(LocalDate creationDate) {
        if (creationDate == null || creationDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(INVALID_CREATION_DATE);
        }
        this.creationDate = creationDate;
    }
    /**
     * Checks if the guild is currently recruiting new members.
     *
     * @return true if the guild is recruiting, false otherwise
     */
    public boolean isRecruiting() {
        return recruiting;
    }

    /**
     * Sets the recruiting status of the guild.
     *
     * @param recruiting the new recruiting status
     */
    private void setRecruiting(boolean recruiting) {
        this.recruiting = recruiting;
    }

    /**
     * Calculates the number of days since the guild was created.
     *
     * @return the number of days since the guild's creation
     */
    public int getDaysOfLife() {
        return creationDate.until(LocalDate.now()).getDays();
    }

    /**
     * Returns an array of current members in the guild.
     *
     * @return an array containing all the members of the guild
     */
    public Player[] getMembers() {
        return members;
    }

    /**
     * Counts the number of members currently in the guild.
     *
     * @return the count of non-null members in the guild
     */
    public int getNumMembers() {
        if (members == null) {
            return 0;
        }
        int numMembers = 0;
        for (Player member : members) {
            if (member != null) {
                numMembers++;
            }
        }
        return numMembers;
    }

    /**
     * Searches for the specified member in the guild and returns the index at which they are found.
     * If the member is not found, this method returns -1.
     *
     * @param member the member to find in the guild's member array
     * @return the index of the found member, or -1 if the member is not found
     * @throws NullPointerException if the passed member is null
     */
    private int findMember(Player member) {
        if (member == null) {
            throw new NullPointerException(MEMBER_NULL);
        }

        for (int i = 0; i < members.length; i++) {
            if (members[i] != null && members[i].equals(member)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the first empty slot in the guild's members array.
     * An empty slot is indicated by a `null` entry in the array.
     *
     * @return the index of the first empty slot if available, or -1 if the guild is full
     */
    private int findFirstEmptySlot() {
        for (int i = 0; i < members.length; i++) {
            if (members[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Determines if a specific member is part of the guild.
     *
     * @param member the member to check for
     * @return true if the member is in the guild, false otherwise
     * @throws NullPointerException if the member is null or does not have a pet
     */
    public boolean containsMember(Player member) {
        if (member == null) {
            throw new NullPointerException(MEMBER_NULL);
        }
        if (member.getPet() == null) {
            throw new NullPointerException(MEMBER_NO_PET);
        }

        for (Player existingMember : members) {
            if (existingMember != null && existingMember.equals(member)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new member to the guild if there is space and the member meets all requirements.
     *
     * @param member the new member to add
     * @throws NullPointerException     if the member is null or lacks a required pet
     * @throws IllegalStateException    if the guild has reached its maximum number of members
     * @throws IllegalArgumentException if the member already exists within the guild
     */
    public void addMember(Player member) {
        if (member == null) {
            throw new NullPointerException(MEMBER_NULL);
        }
        if (member.getPet() == null) {
            throw new NullPointerException(MEMBER_NO_PET);
        }
        if (numMembers >= NUM_MAX_MEMBERS) {
            throw new IllegalStateException(INVALID_MAX_MEMBERS);
        }
        if (containsMember(member)) {
            throw new IllegalArgumentException(MEMBER_ALREADY_EXISTS);
        }

        int emptySlot = findFirstEmptySlot();
        if (emptySlot == -1) {
            throw new IllegalStateException(INVALID_MAX_MEMBERS);
        }
        members[emptySlot] = member;
        numMembers++;
        sumLevels += member.getLevel();
    }

    /**
     * Removes a specified member from the guild if they exist within the member list.
     * This method also updates the total levels and the count of members accordingly.
     *
     * @param member the member to be removed from the guild
     * @throws NullPointerException     if the member is null
     * @throws IllegalArgumentException if the member is not found in the guild
     */
    public void removeMember(Player member) {
        if (member == null) {
            throw new NullPointerException(MEMBER_NULL);
        }

        int memberIndex = findMember(member);
        if (memberIndex == -1) {
            throw new IllegalArgumentException(MEMBER_NOT_FOUND);
        }

        sumLevels -= members[memberIndex].getLevel();
        members[memberIndex] = null;
        numMembers--;
    }

    /**
     * Calculates the average level of all members in the guild.
     * If there are no members in the guild, the average level is defined to be 0.0.
     *
     * @return the average level of guild members as a double
     */
    public double getAverageLevel() {
        if (numMembers == 0) {
            return 0.0;
        }
        return (double) sumLevels / numMembers;
    }

    /**
     * Retrieves the maximum number of members that the guild can have.
     *
     * @return the maximum number of allowable members in the guild
     */
    public int getMaxMembers() {
        return NUM_MAX_MEMBERS;
    }
}
