import java.util.*;
import javax.swing.table.DefaultTableModel;

class TCarteira
{
	int codigo;
	int qtdeCota;
	int valorPago;
	int valorAtual;
	String descricao;	
}		
			
class TConta
{
	String cpf;
	String nome;
	int agencia;
	int conta;
	int saldo;
	List<TCarteira> Carteira = new ArrayList<TCarteira>();
}

public class TCorretora
{
	public static final int DEPOSITO=1;
	public static final int SAQUE=2;
	public static final int COMPRA=1;
	public static final int VENDA=2;
	public static final int ADICIONAR_COTA=3;
	public static final int	REMOVER_COTA=4;	

    private List<TConta> Conta = new ArrayList<TConta>();
    
	int CriarConta(String cpf, String nome, int agencia, int conta, int saldo)
	{
		int rslt=0;
		boolean found=false;		

		for (int i = 0; i < Conta.size(); i++){
			//System.out.println("["+i+"]CPF="+Conta.get(i).cpf);
			if(Conta.get(i).cpf.equals(cpf))
			{	
				found=true;
				rslt=-1; // Conta ja existe
			}			
		}

		if(found==false)
		{
			TConta c = new TConta();
			TUtils utils = new TUtils();
			c.cpf=cpf;
			if(utils.PossuiSomenteLetras(nome) == false) rslt=-2; // Nome nao possui apenas letras
			else c.nome=nome;
			c.agencia=agencia;
			c.conta=conta;
			c.saldo=saldo;
			if(rslt==0) Conta.add(c);
			c=null;
			utils=null;
		}	
		
		return rslt;
	}

	int CriarCarteira(String cpf, int codigo, int qtdeCota,	int valorPago,	int valorAtual,	String descricao)
	{
		int rslt=0;
		boolean found=false;

		for (int i = 0; i < Conta.size(); i++){
			if(Conta.get(i).cpf.equals(cpf))
			{	
				for (int j = 0; j < Conta.get(i).Carteira.size(); j++){
					if(Conta.get(i).Carteira.get(j).codigo == codigo)
					{				
						found=true;
						rslt=-1; // Carteira ja existe
						break;
					}
				}
				if(found==false)
				{
					TCarteira ct = new TCarteira();
					ct.codigo=codigo;
					ct.qtdeCota=qtdeCota;
					ct.valorPago=valorPago;
					ct.valorAtual=valorAtual;
					ct.descricao=descricao;
					Conta.get(i).Carteira.add(ct);
					ct=null;
					found=true;
				}				
			}
		}

		if(found==false) rslt=-2; // Conta n existe

		return rslt;
	}

	int AtualizarCarteiras(TFii fii)
	{
		int rslt=0;

		for (int i = 0; i < Conta.size(); i++)
		{
			System.out.println("["+i+"] CPF="+Conta.get(i).cpf+" NOME="+Conta.get(i).nome+" AGENCIA="+Conta.get(i).agencia+" CONTA= "+Conta.get(i).conta+" SALDO="+Conta.get(i).saldo);
			for (int j = 0; j < Conta.get(i).Carteira.size(); j++)
			{
				System.out.println("\t["+j+"] CODIGO="+Conta.get(i).Carteira.get(j).codigo+" QTDE COTAS="+Conta.get(i).Carteira.get(j).qtdeCota+" VALOR PAGO="+Conta.get(i).Carteira.get(j).valorPago+" VALOR ATUAL="+Conta.get(i).Carteira.get(j).valorAtual+" DESC="+Conta.get(i).Carteira.get(j).descricao);
				for (int k = 0; k < fii.Fundo.size(); k++)
				{
					if(Conta.get(i).Carteira.get(j).codigo == fii.Fundo.get(k).codigo)
					{
						System.out.println("\t\t Fundo a ser atualizado, valorAtual="+Conta.get(i).Carteira.get(j).valorAtual+" >>>> valorCota="+fii.Fundo.get(k).valorCota);
						Conta.get(i).Carteira.get(j).valorAtual=fii.Fundo.get(k).valorCota;
					}
				}
				
			}
		}

		return rslt;
	}

