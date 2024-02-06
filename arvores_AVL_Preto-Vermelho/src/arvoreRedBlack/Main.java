package arvoreRedBlack;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        Random random = new Random(123);

        // Gerando 500.000 números aleatórios
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 0; i < 500_000; i++) {
            int randomNumber = random.nextInt(1000000); // Intervalo de 0 a 999.999
            numbers.add(randomNumber);
        }

        // Inserção dos números na árvore
        for (int i = 0; i < 500_000; i++) {
            tree.insert(numbers.get(i));
        }

        // Gravação dos valores da árvore em um arquivo txt
        String txtFile = "arvore_rubro_negra_dados.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(txtFile))) {
            // Escreve o cabeçalho do arquivo txt
            writer.println("Valor");

            // Escreve os valores da árvore no arquivo txt
            writeTreeValuesToFile(tree.getRoot(), writer);

            System.out.println("Valores gravados no arquivo " + txtFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprime o número de rotações e a altura da árvore
        tree.printRotationCount();
        System.out.println("Altura final da árvore: " + tree.getHeight());
    }

    private static void writeTreeValuesToFile(Node node, PrintWriter writer) {
        if (node != null) {
            // Escreve o valor do nó atual no arquivo txt
            writer.println(node.key);

            // Recursivamente escreve os valores dos nós filhos
            writeTreeValuesToFile(node.left, writer);
            writeTreeValuesToFile(node.right, writer);
        }
    }
}
