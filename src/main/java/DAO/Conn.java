package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conn  {
    public static Connection Con;
    private static Connection Conn() throws ClassNotFoundException {
        String url = "jdbc:postgresql://localhost:5432/prog4";//jdbc:"driver"://"url_do_banco":"porta"/"nome_base_dados"
        String user = "postgres";
        String password = "postgres";
        try {
            Class.forName("org.postgresql.Driver");
            Con = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return Con;
    }

    public static Connection getInstance(){
        if(Con == null){
            try {
                Con =  Conn();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return  Con;
    }
}
