package main_package;

import java.util.ArrayList;


/**
 * Graph class 
 *
 */
public class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Link> links;
	
	public Graph(ArrayList<Vertex> vertices,ArrayList<Link> links){
		this.links = links;
		this.vertices = vertices;
	}
	
	public Graph(){
		links = new ArrayList<Link>();
		vertices = new ArrayList<Vertex>();
	}
	
	public void addLink(Link link){
		links.add(link);
	}
	
	public void addVertex(Vertex vertex){
		vertices.add(vertex);
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}
	public ArrayList<Link> getLinks() {
		return links;
	}
	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}
	
	public Boolean isLinked(Vertex vertex1,Vertex vertex2){
		for(Link link : links){
			if((link.getFirstVertex().equals(vertex1) && link.getSecondVertex().equals(vertex2)) || (link.getFirstVertex().equals(vertex2) && link.getSecondVertex().equals(vertex1)) )
				return true;
		}
		return false;
	}
	
	public Link getLink(Vertex vertex1,Vertex vertex2){
			for(Link link : links){
				if((link.getFirstVertex().equals(vertex1) && link.getSecondVertex().equals(vertex2)) || (link.getFirstVertex().equals(vertex2) && link.getSecondVertex().equals(vertex1)) )
					return link;
			}
			return null;
	}
	
	public int linkedCount(Vertex vertex){
		int count = 0;
		for (Link link : links){
			if(link.getFirstVertex().equals(vertex) || link.getSecondVertex().equals(vertex))
				count++;
		}
		return count;
	}
	
	/**
	 * Deletes link between given vertices
	 * 
	 * @param vertex1
	 * @param vertex2
	 */
	public void deleteLink(Vertex vertex1,Vertex vertex2){
		for (Link link : links){
			if(link.getFirstVertex().equals(vertex1) && (link.getSecondVertex().equals(vertex2))
			|| link.getFirstVertex().equals(vertex2) && (link.getSecondVertex().equals(vertex1))){
				links.remove(link);
				break;
			}
		}
	}
	
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
	public Vertex getVertex(int index){
		for (Vertex vertex : vertices){
			if(vertex.getIndex()==index){
				return vertex;
			}
		}
		return null;
	}	
	public Boolean isCutEdge(Vertex vertex){
		if (linkedCount(vertex)<=1)
			return true;
		else
			return false;
	}
	
	public void show(){
		System.out.println("--------Vertices--------");
		System.out.println("Number of vertices: "+vertices.size());
		for(Vertex vertex : vertices){
			System.out.println(vertex.getIndex()+" vertex state: " + vertex.getState());
		}
		System.out.println("---------Links---------");
		System.out.println("Number of vertices: "+links.size());
		for(Link link : links){
			System.out.println("("+link.getFirstVertex().getIndex()+","+link.getSecondVertex().getIndex()+") Link Weight: "+link.getWeight());
		}
	}
}
