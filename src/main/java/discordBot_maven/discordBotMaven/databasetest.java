package discordBot_maven.discordBotMaven;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class databasetest {
    public static void main(String[] args) {
    	//not really needed but keeping for keepsakes
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            // public static Connection getConnection(String url, String user, String password)
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/discordbot","root",""); 
            
            Statement stmt = connect.createStatement();
           
            String strSelect = "select id, name, gdp from users";
            System.out.println("The SQL query is: " + strSelect); 
            System.out.println();
    
            ResultSet rset = stmt.executeQuery(strSelect);
            
            System.out.println("The records selected are:");
            int rowCount = 0;
            while(rset.next()) {
               int id = rset.getInt("id");
               String name = rset.getString("name");
               int gdp = rset.getInt("gdp");
               System.out.println(id + ", " + name + ", " + gdp);
               ++rowCount;
            }
            System.out.println("Total number of records = " + rowCount);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}