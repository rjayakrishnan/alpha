/*

 */

public class Node {

	Node nextNode;// = null;

	String nodeKey;
	float nodalValue;// = null;
	
	Node(){
		nodalValue = 0.0F;
		nodeKey = null;
	}
	
	static Node createNode(float Value, String key) {
		
		Node newNode = new Node();	
		newNode.setValueAtNode(Value);
		newNode.nodeKey = key;
		
		return 	newNode;
	}
	
	void setValueAtNode(float value) {
		this.nodalValue = value;
	}
	
	void setKeyAtNode(String key) {
		this.nodeKey = key;
	}
	
	float getValueAtNode() {
		return nodalValue;
	}
	
	String getkeyAtNode() {
		return nodeKey;
	}
	
	Node getnextNode() {
		
		return this.nextNode;
	}
	
	
	void setnextNode(Node value) {
		this.nextNode = value;
	}
	
}
