package Controller;

import Model.Student;

import javax.swing.*;
import java.util.Vector;

public class StudentComboBoxModel extends DefaultComboBoxModel<Student> {
  public StudentComboBoxModel(Vector<Student> v) {
    super(v);
  }

  @Override
  public Student getSelectedItem() {
    Student selected = (Student) super.getSelectedItem();
    return selected;
  }
}
