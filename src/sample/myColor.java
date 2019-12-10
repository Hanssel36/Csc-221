package sample;

import javafx.scene.paint.Color;

public enum myColor {
    Blue (Color.rgb(0,0,255)),
    Green(Color.rgb(0,255,0)),
    Silver(Color.rgb(192,192,192)),
    Black(Color.rgb(0,0,0));

    private Color colour;

    myColor(Color c){ this.colour = c; }

    public Color returnColour() { return colour; }


    public void defineColour(){

        if(returnColour() == Blue.returnColour()){
            System.out.println("BLUE");
        }else if (returnColour() == Green.returnColour()){
            System.out.println("GREEN");
        }else if(returnColour() == Silver.returnColour()){
            System.out.println("SILVER");
        }else if(returnColour() == Black.returnColour()){
            System.out.println("BLACK");
        }
    }
}
