package cz.muni.fi.PA165.api.service;

/**
 * enum representing colors of brick
 *
 * @author jirikrepl
 */
public enum Color {

    WHITE("Color.WHITE %s"),
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
    
    public String toString(Object... o) {
        return String.format(text, o);
    }
}
