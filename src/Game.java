import java.util.Arrays;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int[] score = {1, 0};
        String[] players = {"", ""};
        String startBoard;
        boolean keepPlaying = true;

        String[][] gameBoard = new String[3][3];
        for (String[] row: gameBoard)
            Arrays.fill(row, " ");

        getNames(players);
        instructions();

        while(keepPlaying){
            if (score[0] > score[1]){   // players who lost last round go first
                play(players[1], gameBoard);
                writeBoard(score, gameBoard, players);
                checkForWinner(players[1], gameBoard);

                play(players[0], gameBoard);
                writeBoard(score, gameBoard, players);
                checkForWinner(players[0], gameBoard);
            } else {
                play(players[0], gameBoard);
                writeBoard(score, gameBoard, players);
                checkForWinner(players[0], gameBoard);

                play(players[1], gameBoard);
                writeBoard(score, gameBoard, players);
                checkForWinner(players[1], gameBoard);
            }
        }

    }

    private static void getNames(String[] players){
        System.out.println(" __________________________________________ ");
        System.out.println("|                                          |");
        System.out.println("|             JOGO DA VELHA                |");
        System.out.println("|__________________________________________|");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do jogador 1: ");
        players[0]= sc.nextLine();
        System.out.println("Bem-vindo "+ players[0] + ", você será o X .");
        System.out.println();
        System.out.println("Digite o nome do jogador 2: ");
        players[1] = sc.nextLine();
        System.out.println("Bem-vindo "+ players[1] + ", você será o 0 .");
        sc.close();
    };

    private static void instructions(){
        System.out.println();
        System.out.println(" _____ _____ _____ ");
        System.out.println("|     |     |     |");
        System.out.println("|  1  |  2  |  3  |");
        System.out.println("|_____|_____|_____|");
        System.out.println("|     |     |     |");
        System.out.println("|  4  |  5  |  6  |");
        System.out.println("|_____|_____|_____|");
        System.out.println("|     |     |     |");
        System.out.println("|  7  |  8  |  9  |");
        System.out.println("|_____|_____|_____|");
        System.out.println();
        System.out.println("Você deverá escolher uma das posições acima para definir sua jogada no tabuleiro");
    }

    private static void writeBoard(int[] score, String[][] gameBoard, String[] players){
        System.out.println(" _____ _____ _____ ");
        System.out.println("|     |     |     |");
        System.out.printf("|  %s  |  %s  |  %s  |\n", gameBoard[0][0], gameBoard[0][1], gameBoard[0][2]);
        System.out.println("|_____|_____|_____|");
        System.out.println("|     |     |     |");
        System.out.printf("|  %s  |  %s  |  %s  |\n", gameBoard[1][0], gameBoard[1][1], gameBoard[1][2]);
        System.out.println("|_____|_____|_____|");
        System.out.println("|     |     |     |");
        System.out.printf("|  %s  |  %s  |  %s  |\n", gameBoard[2][0], gameBoard[2][1], gameBoard[2][2]);
        System.out.println("|_____|_____|_____|");
        System.out.println();

        System.out.println("_________________________________ ");
        System.out.println("             PLACAR             ");
        System.out.printf("   %s  %d  x  %d  %s    \n", players[0], score[0], score[1], players[1],);
        System.out.println("_________________________________");
        System.out.println();

    };

    private static void play(String players, String[][] gameBoard){

    }

    private static void checkForWinner(String player, String[][] gameBoard) {
        if (winLineFound(gameBoard)) {
            win(player);  //aumenta score
            if (wishesToKeepPlaying(player)) {
                resetGameBoard();
            } else {
                gameOver(player);
            }
        }
    }

    private static boolean winLineFound(String[][] gameBoard){

    }

    private static void win(String player){

    }

    private static boolean wishesToKeepPlaying(String player){

    }

    private static void resetGameBoard(){

    }

    private static void gameOver(String player){

    }


    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system

            if (operatingSystem.contains("Windows")) {
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls", "clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();

                startProcess.waitFor();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}




