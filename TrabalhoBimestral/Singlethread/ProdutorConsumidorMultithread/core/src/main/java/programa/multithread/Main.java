package programa.multithread;

import java.util.Map;
import java.util.HashMap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private BitmapFont font;

    private Armazen armazem;
    
    private Produtor[] produtores;
    
    private Produtor produtorCarinaldo;
    private Produtor produtorAstrolabio;
    private Produtor produtorPedronildo;
    
    private Consumidor[] consumidores;
    
    private Consumidor consumidorA;
    private Consumidor consumidorB;
    private Consumidor consumidorC;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture(Gdx.files.internal("libgdx.png"));
        font = new BitmapFont();
       
        
        this.produtores = new Produtor[3];
        this.consumidores = new Consumidor[3];
        
        armazem = new Armazen(5);
        
        produtorCarinaldo = new Produtor("Carinaldo", 2.0, armazem, 0);
        produtorAstrolabio = new Produtor("Astrolabio", 2.5, armazem, 1);
        produtorPedronildo = new Produtor("Pedronildo", 1.5, armazem, 2);
        
        produtores[0] = produtorCarinaldo;
        produtores[1] = produtorAstrolabio;
        produtores[2] = produtorPedronildo;
        

        consumidorA = new Consumidor("ConsumidorA", 1.5,armazem, 0,1,0);
        consumidorB = new Consumidor("ConsumidorB", 2.0,armazem,0,2,1);
        consumidorC = new Consumidor("ConsumidorC", 3.5,armazem,1,2,2);

        consumidores[0] = consumidorA;
        consumidores[1] = consumidorB;
        consumidores[2] = consumidorC;
        
    }
    

    @Override
    public void render() {
    	long startTime = System.currentTimeMillis();
    	float deltaTime = Gdx.graphics.getDeltaTime();  // Convertendo para milissegundos

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        batch.begin();
        batch.draw(image, 140, 210);

        

        for(int i = 0; i < produtores.length; i++) {
        	produtores[i].TentarProduzir(deltaTime);
        }
        
        for(int i = 0; i < consumidores.length; i++) {
        	consumidores[i].TentarConsumir(deltaTime);
        }
        
        font.draw(batch, "Itens no armazém:", 20, 420);
        int x = 400;
        
        Map<String, Integer> estoqueRecursos = armazem.getRecursos();
        
        		
        for(int i = 0; i < estoqueRecursos.size(); i++) {
        	font.draw(batch, "Recurso: " + armazem.GetRecurso(i) + " / Quantidade: " + estoqueRecursos.get(armazem.GetRecurso(i)), 20, x);
            x -= 20;
        }

        font.draw(batch, "Último recurso adcionano: " + armazem.getUltimoProdutorMsg()[0], 20, 320);
        font.draw(batch, "Por produtor: " + armazem.getUltimoProdutorMsg()[1], 20, 300);
        font.draw(batch, "Mensagem: " + armazem.getUltimoProdutorMsg()[2], 20, 280);


        Map<String, Integer> estoqueProdutos = armazem.getProdutos();
        
        int y = 200;
        
        for(int i = 0; i < estoqueProdutos.size(); i++) {
        	font.draw(batch, "Produto: " + armazem.GetProduto(i) + " / Quantidade: " + estoqueProdutos.get(armazem.GetProduto(i)), 20, y);
            y -= 20;
        }
        
        font.draw(batch, "Último recurso adcionano: " + armazem.getUltimoConsumidorMsg()[0], 20, 120);
        font.draw(batch, "Por produtor: " + armazem.getUltimoConsumidorMsg()[1], 20, 100);
        font.draw(batch, "Mensagem: " + armazem.getUltimoConsumidorMsg()[2], 20, 80);
        
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        font.draw(batch, "Tempo para completar mostar mudança: " + timeTaken, 20, 40);
        System.out.println("Tempo para completar mostar mudança: " + timeTaken);
        
        
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        font.dispose();
    }
}
