package virus_package;

import graph_structure.Vertex;
import graph_structure.Graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class SIR {
	private Graph sir_graph;

	private ArrayList<Vertex> infectious = new ArrayList<Vertex>();
	private ArrayList<Vertex> toBeInfected = new ArrayList<Vertex>();
	private ArrayList<Vertex> recovered = new ArrayList<Vertex>();
	private ArrayList<Vertex> toBeRemoved = new ArrayList<Vertex>();
	
	private HashMap<Integer, Vertex> infectious_hm = new HashMap<Integer, Vertex>();
	
	private double beta;
	private double gamma;
	
	private HashMap<Integer, Vertex> source;
	
	private double time;
	
	Random generator = new Random();
	
	//source
	
	public SIR(Graph gr, double b, double g, HashMap<Integer, Vertex> s){
			sir_graph = gr;
			beta = b;
			gamma = g;
			source = s;
			
	}
	public void Initialization(){
		time = 0;
		for( Vertex v : sir_graph.getVertices()) { //for the first graph - testing
			if(source.containsKey(v.getIndex())){
				v.setState("I"); // set state to infected
				v.setInfTime(0); // set time
				source.replace(v.getIndex(), v); // update hashmap vertex
			}
			else {
				v.setState("S");
			}
		}
	}
	public ArrayList<Vertex> Start_Spreading(){
		
		Vertex currentVertex;
		Vertex tempVertex; // never used? 
		
		while(!source.isEmpty()){
			
						
			for(Entry<Integer, Vertex> infectious : source.entrySet()) {
//				could use Iterator to remove all infectious - see java notes   
				currentVertex = infectious.getValue();
				System.out.println("time: "+time + " - "+currentVertex.getInfTime() + " > "+gamma);
			    if(time - currentVertex.getInfTime() > gamma){
			    	
			    	toBeRemoved.add(currentVertex);
//			    	source.remove(currentVertex.getIndex()); // remove vertex from infectious list
//			    	System.out.println("Should be removed!!! " + source.size());
			    	
			    	
			    	sir_graph.getVertex(currentVertex.getIndex()).setState("R");
			    	recovered.add(sir_graph.getVertex(currentVertex.getIndex())); // add to recovered vertices list
			    }			  
			}
			for(Vertex tbr : toBeRemoved){
				source.remove(tbr.getIndex());
			}
			toBeRemoved.clear();
			
			  
		    //go through the SI contacts
				    
			double rand;
			
		    for(Entry<Integer, Vertex> infectious : source.entrySet()) {
		    	for(Vertex neigh : sir_graph.getNeighbors(infectious.getValue())){
		    		if(neigh.getState() == "S"){
		    			rand = generator.nextDouble();
		    			System.out.println("rand: "+rand + " <= "+beta);
		    			if( (rand <= beta) && (!source.containsKey(neigh.getIndex())) ){
		    				toBeInfected.add(neigh);
		    			}
		    		}
		    		
		    	}
		    	
		    }
		    time+=1;
		    System.out.println("to be inf size: "+toBeInfected.size());
		    // change the ones to be infectious at next time step
		    for(Vertex toBeInf : toBeInfected){
		    	toBeInf.setState("I");
		    	sir_graph.getVertex(toBeInf.getIndex()).setState("I"); // set state
		    	sir_graph.getVertex(toBeInf.getIndex()).setInfTime(time); // set inf time
		    	
		    	//System.out.println("infectious size: "+infectious_hm.size());
		    	
		    	infectious_hm.put(toBeInf.getIndex(), sir_graph.getVertex(toBeInf.getIndex()));
		    	
		    	
		    	//infectious.add(sir_graph.getVertex(toBeInf.getIndex())); // add to infectious list
		    	
		    }
		    System.out.println("source size: "+source.size());
		}
		return recovered;
		
	}
	public Graph return_Stated_Graph(){
		return sir_graph;
	}	


}
