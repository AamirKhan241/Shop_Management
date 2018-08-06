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

@WebServlet(urlPatterns = "/UpdateUser")
public class UpdateUser extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Updating User");
        Users_DAO uDAO=new Users_DAO_Implt();
        Users u=uDAO.getUser(Integer.valueOf(req.getParameter("serial")));
        u.setPassword(req.getParameter("password"));
        if(uDAO.update(u))
            System.out.println("User Updated");
        else
            System.out.println("User Cant be Updated");
        
        resp.sendRedirect("ViewUsers.jsp");
    }
    
    
    
}
