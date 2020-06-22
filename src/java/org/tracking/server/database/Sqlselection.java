/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.server.database;

/**
 *
 * @author root
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sqlselection 
    {
    
     public void dbConnect(String db_connect_string,
            String db_userid,
            String db_password)
   {
      try {
         Class.forName("com.mysql.jdbc.Driver");//"com.microsoft.sqlserver.jdbc.SQLServerDriver"
         Connection conn = DriverManager.getConnection(db_connect_string,
                  db_userid, db_password);
         System.out.println("connected");
         Statement statement = conn.createStatement();
         String queryString = "select * from school.sch_user"; //"select * from dbo.Users "
         ResultSet rs = statement.executeQuery(queryString);//select * from school.sch_user 
         while (rs.next()) {
            System.out.println(rs.getString(1));
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   public static void main(String[] args)
             
   {
       
       String user = "root";//"sa"
       String pass= "";//"citimax@123"
      Sqlselection connServer = new Sqlselection();
      String url = "jdbc:mysql://localhost:3307/school";//"jdbc:sqlserver://192.168.1.10;databaseName=SchoolPro"
      connServer.dbConnect(url, user,
               pass);
   }

 
}

