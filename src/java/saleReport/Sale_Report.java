package saleReport;

public class Sale_Report {
    private int Serial_No;
    private String Invoice_Number;
    private String Buyer_Name;
    private String Buyer_Contact;
    private String Buyer_Address;
    private String Buyer_State;
    private String Buyer_PinCode;
    private String Buyer_GSTIN;
    private String Sale_Date;
    private String Amount;
    private String Payment_Mode;
    private String Transportation;
    private String GST;
    private String Cartage;

    public int getSerial_No() {
        return Serial_No;
    }

    public void setSerial_No(int Serial_No) {
        this.Serial_No = Serial_No;
    }
    
    public String getInvoice_Number() {
        return Invoice_Number;
    }

    public void setInvoice_Number(String Invoice_Number) {
        this.Invoice_Number = Invoice_Number;
    }

    public String getBuyer_Name() {
        return Buyer_Name;
    }

    public void setBuyer_Name(String Buyer_Name) {
        this.Buyer_Name = Buyer_Name;
    }

    public String getBuyer_Contact() {
        return Buyer_Contact;
    }

    public void setBuyer_Contact(String Buyer_Contact) {
        this.Buyer_Contact = Buyer_Contact;
    }

    public String getBuyer_Address() {
        return Buyer_Address;
    }

    public void setBuyer_Address(String Buyer_Address) {
        this.Buyer_Address = Buyer_Address;
    }

    public String getBuyer_State() {
        return Buyer_State;
    }

    public void setBuyer_State(String Buyer_State) {
        this.Buyer_State = Buyer_State;
    }

    public String getBuyer_PinCode() {
        return Buyer_PinCode;
    }

    public void setBuyer_PinCode(String Buyer_PinCode) {
        this.Buyer_PinCode = Buyer_PinCode;
    }

    public String getBuyer_GSTIN() {
        return Buyer_GSTIN;
    }

    public void setBuyer_GSTIN(String Buyer_GSTIN) {
        this.Buyer_GSTIN = Buyer_GSTIN;
    }

    public String getSale_Date() {
        return Sale_Date;
    }

    public void setSale_Date(String Sale_Date) {
        this.Sale_Date = Sale_Date;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String Amount) {
        this.Amount = Amount;
    }

    public String getPayment_Mode() {
        return Payment_Mode;
    }

    public void setPayment_Mode(String Payment_Mode) {
        this.Payment_Mode = Payment_Mode;
    }

    public String getTransportation() {
        return Transportation;
    }

    public void setTransportation(String Transportation) {
        this.Transportation = Transportation;
    }

    public String getGST() {
        return GST;
    }

    public void setGST(String GST) {
        this.GST = GST;
    }

    public String getCartage() {
        return Cartage;
    }

    public void setCartage(String Cartage) {
        this.Cartage = Cartage;
    }
    

    public Sale_Report() {
    }
    
}
