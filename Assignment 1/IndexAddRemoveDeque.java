
public class IndexAddRemoveDeque<T> extends IndexDeque implements IndexableAddRemove{

    public IndexAddRemoveDeque(int initsize) {
        //IndexAddRemoveDequeconstructor using the size of its super class
        super(initsize);
        
    }

    @Override
    //adds item to the array i places away from front 
    public void addToFront(int i, Object item) {
        //throws an exception if you input a positon too small or too big 
        if (i<0 || size < (i)){
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }
        // puts the array in front to back order, checks if you need to double the size as you are adding more values to the array
        // increments the size and back then shifts the values starting as the requested value from the front to the back right one, and sets the value i places away from
        //front as the requested item 
        FtoB();
        doubleSize();
        size++;
        back++;
        shiftRight(front+i, back);
        setFront(i, item);
        
        
    }

    @Override
    //adds item to the array i places away from back 
    public void addToBack(int i, Object item) {
        //throws an excpetion if tou input a position too big or too small 
        if (i<0 || size < (i)){
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }
        // puts the array in front to back order, checks if you need to double the size as you are adding more values to the array
        // increments the size and back then shifts the values starting as the requested value from teh back  to the back right one, and sets the value i places away from
        //back as the requested item 
        FtoB();
        doubleSize();
        size++;
        back++;
        shiftRight(back-i, back);
        setBack(i, item);
        // TODO Auto-generated method stub
        
    }

    @Override
    //removes the value i away from front and shifts what is necessay to fill the gap 
    public T removeFront(int i) {
        //throws an excpetion if you enter a value too small or too big 
        if (i<0 || size < (i+1)){
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }
        // puts the array in order then stores the value i places away from front, shifts everything to the left starting with the front +i index to the index as back
        // decreased the value of back and size, then returns the stored value 
        FtoB();
        Object temp = data[front +i];
			shiftLeft(front+i,back);
			data[back] = null;
			back--;
			size--;
			return (T) temp;
       
        
    }

    @Override
    //removes the item i away from back and shifts everything needed to fill the gap 
    public T removeBack(int i) {
        //throws an excpetion if you enter a value too small or too big 
        if (i<0 || size < (i+1)){
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }
        // puts the array in order then stores the value i places away from back, shifts everything to the left starting with the back-i index to the index as back
        // decreased the value of back and size, then returns the stored value 
        FtoB();
        Object temp = data[back-i];
			shiftLeft(back-i,back);
			data[back] = null;
			back--;
			size--;
			return (T) temp;
    }
    // method which shifts values to the right starting with bloc and ending with floc
    private void shiftRight(int floc, int bloc)
	{
		for (int i = bloc; i > floc; i--)
		{
			data[i] = data[i-1];
		}
	}
	// method that shifts evething to the right starting with flock and ending with bloc
	private void shiftLeft(int floc, int bloc)
	{
		for (int i = floc; i < bloc; i++)
		{
			data[i] = data[i+1];
		}
	}	

}
