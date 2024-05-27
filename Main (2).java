import java.io.*;
import java.util.Scanner;

public class Main {  
    private static final String FILE_NAME = "tarefas.txt";
    public static void main(String[] args) { 
    Scanner teclado = new Scanner(System.in);
        
        int opcao;
        do {
            System.out.println("\nBem vindo a sua lista de tarefas!");
            System.out.println("1. Criar tarefa");
            System.out.println("2. Ler tarefas");
            System.out.println("3. Atualizar tarefas");
            System.out.println("4. Deletar tarefas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

                opcao = teclado.nextInt();
                teclado.nextLine();

            switch (opcao) {
                case 1:
                    addTarefa();
                    break;
                case 2:
                    viewTarefas();
                    break;
                case 3:
                    editTarefa();
                    break;
                case 4:
                    deleteTarefa();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
        teclado.close();
    }

    private static void addTarefa() {
        FileWriter writer = null;
        BufferedWriter aa = null;
        PrintWriter out = null;
        try {
            writer = new FileWriter(FILE_NAME, true);
            aa = new BufferedWriter(writer);
            out = new PrintWriter(aa);


            System.out.println("");
            System.out.println("ADD tarefa");
            Scanner teclado = new Scanner(System.in);
            System.out.println("Escreva tarefa:");
            String tarefa = teclado.nextLine();
            teclado.nextLine();
            out.println(tarefa + "," + tarefa);
            System.out.println("Tarefa adicionada com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao adicionar a tarefa" + e.getMessage());
        } finally {
            try {
                if (out != null) out.close();
                if (aa != null) aa.close();
                if (writer != null) writer.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar a tarefa: " + e.getMessage());
            }
        }
    }

    private static void viewTarefas() {
        BufferedReader aa = null;
        try {
            aa = new BufferedReader(new FileReader(FILE_NAME));
            String line;
            System.out.println("");
            System.out.println("Visualizar tarefas");
            System.out.println("Tarefas disponíveis:");
            while ((line = aa.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println("Tarefa" + parts[0]);
            }
        } catch (IOException e) {
            System.err.println(">> Erro ao visualizar tarefas: " + e.getMessage());
        } finally {
            try {
                if (aa != null) aa.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar a tarefa: " + e.getMessage());
            }
        }
    }

    private static void editTarefa() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            System.out.println("");
            System.out.println("Editar tarefa");
            System.out.println("Digite a tarefa que deseja editar:");

            Scanner teclado = new Scanner(System.in);
            String nomeProduto = teclado.nextLine();

            File inputFile = new File(FILE_NAME);
            File tempFile = new File("tarefa.txt");

            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(nomeProduto)) {
                    System.out.println("Digite o novo nome da tarefa:");
                    String novoNome = teclado.nextLine();
                    System.out.println("Edite a tarefa:");
                    double novoPreco = teclado.nextDouble();
                    teclado.nextLine(); 
                    writer.write(novoNome + "," + novoPreco + "\n");
                    found = true;
                    System.out.println("tarefa editada com sucesso!");
                } else {
                    writer.write(line + "\n");
                }
            }
            if (!found) {
                System.out.println("Tarefa não encontrada!");
            }

        } catch (IOException e) {
            System.err.println("Erro ao editar a tarefa: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar as tarefas: " + e.getMessage());
            }
        }

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("tarefa.txt");
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
        }
    }

    private static void deleteTarefa() {
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            Scanner teclado = new Scanner(System.in);

            System.out.println("");
            System.out.println("Deletar tarefa");
            System.out.println("Digite o nome da tarefa que deseja excluir:");
            String nomeProduto = teclado.nextLine();

            File inputFile = new File(FILE_NAME);
            File tempFile = new File("tarefa.txt");

            reader = new BufferedReader(new FileReader(inputFile));
            writer = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].equalsIgnoreCase(nomeProduto)) {
                    writer.write(line + "\n");
                } else {
                    found = true;
                }
            }

            if (found) {
                System.out.println("Tarefa excluída com sucesso!");
            } else {
                System.out.println("Tarefa não encontrada!");
            }
        } catch (IOException e) {
            System.err.println("Erro ao excluir a tarefa: " + e.getMessage());
        } finally {
            try {
                if (writer != null) writer.close();
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.err.println("Erro ao fechar os recursos: " + e.getMessage());
            }
        }

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("tarefa.txt");
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
      
        }
  
    }
}