package arvoreAVL;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Random random = new Random(123);

        // Gerando 500.000 números aleatórios
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 0; i < 500_000; i++) {
            int randomNumber = random.nextInt(1000000); // Intervalo de 0 a 999.999
            numbers.add(randomNumber);
        }
        
        // Inserção dos números na árvore
        for (int i = 0; i < 500_000; i++) {
            tree.root = tree.insert(tree.root, numbers.get(i));
        }

        // Gravação dos dados em um arquivo txt
        String txtFile = "arvore_avl_dados.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(txtFile))) {
            // Escreve o cabeçalho do arquivo CSV
            writer.write("Número,Altura\n");

            // Escreve os dados na árvore no arquivo txt
            writeTreeDataToFile(tree.root, writer);

            System.out.println("Dados gravados no arquivo " + txtFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprime o número de rotações e a altura da árvore
        System.out.println("Número total de rotações: " + tree.rotationCount);
        System.out.println("Altura final da árvore: " + tree.getHeight());
    }

    private static void writeTreeDataToFile(Node node, PrintWriter writer) throws IOException {
        if (node != null) {
            // Escreve os dados do nó atual no arquivo txt
            writer.write(node.key + "," + node.height + "\n");

            // Recursivamente escreve os dados dos nós filhos
            writeTreeDataToFile(node.left, writer);
            writeTreeDataToFile(node.right, writer);
        }
    }
}
