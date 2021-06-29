package it.polimi.ingsw.Enums;

/**
 * This Enum contains the state of the player during his turn
 */
public enum InWhichStatePlayer {
    /**
     * CHOSE_ACTION_LEADER_OR_NOT1 turn
     */
    CHOSE_ACTION_LEADER_OR_NOT1,
    /**
     * DISCARD_LEADER_CARD1 turn
     */
    DISCARD_LEADER_CARD1,
    /**
     * PLAY_LEADER_CARD1 turn
     */
    PLAY_LEADER_CARD1,
    /**
     * SELECT_NORMAL_ACTION turn
     */
    SELECT_NORMAL_ACTION,
    /**
     * TAKE_RESOURCES_FROM_THE_MARKET turn
     */
    TAKE_RESOURCES_FROM_THE_MARKET,
    /**
     * BUY_DEVELOPMENT_CARD turn
     */
    BUY_DEVELOPMENT_CARD,
    /**
     * ACTIVATE_PRODUCTION turn
     */
    ACTIVATE_PRODUCTION,
    /**
     * CHOSE_ACTION_LEADER_OR_NOT2 turn
     */
    CHOSE_ACTION_LEADER_OR_NOT2,
    /**
     * DISCARD_LEADER_CARD2 turn
     */
    DISCARD_LEADER_CARD2,
    /**
     * PLAY_LEADER_CARD2 turn
     */
    PLAY_LEADER_CARD2,
    /**
     * DRAW_SOLO_ACTION_TOKEN turn
     */
    DRAW_SOLO_ACTION_TOKEN,
    /**
     * FINISHING_TURN turn
     */
    FINISHING_TURN;
}
