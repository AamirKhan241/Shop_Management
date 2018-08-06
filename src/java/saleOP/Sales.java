package saleOP;

import credit.Credit;
import credit.Credit_DAO;
import credit.Credit_DAO_Implt;
import items.Item;
import items.Item_DAO;
import items.Item_DAO_Implt;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import orders.Order;
import orders.Order_DAO;
import orders.Order_DAO_Implt;
import sale.Sale;
import sale.Sale_DAO;
import sale.Sale_DAO_Impltt;
import saleReport.Sale_Report;
import saleReport.Sale_Report_DAO;
import saleReport.Sale_Report_DAO_Implt;
import shortage.Shortage;
import shortage.Shortage_DAO;
import shortage.Shortage_DAO_Implt;

@WebServlet(urlPatterns = "/Sale")
public class Sales extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Selling");
        boolean status=false;
        //generating Invoice
        Sale_Report_DAO srd=new Sale_Report_DAO_Implt();
        Sale_Report sr=new Sale_Report();
        Sale_DAO sd=new Sale_DAO_Impltt();
        Sale s=new Sale();
        Item_DAO id=new Item_DAO_Implt();
        Order_DAO od=new Order_DAO_Implt();
        List<Order> oList=od.getOrders(Integer.valueOf(req.getParameter("LogIn")));
        
        sr.setBuyer_Name(req.getParameter("buyerName"));
        sr.setBuyer_Contact(req.getParameter("buyerContact"));
        sr.setBuyer_Address(req.getParameter("buyerAddress"));
        sr.setBuyer_State(req.getParameter("buyerState"));
        sr.setBuyer_PinCode(req.getParameter("buyerPincode"));
        sr.setBuyer_GSTIN(req.getParameter("buyerGSTIN"));
        sr.setSale_Date(req.getParameter("sDate"));
        sr.setGST(req.getParameter("tax"));
        if(req.getParameter("tax").equalsIgnoreCase("yes")){
            int amount=0;
            for (Order o : oList) 
                amount+=((Integer.valueOf(id.getItem(o.getItem_Id()).getSale_Price()) * Integer.valueOf(id.getItem(o.getItem_Id()).getTax())/100) + Integer.valueOf(id.getItem(o.getItem_Id()).getSale_Price())) * o.getQuantity();            
            sr.setAmount(String.valueOf(amount));
        }else if(req.getParameter("tax").equalsIgnoreCase("NO")){
            int amount=0;
            for (Order o : oList) 
                amount+=Integer.valueOf(id.getItem(o.getItem_Id()).getSale_Price()) * o.getQuantity();            
            sr.setAmount(String.valueOf(amount));
        }
        sr.setTransportation("By: "+req.getParameter("vNumber")+"("+req.getParameter("vContactNumber")+") to "+req.getParameter("sPlace"));
        sr.setCartage(req.getParameter("cartage"));
        sr.setAmount(String.valueOf(Integer.valueOf(sr.getAmount())+Integer.valueOf(sr.getCartage())));
        
        if(Integer.valueOf(sr.getAmount()) != 0){
            if(req.getParameter("transactionMode").equalsIgnoreCase("Cash"))
                sr.setPayment_Mode("CASH");
            else if(req.getParameter("transactionMode").equalsIgnoreCase("Credit")){
                sr.setPayment_Mode("Credit");
                
                Credit_DAO cdao=new Credit_DAO_Implt();
                Credit c=new Credit();
                c.setCredittor_Name(sr.getBuyer_Name());
                c.setAmount_Creditted(sr.getAmount());
                c.setAmount_Payed("0");
                c.setAmount_Balance(
                        String.valueOf(
                                Double.parseDouble(
                                        cdao.getLastTransaction(sr.getBuyer_Name()).getAmount_Balance()
                                ) 
                                + 
                                Integer.parseInt(c.getAmount_Creditted())
                        )
                );
                c.setCredittor_Contact(sr.getBuyer_Contact());
                
                if(cdao.insert(c)){
                    System.out.println("Credit success");                    
                }else{
                    System.out.println("Credit Failure");
                }
            }
            else if(req.getParameter("transactionMode").equalsIgnoreCase("Cheque"))
                sr.setPayment_Mode("Cheque - "+req.getParameter("chqNumber")+" of "+req.getParameter("bName"));
        
        if(srd.insert(sr)){
            System.out.println("Sale Report Generated");
            sr=srd.getSale_Report("APHSS-"+String.format("%010d", srd.getNextSerial()-1));
            System.out.println("Generating Sale");
            s.setInvoice_Number("APHSS-"+String.format("%010d", srd.getNextSerial()-1));
            for (Order order : oList) {
                s.setItem_Id(order.getItem_Id());
                s.setQuantity(order.getQuantity());
                if(sd.insert(s)){
                    System.out.println("Sale generated");
                    od.deleteL(Integer.valueOf(req.getSession().getAttribute("LogIn").toString()));
                    Item i=id.getItem(order.getItem_Id());
                    i.setStock(String.valueOf(Integer.valueOf(i.getStock())-order.getQuantity()));
                    if(id.update(i)){
                        System.out.println("item updated");
                        status=true;
                        if(Integer.valueOf(i.getStock()) <= 5){
                            Shortage sh=new Shortage();
                            sh.setItem_Id(i.getSerial_No());
                            sh.setItem_Name(i.getItem_Name());

                            Shortage_DAO shdao=new Shortage_DAO_Implt();

                            if(shdao.insert(sh))
                                System.out.println("Shortage Updated");
                            else
                                System.out.println("Shortage Not Updated");
                        }
                    }else{
                        status=false;
                        sd.delete(s.getInvoice_Number());
                        srd.delete(s.getInvoice_Number());
                        System.out.println("error In updating Item");
                        break;
                    }
                }else{
                    status=false;
                    sd.delete(sr.getInvoice_Number());
                    srd.delete(sr.getInvoice_Number());
                    System.out.println("error In generating Sale");
                    break;
                }
            }            
        }else{
            System.out.println("Error in generating Sale Report");
            status=false;
        }
        if(status)
            resp.sendRedirect("ViewSales.jsp");
        }
        else
            resp.sendRedirect("Sale.jsp?GST="+req.getParameter("tax"));
        
//            resp.sendRedirect("GetInvoice.jsp?GST="+req.getParameter("tax")+"&invoice="+s.getInvoice_Number());
    }    
}
