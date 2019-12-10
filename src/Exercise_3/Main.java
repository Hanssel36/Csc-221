package Exercise_3;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main extends Application {

    public void start(Stage primaryStage) throws Exception
    {
        // GUI to take n.
        TextField enterN = new TextField();
        Button but = new Button("Submit");
        int n =3;

        enterN.setText(String.valueOf(n));
        Canvas canvas = new Canvas(600,600);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane flow = new Pane();
        Scene scene = new Scene(flow, canvas.getWidth(), canvas.getHeight());
        but.setLayoutY(50);
        // takes the action
        File file = new File("C:\\Users\\Hanssel\\Desktop\\Code\\Csc 221\\src\\Exercise_3\\emma.txt");
        Scanner sc = new Scanner(new FileReader(file)); // reads the file
        Map<Character, Double> temphash = new LinkedHashMap<>(); // used to temporarily put letters and count of them.


        String Rspace; // used to put the file in a string for regex.
        while(sc.hasNextLine()) {

            // will stop at end of the text file.
            Rspace = sc.nextLine(); // assigns line by line
            Rspace = Rspace.toLowerCase().replaceAll("[^a-zA-Z]", "");
            for (char letters : Rspace.toCharArray()) {
                // if the hash contains the key then increment by 1, if not then set to 1
                temphash.put(letters, temphash.containsKey(letters) ? (temphash.get(letters) + 1) : 1);
            }
        }
        sc.close(); //used to close the file

        flow.getChildren().addAll(canvas, but, enterN);
        primaryStage.setTitle("Assignment 3");
        primaryStage.setScene(scene);
        primaryStage.show();

        but.setOnAction(event -> {


            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // clears the rectangle
            HistogramAlphaBet histo = new HistogramAlphaBet(Integer.parseInt(enterN.getText()), temphash);
            PieChart pieC = new PieChart(canvas.getWidth() / 2, canvas.getHeight() / 2, histo.calcP());
            pieC.draw(gc,temphash);


        }
        );



    }

        public static void main(String[] args) {
            launch(args);
        }


    }


