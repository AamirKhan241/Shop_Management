package orders;

public class Order {
    
    private int serial_No;
    private int LogIn_ID;
    private int Item_Id;
    private int Quantity;


    public int getSerial_No() {
        return serial_No;
    }

    public void setSerial_No(int serial_No) {
        this.serial_No = serial_No;
    }

    public int getLogIn_ID() {
        return LogIn_ID;
    }

    public void setLogIn_ID(int LogIn_ID) {
        this.LogIn_ID = LogIn_ID;
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

    public Order() {
    }
    
}
