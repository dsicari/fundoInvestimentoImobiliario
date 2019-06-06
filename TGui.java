import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File; 

public class TGui extends JApplet implements ActionListener
{
    JButton btnAdicionarConta;
    JButton btnEditarCpfConta;
    JButton btnEditarNomeConta;
    JButton btnEditarDadosBancariosConta;
    JButton btnMovimentarConta;
    JButton btnAdicionarCarteira;
    JButton btnOrdemDeCompra;
    JButton btnOrdemDeVenda;
    JButton btnLerCotacaoDia;
    JTextField txtboxCpf;
    JTextField txtboxNome;
    JTextField txtboxAgencia;
    JTextField txtboxConta;
    JTextField txtboxSaldo;    
    JTextField txtboxCodigoFundo;
    JTextField txtboxValorCota;
    JTextField txtboxValorDividendoCota;
    JTextField txtboxDescricaoCota;
    JTable tabelaContas;    
    JTable tabelaCarteiras;  
    JTable tabelaFii;

    TUtils utils;
    TFii Fii;
    TCorretora Corretora;

    public void init() 
    {
        utils=new TUtils();
        Fii=new TFii();
        Corretora=new TCorretora(); 
        
        Corretora.CriarConta("33541768819", "Danilo Sicari", 1234, 5566, 100000);
        Corretora.CriarConta("88547229697", "Fatima Soares", 1234, 5567, 200000); 
        Corretora.CriarConta("87548649719", "Barnabe Luz", 1234, 5568, 0);
        Corretora.CriarConta("76755364543", "Jose Antonio", 1234, 9969, 0); 
        Corretora.CriarConta("01186932233", "Aparecido de Jesus", 1234, 5570, 500000);
        Corretora.CriarConta("43453436477", "Farinha Lactea", 1234, 9971, 650000); 

        JFrame formMain = new JFrame("Corretora FATEC - Fundo Investimento Imobiliario");
        formMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel lblCpf=new JLabel("CPF");  
        lblCpf.setBounds(50, 25, 100, 30);  

        txtboxCpf=new JTextField();  
        txtboxCpf.setBounds(50, 50, 100, 20); 

        JLabel lblNome=new JLabel("Nome");  
        lblNome.setBounds(160, 25, 100, 30);
        
        txtboxNome=new JTextField();  
        txtboxNome.setBounds(160, 50, 100, 20);

        JLabel lblAgencia=new JLabel("Agencia");  
        lblAgencia.setBounds(270, 25, 75, 30);

        txtboxAgencia=new JTextField();  
        txtboxAgencia.setBounds(270, 50, 75, 20);

        JLabel lblConta=new JLabel("Conta");  
        lblConta.setBounds(355, 25, 75, 30);

        txtboxConta=new JTextField();  
        txtboxConta.setBounds(355, 50, 75, 20);

        JLabel lblSaldo=new JLabel("Saldo");  
        lblSaldo.setBounds(445, 25, 75, 30);

        txtboxSaldo=new JTextField();  
        txtboxSaldo.setBounds(445, 50, 75, 20);

        btnAdicionarConta=new JButton("Adicionar Conta");
        btnAdicionarConta.setBounds(530, 50, 140, 20);
        add(btnAdicionarConta);
        btnAdicionarConta.addActionListener(this);

        btnEditarCpfConta=new JButton("Editar Cpf Conta");
        btnEditarCpfConta.setBounds(680, 75, 200, 20);
        add(btnEditarCpfConta);
        btnEditarCpfConta.addActionListener(this);

        btnEditarNomeConta=new JButton("Editar Nome Conta");
        btnEditarNomeConta.setBounds(680, 100, 200, 20);
        add(btnEditarNomeConta);
        btnEditarNomeConta.addActionListener(this);

        btnEditarDadosBancariosConta=new JButton("Editar Dados Bancarios");
        btnEditarDadosBancariosConta.setBounds(680, 125, 200, 20);
        add(btnEditarDadosBancariosConta);
        btnEditarDadosBancariosConta.addActionListener(this);

        btnMovimentarConta=new JButton("Movimentar Conta");
        btnMovimentarConta.setBounds(680, 150, 200, 20);
        add(btnMovimentarConta);
        btnMovimentarConta.addActionListener(this);
        
        //JLabel lblContaVertical = new JLabel("<html>C<br>O<br>N<br>T<br>A<br>S</html>");
        //lblContaVertical.setBounds(20,23,20,200);

        String[] colunas = {"CPF", "Nome", "Agencia", "Conta", "Saldo"}; 
        DefaultTableModel model = new DefaultTableModel();
        for(String strTemp : colunas){
            model.addColumn(strTemp);           
        }
        utils.EmptyTable(model);
        Corretora.PreencherTabelaContas(model);

        tabelaContas = new JTable(model);        
        tabelaContas.setBounds(50, 75, 620, 100);
        // Evento clique mouse tabela contas
        tabelaContas.addMouseListener(new MouseAdapter()
        {
            //public void mouseClicked(MouseEvent e){
            public void mousePressed(MouseEvent e){
                //if (e.getClickCount() == 2){
                    int row = tabelaContas.getSelectedRow();
                    String cpf = tabelaContas.getModel().getValueAt(row, tabelaContas.getColumn("CPF").getModelIndex()).toString();
                    DefaultTableModel m = (DefaultTableModel)tabelaCarteiras.getModel();
                    utils.EmptyTable(m);
                    Corretora.PreencherTabelaCarteiras(cpf, m);
                //}
            }
        });

        JScrollPane sp = new JScrollPane(tabelaContas); 
        sp.setBounds(50, 75, 620, 100);

        /* // TODO - Cadastro de fundos imobiliarios
        JLabel lblCodigoFundo=new JLabel("Codigo Fundo");  
        lblCodigoFundo.setBounds(50, 275, 100, 30);  

        txtboxCodigoFundo=new JTextField();  
        txtboxCodigoFundo.setBounds(50, 300, 100, 20); 

        JLabel lblValorCota=new JLabel("Valor Cota");  
        lblValorCota.setBounds(160, 275, 100, 30);  

        txtboxValorCota=new JTextField();  
        txtboxValorCota.setBounds(160, 300, 100, 20); 

        JLabel lblValorDividendoCota=new JLabel("Valor Dividendo");  
        lblValorDividendoCota.setBounds(270, 275, 100, 30);  

        txtboxValorDividendoCota=new JTextField();  
        txtboxValorDividendoCota.setBounds(270, 300, 100, 20); 

        JLabel lblDescricaoCota=new JLabel("Descricao Cota");  
        lblDescricaoCota.setBounds(380, 275, 100, 30);  

        txtboxDescricaoCota=new JTextField();  
        txtboxDescricaoCota.setBounds(380, 300, 100, 20); 
        */

        btnOrdemDeCompra=new JButton("Ordem de compra");
        btnOrdemDeCompra.setBounds(680, 200, 200, 20);
        add(btnOrdemDeCompra);
        btnOrdemDeCompra.addActionListener(this);

        btnOrdemDeVenda=new JButton("Ordem de venda");
        btnOrdemDeVenda.setBounds(680, 225, 200, 20);
        add(btnOrdemDeVenda);
        btnOrdemDeVenda.addActionListener(this);

        //JLabel lblCarteirasVertical = new JLabel("<html>C<br>A<br>R<br>T<br>E<br>I<br>R<br>AS</html>");
        //lblCarteirasVertical.setBounds(20,97,20,200);
        //lblCarteirasVertical.setFont(new Font("", Font.PLAIN, 8));

        String[] colunasTabelaCarteiras = {"Codigo", "Qtde Cotas", "Valor Pago", "Valor Atual", "Descricao"}; 
        DefaultTableModel modelTabelaCarteiras = new DefaultTableModel();
        for(String strTemp : colunasTabelaCarteiras){
            modelTabelaCarteiras.addColumn(strTemp);           
        }        

        tabelaCarteiras = new JTable(modelTabelaCarteiras);        
        tabelaCarteiras.setBounds(50, 200, 620, 100);        

        JScrollPane spTabelaCarteiras = new JScrollPane(tabelaCarteiras); 
        spTabelaCarteiras.setBounds(50, 200, 620, 100);            

        btnLerCotacaoDia=new JButton("Ler Cotacao Dia");
        btnLerCotacaoDia.setBounds(680, 325, 200, 20);
        add(btnLerCotacaoDia);
        btnLerCotacaoDia.addActionListener(this);

        String[] colunasTabelaFii = {"Codigo", "Valor Cota", "Valor Dividendo", "Descricao"};
        DefaultTableModel modelTabelaFii = new DefaultTableModel();
        for(String strTemp : colunasTabelaFii){
            modelTabelaFii.addColumn(strTemp);           
        }

        tabelaFii = new JTable(modelTabelaFii);      
        tabelaFii.setBounds(50, 325, 620, 100); 
        // Evento double click tabela fundos imobiliarios 
        tabelaFii.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e){
                if (e.getClickCount() == 2){
                    int row = tabelaFii.getSelectedRow();
                    String codFundo = tabelaFii.getModel().getValueAt(row, tabelaFii.getColumn("Codigo").getModelIndex()).toString();
                    System.out.println("Selecionado="+codFundo);
                }
            }
        });      

        JScrollPane spTabelaFii = new JScrollPane(tabelaFii); 
        spTabelaFii.setBounds(50, 325, 620, 100);

        formMain.add(lblCpf);
        formMain.add(txtboxCpf);
        formMain.add(lblNome);
        formMain.add(txtboxNome);
        formMain.add(lblAgencia);
        formMain.add(txtboxAgencia);
        formMain.add(lblConta);
        formMain.add(txtboxConta);
        formMain.add(lblSaldo);
        formMain.add(txtboxSaldo);
        formMain.add(btnAdicionarConta);
        formMain.add(btnEditarCpfConta);
        formMain.add(btnEditarNomeConta);
        formMain.add(btnEditarDadosBancariosConta);
        formMain.add(btnMovimentarConta);
        //formMain.add(lblContaVertical);
        //formMain.add(lblCarteirasVertical);
        formMain.getContentPane().add(sp);
        formMain.add(btnOrdemDeCompra);
        formMain.add(btnOrdemDeVenda);
        formMain.getContentPane().add(spTabelaCarteiras);
        /*
        formMain.add(lblCodigoFundo);
        formMain.add(txtboxCodigoFundo);
        formMain.add(lblValorCota);
        formMain.add(txtboxValorCota);
        formMain.add(lblValorDividendoCota);
        formMain.add(txtboxValorDividendoCota);
        formMain.add(lblDescricaoCota);
        formMain.add(txtboxDescricaoCota);
        */
        formMain.add(btnLerCotacaoDia);
        formMain.getContentPane().add(spTabelaFii);

        formMain.setSize(900,475);   
        formMain.setLocationRelativeTo(null);  
        formMain.setLayout(null);   

        formMain.setVisible(true);
    }

    // Eventos clique botao
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdicionarConta) 
        {
            if(txtboxCpf.getText().equals("") || txtboxNome.getText().equals("") || txtboxAgencia.getText().equals("") || txtboxConta.getText().equals("") || txtboxSaldo.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "ERRO: Ha campos em branco", "ERROR", JOptionPane.ERROR_MESSAGE); 
                return; 
            } 
            int r=Corretora.CriarConta(txtboxCpf.getText(), txtboxNome.getText(), Integer.parseInt(txtboxAgencia.getText()), Integer.parseInt(txtboxConta.getText()), Integer.parseInt(txtboxSaldo.getText()));
            if(r==0)
            {
                JOptionPane.showMessageDialog(null, "Conta criada com sucesso");
                DefaultTableModel model = (DefaultTableModel)tabelaContas.getModel();
                utils.EmptyTable(model);
                Corretora.PreencherTabelaContas(model);
            }
            else if(r==-1)    JOptionPane.showMessageDialog(null, "ERRO: Conta com este CPF ja existe", "ERROR", JOptionPane.ERROR_MESSAGE);
            else if(r==-2)    JOptionPane.showMessageDialog(null, "ERRO: Nome nao possui apenas letras/espaco", "ERROR", JOptionPane.ERROR_MESSAGE);
            Corretora.ListarContas();

        }

        if (e.getSource() == btnEditarCpfConta) 
        {
            String cpf = JOptionPane.showInputDialog("Digite o cpf: ");
            if(cpf==null || cpf.isEmpty()) return;
            System.out.println(cpf);
            String novoCpf = JOptionPane.showInputDialog("Digite o novo cpf: ");
            if(novoCpf==null || novoCpf.isEmpty()) return;
            System.out.println(novoCpf);
            int r=Corretora.EditarCpfConta(cpf, novoCpf);
            if(r==0)
            {
                JOptionPane.showMessageDialog(null, "Operacao realizada com sucesso"); 
                DefaultTableModel model = (DefaultTableModel)tabelaContas.getModel();
                utils.EmptyTable(model);
                Corretora.PreencherTabelaContas(model);
            }
            else if(r==-1)    JOptionPane.showMessageDialog(null, "ERRO: CPF nao esta cadastrado", "ERROR", JOptionPane.ERROR_MESSAGE);
            Corretora.ListarContas();
        }

        if (e.getSource() == btnEditarNomeConta) 
        {
            String cpf = JOptionPane.showInputDialog("Digite o cpf: ");
            if(cpf==null || cpf.isEmpty()) return;
            System.out.println(cpf);
            String novoNome = JOptionPane.showInputDialog("Digite o novo nome: ");
            if(novoNome==null || novoNome.isEmpty()) return;
            int r=Corretora.EditarNomeConta(cpf, novoNome);
            if(r==0)
            {
                JOptionPane.showMessageDialog(null, "Operacao realizada com sucesso"); 
                DefaultTableModel model = (DefaultTableModel)tabelaContas.getModel();
                utils.EmptyTable(model);
                Corretora.PreencherTabelaContas(model);
            }
            else if(r==-1)    JOptionPane.showMessageDialog(null, "ERRO: CPF nao esta cadastrado", "ERROR", JOptionPane.ERROR_MESSAGE);
            else if(r==-2)    JOptionPane.showMessageDialog(null, "ERRO: Nome nao possui apenas letras/espaco", "ERROR", JOptionPane.ERROR_MESSAGE);
            Corretora.ListarContas();
        }

        if (e.getSource() == btnEditarDadosBancariosConta) 
        {
            String cpf = JOptionPane.showInputDialog("Digite o cpf: ");
            if(cpf==null || cpf.isEmpty()) return;
            String novaAgencia = JOptionPane.showInputDialog("Digite a nova agencia: ");
            if(novaAgencia==null || novaAgencia.isEmpty()) return;
            String novaConta = JOptionPane.showInputDialog("Digite a nova conta: ");
            if(novaConta==null || novaConta.isEmpty()) return;
            // TODO - Regex somente numeros
            //if(utils.PossuiSomenteNumeros(novaAgencia)==false || utils.PossuiSomenteNumeros(novaConta))
            //{
            //    JOptionPane.showMessageDialog(null, "ERRO: Agencia ou conta nao possui apenas numeros", "ERROR", JOptionPane.ERROR_MESSAGE);
            //    return; 
            //}
            
            int r=Corretora.EditarDadosBancariosConta(cpf, Integer.parseInt(novaAgencia), Integer.parseInt(novaConta));
            if(r==0)
            {
                JOptionPane.showMessageDialog(null, "Operacao realizada com sucesso"); 
                DefaultTableModel model = (DefaultTableModel)tabelaContas.getModel();
                utils.EmptyTable(model);
                Corretora.PreencherTabelaContas(model);
            }
            else if(r==-1)    JOptionPane.showMessageDialog(null, "ERRO: CPF nao esta cadastrado", "ERROR", JOptionPane.ERROR_MESSAGE);
            Corretora.ListarContas();
        }

        if (e.getSource() == btnMovimentarConta) 
        {
            String cpf = JOptionPane.showInputDialog("Digite o cpf: ");
            if(cpf==null || cpf.isEmpty()) return;
            Object[] possibilities = {"Depositar", "Sacar"};
            String s = (String)JOptionPane.showInputDialog(null, "Tipo movimentacao", "", JOptionPane.PLAIN_MESSAGE, null, possibilities, null);
            if(s==null || s.isEmpty()) return;
            String valor = JOptionPane.showInputDialog("Digite o valor: ");
            if(valor==null || valor.isEmpty()) return;
            int r=0;
            if(s.equals("Depositar") == true){
                r=Corretora.MovimentarConta(cpf, Corretora.DEPOSITO, Integer.parseInt(valor));
            }
            else if(s.equals("Sacar") == true){
                r=Corretora.MovimentarConta(cpf, Corretora.SAQUE, Integer.parseInt(valor)); 
            }
            if(r==0){
                JOptionPane.showMessageDialog(null, "Operacao realizada com sucesso!"); 
                DefaultTableModel model = (DefaultTableModel)tabelaContas.getModel();
                utils.EmptyTable(model);
                Corretora.PreencherTabelaContas(model);
            }
            else if(r==-1) JOptionPane.showMessageDialog(null, "ERRO: CPF nao esta cadastrado", "ERROR", JOptionPane.ERROR_MESSAGE); 
            else if(r==-2) JOptionPane.showMessageDialog(null, "ERRO: Conta ficaria negativa", "ERROR", JOptionPane.ERROR_MESSAGE); 
        }

        if (e.getSource() == btnOrdemDeCompra) 
        {            
            if(Fii.Fundo.size()==0)
            {
                JOptionPane.showMessageDialog(null, "ERRO: Nao ha fundos imobiliarios", "ERROR", JOptionPane.ERROR_MESSAGE);    
                return;
            }
            String cpf = JOptionPane.showInputDialog("Digite o cpf: "); 
            if(cpf==null || cpf.isEmpty()) return;
            String codFundo = JOptionPane.showInputDialog("Digite o codigo do fundo: "); 
            if(codFundo==null || codFundo.isEmpty()) return;
            String qtdeCota = JOptionPane.showInputDialog("Digite a quantidade de cotas:"); 
            if(qtdeCota==null || qtdeCota.isEmpty()) return;
            int r=Corretora.OrdemCompra(Integer.parseInt(codFundo), Integer.parseInt(qtdeCota), cpf, Fii);
            if(r==0)
            {
                JOptionPane.showMessageDialog(null, "Operacao realizada com sucesso!"); 
                DefaultTableModel model = (DefaultTableModel)tabelaContas.getModel();
                utils.EmptyTable(model);
                Corretora.PreencherTabelaContas(model);
                model = (DefaultTableModel)tabelaCarteiras.getModel();
                utils.EmptyTable(model);
                Corretora.ListarContas();
            }
            else if(r==-1) JOptionPane.showMessageDialog(null, "ERRO: Codigo Fundo nao encontrado", "ERROR", JOptionPane.ERROR_MESSAGE); 
            else if(r==-2) JOptionPane.showMessageDialog(null, "ERRO: Erro ao movimentar carteira", "ERROR", JOptionPane.ERROR_MESSAGE); 
            else if(r==-3) JOptionPane.showMessageDialog(null, "ERRO: Erro ao movimentar conta", "ERROR", JOptionPane.ERROR_MESSAGE); 
        }

        if (e.getSource() == btnOrdemDeVenda) 
        {
            if(Fii.Fundo.size()==0)
            {
                JOptionPane.showMessageDialog(null, "ERRO: Nao ha fundos imobiliarios", "ERROR", JOptionPane.ERROR_MESSAGE);
                return;    
            }
            String cpf = JOptionPane.showInputDialog("Digite o cpf: "); 
            if(cpf==null || cpf.isEmpty()) return;
            String codFundo = JOptionPane.showInputDialog("Digite o codigo do fundo: "); 
            if(codFundo==null || codFundo.isEmpty()) return;
            String qtdeCota = JOptionPane.showInputDialog("Digite a quantidade de cotas:"); 
            if(qtdeCota==null || qtdeCota.isEmpty()) return;
            int r=Corretora.OrdemVenda(Integer.parseInt(codFundo), Integer.parseInt(qtdeCota), cpf, Fii);
            if(r==0)
            {
                JOptionPane.showMessageDialog(null, "Operacao realizada com sucesso!"); 
                DefaultTableModel model = (DefaultTableModel)tabelaContas.getModel();
                utils.EmptyTable(model);
                Corretora.PreencherTabelaContas(model);
                model = (DefaultTableModel)tabelaCarteiras.getModel();
                utils.EmptyTable(model);
                Corretora.ListarContas();
            }
            else if(r==-1) JOptionPane.showMessageDialog(null, "ERRO: Codigo Fundo nao encontrado", "ERROR", JOptionPane.ERROR_MESSAGE); 
            else if(r==-2) JOptionPane.showMessageDialog(null, "ERRO: Erro ao movimentar carteira", "ERROR", JOptionPane.ERROR_MESSAGE); 
            else if(r==-3) JOptionPane.showMessageDialog(null, "ERRO: Erro ao movimentar conta", "ERROR", JOptionPane.ERROR_MESSAGE); 
        }

        if (e.getSource() == btnLerCotacaoDia) 
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            fileChooser.setDialogTitle("Selecione a cotacao");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Cotacao Fundo Investimento Imobiliario (*.fii)", "fii");
		    fileChooser.addChoosableFileFilter(filter);
            /*
            // Outra forma de fazer file filter
            fileChooser.addChoosableFileFilter(new FileFilter() 
            { 
                public String getDescription() 
                {
                    return "Cotacao Fundo Investimento Imobiliario (*.fii)";
                }
             
                public boolean accept(File f) 
                {
                    if (f.isDirectory())    return true;
                    else    return f.getName().toLowerCase().endsWith(".fii");
                }
            });
            */
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                int r=Fii.LerFundos(selectedFile.getAbsolutePath());
                if(r==0) 
                {
                    DefaultTableModel model = (DefaultTableModel)tabelaFii.getModel();
                    utils.EmptyTable(model);
                    Fii.PreencherTabelaFundos(model);
                    JOptionPane.showMessageDialog(null, "Operacao realizada com sucesso!"); 
                    Corretora.AtualizarCarteiras(Fii);
                    model = (DefaultTableModel)tabelaCarteiras.getModel();
                    utils.EmptyTable(model);
                }
                else JOptionPane.showMessageDialog(null, "ERRO: r="+r, "ERROR", JOptionPane.ERROR_MESSAGE);  
            }
        }
    }
}