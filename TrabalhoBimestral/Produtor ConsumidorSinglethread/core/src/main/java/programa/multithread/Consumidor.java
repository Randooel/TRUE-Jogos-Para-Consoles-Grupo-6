package programa.multithread;

import java.util.Random;

public class Consumidor extends Thread {

    private String nome;
    private double velocidadeConsumo;
    private Random random;
    private Armazen armazem;
    private int recursoUtilizadoA, recursoUtilizadoB;
    private int recursoProduzido;

    // Construtor
    public Consumidor(String nome, double velocidadeConsumo, Armazen armazen, int recursoUtilizadoA, int recursoUtilizadoB, int recursoProduzido) {
        this.armazem = armazen;
    	this.nome = nome;
        this.velocidadeConsumo = velocidadeConsumo;
        this.random = new Random();
        
        this.recursoUtilizadoA = recursoUtilizadoA;
        this.recursoUtilizadoB = recursoUtilizadoB;
        this.recursoProduzido = recursoProduzido;
    }

    @Override
    public void run() {
    	while (true) {
            try {
            	long startTime = System.currentTimeMillis();
                // Simula o tempo necessário para consumir um recurso com base na velocidade de consumo
                Thread.sleep((long) (velocidadeConsumo * 1000));

                if (TryConsumeItems()) {

                    // Aqui você pode adicionar lógica para usar o recurso consumido, por exemplo, construir um item
                    utilizarRecurso( armazem.GetProduto(recursoUtilizadoA), armazem.GetProduto(recursoUtilizadoB));
                    
                    long endTime = System.currentTimeMillis();
                    long timeTaken = endTime - startTime;
                    String mensagem = "Consumidor " + getNome() + " produziu " + armazem.GetProduto(recursoProduzido) + " em " + timeTaken + " ms.";

                    armazem.setUltimaConsumidorMensagem(mensagem);
                } else {
                    System.out.println("Armazém sem recursos! " + nome + " está esperando por novos recursos.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private boolean TryConsumeItems() {
		
    	if(armazem.HaveItemInList(recursoUtilizadoA) && armazem.HaveItemInList(recursoUtilizadoB)) {
    		armazem.RemoveResourcesNoEstoque(recursoUtilizadoA);
    		armazem.RemoveResourcesNoEstoque(recursoUtilizadoB);
    		return true;	
    	}  	
		return false;
	}

	// Método para utilizar o recurso consumido
    public void utilizarRecurso(String recursoA, String recursoB) {
    	armazem.adicionarProduto(recursoProduzido, nome);
        System.out.println(nome + " está utilizando os recursos: " + recursoA + " e " + recursoB);
    }

    // Método para definir a velocidade de consumo
    public void setVelocidadeConsumo(double velocidade) {
        this.velocidadeConsumo = velocidade;
    }

    // Método para obter a velocidade de consumo
    public double getVelocidadeConsumo() {
        return velocidadeConsumo;
    }

    // Adicionado método getNome()
    public String getNome() {
        return nome;
    }
}
