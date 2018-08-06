package database;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.Users;
import user.Users_DAO;
import user.Users_DAO_Implt;

@WebServlet(urlPatterns = "/Log")
public class Log extends HttpServlet{
//LogIn
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("uName").toString().equalsIgnoreCase("admin") && req.getParameter("uPass").toString().equalsIgnoreCase("admin")){
            req.getSession().setAttribute("status", "active");
            req.getSession().setAttribute("role", "ADMIN");            
            req.getSession().setAttribute("LogIn", 1);
            System.out.println("LogIn as Admin");
            req.setAttribute("error", "<script>alert('Log In Granted as ADMIN.');</script>");
        }else {
            boolean log=false;
            Users_DAO uDAO=new Users_DAO_Implt();
            List<Users> list=uDAO.getUser();
            for (Users users : list) {
                if(req.getParameter("uName").toString().equalsIgnoreCase(users.getUsername()) && 
                req.getParameter("uPass").toString().equalsIgnoreCase(users.getPassword())){          
                    log=true;
                    req.getSession().setAttribute("status", "active");
                    req.getSession().setAttribute("role", "shop");            
                    req.getSession().setAttribute("LogIn", users.getUser_id());            
                    System.out.println("LogIn as Shop User");
                    req.setAttribute("error", "<script>alert('Log In Granted as Shop User.');</script>");
                }
            }
            if(!log){
                    req.getSession().setAttribute("status", "inactive");
                    req.getSession().setAttribute("role", "null");            
                    req.getSession().setAttribute("LogIn", -1);            
                    System.out.println("LogIn denied");
                    req.setAttribute("error", "<script>alert('Log In DENIED due to wrong credentials.');</script>");
            }
        }
        RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }        
//LogOut
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("status", "inactive");
        req.getSession().setAttribute("role", "null");            
        req.getSession().setAttribute("LogIn", -1);            
        RequestDispatcher rd=req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }
}
