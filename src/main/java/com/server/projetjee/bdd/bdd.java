package com.server.projetjee.bdd;

import java.sql.*;
import com.server.projetjee.api.athletes;

public class bdd {

    private String url;
    private String user;
    private String password;

    private void add_athele(athletes athele) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    this.url,this.user,this.password);
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("insert into Athlete values (\""+athele.getNom()+"\",\""+athele.getPrenom()+"\",\""+athele.getSexe()+"\",\""+athele.getNaissance()+"\",\""+athele.getClub()+"\",\""+athele.getDiscipline()+"\")");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }



}
