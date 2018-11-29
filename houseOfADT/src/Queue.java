
public class Queue<T> {

	LinkedList<T> queueBody;
	
	Queue(){
		
		queueBody = new LinkedList<T>();
	}
	void enQueue(T queueElement) {
		
		this.queueBody.addNodeToList(queueElement);
	};
	
	T deQueue() {
		
		return this.queueBody.deleteNodeAtPos(1);
	};
	
	T front() {
		return this.queueBody.firstNode.getValueAtNode();
	};
	
	int sizeOfStack() {
		
		return this.queueBody.getSize();
	};
	
	boolean isEmpty()	{ 
		
		if(this.sizeOfStack() > 0) return false;
		else return true;
	};
	
}
