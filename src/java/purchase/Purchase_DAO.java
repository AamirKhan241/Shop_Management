package purchase;

import java.util.List;

public interface Purchase_DAO {
    boolean insert(Purchase p);
    boolean update(Purchase p);
    boolean delete(String purchase_Note);
    
    Purchase getPurchase(String purchase_Note);
    List<Purchase> getPurchase();
    int getNextSerial();
    
}
