/*
 * 
 *  Jay Rajendran : 
 *  
 *  Class BluePrint : 
 *  	
 *  		Data : 
 *  			1. nextNode  - pointer to an object of class NODE representing next NODE.
 *  			2. prevNode - pointer to an object of class NODE representing prev NODE.
 *  			3. nodalValue - array of string that stores value of NODE.
 *  
 *  		Methods  :
 *  			1. Node createNode(String[]) - creates a new object of type NODE with value as per parameter.
 *  			2. void Node() - constructor. 
 *  			3. void setValueAtNode(String[]) - will set the value of the NODE being accessed to passed param.
 *  			4. String[] getValueAtNode() -  will get the value of the NODE being accessed.
 * 			5. Node getnextNode() - retrieves next Node;
 * 			6. Node getprevNode() - retrieves prev Node;
 */

public class Node<T> {

	Node<T> nextNode;// = null;
	Node<T> prevNode;
	
	T nodalValue;// = null;
	
	Node(){
		nodalValue = null;
	}
	
	 Node<T> createNode(T Value) {
		
		Node<T> newNode = new Node<T>();	
		newNode.setValueAtNode(Value);
		
		return 	newNode;
	}
	
	void setValueAtNode(T value) {
		this.nodalValue = value;
	}
	
	T getValueAtNode() {
		return nodalValue;
	}
	Node<T> getnextNode() {
		
		return this.nextNode;
	}
	
	Node<T> getprevNode() {
		
		return this.prevNode;
	}
	
	void setnextNode(Node<T> value) {
		this.nextNode = value;
	}
	
	void setprevNode(Node<T> value) {
		this.prevNode = value;
	}
}
