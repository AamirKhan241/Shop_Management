package items;

import java.util.List;

public interface Item_DAO {
    
    boolean insert(Item i);
    boolean update(Item i);
    boolean delete(int Serial_No);
    
    Item getItem(int Serial_No);
    List<Item> getItem();
    int getNextSerial();
}
