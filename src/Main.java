import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {

        System.out.println("Hello world!");
        DbSql db=new DbSql();
        db.soegBog(1);
        db.udlaanBog(1,2,"201123");
        //db.soegLaaner(1);
        //db.alleLaanere();
        //db.alleBoeger();
        //db.udlaanBog(1,2,"201123");
       // db.udlaanBog(2,2,"201123");
        /*Laener l=db.laanerOpl1(2);
        System.out.println(l.getFnavn()+" "+l.getEnavn()+" har lånt følgende bøger:");
        ArrayList<Bog> liste=l.getLaenteBoeger();
        for(int i=0;i< liste.size();i++){
            System.out.println(liste.get(i));
        }*/
    }
}
