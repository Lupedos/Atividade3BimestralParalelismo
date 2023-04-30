package Atividade3BimestralParalelismo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;


public class Predio {
    private Elevador elevador;
    private Semaphore semaforo;

    public Predio() {
        elevador = new Elevador();
        semaforo = new Semaphore(1); 
    }

    public void chamarElevador(int andar) {
        elevador.adicionarPedido(andar);
    }

    public Semaphore getSemaforo() {
        return semaforo;
    }

    public void iniciar() {
        elevador.start();
    }

    
    private class Elevador extends Thread {
        private int andarAtual;
        private List<Integer> pedidos;
        private Semaphore semaforoElevador;

        public Elevador() {
            andarAtual = 0;
            pedidos = new ArrayList<>();
            semaforoElevador = new Semaphore(1); 
        }

        @Override
        public void run() {
            while (true) {
                try {
                    semaforoElevador.acquire(); 
                    while (!pedidos.isEmpty()) {
                        int proximoAndar = pedidos.remove(0);
                        if (proximoAndar != andarAtual) {
                            moverElevador(proximoAndar);
                        }
                        embarcarPessoas();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaforoElevador.release(); 
                }
            }
        }

        public void adicionarPedido(int andar) {
            pedidos.add(andar);
        }

        private void moverElevador(int proximoAndar) {
            
        }

        private void embarcarPessoas() {
            try {
                semaforo.acquire(); 
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforo.release(); 
            }
        }
    }
}
