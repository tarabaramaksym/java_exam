package DataLayer;

import java.sql.DriverManager;//управление версиями драйевров

import java.sql.Connection;//механизмы соединения БД
import java.sql.Statement;//для запросов select (update)
import java.sql.ResultSet;//хранилище результата выборки
import java.sql.SQLException;
//select

import java.util.Vector;

public class DataBase {
    public Vector<String> getEmployeesNames()
    {
        Vector<String> names = new Vector<>(1000);
        //
        try {
            //0 драйвер для работы с базой данных
            //1 регистрация класса-драйвера
            Class.forName("org.sqlite.JDBC");
            //2 создание соединения Connection
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\MyData\\test.db");
            //3 Выполнение команды sql
            //для select, updatem insert но НЕ ГОДИТЬСЯ для ХП
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT EMPLOYEESID, EMPLOYEES_NAME, IDCODE FROM EMPLOYEES");
            int num = 0;
            while (rs.next())
            {
                int id = rs.getInt("EMPLOYEESID");
                String str = rs.getString("EMPLOYEES_NAME");
                String idCode = rs.getString("IDCODE");
                String result = "Строка #"+num+" id = "+id+" имя = "+str+" IdCode = "+idCode;
                System.out.println(result);
                num++;
                names.add(str);
            }
            conn.close();//ее место в секции finally
            //
        } catch (ClassNotFoundException ex) {
            System.out.println("не зарегистрирован файл драйвера");
        } catch (SQLException ex) {
            System.out.println("Ошибка в тексте запроса sql");
        }

        //

        return names;
    }
}
