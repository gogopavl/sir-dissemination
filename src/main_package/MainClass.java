package main_package;

import virus_package.SIR;
import graph_structure.Link;
import graph_structure.Vertex;
import graph_structure.Graph;
import java.io.*;
import java.util.*;

public class MainClass {

	/**
         * Main method to create instances and invoke all needed functions
         * @param args None
         *
         * @throws NumberFormatException
         * @throws IOException 
         * 
         * @author gogopavl
         */
	public static void main(String[] args) throws NumberFormatException, IOException {
		
                ArrayList<Graph> graphs = Graph.fromFileToGraph();	//read input from file
		HashMap<Integer, Vertex> source = fromFileToSource(); //read infected nodes from file
		
		double beta = 0.01; //infection probability
		double gamma = 1; //recovery rate
		
		System.out.println("Started");
		
		SIR sr = new SIR(graphs.get(0), beta , gamma, source);
		sr.Initialization();
		
		
		ArrayList<Vertex> recovered = sr.Start_Spreading();
		System.out.println("Recovered size: "+recovered.size());
		
		Graph test = sr.return_Stated_Graph();
		
		
		test.show();
		
		
		for(Vertex ver : recovered){
			System.out.println("recovered: " + ver.getIndex() + " time: "+ver.getInfTime() + " state: "+ver.getState());
		}
		System.out.println("FIN");
		
		
		//ArrayList<ArrayList<Vertex>> list = new ArrayList<ArrayList<Vertex>>();
		//long vertices_sum = 0;
		//long links_sum = 0;
		
		
		
		/* Get number of vert & links
		for(Graph gr : graphs){
			vertices_sum+=gr.getVertices().size();
			links_sum+=gr.getLinks().size();
		}
		*/
		
		/* Sout graphs
		System.out.println("\n\nTotal vertices: "+vertices_sum +", Total links: "+links_sum+"\n\n");
		for (Graph graph : graphs){		//for-each graph
			graph.show();
		}
		//writeResultToFile(list);	//output
		*/
		 
	}
	
	/**
	 * Method that writes output to a file named "output.txt"
	 * @param list 
	 * @throws IOException
	 */
	public static void writeResultToFile(ArrayList<ArrayList<Vertex>> list) throws IOException{
		PrintWriter fw = new PrintWriter(new FileWriter("output.txt"));	
		for (ArrayList<Vertex> vertices : list){
			for (Vertex vertex : vertices){
				fw.print(vertex.getIndex());
			}
			fw.println("#");	//each graph is separated with the "#" symbol
		}
		fw.close();
	}
	
	/**
	 * Method that reads infected nodes from a file named "source.txt"
	 * @return The infected nodes - ArrayList<Vertex>
	 * @throws IOException
	 */
	public static HashMap<Integer, Vertex> fromFileToSource() throws IOException{
		HashMap<Integer, Vertex> infectedNodesSet = new HashMap<Integer, Vertex>();
		BufferedReader in = new BufferedReader(new FileReader("sources.txt"));
		String line;
		
		while((line = in.readLine()) != null)
		{
			if(!infectedNodesSet.containsKey(new Vertex(Integer.valueOf(line)))){
				infectedNodesSet.put(Integer.valueOf(line), new Vertex(Integer.valueOf(line)));
			}			
		}
		in.close();
		
		return infectedNodesSet;
	}
	
}
