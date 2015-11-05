import java.util.*;

public class ProgTexto
{
    BaseDeDados base;
    Conta[] contas;
    int numeroDaConta = 11;
    
    public ProgTexto()
    {
        final int MAX_MESAS = 14;
        base = new BaseDeDados();
        Scanner leitura = new Scanner(System.in);
        float total = 0f;
        String nome = null;
        contas = new Conta[MAX_MESAS];
        for(int i=0; i < MAX_MESAS; i++) {
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
            
            if(nome.equals("l") || nome.equals("clear") || nome.equals("limpa") || nome.equals("limpar")) {
                limpar();
                continue;
            }
            
            if(isInt(nome)) {
                int num = Integer.parseInt(nome);
                if(num < 0 || num > MAX_MESAS)
                    System.out.println("A conta " + num + " não existe!");
                else {
                    numeroDaConta = num;
                    total = mostrarConta();
                }
                continue;
            }
            
            if(!nome.equals("") && nome.charAt(0) == '/' && nome.length() > 1) {
                int num=1;
                try {
                    num = Integer.parseInt(nome.substring(1, nome.length()).trim());
                } catch(Exception e) {
                    num = 1;
                }
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
                if(isNumeric(recebido1)) {
                    float recebido = Float.parseFloat(recebido1);
                    System.out.println("Troco: " + arredondar(recebido - total));
                } else {
                    System.out.println("Escreva um número");
                }
                continue;
            }
            
            if(nome.startsWith(">>") || nome.startsWith("->")) {
                String str = nome.substring(2, nome.length()).trim();
                if(!isInt(str)) {
                    System.out.println("Escreva um número inteiro positivo.");
                    continue;
                }
                int num = Integer.parseInt(str);
                if(num > MAX_MESAS) {
                    System.out.println("Escreva um numero entre 1 e " + MAX_MESAS);
                    continue;
                }
                contas[num-1].clear();
                for(Produto prod : contas[numeroDaConta-1].get())
                    contas[num-1].get().add(prod);
                contas[numeroDaConta-1].clear();
                total = mostrarConta();
                continue;
            }
            
            if(nome.startsWith("+")) {
                String str = nome.substring(1, nome.length()).trim();
                if(!isInt(str)) {
                    System.out.println("Escreva um número inteiro positivo.");
                    continue;
                }
                int num = Integer.parseInt(str);
                if(num > MAX_MESAS) {
                    System.out.println("Escreva um numero entre 1 e " + MAX_MESAS);
                    continue;
                }
                for(Produto prod : contas[num-1].get()) {
                    contas[numeroDaConta-1].get().add(prod);
                }
                contas[num-1].clear();
                total = mostrarConta();
                continue;
            }
            
            if(nome.equals("ver")) {
                System.out.print(statusContas());
                continue;
            }
            
            ArrayList<Produto> produto = base.getNome(nome);
            
            if(produto.size() == 0) {
                total = mostrarConta();
                if(!nome.equals(""))
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
                int escolha=-1;
                boolean eNumero=false;
                do {
                    System.out.print("escolha: ");
                    String escolha1 = leitura.nextLine();
                    if(isInt(escolha1)) {
                        escolha = Integer.parseInt(escolha1);
                        eNumero = true;
                    } else
                        System.out.println("Escolha um numero ou digite 0 para sair");
                } while (!eNumero || escolha > produto.size() || escolha < 0);
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

        total = contas[numeroDaConta-1].getTotal();
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

        total = contas[numeroDaConta-1].getTotal();
        System.out.println("\nTotal: " + total + "\n---------------");
        return total;
    }
    
    private boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    
    private boolean isInt(String str)
    {
      return str.matches("\\d+");
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
    
    private void limpar()
    {
        for(int i=0; i < 51; i++) {
            System.out.println();
        }
    }
    
    private float arredondar(float numero)
    {
        return (float) Math.round(numero*100)/100;
    }
    
    private String statusContas()
    {
        String resultado = "";
        for(int i=0; i < contas.length; i++) {
            if(!contas[i].isEmpty()) {
                resultado += i+1 + ": " + contas[i].getTotal() + " - ";
                for(Produto prod : contas[i].get()) {
                    resultado += prod.getNome() + ", ";
                }
                resultado += "\n";
            }
        }
        return resultado;
    }
}