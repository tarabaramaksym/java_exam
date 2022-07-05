
package DataHibernate;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USER_INFO")
@SequenceGenerator(name = "UserInfo_Seq_Gen", sequenceName = "USER_INFO_SEQUENCE", allocationSize = 1000, initialValue = 1)
public class UserInfo implements Serializable {

  @Override
  public String toString() {
    return this.email;
  }

  @Id
  @Column(name = "ID", nullable = false)
  private Integer id;
  @Column(name = "EMAIL", length = 100)
  private String email;
  @Column(name = "PHONE", length = 100)
  private String phone;

  @Column(name = "USER_RULE")
  private Integer rule;

  public UserInfo() {
  }

  public UserInfo(Integer id, String email, String phone, Integer rule) {
    this.id = id;
    this.email = email;
    this.phone = phone;
    this.rule = rule;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getRule() {
    return rule;
  }

  public void setRule(Integer rule) {
    this.rule = rule;
  }

}
