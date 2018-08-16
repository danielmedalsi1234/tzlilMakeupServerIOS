package danielmedalsi1234.com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PullRequest" , urlPatterns = "/PullRequest")
public class PullRequest extends HttpServlet {
    List<String> rsArr = new ArrayList();
    PushContact pushContact;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //send data to client
        PullNamesFromContacts();
        for (String name : rsArr) {
            response.getWriter().write(name);
        }
        rsArr.removeAll(rsArr);
    }
    public void PullNamesFromContacts(){
        ResultSet rs;
        PreparedStatement ps;
        try {
            pushContact.getConnection();
            System.out.println("in connection");
            ps = pushContact.getConnection().
                    prepareStatement("select * from Contacts");
            rs = ps.executeQuery();

            while (rs.next()) {
                rsArr.add(rs.getString(1) + ",");

            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error!!!");
        }
    }
}