	int EditarNomeConta(String cpf, String novoNome)
	{
		int rslt=0;
		boolean found=false;
		TUtils utils=new TUtils();

		for (int i = 0; i < Conta.size(); i++){
			if(Conta.get(i).cpf.equals(cpf))
			{				
				if(utils.PossuiSomenteLetras(novoNome) == false) rslt=-2; // Nome nao possui apenas letras
				else Conta.get(i).nome=novoNome;
				found=true;
			}
		}

		if(found==false) rslt=-1;

		utils=null;
		return rslt;
	}

	int EditarCpfConta(String cpf, String novoCpf)
	{
		int rslt=0;
		boolean found=false;
		System.out.println("CPF="+cpf);
		System.out.println("NOVO CPF="+novoCpf);
		for (int i = 0; i < Conta.size(); i++){
			System.out.println("["+i+"]CPF="+Conta.get(i).cpf);
			if(Conta.get(i).cpf.equals(cpf))
			{				
				Conta.get(i).cpf=novoCpf;
				found=true;
			}
		}

		if(found==false) rslt=-1;

		return rslt;	
	}
	
	int EditarDadosBancariosConta(String cpf, int novaAgencia, int novaConta)
	{
		int rslt=0;
		boolean found=false;

		for (int i = 0; i < Conta.size(); i++){
			if(Conta.get(i).cpf.equals(cpf))
			{				
				Conta.get(i).agencia=novaAgencia;
				Conta.get(i).conta=novaConta;
				found=true;
			}
		}

		if(found==false) rslt=-1;

		return rslt;
	}
	
	
	int ListarContas()
	{		
		System.out.println("Corretora possui: " + Conta.size() + " contas");	
		// Outra forma de percorrer lista
		//Conta.forEach(c -> System.out.println(c));
		for (int i = 0; i < Conta.size(); i++){
			System.out.println("["+i+"] CPF="+Conta.get(i).cpf+" NOME="+Conta.get(i).nome+" AGENCIA="+Conta.get(i).agencia+" CONTA= "+Conta.get(i).conta+" SALDO="+Conta.get(i).saldo);
			for (int j = 0; j < Conta.get(i).Carteira.size(); j++){
				System.out.println("\t["+j+"] CODIGO="+Conta.get(i).Carteira.get(j).codigo+" QTDE COTAS="+Conta.get(i).Carteira.get(j).qtdeCota+" VALOR PAGO="+Conta.get(i).Carteira.get(j).valorPago+" VALOR ATUAL="+Conta.get(i).Carteira.get(j).valorAtual+" DESC="+Conta.get(i).Carteira.get(j).descricao);
			}
		}
		return 0;
	}

	int ListarCarteirasConta(String cpf)
	{		
		for (int i = 0; i < Conta.size(); i++){
			if(Conta.get(i).cpf.equals(cpf))
			{
				System.out.println("["+i+"] CPF="+Conta.get(i).cpf+" NOME="+Conta.get(i).nome+" AGENCIA="+Conta.get(i).agencia+" CONTA= "+Conta.get(i).conta+" SALDO="+Conta.get(i).saldo);
				for (int j = 0; j < Conta.get(i).Carteira.size(); j++){
					System.out.println("\t["+j+"] CODIGO="+Conta.get(i).Carteira.get(j).codigo+" QTDE COTAS="+Conta.get(i).Carteira.get(j).qtdeCota+" VALOR PAGO="+Conta.get(i).Carteira.get(j).valorPago+" VALOR ATUAL="+Conta.get(i).Carteira.get(j).valorAtual+" DESC="+Conta.get(i).Carteira.get(j).descricao);
				}
			}		
		}
		return 0;
	}

	void PreencherTabelaContas(DefaultTableModel model)
	{	
		TUtils utils=new TUtils();	
		for (int i = 0; i < Conta.size(); i++){
			model.addRow(new Object[]{Conta.get(i).cpf, Conta.get(i).nome, Conta.get(i).agencia, Conta.get(i).conta, utils.ConverterMoeda(Conta.get(i).saldo)});
		}
		utils=null;
	}

