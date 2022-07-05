package DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
//Component - Spring аттрибу, указывает, что у нас 
//компонент. который может включатся inject во внутрь другого

@Component
public class ProductRowMapper implements RowMapper<Product> {

  @Override
  public Product mapRow(ResultSet rs, int numRow) throws SQLException {
    //
    long id = rs.getInt("PRODUCTS_ID");
    String NAME = rs.getString("NAME");
    int PRICE = rs.getInt("PRICE");
    Product cl = new Product(id,
        NAME, PRICE);
    //
    return cl;// неполная реализация
  }
}
