public class TreeSet <E extends Comparable<E>> {

    private TreeNode<E> root;
    private int size;
    private String str;

    public TreeSet(){
        root = null;
        size = 0;
        str = "";
    }

    public void add(E value){
        if (root == null) {
            root = new TreeNode<E>(value);
            size = 1;
        }
        else
            add(root,value);
    }

    private void add(TreeNode<E> node, E value){
        if(value.compareTo(node.getValue()) == 0)
            return;
        if(value.compareTo(node.getValue()) < 0){
            if(node.getLeft() == null){
                node.setLeft(new TreeNode<E>(value));
                size++;
                return;
            }
            add(node.getLeft(), value);
        }
        if(value.compareTo(node.getValue()) > 0){
            if(node.getRight() == null){
                node.setRight(new TreeNode<E>(value));
                size++;
                return;
            }
            add(node.getRight(), value);
        }
    }



    public boolean contains(TreeNode<E> root, E value){
        if(root == null){
            return false;
        }
        if(root.getValue().equals(value)){
            return true;
        }
        if(value.compareTo(root.getValue()) < 0){
            return contains(root.getLeft(),value);
        }

        return contains(root.getRight(),value);

    }

    public E minValue(TreeNode<E> root){
        if(root.getLeft() == null)
            return root.getValue();
        return minValue(root.getLeft());
    }

    public void remove(E value){
        if(root == null){
            return;
        }

        if(contains(root,value) == true){
            if(root.getLeft() == null && root.getRight() == null){
                root = null;
                size = 0;
                return;
            }
            else{
                size--;
                root = remove(root,value);
            }
        }
    }

    public TreeNode<E> remove(TreeNode<E> root, E value){
        if(root == null){
            return null;
        }

        if(value.compareTo(root.getValue()) < 0){
            root.setLeft(remove(root.getLeft(),value));
        }
        else if(value.compareTo(root.getValue()) > 0){
            root.setRight(remove(root.getRight(),value));
        }
        else{
            if(root.getLeft() == null && root.getRight() == null){
                root = null;
            }
            else if(root.getLeft() == null){
                return root.getRight();
            }
            else if(root.getRight() == null){
                return root.getLeft();
            }
            else{
                E temp = minValue(root.getRight());
                root.value = temp;
                root.setRight(remove(root.getRight(),temp));
            }
        }
        return root;
    }

    public void rotateRight(){
        rotateRight(root);
    }

    private void rotateRight(TreeNode<E> value){
        if(value == null){
            return;
        }
        else if(value.getLeft() == null){
            return;
        }
        else{
            TreeNode<E> temp = value.getLeft();
            value.setLeft(temp.getRight());
            temp.setRight(value);
            root = temp;
        }
    }

    public void rotateLeft(){
        rotateLeft(root);
    }

    private void rotateLeft(TreeNode<E> value){
        if(value == null){
            return;
        }
        else if(value.getRight() == null){
            return;
        }
        else{
            TreeNode<E> temp = value.getRight();
            value.setRight(temp.getLeft());
            temp.setLeft(value);
            root = temp;
        }
    }


    public int size(){
        return size;
    }

    public E root(){
        return null;
    }

    public String inOrder(){
        if(size == 0){
            return "[]";
        }
        str = "";
        inOrder(root);
        return "[" + str.substring(0,str.length() - 2) + "]";
    }

    private void inOrder(TreeNode<E> node){
        if(node != null){
            inOrder(node.getLeft());
            str += node.getValue() + ", ";
            inOrder(node.getRight());
        }
    }

    public String preOrder(){
        if(size == 0){
            return "[]";
        }
        str = "";
        preOrder(root);
        return "[" + str.substring(0,str.length() - 2) + "]";
    }

    private void preOrder(TreeNode<E> node){
        if(node != null){
            str += node.getValue() + ", ";
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public String postOrder(){
        if(size == 0){
            return "[]";
        }
        str = "";
        postOrder(root);
        return "[" + str.substring(0,str.length() - 2) + "]";
    }

    private void postOrder(TreeNode<E> node){
        if(node != null){
            postOrder(node.getLeft());
            postOrder(node.getRight());
            str += node.getValue() + ", ";
        }
    }




    public class TreeNode<E>{
        private E value;
        private TreeNode<E> left;
        private TreeNode<E> right;

        public TreeNode(E storeValue){
            this.value = storeValue;
            this.left = null;
            this.right = null;
        }

        public E getValue(){
            return value;
        }

        public TreeNode<E> getLeft(){
            return left;
        }

        public TreeNode<E> getRight(){
            return right;
        }

        public void setRight(TreeNode<E> right){
            this.right = right;
        }

        public void setLeft(TreeNode<E> left){
            this.left = left;
        }
        public String toString(){
            return value.toString();
        }

    }



}
