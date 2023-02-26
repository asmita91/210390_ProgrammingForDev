package Question8A;

public class Stacks {

        int top=-1;
        int stk[];
        int size;
        Stacks(int size){
            this.size=size;
            stk=new int[this.size];
        }
        boolean isFull(){
            return top==size-1;
        }
        boolean isEmpty(){
            return top==-1;
        }
        void push(int data){
            if(isFull()){
                System.out.println("stack overflow");
            }
            else{
                top++;
                stk[top]=data;
            }
        }
        int pop(){
            if(isEmpty()){
                System.out.println("stack underflow");
                return -99999;
            }
            else{
                int result=stk[top];
                top--;
                return result;
            }
        }
        int peek(){
            if(isEmpty()){
                System.out.println("stack underflow");
                return -999999;
            }
            else{
                return stk[top];
            }
        }
    }

