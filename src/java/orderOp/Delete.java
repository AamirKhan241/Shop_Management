package orderOp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import orders.Order_DAO;
import orders.Order_DAO_Implt;

@WebServlet(urlPatterns = "/DeleteOrder")
public class Delete extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Deleting");
        Order_DAO od=new Order_DAO_Implt();
        if(od.delete(Integer.valueOf(req.getParameter("serial")))){
            System.out.println("Deleted");
        }else{
            System.out.println("Not Deleted");
        }
        resp.sendRedirect("Sale.jsp?GST="+req.getParameter("tax"));
    }
    
    
}
