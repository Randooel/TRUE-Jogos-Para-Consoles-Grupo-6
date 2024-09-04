package programa.multithread;

public class Recurso {

    public class RecursoBase {
        private String tipo;

        public RecursoBase GetRecurso() {
        	return this;
        }
        
        public RecursoBase(String tipo) {
            this.tipo = tipo;
        }

        public String getTipo() {
            return tipo;
        }
    }

    public class Madeira extends RecursoBase {
        public Madeira() {
            super("Madeira");
        }
    }

    public class Pedra extends RecursoBase {
        public Pedra() {
            super("Pedra");
        }
    }

    public class Ferro extends RecursoBase {
        public Ferro() {
            super("Ferro");
        }
    }
    
    public class ParedeDeMadeiraPedra extends RecursoBase {
        public ParedeDeMadeiraPedra() {
            super("ParedeDeMadeiraPedra");
        }
    }
    
    public class ParedeDeMadeiraFerro extends RecursoBase {
        public ParedeDeMadeiraFerro() {
            super("ParedeDeMadeiraFerro");
        }
    }
    
    public class ParedeDePedraFerro extends RecursoBase {
        public ParedeDePedraFerro() {
            super("ParedeDePedraFerro");
        }
    }

}

