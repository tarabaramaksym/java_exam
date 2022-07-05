package JavaMail;

import DataHibernate.HibernateDB;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import javax.servlet.http.HttpSession;

/**
 *
 * @author masya
 */
@WebServlet("/")
public class JavaMailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private static String USER_NAME = "azure.itstep24.kharkov"; // GMail user name (just the part before "@gmail.com")
  private static String PASSWORD = "Azure29$13"; // GMail password
  private static String RECIPIENT1 = "tarabara.maksym@protonmail.com";
  private static String APP_PASSWORD = "lbpabxmyynefoqho"; // App GMail password

  HibernateDB db = new HibernateDB();

  private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
    Properties props = System.getProperties();
    String host = "smtp.gmail.com";
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.user", from);
    props.put("mail.smtp.password", pass);
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
      message.setFrom(new InternetAddress(from));
      InternetAddress[] toAddress = new InternetAddress[to.length];

      // To get the array of addresses
      for (int i = 0; i < to.length; i++) {
        toAddress[i] = new InternetAddress(to[i]);
      }

      for (int i = 0; i < toAddress.length; i++) {
        message.addRecipient(Message.RecipientType.TO, toAddress[i]);
      }

      message.setSubject(subject);
      message.setText(body);
      Transport transport = session.getTransport("smtp");
      transport.connect(host, from, pass);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    } catch (AddressException ae) {
      ae.printStackTrace();
    } catch (MessagingException me) {
      me.printStackTrace();
    }
  }

  /**
   * @see HttpServlet#HttpServlet()
   */
  public JavaMailServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  private boolean login = false;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    
    Object obj = session.getAttribute("login");
    if(obj == null) {
        session.setAttribute("login", false);
    }
    if ((boolean)session.getAttribute("login")) {
      RequestDispatcher dispatcher //
          = this.getServletContext().getRequestDispatcher("/index.jsp");

      dispatcher.forward(request, response);
    } else {
      RequestDispatcher dispatcher //
          = this.getServletContext().getRequestDispatcher("/login.jsp");

      dispatcher.forward(request, response);
    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession(true);
    if ((boolean) session.getAttribute("login")) {
      String email = request.getParameter("email");
      String phone = request.getParameter("phone");
      String message = request.getParameter("message");

      if (!email.isEmpty() && !phone.isEmpty() && !message.isEmpty()) {

        insertLog(email, message);

        String from = USER_NAME;
        String pass = APP_PASSWORD;// PASSWORD;
        String[] to = { email };

        sendFromGMail(from, pass, to, "JavaMail", phone + ". " + message);

      }
    } else {
      String username = request.getParameter("username");
      String pass = request.getParameter("password");
      session.setAttribute("login", db.userIsAuthorized(username, pass));
    }
    doGet(request, response);

  }

  public void insertLog(String email, String messageText) {

    Connection connection = null;

    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");

      Locale locale = java.util.Locale.getDefault();
      java.util.Locale.setDefault(java.util.Locale.ENGLISH);

      connection = DriverManager.getConnection(
          "jdbc:oracle:thin:@localhost:1521:xe", "system",
          "123");

      java.util.Locale.setDefault(locale);

      String call = "DECLARE  EMAIL_PARAM VARCHAR2(200);  MESSAGE_PARAM VARCHAR2(200);  v_Return NUMBER;BEGIN  EMAIL_PARAM := ?;  MESSAGE_PARAM := ?;  v_Return := HR.INSERT_MAIL_LOG(    EMAIL_PARAM => EMAIL_PARAM,    MESSAGE_PARAM => MESSAGE_PARAM  );END;";

      CallableStatement cstmt = connection.prepareCall(call);
      cstmt.setQueryTimeout(1800);
      cstmt.setString(1, email);
      cstmt.setString(2, messageText);

      cstmt.executeUpdate();

    } catch (ClassNotFoundException ex) {
      System.out.printf("Не найден класс драйвера для Оракла. %s", ex.toString());

    } catch (SQLException ex) {
      System.out.printf("Проблемы соединения с сервером. %s", ex.toString());

    }
  }

}