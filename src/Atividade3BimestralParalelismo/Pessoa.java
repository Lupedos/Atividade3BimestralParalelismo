package Atividade3BimestralParalelismo;


public class Pessoa extends Thread {
 private int andarAtual;
 private int destino;
 private Predio predio;

 public Pessoa(int andarAtual, int destino, Predio predio) {
     this.setAndarAtual(andarAtual);
     this.setDestino(destino);
     this.setPredio(predio);
 }

public int getAndarAtual() {
	return andarAtual;
}

public void setAndarAtual(int andarAtual) {
	this.andarAtual = andarAtual;
}

public Predio getPredio() {
	return predio;
}

public void setPredio(Predio predio) {
	this.predio = predio;
}

public int getDestino() {
	return destino;
}

public void setDestino(int destino) {
	this.destino = destino;
}
}
