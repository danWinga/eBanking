/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tracking.shared.Products;
import example.upload.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.tracking.server.database.DatabaseBean;
/**
 *
 * @author root
 */
public class DisplayProductImage extends HttpServlet {
    private static final long serialVersionUID = 4593558495041379082L;
 
    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        Statement stmt = null;
        ResultSet rs;
        InputStream sImage;
        try {
 
            String id = request.getParameter("Image_id");
            System.out.println("inside servlet–>" + id);
            DatabaseBean db = new DatabaseBean();
            Connection con = db.getConnection();
            stmt = con.createStatement();
            String strSql = "select images from productGroup where id ='" + id + "' ";//select imagefrom team_players SET image = ? WHERE team_player_id = ?"
            rs = stmt.executeQuery(strSql);
            if (rs.next()) {
                byte[] bytearray = new byte[1048576];
                int size = 0;
                sImage = rs.getBinaryStream(1);
                response.reset();
                response.setContentType("image/jpeg");
                while ((size = sImage.read(bytearray)) != -1) {
                    response.getOutputStream().
                            write(bytearray, 0, size);
                }
            }
            rs.close();
            db.cleanup();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}