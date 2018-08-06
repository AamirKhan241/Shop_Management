package orderOp;

import items.Item;
import items.Item_DAO;
import items.Item_DAO_Implt;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import orders.Order;
import orders.Order_DAO;
import orders.Order_DAO_Implt;

@WebServlet(urlPatterns = "/UpdateOrder")
public class Update extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String data[]=req.getParameter("id").split(",");
        
        Order_DAO odao=new Order_DAO_Implt();
        Order o=odao.getOrder(Integer.valueOf(data[1]));
        Item_DAO idao=new Item_DAO_Implt();
        Item i=idao.getItem(Integer.valueOf(data[2]));
        if(((o.getQuantity() == 1) && data[0].equals("min")) || (o.getQuantity() == Integer.valueOf(i.getStock()) && data[0].equals("plus"))){    
            if((o.getQuantity() == 1) && data[0].equals("min")){
                odao.delete(o.getSerial_No());
                System.out.println("Sale Updated");
                resp.sendRedirect("Sale.jsp?GST="+req.getParameter("tax"));
            }
            else if((o.getQuantity() == Integer.valueOf(i.getStock())) && data[0].equals("plus")){
                req.setAttribute("Message", "<script>alert('You reached the maximum number of copies!');</script>");
                RequestDispatcher rd=req.getRequestDispatcher("Sale.jsp?GST="+req.getParameter("tax"));
                System.out.println("Sale Updated");
                rd.forward(req, resp);
            }
        }else{        
            if(data[0].equals("plus"))
                o.setQuantity(o.getQuantity()+1);
            else
                o.setQuantity(o.getQuantity()-1);
        
            if(odao.update(o)){
                System.out.println("Order Updated");
                resp.sendRedirect("Sale.jsp?GST="+req.getParameter("tax"));
            }else{
                System.out.println("Error in updating Order");
            }
        }
    }
    
}
