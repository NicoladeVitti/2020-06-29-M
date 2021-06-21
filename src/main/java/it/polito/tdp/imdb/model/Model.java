package it.polito.tdp.imdb.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {

	private ImdbDAO dao;
	private Map<Integer, Director> idMap;
	private Graph<Director, DefaultWeightedEdge> grafo;
	
	
	//RICORSIONE
	
	
	public Model() {
		dao = new ImdbDAO();
		idMap = new HashMap<>();
		
	}
	
	public void creaGrafo(Integer anno) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao.listAllDirectors(idMap);
		
		//AGGIUNGO I VERTICI 
		Graphs.addAllVertices(grafo, dao.getVertici(anno, idMap));
		
		//AGGIUNGO GLI ARCCHI 
		for(Adiacenza a : dao.getAdiacenze(idMap, anno)) {
			if(grafo.containsVertex(a.getD1()) && grafo.containsVertex(a.getD2())) {
				Graphs.addEdgeWithVertices(grafo, a.getD1(), a.getD2(), a.getPeso());
			}
		}
	}
	
	public Integer getNumArchi() {
		return grafo.edgeSet().size();
	}
	
	public Integer getNumVertici() {
		return grafo.vertexSet().size();
	}
	
	public Set<Director> getVertici(){
		return grafo.vertexSet();
	}
	
	public List<Opponente> getAdiacenti(Director partenza){
		
		List<Opponente> result = new ArrayList<>();
		
		for(DefaultWeightedEdge e : grafo.edgesOf(partenza)) {
			Director d = Graphs.getOppositeVertex(grafo, e, partenza);
			Opponente o = new Opponente(d, grafo.getEdgeWeight(e));
			result.add(o);
		}
		
		Collections.sort(result);
		return result;
	}
	
}
