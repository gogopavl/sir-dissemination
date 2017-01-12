package graph_structure;

/**
 * Class implementing a vertex
 * @author gogopavl
 */
public class Vertex {
	private int index; // Any vertex is unique - identifier
	private String state; // The state e.g. "I" for infected
	private double infTime; // The time since the vertex changed state
	
        /**
         * Vertex constructor
         * @param index The given integer - identifier
         */
	public Vertex(int index){
		this.index = index;
	}
        
        /**
         * Getter method for Vertex index
         * @return The index value (int)
         */
	public int getIndex() {
		return index;
	}
        
        /**
         * Setter method for Vertex index
         * @param index An integer number (>0)
         */
	public void setIndex(int index) {
		this.index = index;
	}
        /**
         * Getter method for Vertex state
         * @return The state value (String)
         */
	public String getState() {
		return state;
	}
        
        /**
         * Setter method for Vertex state
         * @param state A String (S, I, R)
         */
	public void setState(String state) {
		this.state = state;
	}
        
        /**
         * Getter method for Vertex infTime
         * @return Value of infTime (double)? - check
         */
	public double getInfTime() {
		return infTime;
	}

        /**
         * Setter method for Vertex infTime
         * @param infTime A double
         */
	public void setInfTime(double infTime) {
		this.infTime = infTime;
	}
}
