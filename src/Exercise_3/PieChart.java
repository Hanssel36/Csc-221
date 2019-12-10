package Exercise_3;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;


public class PieChart {
private double w1,h1;
private LinkedHashMap<Character,Double> prop; // holds the probability of the letters

        public PieChart( double w1, double h1, LinkedHashMap<Character,Double> prop)
        {
            setHeight(h1);
            setWidth(w1);
            setAngle(prop);
        }

    public void setWidth(double X){
        this.w1 = X;
    }

    public double getWidth(){
        return w1;
    }

    public void setHeight(double X){
        this.h1 = X;
    }

    public double getHeight(){
        return h1;
    }

    public void setAngle(LinkedHashMap<Character,Double> prop){
            this.prop = prop;
    }

    public double[] getAngle(){
        double[] angle; // array of angles
        angle = new double[prop.size()];
        int count =0;
        for(double vals: prop.values()){
            angle[count] = 360*vals;
            count =count + 1;
        }
            return angle;
    }

    // For Project this was not needed
    public double RestOfProp(){


            double sum = 0.0;
            for (double vals: prop.values()){
                sum += vals;
            }
            return 1.0-sum;
    }

public void draw(GraphicsContext gc, Map<Character,Double> gradeNum){
    Random rand = new Random();

//Color c = new Color(r,g,b);
    double s = 0; //used for helping the pie segments find their right position

    double pad = 20; // used for padding between the labels and their colors.
    for(int i = 0; i < getAngle().length; i++) {
        int r = rand.nextInt(256); // goes from 0 to 255
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);

        gc.setFill(Color.rgb(r,g,b));
        gc.fillArc(75 , 150 , getWidth(), getHeight(), s, getAngle()[i], ArcType.ROUND);
        Double value = (Double) gradeNum.values().toArray()[i]; // this is to turn Double
        Integer va = value.intValue();// turns into an Integer
        gc.fillText(prop.keySet().toArray()[i] + ", "+ va,getWidth()+75, pad);

        s = s +getAngle()[i];
        pad +=20;
    }

    gc.strokeArc(75,150,getWidth(),getHeight(),0,360, ArcType.OPEN); // This creates the circle of the piechart
}
}
