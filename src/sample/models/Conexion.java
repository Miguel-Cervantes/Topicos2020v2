package sample.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private static String server="127.0.0.1";//"loclhost"
    //private static String serverLenovo="192.168.1.137";//"loclhost"
    private static String user  ="topicos2020";
    private static String pwd   ="123topicos";
    private static String db    ="restaurante";

    public static Connection con;
    public static void CrearConexion(){

        try{ Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+server+":3306/"+db+"?serverTimezone=UTC",user,pwd);
        }catch(Exception e){e.printStackTrace();}

        /*try{ Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+serverLenovo+":3306/"+db+"?serverTimezone=UTC",user,pwd);
        }catch(Exception e){e.printStackTrace();}*/


    }


}
