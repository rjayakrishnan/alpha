
public class Stack<T> {
	
	LinkedList<T> stackBody;
	
	Stack(){
		
		stackBody = new LinkedList<T>();
	}
	T pop() {
		
		return stackBody.deleteNodeAtPos(1);
	};
	
	void push(T stackElement) {
		
		stackBody.addNodeToStartOfList(stackElement);
		
	};
	
	T peek() {
		
		return stackBody.firstNode.getValueAtNode();
	};
	
	int sizeOfStack() {
		return stackBody.getSize();
	};
	
	boolean isEmpty()	{ 
		
		if(this.sizeOfStack() > 0) return false;
		else return true;
	};
	
	
	
}
