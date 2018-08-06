package userOp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.Users_DAO;
import user.Users_DAO_Implt;

@WebServlet(urlPatterns = "/DeleteUser")
public class DeleteUser extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Deleting User");
        Users_DAO uDAO=new Users_DAO_Implt();
        if(uDAO.delete(Integer.valueOf(req.getParameter("serial")))){
            System.out.println("User Deleted");
        }else{
            System.out.println("User can't be Deleted");
        }
        resp.sendRedirect("ViewUsers.jsp");
    }    
}
