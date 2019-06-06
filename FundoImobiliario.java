/*
    ALUNO: Danilo de Nadai Sicari
    DATA: 06/06/2019
    CURSO: Sistemas para Internet
    FACULDADE: Fatec - Araras

    Para executar:  javac FundoImobiliario.java
                    java FundoImobiliario
    
    - Ordem tabelas da interface:   1 - Tabela Contas 
                                    2 - Tabela Carteiras
                                    3 - Tabela Fundos Imobiliarios
    - Ha contas predefinidas via codigo e usuario pode cadastrar novas contas
    - Para alterar nome, cpf e dados bancarios, clicar nos botoes laterais e informar cpf da conta
    - Ao clicar em uma conta na tabela contas, a carteira deve ser exibida na Tabela Carteiras
    - Ler cotacao do dia selecionando arquivo *.fii . Embora exercicio tenha solicitado, mas este arquivo nao eh um arquivo txt.
      A lista de Fundos da classe TFii foram gravadas neste arquivo
    - Toda vez que uma cotacao eh lida, todas as carteiras, de todas as contas sao atualizadas com o valor de cota atual do fundo
    - Ordem de compra e venda eh feita somente quando ha fundos carregados
    - ListarContas() ira listar no console todas as contas + todas carteiras de cada conta

    TODO
    - Ganhos com dividendo
    - Regex somente numeros para validacao
*/

public class FundoImobiliario {
    
    public static void main ( String[] args) 
    {
        // Para iniciar interface usuario
        TGui gui=new TGui();
        gui.init();

        /*
        // Para testar classes
        TUtils utils=new TUtils();
        TFii Fii=new TFii();
        TCorretora Corretora=new TCorretora(); 
        int r=0;
        Corretora.CriarConta("33541768819", "Danilo Sicari", 1234, 5566, 0);
        Corretora.CriarConta("88547229697", "Fatima Soares", 1234, 9989, 0);        
        if((r = Corretora.CriarConta("88547229697", "Fatima22", 1234, 8896, 0)) != 0){
            System.out.println("Nao foi possivel criar conta, r="+r);
        } 
        Corretora.ListarContas();
        Corretora.PesquisarConta("33541768819", true);
        Corretora.PesquisarConta("33542768819", true);
        Corretora.EditarNomeConta("33541768819", "Daniel");
        Corretora.EditarDadosBancariosConta("33541768819", 1234, 5567);
        Corretora.EditarCpfConta("33541768819", "3354176881X");
        Corretora.MovimentarConta("3354176881X", Corretora.DEPOSITO, 1000000);
        Corretora.PesquisarConta("3354176881X", true);
        Corretora.CriarCarteira("3354176881X", 1, 2, 2000, 2000, "Shopping Araras Loja 15A");
        Corretora.CriarCarteira("3354176881X", 4, 22, 20000, 20000, "Loteamento Campo Limpo");
        Corretora.ListarContas();
        System.out.println(utils.ConverterMoeda(100290));
        System.out.println(utils.PossuiSomenteLetras("danilo sicari"));
        System.out.println(utils.PossuiSomenteLetras("danilos2"));
        Fii.AdicionarFundo(125, 10200, 1500, "Clube de campo");
        Fii.AdicionarFundo(126, 19000, 1900, "Hall of fame");
        Fii.AdicionarFundo(127, 15000, 100, "Shopping Araras sala 13.1");
        Fii.AdicionarFundo(128, 35000, 4000, "Sinistro Moto Clube");
        Fii.AdicionarFundo(129, 10100, 200, "Bar do te");        
        Fii.ListarFundos();
        Fii.SalvarFundos("listaFundos_3.fii");
        Fii.LerFundos();
        Fii.ListarFundos();
        Corretora.OrdemCompra(125, 2, "3354176881X", Fii);  
        Corretora.ListarContas();
        */
    }
}