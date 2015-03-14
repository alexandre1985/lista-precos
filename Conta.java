import java.util.ArrayList;

public class Conta
{
    int numero;
    ArrayList<Produto> produtos;
    
    public Conta(int num)
    {
        numero = num;
        produtos = new ArrayList<Produto>();
    }
    
    public int getNumero() { return numero; }
    
    public ArrayList<Produto> get() { return produtos; }
    
    public void clear() { produtos.clear(); }
}
