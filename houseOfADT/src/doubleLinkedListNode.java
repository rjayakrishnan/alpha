

public class doubleLinkedListNode {

	doubleLinkedListNode nextNode;// = null;
	doubleLinkedListNode prevNode;
	
	Object nodalValue;// = null;
	
	doubleLinkedListNode(){
		nodalValue = null;
	}
	
	static doubleLinkedListNode createNode(Object Value) {
		
		doubleLinkedListNode newNode = new doubleLinkedListNode();	
		newNode.setValueAtNode(Value);
		
		return 	newNode;
	}
	
	void setValueAtNode(Object value) {
		this.nodalValue = value;
	}
	
	Object getValueAtNode() {
		return nodalValue;
	}
}
