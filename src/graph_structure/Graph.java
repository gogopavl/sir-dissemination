package graph_structure;

import java.util.ArrayList;

/**
 * Class implementing a Graph structure
 * @author gogopavl
 */
public class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Link> links;
	
        /**
         * Constructor for a Graph structure
         * @param vertices An ArrayList containing all vertices
         * @param links An ArrayList containing all links among vertices
         */
	public Graph(ArrayList<Vertex> vertices,ArrayList<Link> links){
		this.links = links;
		this.vertices = vertices;
	}
	
        /**
         * Default constructor
         */
	public Graph(){
                links = new ArrayList<>();
                vertices = new ArrayList<>();
	}
	
        /**
         * Getter method for the Graph's vertices
         * @return The ArrayList containing the vertices
         */
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
        
        /**
         * Setter method for the Graph's vertices
         * @param vertices The given ArrayList with the vertices
         */
	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}
        
        /**
         * Getter method for the Graph's links
         * @return The ArrayList containing the link
         */
	public ArrayList<Link> getLinks() {
		return links;
	}
        
        /**
         * Setter method for the Graph's links
         * @param links The given ArrayList with the links
         */
	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}
        
        /**
         * Method to add a Link to already existing ArrayList of links
         * @param link The Link to add
         */
	public void addLink(Link link){
		links.add(link);
	}
	
        /**
         * Method to add a Vertex to already existing ArrayList of vertices
         * @param vertex The Vertex to add
         */
	public void addVertex(Vertex vertex){
		vertices.add(vertex);
	}
        
        /**
         * Method to add a Link to already existing ArrayList of links
         * @param links The given ArrayList of links
         */
	public void addLinks(ArrayList<Link> links){
		this.links.addAll(links);
	}
	
        /**
         * Method to add a Vertex to already existing ArrayList of vertices
         * @param vertices The given ArrayList of vertices
         */
	public void addVertices(ArrayList<Vertex> vertices){
		this.vertices.addAll(vertices);
	}
	
        /**
         * Method to check if a pair of vertices is linked
         * @param vertex1 The first Vertex
         * @param vertex2 The second Vertex
         * @return true if they are linked, otherwise false
         */
	public Boolean isLinked(Vertex vertex1,Vertex vertex2){
		for(Link link : links){
			if((link.getFirstVertex().equals(vertex1) && link.getSecondVertex().equals(vertex2)) || (link.getFirstVertex().equals(vertex2) && link.getSecondVertex().equals(vertex1)) )
				return true;
		}
		return false;
	}
	
        /**
         * Method that returns the Link between two vertices, if it exists
         * @param vertex1
         * @param vertex2
         * @return 
         */
	public Link getLink(Vertex vertex1,Vertex vertex2){
                for(Link link : links){
                        if(isLinked(vertex1, vertex2))
                                return link;
                }
                return null;
	}
	
        /**
         * Method that returns the number of links a Vertex forms
         * @param vertex The Vertex of interest
         * @return The wanted number (integer)
         */
	public int linkedCount(Vertex vertex){
		int count = 0;
		for (Link link : links){
			if(link.getFirstVertex().equals(vertex) || link.getSecondVertex().equals(vertex))
				count++;
		}
		return count;
	}
	
	/**
	 * Method that deletes a link between two given vertices
	 * @param vertex1 The first Vertex
	 * @param vertex2 The second Vertex
	 */
	public void deleteLink(Vertex vertex1,Vertex vertex2){
		for (Link link : links){
			if(link.getFirstVertex().equals(vertex1) && (link.getSecondVertex().equals(vertex2)) || link.getFirstVertex().equals(vertex2) && (link.getSecondVertex().equals(vertex1))){
				links.remove(link);
				break;
			}
		}
	}
	
        /**
         * Method that returns the neighbors of a given Vertex (immediate connections)
         * @param vertex The Vertex of interest
         * @return An ArrayList of vertices
         */
	public ArrayList<Vertex> getNeighbors(Vertex vertex){
		ArrayList<Vertex> neighbors = new ArrayList<Vertex>();
		for (Link link : links){
			if(link.getFirstVertex().equals(vertex)) 
				neighbors.add(link.getSecondVertex());
			else if (link.getSecondVertex().equals(vertex))
				neighbors.add(link.getFirstVertex());
		}
		return neighbors;
	}
        
        /**
         * Method that returns a Vertex base on its index
         * @param index
         * @return 
         */
	public Vertex getVertex(int index){
		for (Vertex vertex : vertices){
			if(vertex.getIndex()==index){
				return vertex;
			}
		}
		return null;
	}	
        
        /**
         * Method that determines whether a Vertex is cut or not (1 or less connections)
         * @param vertex The given Vertex
         * @return true if the given Vertex is cut, otherwise false
         */
	public Boolean isCutEdge(Vertex vertex){
		if (linkedCount(vertex)<=1)
			return true;
		else
			return false;
	}
	
        /**
         * Void method that displays the graph structure
         * First prints all vertices & their state
         * Then prints all links & their weight
         */
	public void show(){
		System.out.println("--------Vertices--------");
		System.out.println("Number of vertices: "+vertices.size());
		for(Vertex vertex : vertices){
			System.out.println(vertex.getIndex()+" vertex state: " + vertex.getState());
		}
		System.out.println("---------Links---------");
		System.out.println("Number of links: "+links.size());
		for(Link link : links){
			System.out.println("("+link.getFirstVertex().getIndex()+","+link.getSecondVertex().getIndex()+") Link Weight: "+link.getWeight());
		}
	}
}
