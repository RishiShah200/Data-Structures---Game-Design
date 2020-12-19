import java.util.EmptyStackException;

public class SuperList<E> {

    ListNode<E> root;
    ListNode<E> end;
    int size;

    public SuperList() {
        root = null;
        end = null;
        size = 0;
    }

    public SuperList(E value) {
        root = new ListNode<E>(value);
        end = null;
        size = 1;
    }

    public int size() {
        return size;
    }

    public E stackPeek() {
        return end == null ? null : end.getValue();
    }

    public E queuePeek() {
        return end == null ? null : root.getValue();
    }

    public void clear(){
        root = null;
        end = null;
        size = 0;
    }

    //public boolean contains(E value){

  //  }


    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ListNode<E> temp = root;
        for (int x = 0; x < index; x++) {
            if (temp.hasNext()) {
                temp = temp.getNext();
            }
        }
        return temp.getValue();
    }

    public E remove(int index){
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if(index==0)
            return poll();
        if(index == size - 1)
            return pop();
        ListNode<E> node = root;
        for(int x = 0;x<index;x++){
            if(node.hasNext())
                node = node.getNext();
        }
        ListNode<E> beforeNode = node.getPrevious();
        ListNode<E> afterNode = node.getNext();
        beforeNode.setNext(afterNode);
        afterNode.setPrevious(beforeNode);
        size--;
        return node.getValue();
    }

    public void add(E value) {
        if (isEmpty()) {
            root = new ListNode<E>(value);
            end = root;
        } else {
            ListNode<E> node = new ListNode<E>(value);
            node.setPrevious(end);
            end.setNext(node);
            end = node;
        }
        size++;
    }

    public E pop() {
        if(isEmpty())
            throw new EmptyStackException();
        E temp = end.getValue();
        if (end.hasPrevious()) {
            end = end.getPrevious();
            end.setNext(null);
        } else {
            end = null;
            root = null;
        }
        size--;
        return temp;
    }

    public void push(E value) {
        add(value);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E temp = root.getValue();
        if (root.hasNext()) {
            root = root.getNext();
            root.setPrevious(null);
        } else {
            root = null;
            end = null;
        }
        size--;
        return temp;
    }

    public void add(int index, E value) {
        if (index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ListNode<E> node = new ListNode<E>(value);
        if (index == 0) {
            if(isEmpty()){
                root = new ListNode<E>(value);
                end = root;
                size = 1;
            }
            else{
                node.setNext(root);
                root.setPrevious(node);
                root = node;
                size++;
            }

        }else if (index == size) {
            add(value);
        } else {
            ListNode<E> beforeNode = root;
            for (int x = 0; x < index - 1; x++) {
                beforeNode = beforeNode.getNext();
            }
            ListNode<E> afterNode = beforeNode.getNext();
            node.setPrevious(beforeNode);
            node.setNext(afterNode);
            beforeNode.setNext(node);
            afterNode.setPrevious(node);
            size++;
        }
    }

    public String toString() {
        String st = "[";
        ListNode<E> temp = root;
        if(!isEmpty()){
            for (int x = 0; x < size; x++) {
                st += temp.getValue();
                if (x < size - 1) {
                    st += ", ";
                }
                temp = temp.getNext();
            }
        }
        st += "]";
        return st;
    }

    public ListNode<E> getRoot() {
        return root;
    }

    public void setRoot(ListNode<E> root) {
        this.root = root;
    }

    public ListNode<E> getEnd() {
        return end;
    }

    public void setEnd(ListNode<E> end) {
        this.end = end;
    }

    public class ListNode<E> {
        E value;
        ListNode<E> next;
        ListNode<E> previous;

        public ListNode(E value) {
            this.value = value;
            next = null;
            previous = null;

        }

        public E getValue() {
            return value;
        }


        public ListNode<E> getNext() {
            return next;
        }

        public void setNext(ListNode<E> newNode) {
            next = newNode;
        }

        public ListNode<E> getPrevious() {
            return previous;
        }

        public void setPrevious(ListNode<E> newNode) {
            previous = newNode;
        }

        public boolean hasNext() {
            if (getNext() != null) {
                return true;
            }
            return false;
        }

        public boolean hasPrevious() {
            if (getPrevious() != null) {
                return true;
            }
            return false;
        }

    }
}


//    SuperList<Integer> list=new SuperList<Integer>();
//list.add(0);
//        list.add(0,8);
//        list.add(2,3);
//        System.out.println(list);
//        list.push(2);
//        System.out.println(list.pop());
//        System.out.println(list.poll());
