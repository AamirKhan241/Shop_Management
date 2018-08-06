package saleReport;

import java.util.List;


public interface Sale_Report_DAO {
    
    boolean insert(Sale_Report sr);
    boolean update(Sale_Report sr);
    boolean delete(String invoice);
    
    Sale_Report getSale_Report(String Invoice_Number);
    List<Sale_Report> getSale_Report();
    
    int getNextSerial();
    String getDate();   
}