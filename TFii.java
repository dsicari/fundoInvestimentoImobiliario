import java.util.*;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.table.DefaultTableModel;

class TFundo implements Serializable 
{
    int codigo;
    int valorCota;
    int valorDividendo;
    String descricao;
}
    
public class TFii
{
    List<TFundo> Fundo = new ArrayList<TFundo>();	

    int SalvarFundos(String filename)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(Fundo);
            oos.close();
        }
        catch(IOException e) 
        {
            e.printStackTrace();
        }
        return 0;
    }

    int LerFundos(String file)
    {
        int rslt=0;
        try
        {
            Fundo.clear();
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Fundo = (List<TFundo>) ois.readObject();  
            ois.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            rslt=-1;
        } 
        catch (IOException e) {
            System.out.println("Error initializing stream");
            rslt=-2;
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            rslt=-3;
        }
        return rslt;
    }

    void PreencherTabelaFundos(DefaultTableModel model)
	{		
		TUtils utils=new TUtils();	
        for (int j = 0; j < this.Fundo.size(); j++)
        {				
            model.addRow(new Object[]{this.Fundo.get(j).codigo,
                                        utils.ConverterMoeda(this.Fundo.get(j).valorCota),
                                        utils.ConverterMoeda(this.Fundo.get(j).valorDividendo),
                                        this.Fundo.get(j).descricao});
        }
		utils=null;
	}

    int AdicionarFundo(int codigo, int valorCota, int valorDividendo, String descricao)
    {
        boolean found=false;
        int rslt=0;
        for (int i = 0; i < Fundo.size(); i++){
			if(Fundo.get(i).codigo == codigo)
			{		
                found=true;
                rslt=-1; // Fundo ja existe
                break;
			}
        }
        
        if(found == false)
        {
            TFundo f = new TFundo();
            f.codigo=codigo;
            f.valorCota=valorCota;
            f.valorDividendo=valorDividendo;
            f.descricao=descricao;
            Fundo.add(f);
            f=null;
        }

		return rslt;
    }

    int RemoverFundo(int codigo)
	{
		int rslt=0;
		boolean found=false;

		for (int i = 0; i < Fundo.size(); i++){
			if(Fundo.get(i).codigo == codigo)
			{	
                Fundo.remove(i);	
                found=true;
                break;
			}
		}

		if(found==false) rslt=-1;

		return rslt;
	}

    int PesquisarFundo(int codigo, boolean log)
	{
		boolean found=false;
		int rslt=-1;

		for (int i = 0; i < Fundo.size(); i++){
			if(Fundo.get(i).codigo == codigo)
			{
				if(log == true)
				{
					System.out.print("Fundo encontrado: ");
                    System.out.println("["+i+"] CODIGO="+Fundo.get(i).codigo+" VALOR_COTA="+Fundo.get(i).valorCota+" VALOR_DIVIDENDO="+Fundo.get(i).valorDividendo+" DESCRICAO="+Fundo.get(i).descricao);
                }	
                rslt=i;			
				found=true;
				break;
			}
		}
		if(found == false){
			rslt=-1;
			if(log == true)System.out.println("Fundo nao encontrado");
		} 
		return rslt;
    }
    
    int ListarFundos()
	{
		int rslt=0;

		for (int i = 0; i < Fundo.size(); i++){
			System.out.println("["+i+"] CODIGO="+Fundo.get(i).codigo+" VALOR COTA="+Fundo.get(i).valorCota+" VALOR DIVIDENDO="+Fundo.get(i).valorDividendo+" DESCRICAO="+Fundo.get(i).descricao);
		}				
		 
		return rslt;
	}

}