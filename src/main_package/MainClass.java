package main_package;

import virus_package.SIR;
import graph_structure.Link;
import graph_structure.Vertex;
import graph_structure.Graph;
import java.io.*;
import java.util.*;

public class MainClass {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		ArrayList<Graph> graphs = fromFileToGraph();	//read input from file
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
	
	
	/**
	 * Method that reads input -graphs- from file
	 * 
	 * @return The graph
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static ArrayList<Graph> fromFileToGraph() throws NumberFormatException, IOException{
		ArrayList<Graph> graphs = new ArrayList<Graph>();
		BufferedReader in = new BufferedReader(new FileReader("input.txt"));
		ArrayList<Integer> ints = null;
		ArrayList<Vertex> vertices = null;
		ArrayList<Link> links = null;
		String line;
		Graph graph;
		while((line = in.readLine()) != null)
		{
			if (line.charAt(0) == '#'){	
				graph = new Graph(vertices,links);
				if(graph.getVertices()!=null)
					graphs.add(graph);	
				ints = new ArrayList<Integer>();
				vertices = new ArrayList<Vertex>();
				links = new ArrayList<Link>();
			}
			else
			{
				int i=0;
				Vertex lineVertex = null;
				Vertex linkVertex = null;
				int weight = 0;
				for (String part : line.split("\\s+")) {
					Vertex vertex = null;
					Integer vertexNumber = Integer.valueOf(part);
					if(ints.contains(vertexNumber)){
						vertex = bringVertex(vertices,vertexNumber);
					}
					else if(!ints.contains(vertexNumber) && i==0 || i==1){
						ints.add(vertexNumber);
						vertex = new Vertex(vertexNumber);
						vertices.add(vertex);
					}
					if(i==0){
						lineVertex = vertex;
					}
					else if(i==1){
						linkVertex = vertex;
					}
					else{
						weight = Integer.valueOf(part);
					}
					i++;
				}
				if (!isLinked(lineVertex,linkVertex,links)){
					Link link = new Link(lineVertex,linkVertex,weight);
					links.add(link);	
				}
			}
		}
		in.close();
		return graphs;
	}
	/**
	 * Method that gets a certain vertex from a given list
	 * 
	 * @param vertices
	 * @param integer
	 * @return
	 */
	public static Vertex bringVertex(ArrayList<Vertex> vertices,int integer){
		for (Vertex vertex : vertices){
			if(vertex.getIndex()==integer)
				return vertex;
		}
		return null;
	}
	/**
	 * Boolean method that returns "true" if the given vertices are linked, otherwise it returns "false"
	 * 
	 * @param vertex1
	 * @param vertex2
	 * @param links
	 * @return
	 */
	public static Boolean isLinked(Vertex vertex1,Vertex vertex2,ArrayList<Link> links){
		for(Link link : links){
			if((link.getFirstVertex().equals(vertex1) && link.getSecondVertex().equals(vertex2)) || (link.getFirstVertex().equals(vertex2) && link.getSecondVertex().equals(vertex1)) )
				return true;
		}
		return false;
	}
}
