package myProject;

//Gili Faibish 315141481
//Ori Gigi 207314196
public class LinkedListTest {
	
	public static LinkedList list;
 
	public static void main(String[] args) {
 
		// Default constructor - let's put "0" into head element.
		list = new LinkedList();
 
		// add more elements to LinkedList
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
 
		/*
		 * Please note that primitive values can not be added into LinkedList directly. They must be converted to their
		 * corresponding wrapper class.
		 */
 
		System.out.println("Print: crunchifyList: \t\t" + list);
		System.out.println(".size(): \t\t\t\t" + list.size());
		System.out.println(".get(3): \t\t\t\t" + list.get(3) + " (get element at index:3 - list starts from 0)");
		System.out.println(".remove(2): \t\t\t\t" + list.remove(2) + " (element removed)");
		System.out.println(".get(3): \t\t\t\t" + list.get(3) + " (get element at index:3 - list starts from 0)");
		System.out.println(".size(): \t\t\t\t" + list.size());
		System.out.println("Print again: crunchifyList: \t" + list);
		list.undo();
		System.out.println("Print again: crunchifyList: \t" + list);
		list.undo();
		System.out.println("Print again: crunchifyList: \t" + list);
		list.undo();
		System.out.println("Print again: crunchifyList: \t" + list);
		list.add("1");
		System.out.println("Print again: crunchifyList: \t" + list);
	}
		
		/**
		 * Example outputs

				Print:  		[1][2][3][4][5]
				.size(): 				5
				.get(3): 				4 (get element at index:3 - list starts from 0)
				.remove(2): 				true (element removed)
				.get(3): 				5 (get element at index:3 - list starts from 0)
				.size(): 				4
				Print again:  	[1][2][4][5]
				Print again:  	[1][2][3][4][5]
				Print again:  	[1][2][3][4]
				Print again:  	[1][2][3]
				Print again:  	[1][2][3][1]
		  
		  

		 * 
		 */

}