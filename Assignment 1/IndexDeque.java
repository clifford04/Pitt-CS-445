
public class IndexDeque<T> extends MyDeque<T> implements Indexable<T>  {

    public IndexDeque(int initsize) {
        //IndexDeque constructor using the size of its super class
        super(initsize);
        
    }

    @Override
    //gets teh value i away from front 
    public T getFront(int i) {
        // puts the array in front to back order 
        FtoB();
        // throws an excpetion if the postion asked is too big or too small 
        if (i<0 || size < (i+1)){
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }
        
        return data[front +i];
    }

    @Override
    // gets the value i away fron back 
    public T getBack(int i) {
        // puts the array in front to back order 
        FtoB();
        // throws an excpetion if the postion asked is too big or too small 
        if (i<0 || size < (i+1)){
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }
        return data[back-i];
    }

    @Override
    //sets the value i away from front to T
    public void setFront(int i, T item) {
        // puts the array in front to back order 
        FtoB();
        // throws an excpetion if the postion asked is too big or too small 
        if (i<0 || size < (i+1)){
            throw new IndexOutOfBoundsException("Illegal Index " + i);
        }
        data[front+i] = item;
        
    }

    @Override
    //sets the value i away from back to T
    public void setBack(int i, T item) {
        // puts the array in front to back order 
        FtoB();
        // throws an excpetion if the postion asked is too big or too small 
        if (i<0 || size < (i+1)){
            throw new IndexOutOfBoundsException("Illegal Index"  + i);
        }
        data[back-i] = item;
        
    }

}
