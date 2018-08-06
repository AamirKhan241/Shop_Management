package credit;

public class Credit {
    private int serial_No;
    private String credittor_Name;
    private String credittor_Contact;
    private String date;
    private String amount_Creditted;
    private String amount_Payed;
    private String amount_Balance;
    

    public int getSerial_No() {
        return serial_No;
    }

    public void setSerial_No(int serial_No) {
        this.serial_No = serial_No;
    }

    public String getCredittor_Name() {
        return credittor_Name;
    }

    public void setCredittor_Name(String credittor_Name) {
        this.credittor_Name = credittor_Name;
    }

    public String getCredittor_Contact() {
        return credittor_Contact;
    }

    public void setCredittor_Contact(String credittor_Contact) {
        this.credittor_Contact = credittor_Contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount_Creditted() {
        return amount_Creditted;
    }

    public void setAmount_Creditted(String amount_Creditted) {
        this.amount_Creditted = amount_Creditted;
    }

    public String getAmount_Payed() {
        return amount_Payed;
    }

    public void setAmount_Payed(String amount_Payed) {
        this.amount_Payed = amount_Payed;
    }

    public String getAmount_Balance() {
        return amount_Balance;
    }

    public void setAmount_Balance(String amount_Balance) {
        this.amount_Balance = amount_Balance;
    }

    public Credit() {
    }    
}
