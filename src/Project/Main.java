package Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.DriverManager;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Arrays;
import Exercise_3.HistogramAlphaBet;
import Exercise_3.PieChart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application
{
    public void start(Stage primaryStage)throws SQLException {
        Canvas canvas = new Canvas(600,600);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane flow = new Pane();
        Scene scene = new Scene(flow, canvas.getWidth(), canvas.getHeight());
        TextField enterN = new TextField();

        Map<Character, Double> temphash;
        temphash = GradesandNum();

        flow.getChildren().addAll(canvas);
        primaryStage.setTitle("Project");
        primaryStage.setScene(scene);
        primaryStage.show();

        enterN.setText(String.valueOf(temphash.size()));

        HistogramAlphaBet histo = new HistogramAlphaBet(Integer.parseInt(enterN.getText()), temphash);
        PieChart pieC = new PieChart(canvas.getWidth() / 2, canvas.getHeight() / 2, histo.calcP());
        pieC.draw(gc,temphash);

    }


    public static void main (String args[])
    {
        String t = "Classes";

        try
        {
            Connection conn = null;

            //  Attempt to establish a connection to the specified database via the
            //  DriverManager
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root" , "Root");

            if (conn != null)
            {

                System.out.println("We have connected to our database!");
                PreparedStatement stmt = conn.prepareStatement("CREATE database IF NOT EXISTS Student;");
                stmt.execute();

                Students stu = new Students(conn);
                Courses c = new Courses(conn);
                Classes cl = new Classes(conn);

                // Students
                stu.insert("Jose", "Cruz", "M",conn);
                stu.insert("Izuku", "Midoriya", "M",conn);
                stu.insert("Juna", "Crawford", "F",conn);
                stu.insert("Rose", "Orlando", "F",conn);
                stu.insert("Edward", "Garcia", "M",conn);
                stu.insert("Velvet", "Crowe", "F",conn);

                //Courses
                c.insert("Csc 211", "Computer Science", conn);
                c.insert("Csc 217", "Computer Science", conn);
                c.insert("Csc 220", "Computer Science", conn);
                c.insert("Csc 335", "Computer Science", conn);

                // Classes
                cl.insert(1,1,2019,"FALL", "C",conn);
                cl.insert(1,2,2019,"FALL", "B",conn);
                cl.insert(1,3,2019,"FALL", "A",conn);
                cl.insert(1,4,2019,"FALL", "D",conn);
                cl.insert(2,3,2019,"FALL", "A",conn);
                cl.insert(2,4,2019,"FALL", "F",conn);
                cl.insert(1,5,2019,"FALL", "C",conn);
                cl.insert(1,6,2019,"FALL", "B",conn);
                cl.insert(3,1,2019,"SPRING", "A",conn);
                cl.insert(3,1,2019,"SPRING", "D",conn);
                cl.insert(4,3,2019,"SPRING", "W",conn);
                cl.insert(4,2,2019,"SPRING", "B",conn);
                cl.insert(4,6,2019,"FALL", "F",conn);


                // returns the number of student for 211 and their grades
                System.out.println("Number of Students in 211 Fall 2019: "+Main.numOfStudents(conn));

                // The string t represents the table that would like to be seen
                Main.showValues(conn, t);

                launch(args);
                conn.close();
            }



        }  catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }     catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
     }

    }
    // returns a map of total grades and their count for the piechart
    public Map<Character, Double> GradesandNum() throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root" , "Root");
        Map<Character, Double> temphash = new LinkedHashMap<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT GPA from Classes");

        ResultSet rs = stmt.executeQuery();
        String grades = new String();
        while (rs.next()){
            grades += rs.getString(1);
        }
        char[] temp = grades.toCharArray();
        Arrays.sort(temp);
        String sorted = new String(temp);

        for (char letters : sorted.toCharArray()) {
            // if the hash contains the key then increment by 1, if not then set to 1
            temphash.put(letters, temphash.containsKey(letters) ? (temphash.get(letters) + 1) : 1);
        }
        conn.close();
        return temphash;
    }

    // returns the number of students in a class
    public static int numOfStudents(Connection conn)throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("SELECT * from Classes");
        Map<Character, Integer> temphash = new LinkedHashMap<>();
        String grades = new String();
        ResultSet rs = stmt.executeQuery();

        int count = 0;
        while (rs.next()){
            //courseID number

            if(rs.getInt(2) == 1 && rs.getInt(4) == 2019 && rs.getString(5).equals("FALL")){
                // Returns the number of students in Csc 211 using courseID and checking if its in fall 2019
                // gets the Student Id
                grades += rs.getString(6);
                count +=1;
            }

        }
        for (char letters : grades.toCharArray()) {
            // if the hash contains the key then increment by 1, if not then set to 1
            temphash.put(letters, temphash.containsKey(letters) ? (temphash.get(letters) + 1) : 1);
        }
        for(Map.Entry<Character,Integer> it: temphash.entrySet()){
            System.out.println(it.getKey() + " : " +it.getValue());
        }
        return count;
    }

    public static void showValues(Connection conn, String table)
    {
        try
        {
            String str = "SELECT * FROM ";
            PreparedStatement stmt = conn.prepareStatement(str);
            ResultSet rset = stmt.executeQuery( str+ table);
            Main.showResults(table, rset);
        }  catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void showColumns(Connection conn)
    {
        try
        {
            String str = "SHOW COLUMNS FROM Student";
            PreparedStatement stmt = conn.prepareStatement(str);
            ResultSet rset = stmt.executeQuery(str);
            Main.showResults("Student", rset);
        }  catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void showResults(String tableName, ResultSet rSet)
    {
        try
        {
            ResultSetMetaData rsmd = rSet.getMetaData();
            int numColumns = rsmd.getColumnCount();
            String resultString = null;
            if (numColumns > 0)
            {
                resultString = "\nTable: " + tableName + "\n" +
                        "=======================================================\n";
                for (int colNum = 1; colNum <= numColumns; colNum++)
                    resultString += rsmd.getColumnLabel(colNum) + " ";
            }
            System.out.println(resultString);
            System.out.println(
                    "=======================================================");

            while (rSet.next())
            {
                resultString = "";
                for (int colNum = 1; colNum <= numColumns; colNum++)
                {
                    String column = rSet.getString(colNum);
                    if (column != null)
                        resultString += column + " ";
                }
                System.out.println(resultString + '\n' +
                        "------------------------------------------------------------");
            }
        }  catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


}

