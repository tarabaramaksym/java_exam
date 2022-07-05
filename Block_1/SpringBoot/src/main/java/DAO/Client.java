package DAO;

import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

public class Client {

  private long clientId;
  private String name;
  private String mail;
  private String phone;

  public Client(long clientId, String name, String mail, String phone) {
    this.clientId = clientId;
    this.name = name;
    this.mail = mail;
    this.phone = phone;
  }

  @Override
  public String toString() {
    return this.name + " " + this.mail;
  }

  public long getClientId() {
    return clientId;
  }

  public void setClientId(long clientId) {
    this.clientId = clientId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getMail() {
    return mail;
  }

  public void setMail(String email) {
    this.mail = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public static final String GET_ALL = "SELECT CLIENTS_ID, NAME, MAIL, PHONE FROM HR.Clients";
  public static final String GET_CLIENT = "SELECT CLIENTS_ID, NAME, MAIL, PHONE FROM hr.clients where clients_id = :clientId";
  // праметр в запросе или обычный символ вопрос ? (jdbc) или именной при помощи
  // двоеточия

  public static List<Client> getClients(SimpleDriverDataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    List<Client> clients = jdbcTemplate.query(Client.GET_ALL,
        new ClientRowMapper());

    return clients;
    //
  }

  //
  public static List<Client> getClients(SimpleDriverDataSource dataSource, long clientId) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    List<Client> clients = jdbcTemplate.query(Client.GET_CLIENT, // текст запроса в котором есть параметр
        new Object[] { clientId }, // список значений для параметров внутри запроса
        new ClientRowMapper());// механизм мэппинга результа запрос в класс коллекции
    return clients;
  }

}
