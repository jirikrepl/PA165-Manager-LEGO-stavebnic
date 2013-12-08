package cz.muni.fi.PA165;

/**
 * enum representing colors of brick
 *
 * @author jirikrepl
 */
public enum Color {

    WHITE("white"),
    BRICKYELLOW("brick-yellow"),
    NOUGAT("nougat"),
    BRIGHTRED("bright-red"),
    BRIGHTBLUE("bright-blue"),
    BRIGHTYELLOW("bright-yellow"),
    BLACK("black"),
    DARKGREEN("dark-green"),
    BRIGHTGREEN("bright-green"),
    DARKORANGE("dark-orange"),
    MEDIUMBLUE("medium-blue"),
    COOLYELLOW("cool-yellow"),
    DARKBROWN("dark-brown"),
    OLIVEGREEN("olive-green"),
    LIGHTPURPLE("light-purple"),
    BRIGHTPURPLE("bright-purple"),
    MEDIUMLILAC("medium-lilac");

    private final String text;

    Color(String text) {
        this.text = text;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : text.equals(otherName);
    }

    public String toString() {
        return text;
    }
}
