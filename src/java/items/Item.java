package items;

public class Item {
    
    private int Serial_No;
    private String Item_Name; 
    private String Brand; 
    private String Quality;
    private String Color; 
    private String Weight; 
    private String Stock;
    private String Shelf_Location; 
    private String Cost_Price; 
    private String Sale_Price; 
    private String Tax; 

    public int getSerial_No() {
        return Serial_No;
    }

    public void setSerial_No(int Serial_No) {
        this.Serial_No = Serial_No;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String Item_Name) {
        this.Item_Name = Item_Name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getQuality() {
        return Quality;
    }

    public void setQuality(String Quality) {
        this.Quality = Quality;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String Stock) {
        this.Stock = Stock;
    }

    public String getShelf_Location() {
        return Shelf_Location;
    }

    public void setShelf_Location(String Shelf_Location) {
        this.Shelf_Location = Shelf_Location;
    }

    public String getCost_Price() {
        return Cost_Price;
    }

    public void setCost_Price(String Cost_Price) {
        this.Cost_Price = Cost_Price;
    }

    public String getSale_Price() {
        return Sale_Price;
    }

    public void setSale_Price(String Sale_Price) {
        this.Sale_Price = Sale_Price;
    }

    public String getTax() {
        return Tax;
    }

    public void setTax(String Tax) {
        this.Tax = Tax;
    }

    public Item() {
    }    
    
}
