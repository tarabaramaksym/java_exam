package View;

import Controller.StudentComboBoxModel;
import Model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

public class MyWindow extends JFrame implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() instanceof JButton) {
      JButton btn = (JButton) e.getSource();
      if (btn == btnSelect) {
        Student selected = (Student) cbox.getSelectedItem();
        JOptionPane.showMessageDialog(MyWindow.this,
            selected);
      }
      if (btn == btnRemove) {

      }
    }
  }

  JComboBox<Student> cbox;
  Vector<Student> students;
  JButton btnSelect;
  JButton btnRemove;

  public MyWindow() {
    this.setTitle("JComboBox tester");
    this.setSize(600, 75);
    this.setLayout(new FlowLayout(FlowLayout.LEFT));

    students = new Vector<Student>(100);
    students.add(new Student(1, "Petr", 22, (float) 99.99, "35pr30"));
    students.add(new Student(2, "Ivan", 31, (float) 96.54, "35pr32"));
    students.add(new Student(3, "Anna", 36, (float) 82.13, "35pr31"));

    StudentComboBoxModel model = new StudentComboBoxModel(students);
    this.cbox = new JComboBox<>();
    this.cbox.setModel(model);

    this.add(cbox);
    //
    this.btnSelect = new JButton("Выбрать");
    this.btnSelect.addActionListener(this);
    this.btnRemove = new JButton("Удалить");
    this.btnRemove.addActionListener(this);
    this.add(btnSelect);
    this.add(btnRemove);

  }

}
