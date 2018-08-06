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

@WebServlet(urlPatterns = "/AddOrder")
public class Add extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Adding Order");
        
        Item_DAO id=new Item_DAO_Implt();
        Order_DAO od=new Order_DAO_Implt();
        boolean found=false;
        for (Order order : od.getOrders(Integer.valueOf(req.getSession().getAttribute("LogIn").toString()))) {
            if(order.getItem_Id() == Integer.valueOf(req.getParameter("serial"))){
                found=true;
                break;
            }
        }
        if(!found){
            Item i=id.getItem(Integer.valueOf(req.getParameter("serial")));
            if (Integer.valueOf(i.getStock()) > 0 ) {
                Order o=new Order();
                o.setItem_Id(i.getSerial_No());
                o.setQuantity(1);
                o.setLogIn_ID(Integer.valueOf(req.getSession().getAttribute("LogIn").toString()));
                System.out.println(o.getItem_Id());
                System.out.println(o.getQuantity());
                System.out.println(o.getLogIn_ID());
                if(od.insert(o)){
                    System.out.println("Inserted");
                }else{
                    System.out.println("Not Inserted");
                }
                resp.sendRedirect("Sale.jsp?GST="+req.getParameter("tax"));
            }else{
                req.setAttribute("Message", "<script>alert('Out Of Stock!');</script>");
                RequestDispatcher rd=req.getRequestDispatcher("Sale.jsp?GST="+req.getParameter("tax"));
                rd.forward(req, resp);
            }
        }else{
            req.setAttribute("Message", "<script>alert('Already Added!');</script>");
            RequestDispatcher rd=req.getRequestDispatcher("Sale.jsp?GST="+req.getParameter("tax"));
            rd.forward(req, resp);
        }
    }
    
}
