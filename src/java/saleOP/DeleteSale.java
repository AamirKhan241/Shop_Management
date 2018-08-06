package saleOP;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sale.Sale_DAO;
import sale.Sale_DAO_Impltt;
import saleReport.Sale_Report_DAO;
import saleReport.Sale_Report_DAO_Implt;

@WebServlet(urlPatterns = "/DeleteSale")
public class DeleteSale extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("deleting");
        Sale_DAO sd=new Sale_DAO_Impltt();
        if(sd.delete(req.getParameter("invoice"))){
            System.out.println("Sale Deleted");
            Sale_Report_DAO srd=new Sale_Report_DAO_Implt();
            if(srd.delete(req.getParameter("invoice"))){
                System.out.println("Sale Report deleted");
            }
        }
        resp.sendRedirect("ViewSales.jsp");
    }
    
}
