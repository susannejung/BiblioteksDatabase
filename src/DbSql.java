import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


public class DbSql {
    private Connection connection;
    private Statement stmt;
    private Statement stmt1;

    DbSql() {
        connection = null;
        stmt = null;
        try {
            String url = "jdbc:sqlite:C://Programmering1sem/Databaser/BiblioteksDatabase.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void alleBoeger() {
        try {
            String sql = "select * from Bog";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Connection to SQLite has been established.");
            while (rs.next()) {
                System.out.println(rs.getInt("bogId"));
                System.out.println(rs.getString("titel"));
                System.out.println(rs.getString("forfatter"));
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void udlaanBog(int bogId,int lId,String dato){
        try {
 String sql = "insert into udlaan (bogId,lId,laanDato,afleverDato) values("+String.valueOf(bogId)+","+String.valueOf(lId)+","+dato+","+"NULL"+");";
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
            System.out.println("Connection to SQLite has been established.");

            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void laanerOpl(int lId){
        try {
            String sql = "select * from Laener inner join udlaan on Laener.lId=udlaan.lId inner join Bog on Bog.bogId=udlaan.bogId";
            sql+=" where Laener.lId='"+String.valueOf(lId)+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Connection to SQLite has been established.");
            while (rs.next()) {
                System.out.println(rs.getInt("lId"));
                System.out.println(rs.getString("fnavn"));
                System.out.println(rs.getString("enavn"));
                System.out.println(rs.getString("titel"));
                System.out.println(rs.getString("forfatter"));

            }
            stmt.close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Laener laanerOpl1(int lId){
        Laener l=new Laener();
        l.setlNr(lId);
        try {
            String sql = "select * from Laener where Laener.lId='"+String.valueOf(lId)+"'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Connection to SQLite has been established.");
            if(rs.next()){
                l.setFnavn(rs.getString("fnavn"));
                l.setEnavn(rs.getString("enavn"));
            }
            sql = "select * from udlaan inner join Bog on Bog.bogId=udlaan.bogId";
            sql+=" where udlaan.lId='"+String.valueOf(lId)+"'";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Bog b=new Bog();
                b.setTitel(rs.getString("titel"));
                b.setForfatter(rs.getString("forfatter"));
                l.laenBog(b);
                System.out.println("Hej");
            }
            stmt.close();

            return l;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}



