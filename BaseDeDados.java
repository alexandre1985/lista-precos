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
            if(contem(produto.getNome(), nome)) {
                resultado.add(produto);
            }
        }
        return resultado;
    }
    
    private boolean contem(String string1, String string2)
    {
        String[] palavras = string2.split(" ");
        for(String palavra : palavras) {
            if(!string1.contains(palavra))
                return false;
        }
        return true;
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
        produtos.add(new Produto(3, "cafe longo", 0.7f));
        produtos.add(new Produto(3, "abatanado", 0.7f));
        produtos.add(new Produto(4, "pingo", 0.7f));
        produtos.add(new Produto(5, "pingo descafenado", 0.75f));
        produtos.add(new Produto(6, "cevada", 0.6f));
        produtos.add(new Produto(7, "cevada dupla", 0.8f));
        produtos.add(new Produto(8, "pingo cevada", 0.7f));
        produtos.add(new Produto(9, "carioca cafe", 0.55f));
        produtos.add(new Produto(10, "carioca limao", 0.55f));
        produtos.add(new Produto(11, "meia leite", 0.9f));
        produtos.add(new Produto(11, "meia leite cevada", 0.9f));
        produtos.add(new Produto(12, "meia leite descafenado", 0.95f));
        produtos.add(new Produto(140, "galao", 1f));
        produtos.add(new Produto(13, "cafe descafenado dolce gusto", 0.7f));
        produtos.add(new Produto(14, "cappuccino dolce gusto", 1.2f));
        produtos.add(new Produto(15, "macchiato dolce gusto", 1.2f));
        produtos.add(new Produto(16, "chococino dolce gusto", 1.2f));
        produtos.add(new Produto(17, "cha diverso", 0.8f));
        produtos.add(new Produto(18, "cha 2 chavenas", 1.4f));
        produtos.add(new Produto(19, "cha com leite", 1f));
        produtos.add(new Produto(20, "cha limao carioca", 0.7f));
        produtos.add(new Produto(21, "cha verde", 1f));
        produtos.add(new Produto(21, "cha gorreana", 1f));
        produtos.add(new Produto(22, "leite copo", 0.7f));
        produtos.add(new Produto(23, "leite chocolatado pacote", 0.8f));
        produtos.add(new Produto(24, "ucal", 1.1f));
        produtos.add(new Produto(25, "torrada pao forma", 1.1f));
        produtos.add(new Produto(26, "meia torrada", 0.7f));
        produtos.add(new Produto(27, "torrada sem manteiga", 0.9f));
        produtos.add(new Produto(28, "meia torrada sem manteiga", 0.75f));
        produtos.add(new Produto(29, "torrada bijou", 0.7f));
        produtos.add(new Produto(29, "torrada bijou com mel", 0.7f));
        produtos.add(new Produto(30, "torrada bijou sem manteiga", 0.6f));
        produtos.add(new Produto(31, "torrada pao pada", 0.8f));
        produtos.add(new Produto(31, "torrada pao centeio", 0.8f));
        produtos.add(new Produto(32, "pao com manteiga", 0.6f));
        produtos.add(new Produto(33, "pao", 0.2f));
        produtos.add(new Produto(34, "pada com manteiga", 0.7f));
        produtos.add(new Produto(34, "centeio com manteiga", 0.7f));
        produtos.add(new Produto(35, "pada com doce", 1f));
        produtos.add(new Produto(35, "centeio com doce", 1f));
        produtos.add(new Produto(36, "bico de pato com manteiga", 0.7f));
        produtos.add(new Produto(37, "bico de pato", 0.3f));
        produtos.add(new Produto(38, "pao normal com queijo", 1.3f));
        produtos.add(new Produto(38, "pao normal com fiambre", 1.3f));
        produtos.add(new Produto(39, "pao normal com queijo e fiambre", 1.65f));
        produtos.add(new Produto(40, "pada com queijo", 1.4f));
        produtos.add(new Produto(40, "pada com fiambre", 1.4f));
        produtos.add(new Produto(40, "centeio com queijo", 1.4f));
        produtos.add(new Produto(40, "centeio com fiambre", 1.4f));
        produtos.add(new Produto(41, "pada com queijo e fiambre", 1.7f));
        produtos.add(new Produto(41, "centeio com queijo e fiambre", 1.7f));
        produtos.add(new Produto(42, "bico de pato com queijo", 1.3f));
        produtos.add(new Produto(42, "bico de pato com fiambre", 1.3f));
        produtos.add(new Produto(43, "bico de pato com queijo e fiambre", 1.7f));
        produtos.add(new Produto(44, "pao normal com presunto", 2.5f));
        produtos.add(new Produto(45, "pada com presunto", 2.7f));
        produtos.add(new Produto(45, "centeio com presunto", 2.7f));
        produtos.add(new Produto(46, "pao normal com queijo brie", 1.4f));
        produtos.add(new Produto(47, "pada com queijo brie", 1.5f));
        produtos.add(new Produto(47, "centeio com queijo brie", 1.5f));
        produtos.add(new Produto(48, "pao com pate", 2.5f));
        produtos.add(new Produto(141, "sandes americana", 2.5f));
        produtos.add(new Produto(49, "tosta queijo", 1.6f));
        produtos.add(new Produto(49, "tosta fiambre", 1.6f));
        produtos.add(new Produto(50, "tosta mista", 1.8f));
        produtos.add(new Produto(51, "cachorro normal", 1.6f));
        produtos.add(new Produto(52, "cachorro com queijo", 1.8f));
        produtos.add(new Produto(52, "cachorro com fiambre", 1.8f));
        produtos.add(new Produto(53, "cachorro com queijo e fiambre", 2.2f));
        produtos.add(new Produto(54, "sopa pequena", 1.5f));
        produtos.add(new Produto(55, "sopa grande", 1.8f));
        produtos.add(new Produto(56, "croissant simples", 0.8f));
        produtos.add(new Produto(57, "croissant com manteiga", 0.9f));
        produtos.add(new Produto(60, "croissant com queijo", 1.5f));
        produtos.add(new Produto(60, "croissant com fiambre", 1.5f));
        produtos.add(new Produto(62, "croissant com queijo e fiambre", 1.8f));
        produtos.add(new Produto(64, "croissant chocolate", 1.2f));
        produtos.add(new Produto(63, "torrado", 0.1f));
        produtos.add(new Produto(65, "lanche", 1.1f));
        produtos.add(new Produto(66, "lanche misto", 1.2f));
        produtos.add(new Produto(142, "lanche frango", 1.5f));
        produtos.add(new Produto(67, "panike", 1f));
        produtos.add(new Produto(67, "folhado misto", 1f));
        produtos.add(new Produto(68, "bolo cenoura", 0.8f));
        produtos.add(new Produto(68, "bolo arroz", 0.8f));
        produtos.add(new Produto(68, "nata", 0.8f));
        produtos.add(new Produto(69, "fatia bolo caseiro", 1f));
        produtos.add(new Produto(70, "tarte", 1f));
        produtos.add(new Produto(71, "rissol", 0.85f));
        produtos.add(new Produto(72, "croquete", 0.85f));
        produtos.add(new Produto(72, "bolinho bacalhau", 0.85f));
        produtos.add(new Produto(73, "chamuca", 0.85f));
        produtos.add(new Produto(74, "folhado frango", 1.2f));
        produtos.add(new Produto(143, "pao com chourico", 1.5f));
        produtos.add(new Produto(144, "pao leitao", 2f));
        produtos.add(new Produto(75, "pastel chaves", 1.5f));
        produtos.add(new Produto(76, "quiche", 1.3f));
        produtos.add(new Produto(76, "pizza", 1.3f));
        produtos.add(new Produto(77, "agua pedras salgadas", 1.1f));
        produtos.add(new Produto(78, "agua pedras salgadas com rodela limao", 1.2f));
        produtos.add(new Produto(79, "agua pedras salgadas com groselha", 1.2f));
        produtos.add(new Produto(82, "frize", 1.2f));
        produtos.add(new Produto(83, "agua 33cl", 0.7f));
        produtos.add(new Produto(84, "agua 50cl", 0.8f));
        produtos.add(new Produto(85, "agua 1.5l", 1f));
        produtos.add(new Produto(86, "coca cola", 1.1f));
        produtos.add(new Produto(87, "fanta", 1.1f));
        produtos.add(new Produto(88, "sumol", 1.1f));
        produtos.add(new Produto(89, "7up", 1.1f));
        produtos.add(new Produto(90, "ice tea", 1.1f));
        produtos.add(new Produto(91, "b!", 1.2f));
        produtos.add(new Produto(92, "compal", 1.1f));
        produtos.add(new Produto(94, "sumo laranja natural", 1.6f));
        produtos.add(new Produto(95, "super bock", 1.1f));
        produtos.add(new Produto(95, "cerveja", 1.1f));
        produtos.add(new Produto(96, "mini", 0.8f));
        produtos.add(new Produto(101, "panache", 1.5f));
        produtos.add(new Produto(102, "tango", 1.5f));
        produtos.add(new Produto(103, "sumo natural", 2f));
        produtos.add(new Produto(112, "macieira", 1.5f));
        produtos.add(new Produto(112, "aguardente", 1.5f));
        produtos.add(new Produto(113, "croft", 1.5f));
        produtos.add(new Produto(114, "meio croft", 0.8f));
        produtos.add(new Produto(114, "meia aguardente", 0.8f));
        produtos.add(new Produto(115, "amendoa amarga", 1.2f));
        produtos.add(new Produto(116, "licor beirao", 1.2f));
        produtos.add(new Produto(117, "whiskey novo", 2.8f));
        produtos.add(new Produto(118, "whiskey velho", 3.5f));
        produtos.add(new Produto(150, "kinder ovo", 1.5f));
        produtos.add(new Produto(151, "kinder barra", 0.8f));
        produtos.add(new Produto(152, "kit kat", 1.1f));
        produtos.add(new Produto(152, "mars", 1.1f));
        produtos.add(new Produto(152, "chocolate", 1.1f));
        produtos.add(new Produto(154, "halls", 0.9f));
        produtos.add(new Produto(155, "chiclet trident", 0.8f));
        produtos.add(new Produto(156, "chiclet", 0.1f));
        produtos.add(new Produto(0, "pao", 0.12f));
        produtos.add(new Produto(0, "pada", 0.25f));
        produtos.add(new Produto(0, "centeio", 0.25f));
        produtos.add(new Produto(111, "vinho porto", 1.8f));
        produtos.add(new Produto(0, "bolos", 1f));
        produtos.add(new Produto(13, "cafe buondi dolce gusto", 0.70f));
    }
}
