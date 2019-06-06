import java.util.regex.*;  
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import javax.swing.table.DefaultTableModel;

public class TUtils
{
    String ConverterMoeda(int valor)
    {
        //import java.text.NumberFormat;
        //import java.util.Locale;
        //NumberFormat format = NumberFormat.getCurrencyInstance(Locale.BR);
        //String currency = format.format(Double(valor/100));    
        String currency = "R$ "+((double)valor/100);
        return currency;
    }

    boolean PossuiSomenteLetras(String str)
    { 
        return Pattern.matches("^[a-zA-Z\\s]+$", str);
    }

    boolean PossuiSomenteNumeros(String str)
    { 
        return Pattern.matches("^[0-9]+$", str);
    }

    boolean WriteObjectToFile(Object serObj, String filepath) 
    { 
        boolean rslt=false;
        try 
        { 
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
            //System.out.println("The Object  was succesfully written to a file");
            rslt=true;            
        } 
        catch (Exception ex) {
            //ex.printStackTrace();
        }
        return rslt;
    }

    void EmptyTable(DefaultTableModel model)
    {
        while (model.getRowCount()>0)   model.removeRow(0);
    }
}