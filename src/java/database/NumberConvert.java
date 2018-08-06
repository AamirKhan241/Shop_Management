package database;

public class NumberConvert {
    String Conv="";
    public void pw(int n,String ch)
    {
        String  one[]={""," One"," Two"," Three"," Four"," Five"," Six"," Seven"," Eight"," Nine"," Ten"," Eleven"," Twelve"," Thirteen",
            " Fourteen","Fifteen"," Sixteen"," Seventeen"," Eighteen"," Nineteen"};
 
        String ten[]={" "," "," Twenty"," Thirty"," Forty"," Fifty"," Sixty","Seventy"," Eighty"," Ninety"};
 
        if(n > 19) { Conv+=ten[n/10]+" "+one[n%10];} else { Conv+=one[n];}
        if(n > 0)Conv+=ch;
    }

    public String Convert(int n) {    
        Conv="";
        if(n <= 0)
            return "Zero Only.";
        else{
            pw((n/1000000000)," Hundred");
            pw((n/10000000)%100," crore");
            pw(((n/100000)%100)," lakh");
            pw(((n/1000)%100)," thousand");
            pw(((n/100)%10)," hundred");
            pw((n%100)," ");
        }
        
        return Conv+" Only.";
    }    
    private static NumberConvert NC=new NumberConvert();
    
    private NumberConvert(){
    }
    
    public static NumberConvert getInstance(){
        return NC;
    }
}
