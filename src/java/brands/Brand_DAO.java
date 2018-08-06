package brands;

import java.util.List;

public interface Brand_DAO {
    
    boolean insert(Brand b);
    boolean update(Brand b);
    boolean delete(int serial_No);
    
    Brand getBrand(int serial_No);
    List<Brand> getBrand();
    
    int getNextSerial();
}
