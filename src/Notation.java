import java.text.ParseException;

public class Notation {
    private static int prec(Character op){
        switch(op){
            case '+':
            case '-':return 0;
            case '*':
            case '/':return 1;
            case '(':return 3;
            case ')':return 3;
            default: return 2;
        }
    }
    private static void moveOperatorsToPostfix(MyStack<Character> ops, MyQueue<Character> digs, Character key){
        while(prec(ops.top()) <= prec(key)){
            digs.enqueue(ops.pop());
        }
    }
    public static String convertInfixToPostfix(String infix){
        MyStack<Character> operatorStack = new MyStack<>(20);
        MyQueue<Character> numberQueue = new MyQueue<>(20);
        for(Character c : infix.toCharArray()){
            if(Character.isDigit(c)){
                numberQueue.enqueue(c);
            }
            else if(c == '('){
                operatorStack.push(c);
            }
            else if(c == '*' || c == '/' ||c == '+' ||c == '-'){
                try {
                    moveOperatorsToPostfix(operatorStack, numberQueue, c);
                } catch(StackUnderflowException e){
                    throw new InvalidNotationFormatException();
                }
                operatorStack.push(c);
            }
            else if(c == ')') {
                try {
                    moveOperatorsToPostfix(operatorStack, numberQueue, ' ');
                } catch(StackUnderflowException e){
                    throw new InvalidNotationFormatException();
                }
                if(operatorStack.top() != '(')
                    throw new InvalidNotationFormatException();
                operatorStack.pop();
            }
        }
        while(!operatorStack.isEmpty()){
            numberQueue.enqueue(operatorStack.pop());
        }
        return numberQueue.toString();
    }
    public static String convertPostfixToInfix(String postfix){
        MyStack<String> operandStack = new MyStack<>(20);
        for(Character c : postfix.toCharArray()){
            if(c == ' ') continue;
            else if(Character.isDigit(c)){
                operandStack.push(c.toString());
            }
            else if(c == '*' || c == '/' ||c == '+' ||c == '-'){
                String section;
                try{
                    String op1 = operandStack.pop();
                    String op2 = operandStack.pop();
                    section = "(" + op2 + c + op1 + ")";
                    operandStack.push(section);
                }catch(StackUnderflowException e){
                    throw new InvalidNotationFormatException();
                }
            }
        }
        String result;
        try{
            result = operandStack.pop();
        }catch(StackUnderflowException e){
            throw new InvalidNotationFormatException();
        }
        if(!operandStack.isEmpty()){
            throw new InvalidNotationFormatException();
        }
        return result;
    }
    public static double evaluatePostfixExpression(String postfix){
        MyStack<Double> operands = new MyStack<>(20);
        for(Character c : postfix.toCharArray()){
            if(c == ' ') continue;
            else if(Character.isDigit(c)){
                operands.push(Double.parseDouble(c.toString()));
            }
            else if(c == '+'){
                try {
                    Double op1 = operands.pop();
                    Double op2 = operands.pop();
                    operands.push(op2 + op1);
                } catch (StackUnderflowException e){
                    throw new InvalidNotationFormatException();
                }
            }
            else if(c == '-'){
                try {
                    Double op1 = operands.pop();
                    Double op2 = operands.pop();
                    operands.push(op2 - op1);
                } catch (StackUnderflowException e){
                    throw new InvalidNotationFormatException();
                }
            }
            else if(c == '*'){
                try {
                    Double op1 = operands.pop();
                    Double op2 = operands.pop();
                    operands.push(op2 * op1);
                } catch (StackUnderflowException e){
                    throw new InvalidNotationFormatException();
                }
            }
            else if(c == '/'){
                try {
                    Double op1 = operands.pop();
                    Double op2 = operands.pop();
                    operands.push(op2 / op1);
                } catch (StackUnderflowException e){
                    throw new InvalidNotationFormatException();
                }
            }
        }
        if(operands.size() != 1){ throw new InvalidNotationFormatException();}
        return operands.pop();
    }
}
