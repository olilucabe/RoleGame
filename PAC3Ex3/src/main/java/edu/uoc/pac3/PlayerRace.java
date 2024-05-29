package edu.uoc.pac3;

public enum PlayerRace {
    WARRIOR("Warrior", 150, 18, 6, 20, 10),
    NINJA("Ninja", 120, 12, 14, 15, 18),
    SHAMAN("Shaman", 110, 10, 20, 10, 12),
    DARK_MAGE("Dark Mage", 130, 15, 15, 18, 10);

    private final String name;
    private final int maxHP;
    private final int vitality;
    private final int intelligence;
    private final int strength;
    private final int agility;

    PlayerRace(String name, int maxHP, int vitality, int intelligence, int strength, int agility) {
        this.name = name;
        this.maxHP = maxHP;
        this.vitality = vitality;
        this.intelligence = intelligence;
        this.strength = strength;
        this.agility = agility;
    }


    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getVitality() {
        return vitality;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    /**
     * Returns the best partner race according to game logic.
     * The partnerships are predefined:
     * - WARRIOR pairs with SHAMAN
     * - SHAMAN pairs with WARRIOR
     * - NINJA pairs with DARK_MAGE
     * - DARK_MAGE pairs with NINJA
     *
     * @return the PlayerRace that is the best partner for this race.
     */
    public PlayerRace getBestPartner() {
        return switch (this) {
            case WARRIOR -> SHAMAN;
            case SHAMAN -> WARRIOR;
            case NINJA -> DARK_MAGE;
            case DARK_MAGE -> NINJA;
            default -> null;
        };
    }

    public static PlayerRace getRace(String name) {
        for (PlayerRace race : PlayerRace.values()) {
            if (race.getName().equalsIgnoreCase(name)) {
                return race;
            }
        }
        return null;
    }
}
