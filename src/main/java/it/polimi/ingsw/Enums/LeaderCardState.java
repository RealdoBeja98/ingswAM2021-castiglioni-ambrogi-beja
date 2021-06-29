package it.polimi.ingsw.Enums;

/**
 * This enum contains the state of the action you are going to do with a LeaderCard
 * It's used onliy in gui to reduce the number of buttons
 */
public enum LeaderCardState {
    /**
     * DISCARD_LEADER_CARD leader card states
     */
    DISCARD_LEADER_CARD,
    /**
     * PLAY_LEADER_CARD leader card states
     */
    PLAY_LEADER_CARD,
    /**
     * ADD_RESOURCE_TO_LEADER_CARD leader card states
     */
    ADD_RESOURCE_TO_LEADER_CARD,
    /**
     * CHANGE_WHITE_MARBLE_WITH leader card states
     */
    CHANGE_WHITE_MARBLE_WITH,
    /**
     * PAY_WITH_EXTRA_STORAGE_LEADER_CARD leader card states
     */
    PAY_WITH_EXTRA_STORAGE_LEADER_CARD,
    /**
     * SELECT_PRODUCTION_POWER_LEADER_CARD leader card states
     */
    SELECT_PRODUCTION_POWER_LEADER_CARD,
    /**
     * MOVE_RESOURCES_WAREHOUSE_TO_ES_LC leader card states
     */
    MOVE_RESOURCES_WAREHOUSE_TO_ES_LC,
    /**
     * MOVE_RESOURCE_ES_LC_TO_WAREHOUSE leader card states
     */
    MOVE_RESOURCE_ES_LC_TO_WAREHOUSE
}
