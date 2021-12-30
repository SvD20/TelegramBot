import com.mysql.cj.xdevapi.Statement;
import org.aopalliance.reflect.Class;
import org.glassfish.grizzly.Connection;

import java.sql.*;
import java.util.ArrayList;

public class Login {
 private Connection con;//структура, содержащая ссылку, логин и пароль для подлкючения к БД
 private Statement st;//структура для запроса в БД
 private ResultSet rs;//структура, содержащая информацию о подключаемой БД

 public Login () {
     try {
         Class.forName("com.mysql.jdbc.Driver");
         con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/Schedules?useUnicode=true&serverTimezone=UTC", "root", "");
         st = con.createStatement();

     }catch  (Exception e) {
         System.out.println("Error: " + e);
     }
 }

 public ArrayList getSchedule_of_teacher () {
     ArrayList<String> list = null;

     try {
         list = new ArrayList<>();
         PreparedStatement st = null;
         String query = "SELECT * FROM Schedule_of_AP";
         st = con.prepareStatement(query);
         rs = st.executeQuery();

         while (rs.next()) { //если находит новые данные
             list.add(rs.getString("schedule")); // добавляем  содержимое schedule столбца в наш список
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }

     return list;
 }

    public ArrayList getSchedule_of_group () {
        ArrayList<String> list = null;

        try {
            list = new ArrayList<>();
            PreparedStatement st = null;
            String query = "SELECT * FROM Schedule_of_914303";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) { //если находит новые данные
                list.add(rs.getString("schedule")); // добавляем schedule ряда в наш список
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


}
