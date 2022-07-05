package DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
//Component - Spring аттрибу, указывает, что у нас 
//компонент. который может включатся inject во внутрь другого

@Component
public class OrderRowMapper implements RowMapper<Order> {

  @Override
  public Order mapRow(ResultSet rs, int numRow) throws SQLException {
    //
    long id = rs.getInt("ORDERS_ID");
    String DESC = rs.getString("DESCRIPTION");
    Date ORDERS_DATE = rs.getDate("ORDERS_DATE");
    int TOTAL_COST = rs.getInt("TOTAL_COSTS");
    long CLIENT_ID = rs.getLong("CLIENT_ID");
    Order cl = new Order(id,
        DESC, ORDERS_DATE, TOTAL_COST, CLIENT_ID);
    //
    return cl;// неполная реализация
  }
}
