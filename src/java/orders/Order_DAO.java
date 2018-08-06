package orders;

import java.util.List;

public interface Order_DAO {
    
    boolean insert(Order o);
    boolean update(Order o);
    boolean delete(int serial_No);
    boolean deleteL(int LogIn);
    
    Order getOrder(int serial_No);
    List<Order> getOrders();
    List<Order> getOrders(int LogIn_id);
    String getTax(int LogIn);
    String getAmount(int LogIn,String tax);
    
    int getNextSerial();
}
