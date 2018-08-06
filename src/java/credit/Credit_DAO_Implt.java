package credit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Credit_DAO_Implt implements Credit_DAO{
    private PreparedStatement ps;
    private int i=0;
    @Override
    public boolean insert(Credit c) {
        try {
            ps=database.Database.getInstance().getConnection().prepareStatement("insert into credit(serial_no,credittor_name,"
                    + "amount_creditted,amount_payed,amount_balance,credittor_contact) values("+getNextSerialNo()+",'"+c.getCredittor_Name()+
                    "','"+c.getAmount_Creditted()+"','"+c.getAmount_Payed()+"','"+c.getAmount_Balance()+"','"+c.getCredittor_Contact()
                    +"')");            
            i=ps.executeUpdate();
            ps.close();
            if(i>0)
                return true;
        } catch (Exception e) {
            System.out.println("insertion not successfully");
            e.printStackTrace();
            System.out.println("insertion not successfully");
        }
        return false;
    }

    @Override
    public boolean delete(int serial_No) {
        try {
            ps=database.Database.getInstance().getConnection().prepareStatement("delete from credit where serial_no=?");
            ps.setInt(1, serial_No);
            i=ps.executeUpdate();
            ps.close();
            if(i>0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteU(String credittor_Name) {
        try {
            ps=database.Database.getInstance().getConnection().prepareStatement("delete from credit where credittor_name=?");
            ps.setString(1, credittor_Name);
            i=ps.executeUpdate();
            ps.close();
            if(i>0)
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Credit> getCreditHistory(String credittor_Name) {
        List<Credit> list=new ArrayList<>();
        try {
            if(credittor_Name.equalsIgnoreCase("all") || credittor_Name.equalsIgnoreCase("")){
                ps=database.Database.getInstance().getConnection().prepareStatement("select * from credit order by 1");
            }else{
                ps=database.Database.getInstance().getConnection().prepareStatement("select * from credit where credittor_name='"+credittor_Name+"' order by 1");
            }
            
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Credit c=new Credit();
                c.setSerial_No(rs.getInt(1));;
                c.setCredittor_Name(rs.getString(2));
                c.setDate(rs.getString(3));
                c.setAmount_Creditted(rs.getString(4));
                c.setAmount_Payed(rs.getString(5));
                c.setAmount_Balance(rs.getString(6));
                c.setCredittor_Contact(rs.getString(7));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public int getNextSerialNo() {
        try {
            ps=database.Database.getInstance().getConnection().prepareStatement("select max(serial_no) m from credit");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1)+1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Credit getLastTransaction(String credittor_Name) {
        Credit c=new Credit();
        try {
            ps=database.Database.getInstance().getConnection().prepareStatement("select max(serial_no) m from credit where credittor_name='"+credittor_Name+"'");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                ps=database.Database.getInstance().getConnection().prepareStatement("select * from credit where serial_no="+rs.getInt(1));
                ResultSet rss=ps.executeQuery();
                while (rss.next()) { 
//                    return rss.getInt("Amount_Balance");
                    c.setSerial_No(rss.getInt(1));;
                    c.setCredittor_Name(rss.getString(2));
                    c.setDate(rss.getString(3));
                    c.setAmount_Creditted(rss.getString(4));
                    c.setAmount_Payed(rss.getString(5));
                    c.setAmount_Balance(rss.getString(6));
                    c.setCredittor_Contact(rss.getString(7));
                }
            }
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public List<String> getCreditor() {
        List<String> name=new ArrayList();
        try {
            ps=database.Database.getInstance().getConnection().prepareStatement("select distinct CREDITTOR_NAME from credit");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                name.add(rs.getString(1));             
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return name;
    }
    
    @Override
    public double getTotalAmount(String credittor_Name) {
        double amount=0;
        try {
            if(credittor_Name.equalsIgnoreCase("all") || credittor_Name.equalsIgnoreCase("")){
                ps=database.Database.getInstance().getConnection().prepareStatement("select distinct credittor_name from credit");
                ResultSet rs1=ps.executeQuery();
                while (rs1.next()) {
                    ps=database.Database.getInstance().getConnection().prepareStatement("select max(serial_no) m from credit where credittor_name='"+rs1.getString(1)+"'");
                    ResultSet rs2=ps.executeQuery();
                    while (rs2.next()) {
                        ps=database.Database.getInstance().getConnection().prepareStatement("select amount_balance from credit where serial_no="+rs2.getInt(1));
                        ResultSet rs3=ps.executeQuery();
                        while (rs3.next()) { 
                            amount+=rs3.getDouble(1);
                        }
                    }
                }
            }
            else{
                return Double.valueOf(getLastTransaction(credittor_Name).getAmount_Balance());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }
}
