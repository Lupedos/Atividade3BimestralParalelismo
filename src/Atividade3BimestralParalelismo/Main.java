package Atividade3BimestralParalelismo;

import java.util.concurrent.Semaphore;

public class Main {
public static void main(String[] args)
{
	

	Predio predio = new Predio();
	
	 Semaphore semaforo = predio.getSemaforo();
	 
     predio.chamarElevador(1);
     

     try {
         semaforo.acquire(); 
         
     }
     catch(Exception e)
     {
    	 System.out.println(e.getMessage());
     }
}
	 

}
