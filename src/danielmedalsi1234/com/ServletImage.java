package danielmedalsi1234.com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletImage",urlPatterns = "/ServletImage")
public class ServletImage extends HttpServlet {
    int imageFileLength = 0;
    InputStream imageStream = null;
    File imageFile = null;
    List<Blob> rsArr = new ArrayList();
    private static final String IMAGE_FILE = "/home/danielmedalsi1234/pic1.png";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        /*try {
            pullImagefromDatabase(Img);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("select * from Album");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                byte[] img = rs.getBytes("image");
                ImageIcon image = new ImageIcon(img);
                Image im = image.getImage();
                Image myImage = im.getScaledInstance(100,100,Image.SCALE_SMOOTH);
                ImageIcon newImage = new ImageIcon(myImage);

            }
            ps.close();
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"no data");
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
            String url = "jdbc:mysql://35.196.251.187:3306/tzlilMakeUp";
            //here enter username for mysql
            String user_name = "danielmedalsi1234";
            //here enter password for mysql
            String password = "de12de9578641";
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url, user_name, password);
            System.out.println("connected");
            conn.close();
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error connect");
        }
        return null;
    }

    public static void PullImage(){


    }


}
