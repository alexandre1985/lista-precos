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
    
    public boolean isEmpty() { return produtos.isEmpty(); }
    
    public float getTotal()
    {
        float total = 0;
        for(Produto prod : produtos) {
            total += prod.getPreco();
        }
        return arredondar(total);
    }
    
    private float arredondar(float numero)
    {
        return (float) Math.round(numero*100)/100;
    }
}
