package brandsOP;

import brands.Brand;
import brands.Brand_DAO;
import brands.Brand_DAO_Implt;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/AddBrand")
public class AddBrand extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Adding Brand");
        
        Brand b=new Brand();
        b.setBrand_Name(req.getParameter("brandName"));
        b.setSupplier_Name(req.getParameter("supplierName"));
        b.setContact_Number(req.getParameter("contactNumber"));
        b.setDescription(req.getParameter("description"));
        
        Brand_DAO bd=new Brand_DAO_Implt();
        if(bd.insert(b)){
            System.out.println("Brand Successfully Inserted");
            resp.sendRedirect("ViewBrands.jsp");
        }else{
            System.out.println("Error in addind brand");
            req.setAttribute("error", "<script>alert('ERROR in adding Brand');alert('RE-ENTRE the correct and appropriate details.');</script>");
            RequestDispatcher rd=req.getRequestDispatcher("AddBrand.jsp");
            rd.forward(req, resp);
        }
    }    
}