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

        // Gerando 500.000 n�meros aleat�rios
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int i = 0; i < 500_000; i++) {
            int randomNumber = random.nextInt(1000000); // Intervalo de 0 a 999.999
            numbers.add(randomNumber);
        }
        
        // Inser��o dos n�meros na �rvore
        for (int i = 0; i < 500_000; i++) {
            tree.root = tree.insert(tree.root, numbers.get(i));
        }

        // Grava��o dos dados em um arquivo txt
        String txtFile = "arvore_avl_dados.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(txtFile))) {
            // Escreve o cabe�alho do arquivo CSV
            writer.write("N�mero,Altura\n");

            // Escreve os dados na �rvore no arquivo txt
            writeTreeDataToFile(tree.root, writer);

            System.out.println("Dados gravados no arquivo " + txtFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprime o n�mero de rota��es e a altura da �rvore
        System.out.println("N�mero total de rota��es: " + tree.rotationCount);
        System.out.println("Altura final da �rvore: " + tree.getHeight());
    }

    private static void writeTreeDataToFile(Node node, PrintWriter writer) throws IOException {
        if (node != null) {
            // Escreve os dados do n� atual no arquivo txt
            writer.write(node.key + "," + node.height + "\n");

            // Recursivamente escreve os dados dos n�s filhos
            writeTreeDataToFile(node.left, writer);
            writeTreeDataToFile(node.right, writer);
        }
    }
}
