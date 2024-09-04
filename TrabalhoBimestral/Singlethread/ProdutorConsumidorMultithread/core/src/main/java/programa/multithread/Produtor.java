package programa.multithread;

import java.util.Random;

public class Produtor{

    private String nome;
    private double velocidadeProducao;
    private double tempoAtual = 0;
    private Armazen armazem;
    protected int recursoID;
    private Random random;

    public Produtor(String nome, double velocidadeProducao, Armazen armazem, int recursoID) {
        this.nome = nome;
        this.velocidadeProducao = velocidadeProducao;
        this.armazem = armazem;
        this.random = new Random();
        this.recursoID = recursoID;
    }

    public String getNome() {
        return nome;
    }

    public void TentarProduzir(float deltaTime) {
    	
    	if(tempoAtual < velocidadeProducao){
    		tempoAtual += deltaTime;
    	}
        else{
        	tempoAtual =0;
                    if (armazem.isCheio(recursoID)) {
                        System.out.println("O armazém está cheio. Aguardando espaço para continuar a produção...");

                    }

                    long startTime = System.currentTimeMillis();
                    
                    armazem.adicionarRecurso(recursoID, nome);
                    long endTime = System.currentTimeMillis();

                    long timeTaken = endTime - startTime;
                    String mensagem = "Produtor " + getNome() + " produziu " + armazem.GetRecurso(recursoID) + " em " + timeTaken + " ms.";
                    System.out.println(mensagem);
                    armazem.setUltimaProdutorMensagem(mensagem);
                    

        }
    }

    public void setVelocidadeProducao(double velocidade) {
        this.velocidadeProducao = velocidade;
    }

    public double getVelocidadeProducao() {
        return velocidadeProducao;
    }
}
