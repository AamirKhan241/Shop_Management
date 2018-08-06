package sale;

import items.Item;
import items.Item_DAO;
import items.Item_DAO_Implt;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Sale_DAO_Impltt implements Sale_DAO{
    private PreparedStatement ps;
    private int i=0;

    @Override
    public boolean insert(Sale s) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("insert into salep values("+getNextSerial()+",?,?,?)");
            
            ps.setString(1, s.getInvoice_Number());
            ps.setInt(2, s.getItem_Id());
            ps.setInt(3, s.getQuantity());
            
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
    public boolean update(Sale s) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("update salep set quantity=? where serial_no=?");
            
            ps.setInt(1, s.getQuantity());
            ps.setInt(2, s.getSerial_No());
            
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
    public Sale getSales(int serial_No) {
        Sale s=new Sale();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Salep where serial_no="+serial_No);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                s.setSerial_No(rs.getInt(1));
                s.setInvoice_Number(rs.getString(2));
                s.setItem_Id(rs.getInt(3));
                s.setQuantity(rs.getInt(4));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public List<Sale> getSales() {
        List<Sale> list=new ArrayList<>();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Salep");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Sale s=new Sale();
                s.setSerial_No(rs.getInt(1));
                s.setInvoice_Number(rs.getString(2));
                s.setItem_Id(rs.getInt(3));
                s.setQuantity(rs.getInt(4));
                list.add(s);
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
            ps=database.Database.getInstance().getConnection().prepareCall("select max(serial_no) m from Salep");
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
    public boolean delete(String invoice) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("delete from salep where invoice_Number=?");
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

    @Override
    public List<Sale> getSales(String invoice) {
        List<Sale> list=new ArrayList<>();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Salep where invoice_number=?");
            ps.setString(1, invoice);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Sale s=new Sale();
                s.setSerial_No(rs.getInt(1));
                s.setInvoice_Number(rs.getString(2));
                s.setItem_Id(rs.getInt(3));
                s.setQuantity(rs.getInt(4));
                list.add(s);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public String getTax(String invoice) {
        double tax=0;
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from salep where invoice_number='"+invoice+"'  order by 1");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Item_DAO id=new Item_DAO_Implt();
                Item it=id.getItem(rs.getInt(3));
                tax+=(Integer.valueOf(it.getSale_Price()) * Integer.valueOf(it.getTax())/100) * rs.getInt(4);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(tax/2);
    }
}
