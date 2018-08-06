package purchase;

public class Purchase {
    private int serial_No;
    private String purchase_Note;
    private String supplier_Name;
    private String supplier_Contact;
    private String supplier_GSTIN;
    private String supplier_Brand;
    private String purchase_Date;
    private String purchase_Mode;
    private String purchase_Amount;
    private String Invoice;

    public int getSerial_No() {
        return serial_No;
    }

    public void setSerial_No(int serial_No) {
        this.serial_No = serial_No;
    }

    public String getPurchase_Note() {
        return purchase_Note;
    }

    public void setPurchase_Note(String purchase_Note) {
        this.purchase_Note = purchase_Note;
    }

    public String getSupplier_Name() {
        return supplier_Name;
    }

    public void setSupplier_Name(String supplier_Name) {
        this.supplier_Name = supplier_Name;
    }

    public String getSupplier_Contact() {
        return supplier_Contact;
    }

    public void setSupplier_Contact(String supplier_Contact) {
        this.supplier_Contact = supplier_Contact;
    }

    public String getSupplier_GSTIN() {
        return supplier_GSTIN;
    }

    public void setSupplier_GSTIN(String supplier_GSTIN) {
        this.supplier_GSTIN = supplier_GSTIN;
    }

    public String getSupplier_Brand() {
        return supplier_Brand;
    }

    public void setSupplier_Brand(String supplier_Brand) {
        this.supplier_Brand = supplier_Brand;
    }

    public String getPurchase_Date() {
        return purchase_Date;
    }

    public void setPurchase_Date(String purchase_Date) {
        this.purchase_Date = purchase_Date;
    }

    public String getPurchase_Mode() {
        return purchase_Mode;
    }

    public void setPurchase_Mode(String purchase_Mode) {
        this.purchase_Mode = purchase_Mode;
    }

    public String getPurchase_Amount() {
        return purchase_Amount;
    }

    public void setPurchase_Amount(String purchase_Amount) {
        this.purchase_Amount = purchase_Amount;
    }

    public String getInvoice() {
        return Invoice;
    }

    public void setInvoice(String Invoice) {
        this.Invoice = Invoice;
    }

    public Purchase() {
    }
    
}
