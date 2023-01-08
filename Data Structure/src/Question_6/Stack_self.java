package Question_6;



public class Stack_self<Integer> {
        int[] data;
        int size;
        int top;

        public Stack_self(int size) {
                this.size = size;
                data = new int[size];
                top = -1;
        }
        public Stack_self( ) {

        }

        public int getSize() {
                return size;
        }

        public int getTop() {
                return top;
        }
        public boolean isEmpty(){
                return data.length == 0;
        }
        public boolean Full(){
                return top == size-1;
        }
        public void push(int num) throws Exception {
                if (!Full()){
                        top+=1;
                        data[top] = num;

                }else {
                        throw new Exception("stack is full");
                }
        }
        public int pop() throws Exception {
                if(!isEmpty()){
                        top-=1;
                        int res = data[top];
                        this.data = new int[data.length-1];
                        return res;
                }
                throw new Exception("stack empty");
        }
        public int peek() throws Exception {
                if (!isEmpty()){
                        return data[top];
                }
                throw new Exception("stack empty");
        }
        public void printstack(){
                for (int i=0;i<data.length;i++){
                        System.out.println(data[i]);
                }
        }
        public void reversestack() throws Exception {
                Stack_self stackreverse = new Stack_self(size);
                int i=0, j=size-1;
                while(i<size){
                        stackreverse.push(data[j]);
                        i++;
                        j--;
                }
                stackreverse.printstack();
        }
        public void removeelement(int val) throws Exception {
                Stack_self afterremove = new Stack_self(size);
                int i=0;
                int j=0;
                while(i<size){
                        if(data[i]!=val){
                                afterremove.push(data[i]);
                        j++;
                        }
                        i++;
                }
                Stack_self afterremove2 = new Stack_self(size-(i-j));
                i=0;
                while(i<size){
                        if(data[i]!=val){
                                afterremove2.push(data[i]);

                        }
                        i++;
                }

                afterremove2.printstack();
        }

}
