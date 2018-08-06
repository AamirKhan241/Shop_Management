package sale;

import java.util.List;

public interface Sale_DAO {
    
    boolean insert(Sale s);
    boolean update(Sale s);
    boolean delete(String invoice);
    
    Sale getSales(int serial_No);
    List<Sale> getSales(String invoice);
    List<Sale> getSales();
    String getTax(String invoice);
    int getNextSerial();    
}
