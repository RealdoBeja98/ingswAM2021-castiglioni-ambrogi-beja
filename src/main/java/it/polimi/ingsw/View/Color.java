package it.polimi.ingsw.View;

/**
 * This Class represents the color used on the CLI
 */
public enum Color {
    /**
     *ANSI_RED color
     */
    ANSI_RED("\u001B[31m"),
    /**
     *ANSI_GREEN  color
     */
    ANSI_GREEN("\u001B[32m"),
    /**
     *ANSI_YELLOW  color
     */
    ANSI_YELLOW("\u001B[33m"),
    /**
     *ANSI_BLUE  color
     */
    ANSI_BLUE("\u001B[34m"),
    /**
     *ANSI_PURPLE  color
     */
    ANSI_PURPLE("\u001B[35m"),
    /**
     *ANSI_CYAN  color
     */
    ANSI_CYAN("\u001B[36m"),
    /**
     *ANSI_GRAY  color
     */
    ANSI_GRAY("\u001B[37m"),
    /**
     *ANSI_WHITE  color
     */
    ANSI_WHITE("\u001B[0m");


    private final String escape;

    /**
     * Constructor method of this class
     * @param escape: the color to be set.
     */
    Color(String escape) {
        this.escape = escape;
    }

    /**
     * Getter of the parameter escape
     * @return the deck color of the line, of type String
     */
    public String escape() {
        return escape;
    }

}
