package programa.multithread;

import java.util.Random;

public class Produtor extends Thread{

    private String nome;
    private double velocidadeProducao;
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

    public void run() {
            while (true) {
                try {
                    if (armazem.isCheio(recursoID)) {
                        System.out.println("O armazém está cheio. Aguardando espaço para continuar a produção...");
                        Thread.sleep(1000);
                        continue;
                    }

                    long startTime = System.currentTimeMillis();
                    Thread.sleep((long) (velocidadeProducao * 1000));
                    
                    armazem.adicionarRecurso(recursoID, nome);
                    long endTime = System.currentTimeMillis();

                    long timeTaken = endTime - startTime;
                    String mensagem = "Produtor " + getNome() + " produziu " + armazem.GetRecurso(recursoID) + " em " + timeTaken + " ms.";
                    System.out.println(mensagem);
                    armazem.setUltimaProdutorMensagem(mensagem);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
    }

    public void setVelocidadeProducao(double velocidade) {
        this.velocidadeProducao = velocidade;
    }

    public double getVelocidadeProducao() {
        return velocidadeProducao;
    }
}
