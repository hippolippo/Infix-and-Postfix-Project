import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>{
    private ArrayList<T> queueList;
    private int maxSize;
    public MyQueue(int maxSize){
        queueList = new ArrayList<>();
        this.maxSize = maxSize;
    }
    public MyQueue(){this(10);}
    @Override
    public boolean isEmpty() {
        return queueList.size() == 0;
    }

    @Override
    public boolean isFull() {
        return queueList.size() >= maxSize;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if(isEmpty())
            throw new QueueUnderflowException();
        return queueList.remove(0);
    }

    @Override
    public int size() {
        return queueList.size();
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if(queueList.size() == maxSize)
            throw new QueueOverflowException();
        queueList.add(e);
        return true;
    }

    @Override
    public String toString(String delimiter) {
        String out = "";
        boolean first = true;
        for(T e : queueList){
            if(!first)
                out += delimiter;
            out += e.toString();
            first = false;
        }
        return out;
    }

    @Override
    public String toString(){
        return toString("");
    }

    @Override
    public void fill(ArrayList<T> list) {
        for(T e : list){
            enqueue(e);
        }
    }
}
