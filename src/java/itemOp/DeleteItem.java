package itemOp;

import items.Item_DAO;
import items.Item_DAO_Implt;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DeleteItem")
public class DeleteItem extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Deleting Brand");
        
        Item_DAO id=new Item_DAO_Implt();
        if(id.delete(Integer.valueOf(req.getParameter("serial")))){
            System.out.println("Brand successfully deleted");
            resp.sendRedirect("ViewItem.jsp");
        }else{
            System.out.println("error in deleting brand");
            req.setAttribute("message", "<script>alert('ERROR in deleting Brand!\n Contact ADMIN')</script>");
            RequestDispatcher rd=req.getRequestDispatcher("ViewItem.jsp");
            rd.forward(req, resp);
        }        
    }
}
