import java.util.ArrayList;
import java.text.Normalizer.Form;
import java.text.Normalizer;

public class BaseDeDados
{
    ArrayList<Produto> produtos;
    
    public BaseDeDados()
    {
        produtos = new ArrayList<Produto>();
        carregarProdutos();
    }
    
    public ArrayList<Produto> getNome(String nome)
    {
        ArrayList<Produto> resultado = new ArrayList<Produto>();
        nome = formatar(nome);
        if(nome.equals(""))
            return resultado;
        
        for(Produto produto : produtos) {
            if(produto.getNome().equals(nome)) {
                resultado.add(produto);
                return resultado;
            }
        }
        for(Produto produto : produtos) {
            if(produto.getNome().contains(nome)) {
                resultado.add(produto);
            }
        }
        return resultado;
    }
    
    private String formatar(String string)
    {
        return Normalizer.normalize(string.toLowerCase(), Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    
    public Produto getNumero(int num)
    {
        for(Produto produto : produtos) {
            if(num == produto.getNumero())
                return produto;
        }
        return null;
    }
        
    private void carregarProdutos()
    {
        produtos.add(new Produto(1, "cafe", 0.65f));
        produtos.add(new Produto(2, "descafenado", 0.75f));
        produtos.add(new Produto(3, "pingo", 0.70f));
        produtos.add(new Produto(4, "desnatado", 0.65f));
        produtos.add(new Produto(4, "folhado misto", 0.80f));
    }
}
