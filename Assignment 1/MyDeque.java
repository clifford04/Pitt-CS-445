// CS 0445 Spring 2023
// One solution to Recitation Exercise 1.  Your solution may differ, but it should
// be logically equivalent!
import java.util.Arrays;

public class MyDeque<T> implements DequeInterface<T>
{
	protected T [] data; 
	protected  int back;// the index of the back  position of the deque 
	protected  int front; // the index of the front position of the deque 
	protected int size; // equal to the logical size of the deque 
// constructor, creates a Deque
	public MyDeque(int initsize)
	{
		data = (T []) new Object[initsize];
		front = -1; 
		back = -1;
		size = 0;
	}
	// creates a copt of a dequeu, is a deeper but not deep copy
	public MyDeque(MyDeque<T> old)
	{
		// puts the old array in front to back order front = 0, back = logical back 
		old.FtoB();
		data = (T []) new Object[old.data.length];
		for(int i = 0; i <=old.back; i++)
		{
			  data[i] = old.data[i];
		}
		// sets all the values of the new deque to the same values as the old one 
		front = old.front;
		back = old.back;
		size = old.size;
	}
	
	//puts the contents of the deque into a string and prints it our front to back 
	public String toString()

	{
		StringBuilder B = new StringBuilder();
		if (size > 0)
		{
			if (front>= back)
			// puts the array in front to back order front = 0, back = logical back 
				FtoB();
			
			for (int i = front; i < back; i++)
			{
				B.append(data[i].toString());
				B.append(", ");
			}
			B.append(data[back].toString());
			return B.toString();
		}
		
		
		else return new String("Deque is Empty");
	}	
	

	//adds an entry to the position to the front of the deque
	public void addToFront(T newEntry)
	{
		// if it is empty set front and bacj to the same place and size to 1
			if (size == 0) // special case for empty Deque
			{
				back = 0;
				front = 0;
				data[back] = newEntry;
				size = 1;
			}
			else
			{
				// run double size to check if we need to expand the array before we add to it 
				doubleSize();
				// if the front is at the logical front of the array we set fron to the logical back of the array and put the data at the back of the array aswell 
				if(front == 0)
				{
					size++;
					front = data.length-1;
					data[front] = newEntry;
					
				}
				// if front is not the very front of the array we make the size larger and shift front forward one and add the data 
				else
				{
					size++;
					front--;
				data[front] = newEntry;
				
				}
				
			
			}
		
		// first check is check if array is full if array is full run Double() which doulbes the size of the array
		//Double will also deal with the logic of it 
		}
	
	
	// 
	public void addToBack(T newEntry)
	{
		// if it is empty set front and bacj to the same place and size to 1
		if (size == 0) // special case for empty Deque
			{
				back = 0;
				front = 0;
				data[back] = newEntry;
				size = 1;
			}
			else
			{
				// run double size to check if we need to expand the array before we add to it 
				doubleSize();
				// if back is equal to the physical back of the array we set back to the first position in the array and add data to teh front aswell 
				if(back == data.length-1)
				{
					doubleSize();
					size++;
					back = 0;
					data[back] = newEntry;
					
				}
				else
				// if we are not at the back we just incriemnt back forward one and puts the data there 
				{
					doubleSize();
					size++;
					back++;
				data[back] = newEntry;
				
				}
				
			
			}
		
		// first check is check if array is full if array is full run Double() which doulbes the size of the array
		//Double will also deal with the logic of it 
	}
	
	public T removeBack()
	{
		// check to see if there is data in the deque and if the position of back is equal to 0 
		 if(size > 0 &&back ==0)
		{
			// if back is at position 0 then we store teh data of back, set the current back position or index 0 to null
			// then we decrease the size and set back to the last index of the array due to its circualr nature, then we return the value of the previous back 
			T temp = data[back];
			data[back] = null;
			size--;
			back = data.length-1;
			return temp;
		}
		else if (size > 0)
		{
			// if the back is not at position 0 but there are items in the deque then we store the value of back, set teh current back to null and decremetn back by one
			// then we return the value of the previous back 
			T temp = data[back];
			data[back] = null;
			size--;
			back--;
			return temp;
		}
		// if the array is empty we cant return anything so we return null 
		else
			return null;
	}
	
