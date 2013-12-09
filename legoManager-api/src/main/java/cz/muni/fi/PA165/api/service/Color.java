package cz.muni.fi.PA165.api.service;

/**
 * enum representing colors of brick
 *
 * @author jirikrepl
 */
public enum Color {

    WHITE("Color.WHITE"),
    BRICKYELLOW("Color.BRICKYELLOW"),
    NOUGAT("Color.NOUGAT"),
    BRIGHTRED("Color.BRIGHTRED"),
    BRIGHTBLUE("Color.BRIGHTBLUE"),
    BRIGHTYELLOW("Color.BRIGHTYELLOW"),
    BLACK("Color.BLACK"),
    DARKGREEN("Color.DARKGREEN"),   
    BRIGHTGREEN("Color.BRIGHTGREEN"),
    DARKORANGE("Color.DARKORANGE"),    
    MEDIUMBLUE("Color.MEDIUMBLUE"), 
    COOLYELLOW("Color.COOLYELLOW"),
    DARKBROWN("Color.DARKBROWN"),
    OLIVEGREEN("Color.OLIVEGREEN"),
    LIGHTPURPLE("Color.LIGHTPURPLE"),
    BRIGHTPURPLE("Color.BRIGHTPURPLE"),
    MEDIUMLILAC("Color.MEDIUMLILAC");
    
    private final String text;

    Color(String text) {
        this.text = text;
    }

//    public boolean equalsName(String otherName) {
//        return (otherName != null) && text.equals(otherName);
//    }

    public String toString() {
        return text;
    }

//    /**
//     * convert string to enum of color
//     *
//     * @param colorString string from command line arg
//     * @return Color, or exit if color does not exist
//     */
//    public static Color parseColor(String colorString) {
//        // find out if string of color has its Enum
//        Color colorEnum = null;
//        for (Color c : Color.values()) {
//            if (c.equalsName(colorString)) {
//                colorEnum = c;
//                break;
//            }
//        }
//        return colorEnum;
//    }
}
