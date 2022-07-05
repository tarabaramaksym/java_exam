package com.sourceit.springjdbcfororacle;

import DAO.Client;
//jdbc
import DAO.DataSourceCreater;
import DAO.Order;
import DAO.Product;

// 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import oracle.jdbc.OracleDriver;
//spring jdbc
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
//от оракла
import oracle.jdbc.pool.OracleDataSource;//для sqlite нет, нужно использовать "стандарт"
//
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpringJdbcForOracle {

  public static void main(String[] args) {
    System.out.println("-------- Oracle JDBC Connection Testing ------");
    java.util.Locale locale = java.util.Locale.getDefault();
    java.util.Locale.setDefault(java.util.Locale.ENGLISH);
    SimpleDriverDataSource dataSource = DataSourceCreater.create();

    List<Order> clients = Order.getOrders(dataSource);
    clients.forEach(it -> System.out.println(it));
    java.util.Locale.setDefault(locale);
  }

}
