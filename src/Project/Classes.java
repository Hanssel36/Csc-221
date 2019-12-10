package Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Classes {
// Classes(classCode(Primary key), courseID, studentID, year, semester, GPA)


    public Classes(Connection conn){
        String sql = "CREATE TABLE Classes " + "(classCode INT UNSIGNED NOT NULL AUTO_INCREMENT, PRIMARY KEY (classCode), courseID INT UNSIGNED, " +
                "studentID INT UNSIGNED, year INT UNSIGNED, semester VarChar(20), GPA ENUM('A','B','C','D','F','W'))";

        try {

            PreparedStatement pstmt = conn.prepareStatement("drop table if exists Classes;");
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

    public void insert(int cID, int sID, int year, String sem, String GPA, Connection conn)throws SQLException{
        String str = "INSERT Classes (courseID, studentID, year, semester, GPA) VALUES (?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(str);
        pstmt.setInt(1,cID);
        pstmt.setInt(2,sID);
        pstmt.setInt(3,year);
        pstmt.setString(4,sem);
        pstmt.setString(5,GPA);
        pstmt.execute();
    }

}
