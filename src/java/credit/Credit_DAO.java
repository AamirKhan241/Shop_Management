package credit;

import java.util.List;

public interface Credit_DAO {
    
    boolean insert(Credit c);
    boolean delete(int serial_No);
    boolean deleteU(String credittor);
    
    Credit getLastTransaction(String credittor_Name);
    double getTotalAmount(String credittor_Name);
    List<Credit> getCreditHistory(String credittor);
    List<String> getCreditor();
    int getNextSerialNo();
}