	void PreencherTabelaCarteiras(String cpf, DefaultTableModel model)
	{		
		TUtils utils=new TUtils();	
		int r=PesquisarConta(cpf, false);
		if(r>=0)
		{
			for (int j = 0; j < Conta.get(r).Carteira.size(); j++){				
				model.addRow(new Object[]{Conta.get(r).Carteira.get(j).codigo,
										  Conta.get(r).Carteira.get(j).qtdeCota,
										  utils.ConverterMoeda(Conta.get(r).Carteira.get(j).valorPago),
										  utils.ConverterMoeda(Conta.get(r).Carteira.get(j).valorAtual),
										  Conta.get(r).Carteira.get(j).descricao});
			}
		}
		utils=null;
	}

	int getValorCotaConta(String cpf, int codFundo)
	{
		boolean found=false;
		int rslt=0;
		int indexConta=0;
		int indexCarteira=0;

		for (int i = 0; i < Conta.size(); i++)
		{
			if(Conta.get(i).cpf.equals(cpf))
			{
				for (int j = 0; j < Conta.get(i).Carteira.size(); j++)
				{
					indexConta=i;
					if(Conta.get(i).Carteira.get(j).codigo == codFundo)
					{			
						System.out.println("Codigo Fundo encontrado,indexCarteira="+j);	
						indexCarteira=j;
						rslt=Conta.get(i).Carteira.get(j).valorPago;
						found=true;
						break;
					}					
				}
			}
		}
		if(found == false){
			rslt=-1;
			System.out.println("Conta/fundo nao encontrada ao procurar por valor cota");
		} 
		return rslt;
	}

	int PesquisarConta(String cpf, boolean log)
	{
		boolean found=false;
		int rslt=0;

		for (int i = 0; i < Conta.size(); i++){
			if(Conta.get(i).cpf.equals(cpf))
			{
				if(log == true)
				{
					System.out.print("Conta encontrada: ");
					System.out.println("["+i+"] CPF="+Conta.get(i).cpf+" NOME="+Conta.get(i).nome+" AGENCIA="+Conta.get(i).agencia+" CONTA= "+Conta.get(i).conta+" SALDO="+Conta.get(i).saldo);
				}	
				rslt=i;			
				found=true;
				break;
			}
		}
		if(found == false){
			rslt=-1;
			if(log == true)System.out.println("Conta nao encontrada");
		} 
		return rslt;
	}
	
	int MovimentarCarteira(String cpf, int operacao, int codFundo, int qtdeCota, int valorCota, String descricao)
	{
		boolean found=false;
		int rslt=0;
		int indexCarteira=0;
		int indexConta=0;

		for (int i = 0; i < Conta.size(); i++){
			if(Conta.get(i).cpf.equals(cpf))
			{
				indexConta=i;
				System.out.println("Conta index="+indexConta+" Nome="+Conta.get(indexConta).nome);
				for (int j = 0; j < Conta.get(i).Carteira.size(); j++){					
					if(Conta.get(i).Carteira.get(j).codigo == codFundo)
					{			
						System.out.println("Codigo Fundo encontrado,indexCarteira="+j);	
						indexCarteira=j;
						found=true;
						break;
					}					
				}

				if(found==false)
				{
					int y=this.CriarCarteira(cpf, codFundo, 0, 0, 0, descricao);
					indexCarteira=Conta.get(i).Carteira.size()-1;
					System.out.println("Codigo Fundo nao encontrado, criando carteira="+y+" indexCarteira="+indexCarteira);	
					this.ListarCarteirasConta(cpf);
					found=true;
				}
				else break;
			}
		}
		
		if(found == false)	rslt=-1; // Nao encontrou codigo carteira/conta
		else
		{
			if(operacao == ADICIONAR_COTA)	
			{
				System.out.println("Incrementando cota, qtdeCotaAtual="+Conta.get(indexConta).Carteira.get(indexCarteira).qtdeCota+" valorCota="+valorCota+" qtdeNovasCotas="+qtdeCota+" desc="+Conta.get(indexConta).Carteira.get(indexCarteira).descricao);
				Conta.get(indexConta).Carteira.get(indexCarteira).qtdeCota = Conta.get(indexConta).Carteira.get(indexCarteira).qtdeCota + qtdeCota;
				Conta.get(indexConta).Carteira.get(indexCarteira).valorPago = valorCota;
				Conta.get(indexConta).Carteira.get(indexCarteira).valorAtual = valorCota;
				System.out.println("qtdeCotaAtual="+Conta.get(indexConta).Carteira.get(indexCarteira).qtdeCota);
			}
			else
			{
				System.out.println("Removendo cota");
				if(Conta.get(indexConta).Carteira.get(indexCarteira).qtdeCota - qtdeCota < 0){
					System.out.println("Erro: Cotas ficariam negativas");
					rslt=-2; // Cotas ficam negativas
				} 
				else Conta.get(indexConta).Carteira.get(indexCarteira).qtdeCota=Conta.get(indexConta).Carteira.get(indexCarteira).qtdeCota-qtdeCota;
			}
		}

		return rslt;
	}

