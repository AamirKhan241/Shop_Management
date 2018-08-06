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

@WebServlet(urlPatterns = "/UpdateBrand")
public class UpdateBrand extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Updating Brand");
        Brand b=new Brand();
        b.setSerial_No(Integer.valueOf(req.getParameter("serial")));
        b.setBrand_Name(req.getParameter("brandName"));
        b.setSupplier_Name(req.getParameter("supplierName"));
        b.setContact_Number(req.getParameter("contactNumber"));
        b.setDescription(req.getParameter("description"));
        
        Brand_DAO bd=new Brand_DAO_Implt();
        if(bd.update(b)){
            System.out.println("Brand Updated Successfully");
            resp.sendRedirect("ViewBrands.jsp");
        }else{
            System.out.println("Error In updating brand");
            req.setAttribute("error", "<script>alert('ERROR in updating Brand');</script>");
            RequestDispatcher rd=req.getRequestDispatcher("UpdateBrand.jsp?serial="+b.getSerial_No());
            rd.forward(req, resp);
        }
    }    
}