	public T getBack()
	{
		// if there are items in the array return the value at the index of back 
		if (size > 0)
			return data[back];
		else
			return null;
	}
	
	//remove the item at the front index for the deque amd decrease the size 
	public T removeFront()
	{
		// if there are items in the array amd the front index is at the very back of the phyiscal array decrement the size, store the front value
		// set the current front value to null and make front equal to the physical first position in the deque then return the stored previous front 
		if(size > 0 &&front ==data.length-1)
		{
			size--;
			T temp = data[front];
			data[front] = null;
			
			front = 0;
			return temp;
		}
		// if there are items in the array and the front index IS NOT at the very front of the array then you decrment the szie and story teh value of the front index
		// set teh front value to null then move the front up one value in the array and return the stored value 
		else if (size > 0)
		{
			size--;
			T temp = data[front];
			data[front] = null;
			
			front++;
			return temp;
		}
		// if there is nothing in the array return null 
		else
			return null;
	}
	
	public T getFront()
	{
		// if there are items in the array return the value at the index of front 
		if (size > 0)
			return data[front];
		else
			return null;
	}		
	
	public boolean isEmpty()
	{// if there is nothing in the array return true, if there is something in the array return true 
		return size == 0;
	}
	
	// Make a new array to reset Deque. ).
	public void clear()
	{
		//resets the constructor to get rid of all the items and reset the size and the position of front and back 
		data = (T []) new Object[data.length];
		front = -1;
		back = -1;
		size = 0;
	}
		


	public int size() {
		
		return size;
	}

	
	public int capacity() {
		
		return data.length;
	}	
	//returs true if 2 deques have the same values in the same order from front to back does not matter the index just the values in comparison to
	// the positions of front and back due to the circular nature of the deque 
	public boolean equals(MyDeque<T> rhs)
	{// runs front to back on btoh of the arrays putting both of them in logical front to back order 
		FtoB();
		rhs.FtoB();;
		
		// if the sizes arent equal return false 
		if(rhs.size != size)
		{
			return false;
		}
		else
		{
			for(int i = front; i <=back; i ++)
			{// compare the values of the 2 arrays at the same index, since they are in oder due to FtoB, if they are not equal return false 
				if(data[i].equals(rhs.data[i]) == false )
				{
				return false ;	
				}
			}
			// if the 2 arrays are the same size and none of the values are different return true, meaning the 2 arrays are equal
			return true;
		}

		
		
	}
	// doubles the size of the array 
	protected void doubleSize()
	{
		// check if the logical size is greater then or equal to the physical size of the array 
		if(size >= data.length-1)
		{
			
			int bigger = data.length *2;
		if (front> back)
		// puts the array in front to back order and adds null spaces to the back of the array to double the size
		//keeps the same logical deque order due to FtoB and the numbers that follow front and back are the same 
			FtoB();

			data = Arrays.copyOf(data, bigger);
		}
		else
		// if the array is not full do nothing 
		{

		}
            
        
    }
	// my own method which puts the deque in order of front being at index 0 and back being at the last logical position in the array
	// purpose being it allows others methods to be executed more easily such as Tostring() and Equals()
	// keeps the values before and after front and back the same
	//just changes the indexes in the actual array but the deque values are the same 
	protected void FtoB()
	{
		// creates 2 varibales to keep track of the old positions and new positions using front and 0
		int old = front;
		int newp = 0;
		// create a new array to store values 
		T temp[] = (T[])new Object[size];
		for(int i=0; i < size; i++)
		{
			// if we reach the end of the array we go back to index 0 
			if(old == data.length)
			{
				old = 0;
				
			}
			// sets the value of temp at the new position equal to the value of data at the old position 
			temp[newp] = data[old];
			// increment old and new
			old++;
			newp++;
		}
		// set front to 0 and back to size-1 so they are consistent with the values of the new array 
		front = 0;
		back = size-1;
		// set data equal to temp 
		data = Arrays.copyOf(temp,data.length);
	}

}