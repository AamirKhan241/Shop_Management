package orders;

import items.Item;
import items.Item_DAO;
import items.Item_DAO_Implt;
import orders.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Order_DAO_Implt implements Order_DAO{
    private PreparedStatement ps;
    private int i=0;
    @Override
    public boolean insert(Order o) {
        try {
            ps=database.Database.getInstance().getConnection().prepareStatement("insert into orders values("+getNextSerial()+",?,?,?)");
            ps.setInt(1, o.getLogIn_ID());
            ps.setInt(2, o.getItem_Id());
            ps.setInt(3, o.getQuantity());
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
    public boolean update(Order o) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("update orders set quantity=?where serial_no=?");
            ps.setInt(1, o.getQuantity());
            ps.setInt(2, o.getSerial_No());
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
    public boolean delete(int serial_No) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("delete from orders where serial_no="+serial_No);
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
    public boolean deleteL(int LogIn) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("delete from orders where user_id="+LogIn);
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
    public Order getOrder(int serial_No) {
        Order i=new Order();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Orders where serial_no="+serial_No);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                i.setSerial_No(rs.getInt(1));
                i.setLogIn_ID(rs.getInt(2));
                i.setItem_Id(rs.getInt(3));
                i.setQuantity(rs.getInt(4));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Order> getOrders() {
        
        List<Order> list=new ArrayList<>();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Orders order by 1");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Order i=new Order();
                i.setSerial_No(rs.getInt(1));
                i.setLogIn_ID(rs.getInt(2));
                i.setItem_Id(rs.getInt(3));
                i.setQuantity(rs.getInt(4));
                list.add(i);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Order> getOrders(int LogIn_id) {
        List<Order> list=new ArrayList<>();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Orders where user_id="+LogIn_id+"  order by 1");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Order i=new Order();
                i.setSerial_No(rs.getInt(1));
                i.setLogIn_ID(rs.getInt(2));
                i.setItem_Id(rs.getInt(3));
                i.setQuantity(rs.getInt(4));
                list.add(i);
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
            ps=database.Database.getInstance().getConnection().prepareCall("select max(serial_no) m from orders");
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
    
    Item_DAO id=new Item_DAO_Implt();
    Item it;
    @Override
    public String getTax(int LogIn) {
        double tax=0;
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Orders where user_id="+LogIn+"  order by 1");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                it=id.getItem(rs.getInt(3));
                tax+=(Integer.valueOf(it.getSale_Price()) * Integer.valueOf(it.getTax())/100) * rs.getInt(4);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(tax/2);
    }

    @Override
    public String getAmount(int LogIn, String tax) {
        int amount=0;
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from Orders where user_id="+LogIn+"  order by 1");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                it=id.getItem(rs.getInt(3));
                if(tax.equalsIgnoreCase("yes"))
                amount+=((Integer.valueOf(it.getSale_Price()) * Integer.valueOf(it.getTax())/100) + Integer.valueOf(it.getSale_Price()))
                        * rs.getInt(4);
                else if(tax.equalsIgnoreCase("no"))
                    amount+=Integer.valueOf(it.getSale_Price())*rs.getInt(4);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(amount);
        
        
    }
    
}
