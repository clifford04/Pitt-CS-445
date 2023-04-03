import javax.lang.model.util.ElementScanner14;

// CS 0445 Spring 2023
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a circular, doubly linked list of nodes.  See more comments below on the
// specific requirements for the class.

// You should use this class as the starting point for your implementation. 
// Note that all of the methods are listed -- you need to fill in the method
// bodies.  Note that you may want to add one or more private methods to help
// with your implementation -- that is fine.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only two instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	// Note: This method is implemented for you.  See code below.  Also read
	// the comments.  The code here may be helpful for some of your other
	// methods.
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0)  // special case for empty String
		{
			firstC = null;
			length = 0;
		}
		else
		{
			firstC = new CNode(s.charAt(0));  // create first node
			length = 1;
			CNode currNode = firstC;
			// Iterate through remainder of the String, creating a new
			// node at the end of the list for each character.  Note
			// how the nodes are being linked and the current reference
			// being moved down the list.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				currNode = newNode;			// move down the list
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
		}
	}

	// Return the entire contents of the current MyStringBuilder as a String
	// For this method you should do the following:
	// 1) Create a character array of the appropriate length
	// 2) Fill the array with the proper characters from your MyStringBuilder
	// 3) Return a new String using the array as an argument, or
	//    return new String(charArray);
	// Note: This method is implemented for you.  See code below.
	public String toString()
	{
		char [] c = new char[length];
		int i = 0;
		CNode currNode = firstC;
		
		// Since list is circular, we cannot look for null in our loop.
		// Instead we count within our while loop to access each node.
		// Note that in this code we don't even access the prev references
		// since we are simply moving from front to back in the list.
		while (i < length)
		{
			c[i] = currNode.data;
			i++;
			currNode = currNode.next;
		}
		return new String(c);
	}

	// Create a new MyStringBuilder initialized with the chars in array s. 
	// You may NOT create a String from the parameter and call the first
	// constructor above.  Rather, you must build your MyStringBuilder by
	// accessing the characters in the char array directly.  However, you
	// can approach this in a way similar to the other constructor.
	public MyStringBuilder(char [] s)
	{
		if (s == null || s.length == 0)  // special case for empty String
		{
			firstC = null;
			length = 0;
		}
		else
		{
			firstC = new CNode(s[0]);  // create first node
			length = 1;
			CNode currNode = firstC;
			// Iterate through remainder of the character array , creating a new
			// node at the end of the list for each character.  
			for (int i = 1; i < s.length; i++)
			{
				CNode newNode = new CNode(s[i]);  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				currNode = newNode;			// move down the list
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
		}
	}
	
	// Copy constructor -- make a new MyStringBuilder from an old one.  Be sure
	// that you make new nodes for the copy (traversing the nodes in the original
	// MyStringBuilder as you do)
	public MyStringBuilder(MyStringBuilder old)
	{
		if (old == null || old.length() == 0)  // special case for empty String
		{
			firstC = null;
			length = 0;
		}
		else
		{
			firstC = new CNode(old.firstC.data);  // create first node
			length = 1;
			// have nodes to trace both the String builder 
			CNode currNode = firstC;
			CNode oldCurNode = old.firstC;
			// Iterate through both of the string builders , creating a new
			// node at the end of the list for each character in the old string builder . 
			for (int i = 1; i < old.length(); i++)
			{
				CNode newNode = new CNode(oldCurNode.next.data);  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				oldCurNode = oldCurNode.next;
				currNode = newNode;			// move down the list
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
		}
	}
	
	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
		length =0;
	}
	
	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!  Note
	// that you cannot simply link the two MyStringBuilders together -- that is
	// very simple but it will intermingle the two objects, which you do not want.
	// Thus, you should copy the data in argument b to the end of the current
	// MyStringBuilder.
	public MyStringBuilder append(MyStringBuilder b)
	{
		if (length() == 0)  
		{
			return b;
		}
		else if( b.length() == 0 || b == null)
		{
			return this;
		}
		else
		{
			// have nodes to trace both the String builder
			//set the staring node to be the end of the string builder 
			CNode oldcurrNode = firstC.prev;
			if( length == 1)
			{
				 oldcurrNode = firstC;
			}
			CNode currNode = b.firstC;
			// Iterate through both of the string builders , creating a new
			// node at the end of the list for each character in the old string builder . 
			for (int i = 0; i < b.length(); i++)
			{
				CNode newNode = new CNode(currNode.data);  // create Node
				oldcurrNode.next = newNode;  	// link new node after current
				newNode.prev = oldcurrNode;	// line current before new node
				oldcurrNode = newNode;			// move down the list
				currNode = currNode.next;
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			oldcurrNode.next = firstC;
			firstC.prev = oldcurrNode;
			return this;
		}

		

		
	}

	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		// if lenght == 0 then the string builder is empty and the code is the same same as the constuctor 
		if (length == 0)  
		{
			firstC = new CNode(s.charAt(0));  // create first node
			length = 1;
			CNode currNode = firstC;
			// Iterate through remainder of the String, creating a new
			// node at the end of the list for each character.  Note
			// how the nodes are being linked and the current reference
			// being moved down the list.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				currNode = newNode;			// move down the list
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
			return this;
		}
		
		else if (s == null || s.length() == 0)  // special case for empty String
		{
			return this;
		}
		else
		{
			//set the staring node to be the end of the string builder 
			CNode currNode = firstC.prev;
			if( length == 1)
			{
				 currNode = firstC;
			}
			// Iterate through remainder of the String, creating a new
			// node at the end of the list for each character.  
			for (int i = 0; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				currNode = newNode;			// move down the list
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
			return this;
		}

	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{
		if (c == null || c.length == 0)  // special case for empty String
		{
			return this;
		}
		// if length = 0 same as constructo from array of character 
		else if (length() == 0)  
		{
			firstC = new CNode(c[0]);  // create first node
			length = 1;
			CNode currNode = firstC;
			// Iterate through remainder of the character array , creating a new
			// node at the end of the list for each character.  
			for (int i = 1; i < c.length; i++)
			{
				CNode newNode = new CNode(c[i]);  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				currNode = newNode;			// move down the list
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
			return this;
		}
		else
		{
			//set the staring node to be the end of the string builder 
			CNode currNode = firstC.prev;
			if( length == 1)
			{
				 currNode = firstC;
			}
			// Iterate through remainder of the char array , creating a new
			// node at the end of the list for each character.  
			for (int i = 0; i < c.length; i++)
			{
				CNode newNode = new CNode(c[i]);  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				currNode = newNode;			// move down the list
				length++;
			}
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
			return this;
		}
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
		
			//set the staring node to be the end of the string builder 
			CNode currNode = firstC.prev;
			if( length == 1)
			{
				 currNode = firstC;
			}
			// adds a single character 
			
				CNode newNode = new CNode(c);  // create Node
				currNode.next = newNode;  	// link new node after current
				newNode.prev = currNode;	// line current before new node
				currNode = newNode;			// move down the list
				length++;
			
			// After all nodes are created, connect end back to front to make
			// list circular
			currNode.next = firstC;
			firstC.prev = currNode;
			return this;
		
		
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if(index < 0 || index >= length){
			throw new IndexOutOfBoundsException("Index Out Of Bounds!");
		}
		
		char c;
		int i = 0;
		CNode currNode = firstC;
		
		// go to index and return the character at index 
		while (i < index)
		{
			
			i++;
			currNode = currNode.next;
		}
		c = currNode.data;
		return c;
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{
		// if start is invalid or end <= start do nothing
		if(length == 0)
		{
			return null;
		}
		else if (start < 0  || start >= length || end <= start)
		{
			return this;
		}
		//if legnth is 1 remove the only node in the list 
		else if (length == 1)
		{
			firstC = null;
			length--;
			return this;
		}
		else
		{
			CNode currNode = firstC;
			int index = 0;
			// iterate until the start 
			while (index < start)
			{
				currNode = currNode.next; 
				index++;
			}
			// check if end if greater than length and if it is then make the last equal legnth instead last = end
			//makes sure we dont go past the length 
			int last;
			if(end >= length)
			{
				last = length;
			}
			else
			{
				last = end;
			}
			//removes the nodes until last (either length or end )
			while(index < last)
			{
				CNode removeNode = currNode; // sets node as the node taht will be removed 
			if(currNode == firstC) // if the current node is the start 
			{
				firstC = removeNode.next; // make the next node start 
				// change pointers to get rid of the first node 
			firstC.prev = removeNode.prev;
			removeNode.prev.next = firstC;
			currNode = currNode.next;
			removeNode.next = null;
			removeNode.prev = null;
			
			length--;
			index++;
			}
			else
			{
				// change pointers to get rid of the node at currnode 
				removeNode.prev.next = removeNode.next;
				removeNode.next.prev = removeNode.prev;
				currNode = currNode.next;
				removeNode.next = null;
				removeNode.prev = null;
				
				length--;
				index++;
			}
			
			}
			return this;

		}


		
		
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).  If "index"
	// is the last character in the MyStringBuilder, go backward in the list in
	// order to make this operation faster (since the last character is simply
	// the previous of the first character)
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if(length == 0)
		{
			return null;
		}
		else if( index < 0 || index >= length)
		{
			return this;
		}
		//if legnth is 1 remove the only node in the list 
		else if (length == 1)
		{
			firstC = null;
			length--;
			return this;
		}
		else if (index ==0) // if removigin the first node 
		{
			CNode currNode = firstC;
			firstC = currNode.next; // have to change firstC since you are removing it 
			// change pointers to remove first node 
			firstC.prev = currNode.prev;
			currNode.prev.next = firstC;
			currNode.next = null;
			currNode.prev = null;
			length--;
			return this;
		}
		else if (index ==length-1) // if index is the last node in in last 
		{
			CNode currNode = firstC.prev; // go to firstC.prev which is the last one 
			// change pointers to remove last node
			firstC.prev = currNode.prev;
			currNode.prev.next = firstC;
			currNode.next = null;
			currNode.prev = null;
			length--;
			return this;
		}
		else
		{
			CNode currNode = firstC;
			// itearte until index 
			for(int i =0; i < index; i ++)
			{
				currNode = currNode.next;
			}
			// change pointers to remove node at index
			currNode.prev.next = currNode.next;
			currNode.next.prev = currNode.prev;
			currNode.next = null;
			currNode.prev = null;
			length--;
			return this;


		}
		
		
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		
		if(length == 0)
		{
			return -1;
		}
		else if (str.length() == 0)
		{
			return 0;
		}
		else if (str.length() > length)
		{
			return -1;
		}
		else
		{
			CNode currNode = firstC;
			int index = 0;
			//iterate through the string builder 
			while (index < length)
			{
				// check if the current node character is teh same as the first chatacter of the string , else iterate teh string builder 
				if(currNode.data == str.charAt(0))
				{
					// have a temp node to iterate though the list while still holding the position of currnode
					CNode tempNode = currNode;
					int i = 0;
					// nested loop to iterate through the string 
					while(i < str.length())
					{
						// if tempnode character is same as the character in teh string , iterate temp
						if(tempNode.data == str.charAt(i))
						{
							tempNode = tempNode.next;
							i++;
						}
						else
						{
							// if they are not the same break out of this loop and return to the original check 
							break;
						}
					}
					if(i == str.length())
					{
						// if we reached the enf of the string return the index 
						return index;
					}
					else
					{
						// if we are not at the end move currnode up one 
						currNode = currNode.next;
						index++;
					}
				}
				else
				{
					currNode = currNode.next;
					index++;
				}
			}
			// if the string is not in the list return -1 
			return -1;
		}
		
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		
		if( offset < 0 || offset >length)
		{
			return this;
		}
		
		else if (offset == length)
		{
			append(str);
			return this;
		}
		else if (offset == 0) // if you are inserting at the start of the list 
		{
			
			CNode currNode = firstC;
			for(int i =str.length()-1; i >= 0; i --) // move through the sting backwards
			{
				CNode newNode = new CNode(str.charAt(i)); // create a new node containg the char at i
				// add it before the front and hcange hte pointers and move FirstC back 1 
				newNode.next = currNode;
				newNode.prev = currNode.prev;
				currNode.prev.next = newNode;
				currNode.prev = newNode;
				currNode = newNode;
				firstC = newNode;
				length++;

				
			}
			return this;
		}	

			else
		{
			// itearte until index
			CNode currNode = firstC;
			for(int i =0; i < offset; i ++)
			{
				currNode = currNode.next;
			}
			for(int i =str.length()-1; i >= 0; i --)
			{
				CNode newNode = new CNode(str.charAt(i));// create a new node containg the char at i 
				// add it before the index and hcange hte pointers and move FirstC back 1 
				newNode.next = currNode;
				newNode.prev = currNode.prev;
				currNode.prev.next = newNode;
				currNode.prev = newNode;
				currNode = newNode;
				length++;

				
			}
			return this;
		}

	
}
		
		
	

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		if( offset < 0 || offset >length)
		{
			return this;
		}
		
		else if (offset == length)
		{
			append(c);
			return this;
		}
		else
		{
			// similar to insert string expect with no loop since it is just a single character 
			CNode currNode = firstC;
			for(int i =0; i < offset; i ++)
			{
				currNode = currNode.next;
			}
			CNode newNode = new CNode(c);
			newNode.next = currNode;
			newNode.prev = currNode.prev;
			currNode.prev.next = newNode;
			currNode.prev = newNode;
			length++;
			return this;
		}
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder replace(int start, int end, String str)
	{
		// check if end if greater than length and if it is then make the last equal legnth instead last = end
			//makes sure we dont go past the length 
		int last;
			if(end >= length)
			{
				last = length;
			}
			else
			{
				last = end;
			}
		if((start < 0) || start >=length || end <= start)
		{
			return this;
		}
		
		else if(length == 0 )
		{
			append(str);
			return this;
		}
		else if(start == 0) // if we are replacing something starting from the strong of the list 
		{
			// same code as delete from start = 0
			CNode currNode = firstC;
			int index = 0;
			while(index < last)
			{
				CNode removeNode = currNode;
			if(currNode == firstC)
			{
				firstC = removeNode.next;
			firstC.prev = removeNode.prev;
			removeNode.prev.next = firstC;
			currNode = currNode.next;
			removeNode.next = null;
			removeNode.prev = null;
			
			length--;
			index++;
			}
			else
			{
				removeNode.prev.next = removeNode.next;
				removeNode.next.prev = removeNode.prev;
				currNode = currNode.next;
				removeNode.next = null;
				removeNode.prev = null;
				
				length--;
				index++;
			}
			
			}
			// same code as add from start 
			for(int i =str.length()-1; i >= 0; i --)
			{
				CNode newNode = new CNode(str.charAt(i));
				newNode.next = currNode;
				newNode.prev = currNode.prev;
				currNode.prev.next = newNode;
				currNode.prev = newNode;
				currNode = newNode;
				firstC = newNode;
				length++;

				
			}
			return this;

		}
		else 
		{
			// crate a node taht saves the position of start so we do not need to iterate through the list after deleting and 
			// can just jump to to saved node for the insertion 
			CNode saveNode = firstC;
			int index = 0;
			while (index < start-1)
			{
				saveNode = saveNode.next; 
				index++;
			}
			
			CNode currNode = saveNode.next;
			index++;
			
			
			// same code as delete from index 
			while(index < last)
			{
				CNode removeNode = currNode;
			if(currNode == firstC)
			{
				firstC = removeNode.next;
			firstC.prev = removeNode.prev;
			removeNode.prev.next = firstC;
			currNode = currNode.next;
			removeNode.next = null;
			removeNode.prev = null;
			
			length--;
			index++;
			}
			
			else
			{
				removeNode.prev.next = removeNode.next;
				removeNode.next.prev = removeNode.prev;
				currNode = currNode.next;
				removeNode.next = null;
				removeNode.prev = null;
				
				length--;
				index++;
			}
			
			}
			// same code as add from index but we make currNode = saveNode.next so we dont have to iterate through the list again 
			currNode = saveNode.next;
			for(int i =str.length()-1; i >= 0; i --)
			{
				CNode newNode = new CNode(str.charAt(i));
				newNode.next = currNode;
				newNode.prev = currNode.prev;
				currNode.prev.next = newNode;
				currNode.prev = newNode;
				currNode = newNode;
				length++;

				
			}
			return this;
			


		}
	
		
		


	}

	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder.  For this method
	// you should do the following:
	// 1) Create a character array of the appropriate length
	// 2) Fill the array with the proper characters from your MyStringBuilder
	// 3) Return a new String using the array as an argument, or
	//    return new String(charArray);
	public String substring(int start, int end)
	{
		CNode currNode = firstC;
		char [] c = new char [end-start]; // makes the list of the correct size 
		// itearte through hte list until start 
		for(int x = 0; x < start; x++)
		{
			currNode = currNode.next;
		}
		// iterate through the list adding each node to the character array
		for(int i =start; i < end; i ++)
		{
			c[i-start] = currNode.data; // since i is at start we have to subtract start so we dont go out of bounds on the char array 
			currNode = currNode.next;
		}
		return new String(c);
	}

	// Return as a String the reverse of the contents of the MyStringBuilder.  Note
	// that this does NOT change the MyStringBuilder in any way.  See substring()
	// above for the basic approach.
	public String revString()
	//iterate through the nodes backwards adding each node to the character array then printing it out as a string
	{
		CNode currNode = firstC;
		char [] c = new char [length]; 
		for(int i =0; i < length; i ++)
		{
			c[i] = currNode.prev.data;
			currNode = currNode.prev;
		}
		return new String(c);
	}
	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data, next and prev fields directly.
	private class CNode
	{
		private char data;
		private CNode next, prev;

		public CNode(char c)
		{
			data = c;
			next = null;
			prev = null;
		}

		public CNode(char c, CNode n, CNode p)
		{
			data = c;
			next = n;
			prev = p;
		}
	}
}

