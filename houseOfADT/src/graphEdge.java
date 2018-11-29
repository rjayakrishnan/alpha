
public class graphEdge {
	String vertexName;
	int edgeWeight;
	
	void setWeight(int newEdgeWgt) {
		this.edgeWeight = newEdgeWgt;
	}
	
	int getWeight() {
		return this.edgeWeight;
	}
	
	String getVertexName() {
		return this.vertexName;
	} 
	
	void setVertexName(String newVertexName) {
		this.vertexName = newVertexName;
	} 
}
