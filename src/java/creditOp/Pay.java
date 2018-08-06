package creditOp;

import credit.Credit;
import credit.Credit_DAO;
import credit.Credit_DAO_Implt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sale.Sale;
import sale.Sale_DAO;
import sale.Sale_DAO_Impltt;

@WebServlet(urlPatterns = "/Pay")
public class Pay extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Credit_DAO cdao=new Credit_DAO_Implt();
        Credit c=new Credit();
        Credit ct=cdao.getLastTransaction(req.getParameter("namePay"));        
        c.setCredittor_Name(ct.getCredittor_Name());
        c.setCredittor_Contact(ct.getCredittor_Contact());
        c.setAmount_Creditted("0");
        c.setAmount_Payed(req.getParameter("amountToPay"));
        c.setAmount_Balance(String.valueOf(Double.valueOf(ct.getAmount_Balance()) - Double.valueOf(c.getAmount_Payed())));
        
        if(cdao.insert(c))
            System.out.println("Credit History Updated");
        else
            System.out.println("Credit History not Updated");
        
        resp.sendRedirect("ViewCreditHistory.jsp?name="+req.getParameter("name"));
    }    
}
