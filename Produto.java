
public class Produto
{
    private float preco;
    private String nome;
    private int num;
    
    public Produto(int num, String nome, float preco)
    {
        this.nome = nome;
        this.preco = preco;
        this.num = num;
    }
    
    public float getPreco() { return preco; }
    
    public String getNome() { return nome; }

    public int getNumero() { return num; }
        
    public void setPreco(float preco) { this.preco = preco; }
    
    public void setNome(String nome) { this.nome = nome; }
    
    public void setNumero(int num) { this.num = num; }
}
