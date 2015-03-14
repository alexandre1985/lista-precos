import java.util.*;

public class ProgTexto
{
    BaseDeDados base;
    ArrayList<Produto> conta;
    int numeroDaConta = 1;
    
    public ProgTexto()
    {
        base = new BaseDeDados();
        Scanner leitura = new Scanner(System.in);
        float total = 0f;
        String nome = null;
        ArrayList<ArrayList <Produto>> contas = new ArrayList<ArrayList <Produto>>();
        conta = new ArrayList<Produto>();
        contas.add(conta);
        
        do {
            System.out.print("Nome do produto: ");
            nome = leitura.nextLine();
            
            if(nome.equals("0") || nome.equals("sair")) {
                System.out.println("Conta limpa");
                conta.clear();
                total = 0f;
                continue;
            }
            
            if(isNumeric(nome)) {
                int num = Integer.parseInt(nome);
                
            }
            
            if(!nome.equals("") && nome.charAt(0) == '/') {
                int num = Integer.parseInt(nome.substring(1, nome.length()).trim());
                System.out.println("Dá " + total/num + " cada.");
                continue;
            }
            
            if(nome.equals("-")) {
                int ultimo = conta.size()-1;
                if(ultimo == -1) {
                    System.out.println("Não há produtos na conta.");
                    continue;
                }
                conta.remove(ultimo);
                total = mostrarConta();
                continue;
            }
            
            if(nome.equals("t")) {
                System.out.print("Dinheiro recebido: ");
                String recebido1 = leitura.nextLine();
                float recebido = Float.parseFloat(recebido1);
                System.out.println("Troco: " + (recebido - total));
                continue;
            }
            ArrayList<Produto> produto = base.getNome(nome);
            System.out.println("--- Conta " + numeroDaConta + " ---");
            
            if(produto.size() == 0) {
                System.out.println("NÃO HÁ ESSE PRODUTO!!!");           
            }
            else if (produto.size() == 1) {
                conta.add(produto.get(0));
            }
            else if(produto.size() > 1) {
                int i=1;
                for(Produto prod : produto) {
                    System.out.println(i + ". " + prod.getNome() + "   " + prod.getPreco());
                    i++;
                }
                int escolha;
                do {
                    System.out.print("escolha: ");
                    String escolha1 = leitura.nextLine();
                    escolha = Integer.parseInt(escolha1);
                } while (escolha > produto.size() || escolha < 0);
                if(escolha != 0)
                    conta.add(produto.get(escolha - 1));
            }
            
            total = mostrarConta();
        } while(!nome.equals("sair"));
    }
    
    private float mostrarConta()
    {
        float total = 0f;
        for(Produto pedido : conta) {
            System.out.println(pedido.getNome() + "   " + pedido.getPreco());
        }

        for(Produto pedido : conta) {
            total += pedido.getPreco();
        }
        
        total = (float) Math.round(total*100)/100;
        System.out.println("\nTotal: " + total + "\n---------------");
        return total;
    }
    
    private boolean isNumeric(String str)
    {
      return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}