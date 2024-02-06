package arvoreRedBlack;

class Node {
    int key;
    Node parent;
    Node left;
    Node right;
    int color; // 0 = preto, 1 = vermelho
    int height;
  
    Node(int value) {
        key = value;
        parent = null;
        left = null;
        right = null;
        color = 1; // Novos n�s s�o sempre vermelhos
        height = 1;
    }
}
  
class RedBlackTree {
    private Node root;
    private int rotationCount;
  
    RedBlackTree() {
        root = null;
        rotationCount = 0;
    }
    
    public Node getRoot() {
        return root;
    }
    
  
    // M�todos auxiliares para obter a altura de um n� e o m�ximo entre dois valores
    private int height(Node node) {
        if (node == null)
            return 0;
        return max(height(node.left), height(node.right)) + 1;
    }
  
    private int max(int a, int b) {
        return Math.max(a, b);
    }
  
    // Rota��es da �rvore Rubro-Negra
    
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.left = x;
        x.parent = y;

        // Atualiza a altura dos n�s afetados
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
    }

    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == null)
            root = x;
        else if (y == y.parent.left)
            y.parent.left = x;
        else
            y.parent.right = x;
        x.right = y;
        y.parent = x;

        // Atualiza a altura dos n�s afetados
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
    }

  
    // Inser��o na �rvore Rubro-Negra
    private void insertFixup(Node z) {
        while (z.parent != null && z.parent.color == 1) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (y != null && y.color == 1) {
                    z.parent.color = 0;
                    y.color = 0;
                    z.parent.parent.color = 1;
                    z = z.parent.parent;
                    rotationCount++; // Conta a rota��o
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotate(z);
                        rotationCount++; // Conta a rota��o
                    }
                    z.parent.color = 0;
                    z.parent.parent.color = 1;
                    rightRotate(z.parent.parent);
                    rotationCount++; // Conta a rota��o
                }
            } else {
                Node y = z.parent.parent.left;
                
                if (y != null && y.color == 1) {
                    z.parent.color = 0;
                    y.color = 0;
                    z.parent.parent.color = 1;
                    z = z.parent.parent;
                    rotationCount++; // Conta a rota��o
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotate(z);
                        rotationCount++; // Conta a rota��o
                    }
                    z.parent.color = 0;
                    z.parent.parent.color = 1;
                    leftRotate(z.parent.parent);
                    rotationCount++; // Conta a rota��o
                }
            }
        }
        root.color = 0;
    }
  
    public void insert(int key) {
        Node newNode = new Node(key);
        Node y = null;
        Node x = root;
  
        while (x != null) {
            y = x;
            if (key < x.key)
                x = x.left;
            else if (key > x.key)
                x = x.right;
            else
                return; // N�o permite duplicatas
        }
  
        newNode.parent = y;
  
        if (y == null)
            root = newNode;
        else if (key < y.key)
            y.left = newNode;
        else
            y.right = newNode;
  
        // Atualiza a altura do novo n�
        newNode.height = 1;
  
        // Insere o novo n� na �rvore Rubro-Negra
        insertFixup(newNode);
    }
  
    // Outros m�todos (delete, busca, etc.) podem ser adicionados conforme necess�rio
  
    public void printRotationCount() {
        System.out.println("N�mero total de rota��es: " + rotationCount);
    }
  
    public int getHeight() {
        return height(root);
    }
}
