package brands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Brand_DAO_Implt implements Brand_DAO{
    private PreparedStatement ps;
    private int i=0;
    @Override
    public boolean insert(Brand b) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("insert into brands values("+getNextSerial()+",?,?,?,?)");
            ps.setString(1, b.getBrand_Name());
            ps.setString(2, b.getSupplier_Name());
            ps.setString(3, b.getContact_Number());
            ps.setString(4, b.getDescription());            
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
    public boolean update(Brand b) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("update brands set brand_name=?,Supplier_name=?,"
                    + "contact_number=?,description=? where serial_no=?");
            ps.setString(1, b.getBrand_Name());
            ps.setString(2, b.getSupplier_Name());
            ps.setString(3, b.getContact_Number());
            ps.setString(4, b.getDescription());
            ps.setInt(5, b.getSerial_No());
            
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
            ps=database.Database.getInstance().getConnection().prepareCall("delete from brands where serial_no="+serial_No);
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
    public Brand getBrand(int serial_No) {
        Brand b=new Brand();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from brands where serial_no="+serial_No);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                b.setSerial_No(rs.getInt(1));
                b.setBrand_Name(rs.getString(2));
                b.setSupplier_Name(rs.getString(3));
                b.setContact_Number(rs.getString(4));
                b.setDescription(rs.getString(5));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public List<Brand> getBrand() {
        List<Brand> list=new ArrayList<>();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from brands");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Brand b=new Brand();
                b.setSerial_No(rs.getInt(1));
                b.setBrand_Name(rs.getString(2));
                b.setSupplier_Name(rs.getString(3));
                b.setContact_Number(rs.getString(4));
                b.setDescription(rs.getString(5));
                list.add(b);
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
            ps=database.Database.getInstance().getConnection().prepareCall("select max(serial_no) m from brands");
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
