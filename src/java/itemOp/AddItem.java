package itemOp;

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

@WebServlet(urlPatterns = "/AddItem")
public class AddItem extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Adding Item");
        
        Item i=new Item();
        i.setItem_Name(req.getParameter("itemName"));
        i.setBrand(req.getParameter("brand"));
        i.setQuality(req.getParameter("quality"));
        i.setColor(req.getParameter("color"));
        i.setWeight(req.getParameter("weight"));
        i.setStock(req.getParameter("stock"));
        i.setShelf_Location(req.getParameter("shelf"));
        i.setCost_Price(req.getParameter("cPrice"));
        i.setSale_Price(req.getParameter("sPrice"));
        i.setTax(req.getParameter("tax"));
        
        Item_DAO id=new Item_DAO_Implt();
        if(id.insert(i)){
            System.out.println("Item Successfully Inserted");
            resp.sendRedirect("ViewItem.jsp");
        }else{
            System.out.println("Error in adding Item");
            req.setAttribute("message", "<script>alert('ERROR in adding Item');alert('RE-ENTRE the correct and appropriate details.');</script>");
            RequestDispatcher rd=req.getRequestDispatcher("ViewItem.jsp");
            rd.forward(req, resp);
        }
    }
    
}
