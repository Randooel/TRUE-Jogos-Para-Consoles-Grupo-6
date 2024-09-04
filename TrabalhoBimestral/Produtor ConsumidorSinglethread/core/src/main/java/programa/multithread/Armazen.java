package programa.multithread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Armazen {
	private String[] recursosPossiveis;
	private String[] produtosPossiveis;
    Map<String, Integer> estoqueRecursos = new HashMap<>();
    Map<String, Integer> estoqueProdutos = new HashMap<>();
    private double capacidadeMaxima;

    private String[] ultimoProdutorString;
    private String[] ultimoConsumidorString;
    
    Semaphore armazenarSemaphore = new Semaphore(1);

    public Armazen(double capacidadeMaxima) {
    	recursosPossiveis = SetRecursos();
    	produtosPossiveis = SetProdutos();
    	SetStock();
    	
        this.capacidadeMaxima = capacidadeMaxima;

        this.ultimoProdutorString = new String[3];
        this.ultimoConsumidorString = new String[3];

    }
    
    private void SetStock() {
    	for (String string : recursosPossiveis) {
    		estoqueRecursos.put(string, 0);
		}
    	
    	for (String string : produtosPossiveis) {
    		estoqueProdutos.put(string, 0);
		}
    	
	}

	public String[] SetRecursos() {
		String[] temp = {
    	"Madeira",
    	"Pedra",
    	"Ferro"};
    	
		return temp;
    }
	
	public String[] SetProdutos() {
		String[] temp = {
    	"Parede De Madeira Pedra",
    	"Parede De Madeira Ferro",
		"Parede De Pedra Ferro"};
    	
		return temp;
    }
    
    public String GetRecurso(int recursoID) {
    	return recursosPossiveis[recursoID];
    }
    
    public String GetProduto(int recursoID) {
    	return produtosPossiveis[recursoID];
    }

    public boolean HaveItemInList(int recursoID) {
    	return estoqueRecursos.get(recursosPossiveis[recursoID]) > 0;
    }
    
    public void adicionarRecurso(int novoItemID, String produtorNome) {
    	
    	try {
    		armazenarSemaphore.acquire();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        if (estoqueRecursos.get(recursosPossiveis[novoItemID]) < capacidadeMaxima) {
        	AdicionarResourcesNoEstoque(novoItemID);
            this.ultimoProdutorString[0] = recursosPossiveis[novoItemID];
            this.ultimoProdutorString[1] = produtorNome;
            this.ultimoProdutorString[2] = "Recurso " + recursosPossiveis[novoItemID] + " foi adicionado ao armazém por " + produtorNome + ".";
        } else {
            this.ultimoProdutorString[2] = "Armazém cheio! Não é possível adicionar mais recursos.";
        }
        armazenarSemaphore.release();
    }
    
    private void AdicionarResourcesNoEstoque(int novoItemID) {
    	int newValue = estoqueRecursos.get(recursosPossiveis[novoItemID]) + 1;
    	estoqueRecursos.put(recursosPossiveis[novoItemID], newValue);
	}
    
    public void RemoveResourcesNoEstoque(int itemToRemoveID) {
    	int newValue = estoqueRecursos.get(recursosPossiveis[itemToRemoveID]) - 1;
    	estoqueRecursos.put(recursosPossiveis[itemToRemoveID], newValue);
	}
    
    public void adicionarProduto(int novoItemID, String consumidorNome) {
    	
    	try {
    		armazenarSemaphore.acquire();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
        	AdicionarProdutoNoEstoque(novoItemID);
            this.ultimoConsumidorString[0] = produtosPossiveis[novoItemID];
            this.ultimoConsumidorString[1] = consumidorNome;
            //this.ultimoConsumidorString[2] = "Produto " + produtosPossiveis[novoItemID] + " foi adicionado ao armazém por " + produtorNome + ".";

            
            armazenarSemaphore.release();
    }

	public void AdicionarProdutoNoEstoque(int novoItemID) {
		int newValue = estoqueProdutos.get(produtosPossiveis[novoItemID]) + 1;
		estoqueProdutos.put(produtosPossiveis[novoItemID], newValue);
    }
	
	

    public Map<String, Integer> getRecursos() {
        return estoqueRecursos;
    }
    
    public Map<String, Integer> getProdutos() {
        return estoqueProdutos;
    }

    public String[] getUltimoProdutorMsg() {
        return ultimoProdutorString;
    }

    public String[] getUltimoConsumidorMsg() {
        return ultimoConsumidorString;
    }

    public boolean isCheio(int itemID) {
    	return estoqueRecursos.get(recursosPossiveis[itemID]) >= capacidadeMaxima;
    }

    public void setUltimaConsumidorMensagem(String mensagem) {
        this.ultimoConsumidorString[2] = mensagem;
    }
    public void setUltimaProdutorMensagem(String mensagem) {
        this.ultimoProdutorString[2] = mensagem;
    }

	
}
