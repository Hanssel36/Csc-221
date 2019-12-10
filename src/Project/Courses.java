package Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Courses {

    public Courses(Connection conn){
        String sql = "CREATE TABLE courses " +
                " (course_ID INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                " PRIMARY KEY (course_ID), courseTitle varchar(15), " +
                " department varchar(100))";
        try {

            PreparedStatement pstmt = conn.prepareStatement("drop table if exists courses;");
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

    public void insert(String ctitle, String dep, Connection conn) throws SQLException{
        String str = "INSERT Courses (courseTitle, department) VALUES (?,?)";
        PreparedStatement pstmt = conn.prepareStatement(str);
        pstmt.setString(1, ctitle);
        pstmt.setString(2,dep);
        pstmt.execute();

    }


}
