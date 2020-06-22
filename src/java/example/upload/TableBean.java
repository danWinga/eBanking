/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.upload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.tracking.server.database.DatabaseBean;

/**
 *
 * @author root
 */
@ManagedBean(name = "tableBean")
@SessionScoped
 
public class TableBean {
    private String imageID;
    private String imageName;
    
 
    public String getImageName() {
        return imageName;
    }
 
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
 
    public String getImageLength() {
        return imageLength;
    }
 
    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    private String imageLength;
 
    public String getImageID() {
        return imageID;
    }
 
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
 
    public List<TableBean> getAllImage() throws Exception {
        List<TableBean> imageInfo = new ArrayList<TableBean>();
        
        try {
            DatabaseBean db = new DatabaseBean();
            Connection con = db.DBconnect() ;
            stmt = con.createStatement();
            String strSql = "select * from team_players order by team_player_id ";//insert into team_players (image,details) values(?,?)
            //System.err.println("*select all***" + strSql);
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                TableBean tbl = new TableBean();
                tbl.setImageID(rs.getString("team_player_id"));
                tbl.setImageName(rs.getString("details"));
                imageInfo.add(tbl);
            }
            rs.close();
            db.cleanup();
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
}
