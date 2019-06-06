# Projeto Fundo Investimento Imobiliario
- **ALUNO:** Danilo de Nadai Sicari
- **DATA:** 06/06/2019
- **CURSO:** Sistemas para Internet
- **FACULDADE:** Fatec - Araras

![Tela usuario](https://raw.githubusercontent.com/dsicari/fundoInvestimentoImobiliario/master/formMain.png)

## Para executar
Copie os arquivos para um diretorio:
```bash
javac FundoImobiliario.java
java FundoImobiliario
```
## Sobre a aplicação
- Ordem tabelas da interface:   
  - Tabela Contas 
  - Tabela Carteiras
  - Tabela Fundos Imobiliarios
- Ha contas predefinidas via codigo e usuario pode cadastrar novas contas
- Para alterar nome, cpf e dados bancarios, clicar nos botoes laterais e informar cpf da conta
- Ao clicar em uma conta na tabela contas, a carteira deve ser exibida na Tabela Carteiras
- Ler cotacao do dia selecionando arquivo *.fii . Embora exercicio tenha solicitado, mas este arquivo nao eh um arquivo txt.
  A lista de Fundos da classe TFii foram gravadas neste arquivo
- Toda vez que uma cotacao eh lida, todas as carteiras, de todas as contas sao atualizadas com o valor de cota atual do fundo
- Ordem de compra e venda eh feita somente quando ha fundos carregados
- ListarContas() ira listar no console todas as contas + todas carteiras de cada conta

##TODO
- Ganhos com dividendo
- Regex somente numeros para validacao
