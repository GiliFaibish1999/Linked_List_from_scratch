package myProject;

// Gili Faibish 315141481
// Ori Gigi 207314196
class LinkedList {
	private static int counter;
	private Node head;
	
	// variables for the "undo" 
	private Object lastRemovedData; 
	private Stack history;
	private Stack action;
	private Stack indexlst;
	
	// Default constructor
	public LinkedList() {
		head = null;		// empty list
		counter = 0;		// counter is size counter, now zero
		history = new Stack();
		action = new Stack();
		indexlst = new Stack();
	}

	// appends the specified element to the end of this list.
	public void add(Object data) {
		Node n = new Node(data); //creating a new node with the data
		if (counter==0) { // Beginning case, no head 
			head = n;  
		}
		else { // middle case, there is a head
			Node temp = new Node(null);
			temp = head;
			//Node temp = new Node((head.getData()),head.getNext());
			for (int i = 0; i < (counter-1); i++) {
				temp = temp.getNext();
			}	
			temp.setNext(n);
			
		}
		incrementCounter();
		
		// for undo
		action.push("add");
		indexlst.push(counter-1);
	}

	private static int getCounter() {
		return counter;
	}
 
	private static void incrementCounter() {
		counter++;
	}
 
	private void decrementCounter() {
		counter--;
	}
 
	// inserts the specified element at the specified position in this list
	public void add(Object data, int index) {
		Node n = new Node(data); //creating a new node with the data
		if (counter==0) { // Beginning case, no head 
			head = n; 
		}
		else if(index==0) { // at beginning - there is a head to replace
			n.setNext(head);
			head = n;
		}
		else if (index!=0){ // middle case, there is a head
			Node temp = new Node(null);
			temp = head;
			for (int i = 0; i < (index-1); i++) {
				temp = temp.getNext();
			}
			n.setNext(temp.getNext());// connect n to the end of the loop
			temp.setNext(n);// sets n as the next for temp
		}
		incrementCounter();
		// for undo
		action.push("add");
		indexlst.push(index);
	}

	// inserts the specified element at the specified position in this list
	public void addToStack(Object data, int index) {
		Node n = new Node(data); //creating a new node with the data
		if (counter==0) { // Beginning case, no head 
			head = n; 
		}
		else if(index==0) { // at beginning - there is a head to replace
			n.setNext(head);
			head = n;
		}
		else if (index!=0){ // middle case, there is a head
			Node temp = new Node(null);
			temp = head;
			for (int i = 0; i < (index-1); i++) {
				temp = temp.getNext();
			}
			n.setNext(temp.getNext());// connect n to the end of the loop
			temp.setNext(n);// sets n as the next for temp
		}
		incrementCounter();
	}
	
	// returns the element at the specified position in this list.
	public Object get(int index)
	{
		Node temp = new Node(null);
		temp = head;
		for (int i = 0; i < index; i++) { // till I get to the index (if index is zero, zero times, head)
			temp = temp.getNext();
		}
		return temp.getData();
	}
	
	// undo last active operation (add/remove).
	public boolean undo() {
		Object lastAction = action.pop();
		if (lastAction.equals("add")) {	// In case addition was the last action
			this.removeFromStack(Integer.parseInt(indexlst.pop().toString()));
			return true;
		}
		else if (lastAction.equals("remove")){	// In case of removal was the last action
			this.addToStack(history.pop(),Integer.parseInt(indexlst.pop().toString()));
			return true;
		}
		
		// In case no action was made
		return false;
	}
	
	// removes the element at the specified position in this list.
	public boolean remove(int index) {
		if (index==0) {
			lastRemovedData = head.getData();
			head= head.getNext();
		}
		else { // middle case, there is a head
			Node temp = new Node(null);
			temp = head;
			for (int i = 0; i < (index-1); i++) {
				temp = temp.getNext();
			}
			lastRemovedData = temp.getNext().getData();
			temp.setNext(temp.getNext().getNext()); // sets n as the next for temp
		}
		decrementCounter(); // Decrement counter after removal

		action.push("remove");
		indexlst.push(index);
		history.push(lastRemovedData);
		
		// return statement informing action was completed
		return true;
	}
	
	// removes the element at the specified position in this list.
	public boolean removeFromStack(int index) {
		if (index==0) {
			lastRemovedData = head.getData();
			head= head.getNext();
		}
		else { // middle case, there is a head
			Node temp = new Node(null);
			temp = head;
			for (int i = 0; i < (index-1); i++) {
				temp = temp.getNext();
			}
			lastRemovedData = temp.getNext().getData();
			temp.setNext(temp.getNext().getNext()); // sets n as the next for temp
		}
		decrementCounter(); // Decrement counter after removal
		
		// return statement informing action was completed
		return true;
	}
	
	// returns the number of elements in this list.
	public int size() {
		return counter;
	}
 
	//String that will be in the following format: [1][2][3][1]
	public String toString() {
		Node temp = new Node(null);
		temp = head; 
		String mystr = "";
		
		// String creation
		for (int i = 0; i < this.size(); i++) {
			mystr =( mystr + "[" + temp.getData() + "]");
			temp = temp.getNext();
		}
		
		// Returns the string
		return mystr;
	}
 
	private class Node {
		// reference to the next node in the chain, or null if there isn't one.
		Node next;
		// data carried by this node. could be of any type you need.
		Object data;
 
		// Node constructor
		public Node(Object dataValue) {
			next = null;
			data = dataValue;
		}
 
		// another Node constructor if we want to specify the node to point to.
		@SuppressWarnings("unused")
		public Node(Object dataValue, Node nextValue) {
			next = nextValue;
			data = dataValue;
		}
 
		// these methods should be self-explanatory
		public Object getData() {
			return data;
		}
 
		@SuppressWarnings("unused")
		public void setData(Object dataValue) {
			data = dataValue;
		}
 
		public Node getNext() {
			return next;
		}
 
		public void setNext(Node nextValue) {
			next = nextValue;
		}
	}
	private class Stack {
		private Node headS;
		
		// Default constructor
		public Stack() {
			headS = null;		// empty list
		}
		
		public void push(Object data) {
			Node n = new Node(data, headS);
			headS=n;
		}
		
		public Object pop() {
			Object result = headS.getData();
			headS= headS.getNext();
			return result;
		}
	}
}