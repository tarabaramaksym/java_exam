package DAO;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class Order {

  private long id;
  private String description;
  private Date orderDate;
  private int totalCost;
  private long clientId;

  public static final String GET_ALL = "SELECT * FROM HR.Orders";
  public static final String GET_CLIENT = "SELECT * FROM hr.Orders where orders_id = :orderId";

  public Order(long id, String description, Date orderDate, int totalCost, long clientId) {
    this.id = id;
    this.description = description;
    this.orderDate = orderDate;
    this.clientId = clientId;
    this.totalCost = totalCost;
  }

  public static List<Order> getOrders(SimpleDriverDataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    List<Order> objects = jdbcTemplate.query(Order.GET_ALL,
        new OrderRowMapper());

    return objects;
    //
  }

  //
  public static List<Order> getOrders(SimpleDriverDataSource dataSource, long orderId) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    List<Order> objects = jdbcTemplate.query(Order.GET_CLIENT, // текст запроса в котором есть параметр
        new Object[] { orderId }, // список значений для параметров внутри запроса
        new OrderRowMapper());// механизм мэппинга результа запрос в класс коллекции
    return objects;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }

  @Override
  public String toString() {

    return this.description;
  }

}
