package cz.muni.fi.PA165.api.service;

/**
 * enum representing colors of brick
 *
 * @author jirikrepl
 */
public enum Color {

    WHITE("brick.color.WHITE"),
    BRICKYELLOW("brick.color.BRICKYELLOW"),
    NOUGAT("brick.color.NOUGAT"),
    BRIGHTRED("brick.color.BRIGHTRED"),
    BRIGHTBLUE("brick.color.BRIGHTBLUE"),
    BRIGHTYELLOW("brick.color.BRIGHTYELLOW"),
    BLACK("brick.color.BLACK"),
    DARKGREEN("brick.color.DARKGREEN"),   
    BRIGHTGREEN("brick.color.BRIGHTGREEN"),
    DARKORANGE("brick.color.DARKORANGE"),
    
    MEDIUMBLUE("brick.color.MEDIUMBLUE"), 
    COOLYELLOW("brick.color.COOLYELLOW"),
    DARKBROWN("brick.color.DARKBROWN"),
    OLIVEGREEN("brick.color.OLIVEGREEN"),
    LIGHTPURPLE("brick.color.LIGHTPURPLE"),
    BRIGHTPURPLE("brick.color.BRIGHTPURPLE"),
    MEDIUMLILAC("brick.color.MEDIUMLILAC");
    
    private final String text;

    Color(String text) {
        this.text = text;
    }
    
    public String toString(Object... o) {
        return String.format(text, o);
    }
}
