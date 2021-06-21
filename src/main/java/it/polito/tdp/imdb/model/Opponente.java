package it.polito.tdp.imdb.model;

public class Opponente implements Comparable<Opponente> {

	private Director opposto;
	private Double peso;
	
	public Opponente(Director opposto, Double peso) {
		super();
		this.opposto = opposto;
		this.peso = peso;
	}

	public Director getOpposto() {
		return opposto;
	}

	public void setOpposto(Director opposto) {
		this.opposto = opposto;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
	public int compareTo(Opponente o) {
		return Double.compare(peso, o.peso);
	}

	@Override
	public String toString() {
		return opposto.firstName+" "+opposto.lastName+" "+peso;
	}
	
	
	
	
}
