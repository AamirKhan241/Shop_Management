package saleReport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Sale_Report_DAO_Implt implements Sale_Report_DAO{
    private PreparedStatement ps;
    private int i=0;
    
    @Override
    public boolean insert(Sale_Report sr) {

        try {
            ps=database.Database.getInstance().getConnection().prepareCall("insert into Sale_ReportP(serial_no ,invoice_number ,"
                    + "Buyer_name ,Buyer_contact ,buyer_address ,buyer_state ,buyer_pincode ,Buyer_GSTIN ,amount ,payment_Mode ,"
                    + "transportation,GST,cartage ) values("+getNextSerial()+
                    ",'APHSS-"+String.format("%010d", getNextSerial())+"','"+sr.getBuyer_Name()+
                    "','"+sr.getBuyer_Contact()+"','"+sr.getBuyer_Address()+"','"+sr.getBuyer_State()+"','"+sr.getBuyer_PinCode()
                    +"','"+sr.getBuyer_GSTIN()+"','"+sr.getAmount()+"','"+sr.getPayment_Mode()
                    +"','"+sr.getTransportation()+"','"+sr.getGST()+"','"+sr.getCartage()+"')");
            
            i=ps.executeUpdate();
            ps.close();
            if(i>0){
                i=0;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Sale_Report sr) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("update Sale_Reportp set Buyer_name=?,Buyer_contact=?,buyer_address=?,buyer_state=?,buyer_pincode=?,"
                    + "Buyer_GSTIN=?,sale_date=?,amount=?,payment_Mode=?,transportation=?,GST=?,cartage=? where serial_no=?");
            
            ps.setString(1, sr.getBuyer_Name());
            ps.setString(2, sr.getBuyer_Contact());
            ps.setString(3, sr.getBuyer_Address());
            ps.setString(4, sr.getBuyer_State());
            ps.setString(5, sr.getBuyer_PinCode());
            ps.setString(6, sr.getBuyer_GSTIN());
            ps.setString(7, sr.getSale_Date());
            ps.setString(8, sr.getAmount());
            ps.setString(9, sr.getPayment_Mode());
            ps.setString(10, sr.getTransportation());
            ps.setString(11, sr.getGST());
            ps.setString(12, sr.getCartage());
            ps.setInt(13, sr.getSerial_No());
            i=ps.executeUpdate();
            ps.close();
            if(i>0){
                i=0;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Sale_Report getSale_Report(String Invoice_Number) {
        Sale_Report sr=new Sale_Report();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Sale_Reportp where invoice_number=?");
            ps.setString(1, Invoice_Number);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                sr.setSerial_No(rs.getInt(1));
                sr.setInvoice_Number(rs.getString(2));
                sr.setBuyer_Name(rs.getString(3));
                sr.setBuyer_Contact(rs.getString(4));
                sr.setBuyer_Address(rs.getString(5));
                sr.setBuyer_State(rs.getString(6));
                sr.setBuyer_PinCode(rs.getString(7));
                sr.setBuyer_GSTIN(rs.getString(8));
                sr.setSale_Date(rs.getString(9));
                sr.setAmount(rs.getString(10));
                sr.setPayment_Mode(rs.getString(11));
                sr.setTransportation(rs.getString(12));
                sr.setGST(rs.getString(13));
                sr.setCartage(rs.getString(14));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sr;
    }

    @Override
    public List<Sale_Report> getSale_Report() {
        List<Sale_Report> list=new ArrayList<>();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Sale_Reportp order by 2 desc");
            
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Sale_Report sr=new Sale_Report();
                
                sr.setSerial_No(rs.getInt(1));
                sr.setInvoice_Number(rs.getString(2));
                sr.setBuyer_Name(rs.getString(3));
                sr.setBuyer_Contact(rs.getString(4));
                sr.setBuyer_Address(rs.getString(5));
                sr.setBuyer_State(rs.getString(6));
                sr.setBuyer_PinCode(rs.getString(7));
                sr.setBuyer_GSTIN(rs.getString(8));
                sr.setSale_Date(rs.getString(9));
                sr.setAmount(rs.getString(10));
                sr.setPayment_Mode(rs.getString(11));
                sr.setTransportation(rs.getString(12));
                sr.setGST(rs.getString(13));
                sr.setCartage(rs.getString(14));
                list.add(sr);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getNextSerial() {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select count(serial_no) m from Sale_Reportp");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                return rs.getInt("m")+1;
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    @Override
    public boolean delete(String invoice) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("delete from sale_Reportp where invoice_Number=?");
            ps.setString(1, invoice);
            i=ps.executeUpdate();
            ps.close();
            if(i>0){
                i=0;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
