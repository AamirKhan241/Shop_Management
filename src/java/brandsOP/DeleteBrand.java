package brandsOP;

import brands.Brand_DAO;
import brands.Brand_DAO_Implt;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/DeleteBrand")
public class DeleteBrand extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Deleting Brand");
        
        Brand_DAO bd=new Brand_DAO_Implt();
        if(bd.delete(Integer.valueOf(req.getParameter("serial")))){
            System.out.println("Brand successfully deleted");
            resp.sendRedirect("ViewBrands.jsp");
        }else{
            System.out.println("error in deleting brand");
            req.setAttribute("message", "<script>alert('ERROR in deleting Brand!\n Contact ADMIN')</script>");
            RequestDispatcher rd=req.getRequestDispatcher("ViewBrands.jsp");
            rd.forward(req, resp);
        }        
    }
    
}
