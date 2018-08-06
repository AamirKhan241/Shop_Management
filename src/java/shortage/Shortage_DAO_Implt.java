package shortage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Shortage_DAO_Implt implements Shortage_DAO{
    private PreparedStatement ps;
    private int i=0;
    
    @Override
    public boolean insert(Shortage s) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("insert into shortage(serial_no,item_id,item_name) values("
                    +getNextSerial()+",'"+s.getItem_Id()+"','"+s.getItem_Name()+"')");
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
    public boolean delete(int item_Id) {
            try {
            ps=database.Database.getInstance().getConnection().prepareCall("delete from shortage where item_id="+item_Id);
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
    public List<Shortage> getShortage() {
        List<Shortage> list=new ArrayList<>();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from shortage order by 1");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Shortage s=new Shortage();
                s.setSerial_No(rs.getInt(1));
                s.setItem_Id(rs.getInt(2));
                s.setItem_Name(rs.getString(3));
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
            ps=database.Database.getInstance().getConnection().prepareCall("select count(serial_no) m from shortage");
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
