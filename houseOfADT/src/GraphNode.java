

public class GraphNode {
	
	String nameOfVertex;
	graphLinkedList vertexEdgeList;
	
	GraphNode(){
		
		vertexEdgeList = new graphLinkedList();
	}
	String getVertexName() {
		
		return this.nameOfVertex;
	}
	
	graphLinkedList getEdgeList() {
		
		return this.vertexEdgeList;
	}
	
	void setVertexName(String newVertexName) {
		
		this.nameOfVertex = newVertexName;
		
	}
	void clearNode() {
		this.vertexEdgeList = new graphLinkedList();
		nameOfVertex = "";
	}
}
