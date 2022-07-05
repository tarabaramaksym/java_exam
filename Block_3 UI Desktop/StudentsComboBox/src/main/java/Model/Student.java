package Model;

public class Student {

  private int id;
  private String name;
  private int age;
  private float average_rating;
  private String group;

  public Student(int id, String name, int age, float average_rating, String group) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.average_rating = average_rating;
    this.group = group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public float getAverage_rating() {
    return average_rating;
  }

  public void setAverage_rating(float average_rating) {
    this.average_rating = average_rating;
  }

  public String getGroup() {
    return group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  @Override
  public String toString() {
    return name + " " + age + " " + average_rating + " " + group;
  }

}
