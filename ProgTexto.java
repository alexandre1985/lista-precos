import java.util.*;

public class ProgTexto
{
    BaseDeDados base;
    Conta[] contas;
    int numeroDaConta = 1;
    
    public ProgTexto()
    {
        base = new BaseDeDados();
        Scanner leitura = new Scanner(System.in);
        float total = 0f;
        String nome = null;
        contas = new Conta[14];
        for(int i=0; i < 14; i++) {
            contas[i] = new Conta(i+1);
        }
        
        do {
            System.out.print("Nome do produto: ");
            nome = leitura.nextLine();
            
            if(nome.equals("0") || nome.equals("sair")) {
                if(nome.equals("0"))
                    System.out.println("Conta " + numeroDaConta + " limpa");
                else
                    System.out.println("Fechei as contas todas");
                contas[numeroDaConta-1].clear();
                total = 0f;
                continue;
            }
            
            if(isNumeric(nome)) {
                numeroDaConta = Integer.parseInt(nome);
                mostrarConta();
                continue;
            }
            
            if(!nome.equals("") && nome.charAt(0) == '/') {
                int num = Integer.parseInt(nome.substring(1, nome.length()).trim());
                System.out.println("Dá " + total/num + " cada.");
                continue;
            }
            
            if (nome.equals("num") || nome.equals("codigo") || nome.equals("cod")) {
                total = mostrarCodigoConta();
                continue;
            }
            
            if(nome.equals("-")) {
                int ultimo = contas[numeroDaConta-1].get().size()-1;
                if(ultimo == -1) {
                    System.out.println("Não há produtos na conta.");
                    continue;
                }
                contas[numeroDaConta-1].get().remove(ultimo);
                total = mostrarConta();
                continue;
            }
            
            if(nome.equals("t")) {
                System.out.print("Dinheiro recebido: ");
                String recebido1 = leitura.nextLine();
                float recebido = Float.parseFloat(recebido1);
                System.out.println("Troco: " + arredondar(recebido - total));
                continue;
            }
            ArrayList<Produto> produto = base.getNome(nome);
            
            if(produto.size() == 0) {
                total = mostrarConta();
                System.out.println("NÃO HÁ ESSE PRODUTO!!!");           
            }
            else if (produto.size() == 1) {
                contas[numeroDaConta-1].get().add(produto.get(0));
                total = mostrarConta();
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
                if(escolha != 0) {
                    contas[numeroDaConta-1].get().add(produto.get(escolha - 1));
                    total = mostrarConta();
                }
            }
            
        } while(!nome.equals("sair"));
    }
    
    private float mostrarConta()
    {
        float total = 0f;
        System.out.println("--- Conta " + numeroDaConta + " ---");
        for(Produto pedido : contas[numeroDaConta-1].get()) {
            System.out.println(pedido.getNome() + "   " + pedido.getPreco());
        }

        for(Produto pedido : contas[numeroDaConta-1].get()) {
            total += pedido.getPreco();
        }
        
        total = (float) Math.round(total*100)/100;
        System.out.println("\nTotal: " + total + "\n---------------");
        return total;
    }
    
    private float mostrarCodigoConta()
    {
        float total = 0f;
        System.out.println("--- Conta " + numeroDaConta + " ---");
        for(Produto pedido : contas[numeroDaConta-1].get()) {
            System.out.println(codigo(pedido.getNumero()) + " " + pedido.getNome() + " "
            + pedido.getPreco());
        }

        for(Produto pedido : contas[numeroDaConta-1].get()) {
            total += pedido.getPreco();
        }
        
        System.out.println("\nTotal: " + arredondar(total) + "\n---------------");
        return total;
    }
    
    private float arredondar(float numero)
    {
        return (float) Math.round(numero*100)/100;
    }
    
    private boolean isNumeric(String str)
    {
      return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    
    private String codigo(int num)
    {
        String resultado = Integer.toString(num);
        int tamanho = resultado.length();
        String append = "";
        while(tamanho < 5 ) {
            append += "0";
            tamanho++;
        }
        return append + resultado;
    }
}