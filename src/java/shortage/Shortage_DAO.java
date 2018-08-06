package shortage;

import java.util.List;

public interface Shortage_DAO {
    boolean insert(Shortage s);
    boolean delete(int item_Id);
    
    List<Shortage> getShortage();
    int getNextSerial();
}
