package cz.muni.fi.PA165.api.service;

/**
 * enum representing colors of brick
 *
 * @author jirikrepl
 */
public enum Color {


    WHITE("Color.WHITE", "white"),
    BRICKYELLOW("Color.BRICKYELLOW", "brickyellow"),
    NOUGAT("Color.NOUGAT", "nougat"),
    BRIGHTRED("Color.BRIGHTRED", "brightred"),
    BRIGHTBLUE("Color.BRIGHTBLUE", "brightblue"),
    BRIGHTYELLOW("Color.BRIGHTYELLOW", "brightyyellow"),
    BLACK("Color.BLACK", "black"),
    DARKGREEN("Color.DARKGREEN", "darkgreen"),
    BRIGHTGREEN("Color.BRIGHTGREEN", "brightgreen"),
    DARKORANGE("Color.DARKORANGE", "darkorange"),
    MEDIUMBLUE("Color.MEDIUMBLUE", "mediumblue"),
    COOLYELLOW("Color.COOLYELLOW", "coolyellow"),
    DARKBROWN("Color.DARKBROWN", "darkbrown"),
    OLIVEGREEN("Color.OLIVEGREEN", "olivegreen"),
    LIGHTPURPLE("Color.LIGHTPURPLE", "lightpurple"),
    BRIGHTPURPLE("Color.BRIGHTPURPLE", "brightpurple"),
    MEDIUMLILAC("Color.MEDIUMLILAC", "mediumlilac");

    private final String localisation;
    private final String value;

    Color(String localisation, String value) {
        this.localisation= localisation;
        this.value =  value;
    }

    public String toString() {
        return localisation;
    }

    public String getColorString() {
        return value;
    }

    public static Color parseColor(String colorString) {
        for (Color color : Color.values()) {
            if (color.value.equals(colorString)) {
                return color;
            }
        }
        throw new IllegalArgumentException("Unsupported color " + colorString);
    }
}
