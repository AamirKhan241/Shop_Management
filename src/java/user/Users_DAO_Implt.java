package user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Users_DAO_Implt implements Users_DAO{
    private PreparedStatement ps;
    private int i=0;
    @Override
    public boolean insert(Users u) {
                try {
            ps=database.Database.getInstance().getConnection().prepareCall("insert into users values("+getNextUserId()+",?,?)");
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
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
    public boolean update(Users u) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("update users set password=? where user_id=?");
            ps.setString(1, u.getPassword());
            ps.setInt(2, u.getUser_id());
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
    public boolean delete(int user_id) {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("delete from users where user_id="+user_id);
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
    public Users getUser(int user_id) {
        Users u=new Users();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from users where user_id="+user_id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                u.setUser_id(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public List<Users> getUser() {
        List<Users> list=new ArrayList<>();
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select * from users order by 1");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {                
                Users u=new Users();
                u.setUser_id(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                list.add(u);
            }
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getNextUserId() {
        try {
            ps=database.Database.getInstance().getConnection().prepareCall("select max(user_id) m from users");
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
