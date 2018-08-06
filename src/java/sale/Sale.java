package sale;

public class Sale {
    
    private int serial_No;
    private String Invoice_Number;
    private int Item_Id;
    private int Quantity;

    public int getSerial_No() {
        return serial_No;
    }

    public void setSerial_No(int serial_No) {
        this.serial_No = serial_No;
    }

    public String getInvoice_Number() {
        return Invoice_Number;
    }

    public void setInvoice_Number(String Invoice_Number) {
        this.Invoice_Number = Invoice_Number;
    }

    public int getItem_Id() {
        return Item_Id;
    }

    public void setItem_Id(int Item_Id) {
        this.Item_Id = Item_Id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public Sale() {
    }
    
}
