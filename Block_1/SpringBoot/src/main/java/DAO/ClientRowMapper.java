package DAO;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
//Component - Spring аттрибу, указывает, что у нас 
//компонент. который может включатся inject во внутрь другого

@Component
public class ClientRowMapper implements RowMapper<Client> {

  @Override
  public Client mapRow(ResultSet rs, int numRow) throws SQLException {
    //
    long id = rs.getInt("CLIENTS_ID");
    String NAME = rs.getString("NAME");
    String MAIL = rs.getString("MAIL");
    String PHONE = rs.getString("PHONE");

    Client cl = new Client(id,
        NAME, MAIL, PHONE);
    //
    return cl;// неполная реализация
  }
}
