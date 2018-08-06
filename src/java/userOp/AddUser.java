package userOp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.Users;
import user.Users_DAO;
import user.Users_DAO_Implt;

@WebServlet(urlPatterns = "/AddUser")
public class AddUser extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Adding User");
        
        Users u=new Users();
        u.setUsername(req.getParameter("username"));
        u.setPassword(req.getParameter("password"));
        
        Users_DAO uDAO=new Users_DAO_Implt();
        if(uDAO.insert(u)){
            System.out.println("User Added");
        }else{
            System.out.println("User not added");
        }
        resp.sendRedirect("ViewUsers.jsp");        
    }
    
}
