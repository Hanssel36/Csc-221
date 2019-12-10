package Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Students {
    // holds information on students


    public Students(Connection conn){

        try {
            String sql = "CREATE TABLE Students " +
                    " (student_ID INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                    " PRIMARY KEY (student_ID), firstName varchar(255), " +
                    " lastName varchar(255), sex ENUM('M', 'F'))";
            PreparedStatement pstmt = conn.prepareStatement("drop table if exists students;");
            PreparedStatement pstmt2 = conn.prepareStatement(sql);
            pstmt.execute();
            pstmt2.execute();


        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }     catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void insert(String fname, String lname, String sex, Connection conn) throws SQLException{
        String str = "INSERT Students (firstName, lastName, sex) VALUES (?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(str);
        pstmt.setString(1,fname);
        pstmt.setString(2,lname);
        pstmt.setString(3,sex);
        pstmt.execute();
        //pstmt.executeUpdate("INSERT Students " + "(firstName, lastName, sex) VALUES ('Juna', 'Crawford', 'F' )");
    }

}
