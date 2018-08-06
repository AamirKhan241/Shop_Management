package brands;

public class Brand {
    private int serial_No;
    private String Brand_Name;
    private String Supplier_Name;
    private String Contact_Number;
    private String Description;

    public int getSerial_No() {
        return serial_No;
    }

    public void setSerial_No(int serial_No) {
        this.serial_No = serial_No;
    }

    public String getBrand_Name() {
        return Brand_Name;
    }

    public void setBrand_Name(String Brand_Name) {
        this.Brand_Name = Brand_Name;
    }

    public String getSupplier_Name() {
        return Supplier_Name;
    }

    public void setSupplier_Name(String Supplier_Name) {
        this.Supplier_Name = Supplier_Name;
    }

    public String getContact_Number() {
        return Contact_Number;
    }

    public void setContact_Number(String Contact_Number) {
        this.Contact_Number = Contact_Number;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Brand() {
    }
    
}
