package DAO;

import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class Product {

  private long productId;
  private String name;
  private int cost;

  public static final String GET_ALL = "SELECT * FROM HR.Products";
  public static final String GET_CLIENT = "SELECT * FROM hr.products where products_id = :productId";
  // праметр в запросе или обычный символ вопрос ? (jdbc) или именной при помощи
  // двоеточия

  public Product(long productId, String name, int cost) {
    this.productId = productId;
    this.name = name;
    this.cost = cost;
  }

  public static List<Product> getProducts(SimpleDriverDataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    List<Product> objects = jdbcTemplate.query(Product.GET_ALL,
        new ProductRowMapper());

    return objects;
    //
  }

  //
  public static List<Product> getProducts(SimpleDriverDataSource dataSource, long clientId) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    List<Product> objects = jdbcTemplate.query(Product.GET_CLIENT, // текст запроса в котором есть параметр
        new Object[] { clientId }, // список значений для параметров внутри запроса
        new ProductRowMapper());// механизм мэппинга результа запрос в класс коллекции
    return objects;
  }

  public long getProductId() {
    return productId;
  }

  @Override
  public String toString() {
    return this.name;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public static String getGetAll() {
    return GET_ALL;
  }

  public static String getGetClient() {
    return GET_CLIENT;
  }

}
