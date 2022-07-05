
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class InsertProcedures {

  Connection connection = null;

  public void insertClient(String name, String phone, String email) {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      Locale locale = java.util.Locale.getDefault();
      java.util.Locale.setDefault(java.util.Locale.ENGLISH);

      connection = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:xe", "system",
          "123");

      java.util.Locale.setDefault(locale);

      String call = "DECLARE  NAME_PARAM VARCHAR2(200);  PHONE_PARAM VARCHAR2(200);  EMAIL_PARAM VARCHAR2(200);  v_Return NUMBER;BEGIN  NAME_PARAM := ?;  PHONE_PARAM := ?;  EMAIL_PARAM :=?;  v_Return := hr.INSERT_CLIENT(    NAME_PARAM => NAME_PARAM,    PHONE_PARAM => PHONE_PARAM,    EMAIL_PARAM => EMAIL_PARAM  );END;";

      CallableStatement cstmt = connection.prepareCall(call);
      cstmt.setQueryTimeout(1800);
      cstmt.setString(1, name);
      cstmt.setString(2, phone);
      cstmt.setString(3, email);

      cstmt.executeUpdate();

    } catch (ClassNotFoundException ex) {
      System.out.printf("Не найден класс драйвера для Оракла. %s", ex.toString());

    } catch (SQLException ex) {
      System.out.printf("Проблемы соединения с сервером. %s", ex.toString());

    }
  }

  public void insertLog(String action, String table, int rowId, String logInfo) {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      Locale locale = java.util.Locale.getDefault();
      java.util.Locale.setDefault(java.util.Locale.ENGLISH);

      connection = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:xe", "system",
          "123");

      java.util.Locale.setDefault(locale);

      String call = "DECLARE  ACTION VARCHAR2(200);  TBL_NAME VARCHAR2(200);  ID_ROW NUMBER;  LOG_INFO VARCHAR2(200);  LOG_TIME DATE;  v_Return NUMBER;BEGIN "
          +
          "ACTION := ?;  TBL_NAME := ?;  ID_ROW := ?;  LOG_INFO := ?;  LOG_TIME := ?;  v_Return := HR.INSERT_MY_LOGS(    ACTION => ACTION,    TBL_NAME => TBL_NAME,    ID_ROW => ID_ROW,    LOG_INFO => LOG_INFO,    LOG_TIME => LOG_TIME  );END;";

      CallableStatement cstmt = connection.prepareCall(call);
      cstmt.setQueryTimeout(1800);
      cstmt.setString(1, action);
      cstmt.setString(2, table);
      cstmt.setInt(3, rowId);
      cstmt.setString(4, logInfo);
      cstmt.setDate(5, new Date(new java.util.Date().getTime()));

      cstmt.executeUpdate();

    } catch (ClassNotFoundException ex) {
      System.out.printf("Не найден класс драйвера для Оракла. %s", ex.toString());

    } catch (SQLException ex) {
      System.out.printf("Проблемы соединения с сервером. %s", ex.toString());

    }
  }

  public void insertOrder(String description, int totalCost, int clientId) {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      Locale locale = java.util.Locale.getDefault();
      java.util.Locale.setDefault(java.util.Locale.ENGLISH);

      connection = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:xe", "system",
          "123");

      java.util.Locale.setDefault(locale);

      String call = "DECLARE  DESCRIPTION VARCHAR2(200);  ORDERS_DATE VARCHAR2(200);  TOTAL_COSTS NUMBER;  CLIENTS_ID NUMBER;  v_Return NUMBER;BEGIN "
          +
          "DESCRIPTION := ?;  ORDERS_DATE := ?;  TOTAL_COSTS := ?;  CLIENTS_ID := ?;  v_Return := HR.INSERT_ORDERS(    DESCRIPTION => DESCRIPTION,    ORDERS_DATE => ORDERS_DATE,    TOTAL_COSTS => TOTAL_COSTS,    CLIENTS_ID => CLIENTS_ID  );  END;";

      CallableStatement cstmt = connection.prepareCall(call);
      cstmt.setQueryTimeout(1800);
      cstmt.setString(1, description);
      cstmt.setDate(2, new Date(new java.util.Date().getTime()));
      cstmt.setInt(3, totalCost);
      cstmt.setInt(4, clientId);

      cstmt.executeUpdate();

    } catch (ClassNotFoundException ex) {
      System.out.printf("Не найден класс драйвера для Оракла. %s", ex.toString());

    } catch (SQLException ex) {
      System.out.printf("Проблемы соединения с сервером. %s", ex.toString());

    }
  }
  
    public void insertProduct(String name, int cost) {
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      Locale locale = java.util.Locale.getDefault();
      java.util.Locale.setDefault(java.util.Locale.ENGLISH);

      connection = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:xe", "system",
          "123");

      java.util.Locale.setDefault(locale);

      String call = "DECLARE  NAME VARCHAR2(200);  PRICE NUMBER;  v_Return NUMBER;" + 
              "BEGIN  NAME := ?;  PRICE := ?;  v_Return := HR.INSERT_PRODUCTS(    NAME => NAME,    PRICE => PRICE  );END;";
         
      CallableStatement cstmt = connection.prepareCall(call);
      cstmt.setQueryTimeout(1800);
      cstmt.setString(1, name);
      cstmt.setInt(2, cost);

      cstmt.executeUpdate();

    } catch (ClassNotFoundException ex) {
      System.out.printf("Не найден класс драйвера для Оракла. %s", ex.toString());

    } catch (SQLException ex) {
      System.out.printf("Проблемы соединения с сервером. %s", ex.toString());

    }
  }

}