	int MovimentarConta(String cpf, int operacao, int valor)
	{
		int rslt=0; // Movimentacao OK
		boolean found=false;

		for (int i = 0; i < Conta.size(); i++){
			if(Conta.get(i).cpf.equals(cpf))
			{		
				if(operacao==DEPOSITO)		Conta.get(i).saldo+=valor;
				else if(operacao==SAQUE){
					if((Conta.get(i).saldo - valor) < 0) rslt=-2; // saldo iria ficar negativo
					else Conta.get(i).saldo-=valor;
				}				
				found=true;
			}
		}

		if(found==false) rslt=-1; // conta nao encontrada

		return rslt;
	}

	int OrdemCompra(int codFundo, int qtdeCota, String cpf, TFii fii)
	{
		int rslt=0;

		int index_fundo=fii.PesquisarFundo(codFundo, false);
		System.out.println("Index Fundo="+index_fundo);
		if(index_fundo < 0)	rslt=-1; // Fundo nao encontrado
		else
		{
			int r=this.MovimentarCarteira(cpf, ADICIONAR_COTA, codFundo, qtdeCota, fii.Fundo.get(index_fundo).valorCota, fii.Fundo.get(index_fundo).descricao);
			System.out.println("OrdemVenda, MovimentarCarteira("+codFundo+" "+qtdeCota+")="+r);
			if(r != 0)
			{
				rslt=-2; // erro ao movimentar carteira
			}
			else
			{
				//int valorCotaAntigo=this.getValorCotaConta(cpf, codFundo);
				r=this.MovimentarConta(cpf, SAQUE, fii.Fundo.get(index_fundo).valorCota * qtdeCota);
				if(r != 0)
				{
					rslt=-3; // erro ao movimentar conta	
					this.MovimentarCarteira(cpf, REMOVER_COTA, codFundo, qtdeCota, fii.Fundo.get(index_fundo).valorCota, fii.Fundo.get(index_fundo).descricao);
				}
				else
				{
					// movimento conta e carteira foram feitos
				}	
			} 
						
		}

		return rslt;
	}

	int OrdemVenda(int codFundo, int qtdeCota, String cpf, TFii fii)
	{
		int rslt=0;

		int index_fundo=fii.PesquisarFundo(codFundo, false);
		int index_conta=this.PesquisarConta(cpf,false);
		System.out.println("Index Fundo="+index_fundo);
		if(index_fundo < 0)	rslt=-1; // Fundo nao encontrado
		else if(index_conta < 0) rslt=-1; // Conta nao encontrada
		else
		{
			int r=this.MovimentarCarteira(cpf, REMOVER_COTA, codFundo, qtdeCota, fii.Fundo.get(index_fundo).valorCota, fii.Fundo.get(index_fundo).descricao);
			if(r != 0)
			{
				rslt=-2; // erro ao movimentar carteira
			}
			else
			{
				r=this.MovimentarConta(cpf, DEPOSITO, fii.Fundo.get(index_fundo).valorCota * qtdeCota);
				if(r != 0)
				{
					rslt=-3; // erro ao movimentar conta	
					this.MovimentarCarteira(cpf, ADICIONAR_COTA, codFundo, qtdeCota, fii.Fundo.get(index_fundo).valorCota, fii.Fundo.get(index_fundo).descricao);
				}
				else
				{
					// movimento conta e carteira foram feitos
				}	
			} 
						
		}

		return rslt;
	}
}