
public class pseudoSkewedTree {
	Node leftNode;
	Node rightNode;
	int numberOfNodes;
	pseudoSkewedTree() {
		leftNode = new Node();
		leftNode = Node.createNode(0.0F, "HEADER-TAIL");
		
		rightNode = new Node();
		rightNode = Node.createNode(0.0F, "HEADER-TAIL");
		
		numberOfNodes = 0;
	}
	void addNodeToRight(float value, String key) {
		Node tmpNode;
		
		tmpNode = Node.createNode(value, key);
		tmpNode.nextNode = this.rightNode;
		rightNode = tmpNode;
		numberOfNodes++;
	}
	
	void addNodeToLeft(float value, String key) {
		Node tmpNode;
		
		tmpNode = Node.createNode(value, key);
		tmpNode.nextNode = this.leftNode;
		this.leftNode = tmpNode;
		numberOfNodes++;
	}
	
	void destroyTree() {
		Node tmpNode = new Node();
		
		tmpNode = this.leftNode;
		//burn left bridge
		while(!tmpNode.nodeKey.equalsIgnoreCase("HEADER-TAIL")) {
			
			this.leftNode = this.leftNode.getnextNode();
			tmpNode = null;
			
			tmpNode = this.leftNode;
		}
		
		tmpNode = this.rightNode;
		//burn right bridge
		while(!tmpNode.nodeKey.equalsIgnoreCase("HEADER-TAIL")) {
			this.rightNode = this.rightNode.getnextNode();
			tmpNode = null;
			
			tmpNode = this.rightNode;			
		}
		this.leftNode = null;
		this.rightNode = null;
		this.numberOfNodes = 0;
	}
}
