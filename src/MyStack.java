import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T>{
    private ArrayList<T> stackList;
    private int maxSize;
    public MyStack(int maxSize){
        stackList = new ArrayList<>();
        this.maxSize = maxSize;
    }
    public MyStack(){this(10);}
    @Override
    public boolean isEmpty() {
        return stackList.size() == 0;
    }

    @Override
    public boolean isFull() {
        return stackList.size() >= maxSize;
    }

    @Override
    public T pop() throws StackUnderflowException {
        if(isEmpty())
            throw new StackUnderflowException();
        return stackList.remove(stackList.size()-1);
    }

    @Override
    public T top() throws StackUnderflowException {
        if(isEmpty())
            throw new StackUnderflowException();
        return stackList.get(stackList.size()-1);
    }

    @Override
    public int size() {
        return stackList.size();
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        if(stackList.size() == maxSize)
            throw new StackOverflowException();
        stackList.add(e);
        return true;
    }

    @Override
    public String toString(String delimiter) {
        String out = "";
        boolean first = true;
        for(T e : stackList){
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
        for(T e: list){
            push(e);
        }
    }
}
