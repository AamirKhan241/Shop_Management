package items;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Item_DAO_Implt implements Item_DAO{
    private PreparedStatement ps;
    private int ii=0;
    @Override
    public boolean insert(Item i) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("insert into item values("+getNextSerial()+",?,"
                    + "?,?,?,?,?,?,?,?,?)");
            ps.setString(1, i.getItem_Name());
            ps.setString(2, i.getBrand());
            ps.setString(3, i.getQuality());
            ps.setString(4, i.getColor());
            ps.setString(5, i.getWeight());
            ps.setString(6, i.getStock());
            ps.setString(7, i.getShelf_Location());
            ps.setString(8, i.getCost_Price());
            ps.setString(9, i.getSale_Price());
            ps.setString(10, i.getTax());
            ii=ps.executeUpdate();
            ps.close();
            if(ii>0){
                ii=0;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Item i) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("update item set item_name=?,brand=?,"
                    + "quality=?,color=?,weight=?,stock=?,shelf_location=?,cost_price=?,sale_price=?,tax=?"
                    + " where serial_no=?");
            
            ps.setString(1, i.getItem_Name());
            ps.setString(2, i.getBrand());
            ps.setString(3, i.getQuality());
            ps.setString(4, i.getColor());
            ps.setString(5, i.getWeight());
            ps.setString(6, i.getStock());
            ps.setString(7, i.getShelf_Location());
            ps.setString(8, i.getCost_Price());
            ps.setString(9, i.getSale_Price());
            ps.setString(10, i.getTax());
            ps.setInt(11, i.getSerial_No());
            ii=ps.executeUpdate();
            ps.close();
            if(ii>0){
                ii=0;
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
            ps=database.Database.getInstance().getConnection().prepareCall("delete from item where serial_no="+serial_No);
            ii=ps.executeUpdate();
            ps.close();
            if(ii>0){
                ii=0;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Item getItem(int serial_No) {
        Item i=new Item();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from item where serial_no="+serial_No);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                i.setSerial_No(rs.getInt(1));
                i.setItem_Name(rs.getString(2));
                i.setBrand(rs.getString(3));
                i.setQuality(rs.getString(4));
                i.setColor(rs.getString(5));
                i.setWeight(rs.getString(6));
                i.setStock(rs.getString(7));
                i.setShelf_Location(rs.getString(8));
                i.setCost_Price(rs.getString(9));
                i.setSale_Price(rs.getString(10));
                i.setTax(rs.getString(11));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    @Override
    public List<Item> getItem() {
        List<Item> list=new ArrayList<>();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from item ORDER BY 2");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Item i=new Item();
                i.setSerial_No(rs.getInt(1));
                i.setItem_Name(rs.getString(2));
                i.setBrand(rs.getString(3));
                i.setQuality(rs.getString(4));
                i.setColor(rs.getString(5));
                i.setWeight(rs.getString(6));
                i.setStock(rs.getString(7));
                i.setShelf_Location(rs.getString(8));
                i.setCost_Price(rs.getString(9));
                i.setSale_Price(rs.getString(10));
                i.setTax(rs.getString(11));
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
            ps=database.Database.getInstance().getConnection().prepareCall("select max(serial_no) m from item");
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
 
}
