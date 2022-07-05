package DataHibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MY_USER")
@SequenceGenerator(name = "My_User_Seq_Gen", sequenceName = "MY_USER_SEQUENCE", allocationSize = 1000, initialValue = 1)

public class User implements Serializable {

  @Override
  public String toString() {
    return this.userName;
  }

  @Id
  @Column(name = "ID", nullable = false)
  private Integer id;
  @Column(name = "USER_NAME", length = 100)
  private String userName;
  @Column(name = "USER_PASS", length = 200)
  private String pass;

  public User() {
  }

  public User(Integer id, String userName, String pass) {
    this.id = id;
    this.userName = userName;
    this.pass = pass;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

}
