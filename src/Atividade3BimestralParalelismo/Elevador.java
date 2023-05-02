package Atividade3BimestralParalelismo;
<<<<<<< HEAD

import java.util.*;
import java.util.concurrent.Semaphore;
import java.lang.Runnable;
import java.util.concurrent.Semaphore;
=======
public class Elevador extends Thread {
	private boolean cheio;
	
	void AbrirPorta()
	{
		if(cheio == false)
		{
			
		}
		
	}
	void FecharPorta()
	{
		
		
		cheio = true; 
	}
	
	void VisitarAndar()
	{
		
	}
>>>>>>> parent of 28382c3 (teste mas nao funfa)

public class Elevador extends Thread{
    public boolean is_running;
    public boolean in_transit;
    public boolean should_stop;
    public int current_floor;
    boolean door_open;
    List<Pessoa> passageiro_dentro = new ArrayList<Pessoa>();
    public Semaphore sinal = new Semaphore(1);
    private Predio predio;
    public Elevador(int F, Predio predio) {
        this.predio = predio;
        this.is_running = false;
        this.current_floor=F;
        this.door_open=false;
    }
    private void abrir_porta(){
        this.door_open=true;
    }
    private void fechar_porta(){
        this.door_open=false;
    }
    public void visitar_andar(int andar){
        while (current_floor!=andar) {
            if (current_floor>andar) {
                current_floor-=1;
                if (passageiro_dentro.size()>0) {
                    passageiro_dentro.get(0).current_floor-=1;
               }
            }else {
                current_floor+=1;
                if (passageiro_dentro.size()>0) {
                    passageiro_dentro.get(0).current_floor+=1;
               }
            }
        }
    }
    public void receber_Pessoa(Pessoa Pessoa) {
        abrir_porta();
        passageiro_dentro.add(Pessoa);
        predio.Andares.get(this.current_floor).remove(Pessoa);
        fechar_porta();
    }
    public void soltar_passageiro(Pessoa Pessoa) {
        abrir_porta();
        passageiro_dentro.remove(Pessoa);
        Pessoa.arrived=true;
        predio.Andares.get(this.current_floor).add(Pessoa);
        fechar_porta();
    }
    @Override
    public void run() {
        super.run();
        this.should_stop=false;
        this.is_running=true;
        while(!should_stop) {
      System.out.println("Elevador está funcionando");
        }
        this.is_running=false;
    }
}