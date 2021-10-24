package tree.avl;

class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    //返回当前结点的高度，以该结点为根节点的树的高度
    public int height() {
        if (this.left == null && this.right == null) {
            return 1;
        }
        int leftHeight = 1, rightHeight = 1;
        if (this.left != null) {
            leftHeight = 1 + this.left.height();
        }
        if (this.right != null) {
            rightHeight = 1 + this.right.height();
        }
        return Math.max(leftHeight, rightHeight);
    }

    //返回当前节点的左子树的高度
    public int leftHeight() {
        if (this.left == null) {
            return 0;
        }
        return this.left.height();
    }

    //返回当前节点的右子树的高度
    public int rightHeight() {
        if (this.right == null) {
            return 0;
        }
        return this.right.height();
    }

    //左旋转方法
    private void leftRotate() {
        //创建新的结点
        Node newNode = new Node(value);
        newNode.left = left;
        newNode.right = this.right.left;
        this.value = right.value;
        this.right = this.right.right;
        this.left = newNode;
    }

    //右旋方法
    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    public void rotate() {

    }

    //添加结点的方法
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (this.value >= node.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //左旋
        if (rightHeight() - leftHeight() > 1) {
            //右节点的左子树高度大于右子树高度，对右节点进行右旋操作
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
            }
            leftRotate();
            return;//直接结束，不在进入下一步if判断
        }
        //右旋
        if (leftHeight() - rightHeight() > 1) {
            //左结点的左子树高度大于右子树高度，对左节点进行左旋操作
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
            }
            rightRotate();
        }
    }

    //中序遍历
    public void InOrder() {
        if (this.left != null) {
            this.left.InOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.InOrder();
        }
    }

    public void preOrder() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}