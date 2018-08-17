package danielmedalsi1234.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@javax.servlet.annotation.WebServlet(name = "PushContact",urlPatterns = "/PushContact")
public class PushContact extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();

        String first_name = request.getParameter("txt_first_name");
        String last_name = request.getParameter("txt_last_name");
        String phone = request.getParameter("txt_phone");

        printWriter.print("<html>");
        printWriter.print("<body bgcolor=pink>");
        printWriter.print("<br><font color=blue size=15>first_name: "+first_name+"</font>");
        printWriter.print("<br><font color=blue size=15>first_name: "+last_name+"</font>");
        printWriter.print("<br><font color=blue size=15>Phone: "+phone+"</font>");
        printWriter.print("</body>");
        printWriter.print("</html>");

        try {
            insertDataToDatabase(first_name,last_name,phone);
        } catch (Exception e) {
            e.printStackTrace();
        }



    }


    public static void insertDataToDatabase(String first_name,String last_name,String phone)throws Exception{
        String query = "INSERT INTO Contacts(first_name,last_name,phone) VALUES ('"+first_name+"','"+last_name+"','"+phone+"'); ";

        try {
            Connection connection = getConnection();
            PreparedStatement insert = connection.prepareStatement(query);
            insert.executeUpdate();
            System.out.println("try - insert done");
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("try - insert error");
            getConnection().close();
        }finally {
            System.out.println("finaly");
        }
    }

    public static Connection getConnection() throws Exception{
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            //here enter url with/database
            String url = "jdbc:mysql://35.196.251.187:3306/tzlilMakeUp";
            //here enter username for mysql
            String user_name = "danielmedalsi1234";
            //here enter password for mysql
            String password = "de12de9578641";
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, user_name, password);
            System.out.println("connected");
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error connect");
        }
        return null;
    }

}
