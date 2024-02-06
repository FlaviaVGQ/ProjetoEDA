package arvoreAVL;
class Node {
    int key;
    Node left, right;
    int height;
  
    Node(int value) {
        key = value;
        height = 0;
    }
}
  
class AVLTree {
    Node root;
    int rotationCount;
  
    AVLTree() {
        rotationCount = 0;
    }
  
    int height(Node node) {
        if (node == null)
            return -1;
        return node.height;
    }
  
    int max(int a, int b) {
        return Math.max(a, b);
    }
  
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
  
        x.right = y;
        y.left = T2;
  
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
  
        return x;
    }
  
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
  
        y.left = x;
        x.right = T2;
  
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
  
        return y;
    }
  
    int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }
  
    Node insert(Node node, int key) {
        if (node == null)
            return (new Node(key));
  
        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // Duplicates not allowed
            return node;
  
        node.height = 1 + max(height(node.left),
                              height(node.right));
  
        int balance = getBalance(node);
  
        if (balance > 1 && key < node.left.key) {
            rotationCount++;
            return rightRotate(node);
        }
  
        if (balance < -1 && key > node.right.key) {
            rotationCount++;
            return leftRotate(node);
        }
  
        if (balance > 1 && key > node.left.key) {
            rotationCount++;
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
  
        if (balance < -1 && key < node.right.key) {
            rotationCount++;
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
  
        return node;
    }
  
    Node minValueNode(Node node) {
        Node current = node;
  
        while (current.left != null)
            current = current.left;
  
        return current;
    }
  
    Node deleteNode(Node root, int key) {
        if (root == null)
            return root;
  
        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
  
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = minValueNode(root.right);
  
                root.key = temp.key;
  
                root.right = deleteNode(root.right, temp.key);
            }
        }
  
        if (root == null)
            return root;
  
        root.height = max(height(root.left), height(root.right)) + 1;
  
        int balance = getBalance(root);
  
        if (balance > 1 && getBalance(root.left) >= 0) {
            rotationCount++;
            return rightRotate(root);
        }
  
        if (balance > 1 && getBalance(root.left) < 0) {
            rotationCount++;
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
  
        if (balance < -1 && getBalance(root.right) <= 0) {
            rotationCount++;
            return leftRotate(root);
        }
  
        if (balance < -1 && getBalance(root.right) > 0) {
            rotationCount++;
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
  
        return root;
    }
  
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
  
    void printRotationCount() {
        System.out.println("Número total de rotações: " + rotationCount);
    }
  
    int getHeight() {
        return height(root);
    }
}
