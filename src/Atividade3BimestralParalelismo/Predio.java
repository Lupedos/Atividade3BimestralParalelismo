package Atividade3BimestralParalelismo;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.concurrent.ThreadLocalRandom;


public class Predio {
	private final int num_andares;
	
	public List<List<Pessoa>> Andares = new ArrayList<List<Pessoa>>();
	public final Elevador elevador;
	public List<Pessoa> passageiros = new ArrayList<Pessoa>();
	public Pessoa next;
	
	
	public Predio(int num_andares) {
		this.num_andares=num_andares;
		
		for (int i=0; i<this.num_andares;i++) {
			List<Pessoa> andar = new ArrayList<Pessoa>();
			this.Andares.add(andar);
		}
		
		
		this.elevador = new Elevador(ThreadLocalRandom.current().nextInt(0, Andares.size()),this);
		
		
	}
	
	private void get_next() {
		
		HashMap<Pessoa, Integer> line_dict = new HashMap<Pessoa, Integer>();
		
		//Verificar se o andar não tem passageiros ou se só tem passageiros que chegaram ao seu destino
		//Se sim, mudar para o andar mais populoso
		
		for (Pessoa Pessoa : Andares.get(elevador.current_floor)) {
			if (Pessoa.arrived==true) {
				continue;
			}
			int distance = Math.abs(Pessoa.dest_floor-Pessoa.current_floor);
			line_dict.put(Pessoa,distance);
		}
		
		List<Map.Entry<Pessoa, Integer> > list =
	               new LinkedList<Map.Entry<Pessoa, Integer> >(line_dict.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<Pessoa, Integer> >() {
            public int compare(Map.Entry<Pessoa, Integer> o1,
                               Map.Entry<Pessoa, Integer> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
		
		HashMap<Pessoa, Integer> temp = new LinkedHashMap<Pessoa, Integer>();
        for (Map.Entry<Pessoa, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        
		this.next = temp.entrySet().iterator().next().getKey();
		elevador.is_running=true;
		elevador.in_transit = true;
		next.is_next=true;
		
	}
	
	private int fullest_floor() {
		// Mudar metodo para só contar passageiros que ainda não chegaram
		int current_size = this.Andares.get(this.elevador.current_floor).size();
		int ret_val = 0;
		int biggest_floor = 0;
		int counter = 0;
		for (List<Pessoa> andar: this.Andares) {
			if (andar.size() > biggest_floor) {
				biggest_floor = andar.size();
				ret_val=counter;
			}
			counter+=1;
		}
		
		return ret_val;
	}
	
	
	public void run() {
		
		for (Pessoa passageiro : this.passageiros) {
			passageiro.start();
		}
		List<Boolean> chegadas = new ArrayList<Boolean>();
		for (Pessoa passageiro: passageiros) {
			chegadas.add(passageiro.arrived);
		}
		
		if (this.Andares.get(elevador.current_floor).size()==0) {
			elevador.visitar_andar(fullest_floor());
		}
		
		elevador.start();
		get_next();
		
		while (chegadas.contains(false)) {
			if (elevador.in_transit){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				get_next();
			}
			chegadas = new ArrayList<Boolean>();
			for (Pessoa passageiro: passageiros) {
				chegadas.add(passageiro.arrived);
			}
			
		}
		elevador.should_stop=true;
		
	}
	
	

}