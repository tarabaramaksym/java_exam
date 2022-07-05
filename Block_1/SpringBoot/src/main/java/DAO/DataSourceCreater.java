
package DAO;

import java.io.FileInputStream;
import java.io.IOException;
import oracle.jdbc.OracleDriver;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
//для работы со свойствами при помощи Spring
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSourceCreater {
    
    public static SimpleDriverDataSource create() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(OracleDriver.class);//org.sqlite.JDBC
        Properties props = DataSourceCreater.getLoadedProperties();
        
        String url = props.getProperty("oracle.url");
        String driver = props.getProperty("oracle.driver");
        String user = props.getProperty("oracle.user");
        String password = props.getProperty("oracle.password");
        
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        
        return dataSource;
    }
    
    protected static Properties getLoadedProperties()
    {
        Properties prop = new Properties();
        //
        ApplicationContext context = new ClassPathXmlApplicationContext();
        Resource resources = context.getResource("file:src/main/resources/db.properties");
        String path = "db.properties";
        try {
            path = resources.getFile().getPath();
            prop.load(new FileInputStream(path));
        } catch (IOException ex) {
            Logger.getLogger(DataSourceCreater.class.getName()).log(Level.SEVERE, null, ex);
        }
        //
        return prop;
    }
    
}
