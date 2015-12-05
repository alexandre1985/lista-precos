import java.util.*;

public class ProgTexto
{
    final int MAX_MESAS = 14;
    BaseDeDados base;
    Conta[] contas;
    int numeroDaConta = 11;
    
    public ProgTexto()
    {
        base = new BaseDeDados();
        contas = new Conta[MAX_MESAS];
        for(int i=0; i < MAX_MESAS; i++) {
            contas[i] = new Conta(i+1);
        }
    }
    
    public void main()
    {
        Scanner leitura = new Scanner(System.in);
        float total = 0f;
        String nome = null;
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
                if(num < 1 || num > MAX_MESAS)
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
                System.out.println("Dá " + arredondar(total/num) + " cada.");
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
            
            String[] comandos = nome.split(" ");
            if(comandos[0].equals("del")) {
                boolean sucesso = apagarProdutos(comandos);
                if(sucesso)
                    total = mostrarConta();
                continue;
            }
            
            if(comandos[0].equals("mv")) {
                boolean sucesso = mover(comandos);
                if(sucesso)
                    total = mostrarConta();
                continue;
            }
            
            if(comandos[0].equals("i") || comandos[0].equals("ins")) {
                boolean sucesso = inserirManual(comandos);
                if(sucesso)
                    total = mostrarConta();
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
        System.out.println("\n--- Conta " + numeroDaConta + " ---");
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
    
    private boolean mover(String[] comandos)
    {
        if(contas[numeroDaConta-1].isEmpty()) {
            System.err.println("Erro: não posso executar " + comandos[0] + "\nPorque a conta " 
            + numeroDaConta + " está vazia");
            return false;
        }
        for(byte i= 1; i < comandos.length; i++) {
            if(!isNumeric(comandos[i])) {
                System.err.println("Erro: " + comandos[0] + " só aceita numeros como argumentos.");
                return false;
            }
        }
        if(comandos.length <= 2) {
            System.out.println("Comando: " + comandos[0] + " num_da_conta num_produto1 num_produto2 ...\n" + 
            "Para mover num_produto1, num_produto2, etc. para a conta num_da_conta");
            return false;
        }
        int paraAConta = Integer.parseInt(comandos[1]);
        if(paraAConta > MAX_MESAS || paraAConta < 1) {
            System.err.println("Erro: só aceito numeros da mesa entre 1 e " + MAX_MESAS); 
            return false;
        }
      
        Integer[] temp = new Integer[comandos.length-2];
        for(byte i = 2; i < comandos.length; i++) {
            temp[i-2] = Integer.parseInt(comandos[i]);
            if(temp[i-2] > contas[numeroDaConta-1].get().size() || temp[i-2] < 1) {
                System.err.println("Erro: só aceito numeros de produto entre 1 e " + 
                contas[numeroDaConta-1].get().size());
                return false;
            }
        }
        if(!contas[paraAConta-1].isEmpty()) {
            contas[paraAConta-1].get().add(new Produto(0, "0-0-0-0-0", 0));
        }
        
        Arrays.sort(temp, Collections.reverseOrder());
        for(int a : temp) {
            Produto p = contas[numeroDaConta-1].get().get(a-1);
            contas[paraAConta-1].get().add(p);
            contas[numeroDaConta-1].get().remove(a-1);
        }
        return true;
    }
    
    private boolean inserirManual(String[] comandos)
    {
        for(byte i = 1; i < comandos.length; i++) {
            if(!isNumeric(comandos[i])) {
                System.err.println("Erro: " + comandos[0] + " só aceita numeros como argumentos.");
                return false;
            }
        }
        for(byte i = 1; i < comandos.length; i++) {
            float f = Float.parseFloat(comandos[i]);
            contas[numeroDaConta-1].get().add(new Produto(0, "produto", f));
        }
        return true;
    }
    
    private boolean apagarProdutos(String[] comandos)
    {
        if(contas[numeroDaConta-1].isEmpty()) {
            System.err.println("Erro: não posso executar " + comandos[0] + "\nPorque a conta " 
            + numeroDaConta + " está vazia");
            return false;
        }
        for(byte i= 1; i < comandos.length; i++) {
            if(!isNumeric(comandos[i])) {
                System.err.println("Erro: " + comandos[0] + " só aceita numeros como argumentos.");
                return false;
            }
        }
        
        Integer[] temp = new Integer[comandos.length-1];
        for(byte i = 1; i < comandos.length; i++) {
            temp[i-1] = Integer.parseInt(comandos[i]);
            if(temp[i-1] > contas[numeroDaConta-1].get().size() || temp[i-1] < 1) {
                System.err.println("Erro: só aceito argumentos entre 1 e " + 
                contas[numeroDaConta-1].get().size());
                return false;
            }
        }
        Arrays.sort(temp, Collections.reverseOrder());
        for(int a : temp) {
            contas[numeroDaConta-1].get().remove(a-1);
        }
        return true;
    }
}