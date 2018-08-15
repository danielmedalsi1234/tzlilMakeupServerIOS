package danielmedalsi1234.com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "ServletImage",urlPatterns = "/ServletImage")
public class ServletImage extends HttpServlet {
    int imageFileLength = 0;
    InputStream imageStream = null;
    File imageFile = null;
    private static final String IMAGE_FILE = "/home/danielmedalsi1234/pic1.png";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = response.getWriter();
        File Img = new File(request.getParameter("Img"));
        printWriter.print("<html>");
        printWriter.print("<body bgcolor=blue>");
        printWriter.print("<br><font color=blue size=15>first_name: "+Img+"</font>");

        printWriter.print("</body>");
        printWriter.print("</html>");
        try {
            pullImagefromDatabase(Img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pullImagefromDatabase(File blob)throws Exception{

        //String query = "INSERT INTO Album(image) VALUES ('"+blob+"'); ";
        String query2 = "select * from images;";

        try {
            Connection connection = getConnection();
            PreparedStatement insertImage = connection.prepareStatement(query2);
            insertImage.executeUpdate();
            connection.close();
            System.out.println("try - insert done");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("try - insert error");
        }finally {
            System.out.println("finaly");
        }
    }

    public static Connection getConnection() throws Exception{
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            //here enter url with/database
            String url = "jdbc:mysql://104.196.206.60:3306/tzlilMakeUp";
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
