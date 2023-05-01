package Atividade3BimestralParalelismo;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Program{
    public static Predio predio;

    public Program(int N, int andares) {

        int num_andares = andares;

        predio = new Predio(num_andares);

        Random rand = new Random(); 
        //Criar e distribuir passageiros
        for (int i = 0; i < N;i++) {
            int curr_floor = rand.nextInt(num_andares);
            int dest_floor = 0;

            do{
                dest_floor = rand.nextInt(num_andares);
            }while(dest_floor==curr_floor);

            Pessoa Pessoa = new Pessoa(curr_floor, dest_floor, predio);
            predio.Andares.get(curr_floor).add(Pessoa);
            predio.passageiros.add(Pessoa);
        }
        predio.run();


    }

} 
