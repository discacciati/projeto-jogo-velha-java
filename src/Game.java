import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int[] score = {1, 0};
        String[] players = {"", ""};
        int turn = 0;   //player 1 starts the game
        String[] playerSymbol = {"X", "O"};
        boolean keepPlaying = true;

        String[][] gameBoard = new String[3][3];
        for (String[] row: gameBoard)
            Arrays.fill(row, " ");

        getNames(players);
        instructions();

        while(keepPlaying){
            if (score[0] > score[1]){   // players who lost last round go first
                turn = 0;
                play(turn, playerSymbol, score, players, gameBoard);
                turn = 1;
                play(turn, playerSymbol, score, players, gameBoard);

            } else {
                turn = 1;
                play(turn, playerSymbol, score, players, gameBoard);
                turn = 0;
                play(turn, playerSymbol, score, players, gameBoard);
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
        System.out.print("Digite o nome do jogador 1: ");
        players[0]= sc.nextLine();
        System.out.println("Bem-vinde "+ players[0] + ", você será o X .");
        System.out.println();
        System.out.print("Digite o nome do jogador 2: ");
        players[1] = sc.nextLine();
        System.out.println("Bem-vinde "+ players[1] + ", você será o 0 .");
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

    private static void writeBoard(int turn, String[] players, int[] score, String[][] gameBoard){
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
        System.out.printf("   %s  %d  x  %d  %s    \n", players[0], score[0], score[1], players[1]);
        System.out.println("_________________________________");
        System.out.println();

    };

    private static void pickPosition( int turn, String[] playerSymbol, String[] players, String[][] gameBoard){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o número da posição escolhida: ");
        int position = 0;
        try{
            position = sc.nextInt();
        }
        catch (InputMismatchException e){
            System.out.print("Número inválido. Digite o número da posição escolhida: ");
            pickPosition(turn, playerSymbol, players, gameBoard);
        }

        switch(position){
            case 1:
                if (gameBoard[0][0].equals(" "))
                    gameBoard[0][0] = playerSymbol[turn];
                break;
            case 2:
                if (gameBoard[0][1].equals(" "))
                    gameBoard[0][1] = playerSymbol[turn];
                break;
            case 3:
                if (gameBoard[0][2].equals(" "))
                    gameBoard[0][2] = playerSymbol[turn];
                break;
            case 4:
                if (gameBoard[1][0].equals(" "))
                    gameBoard[1][0] = playerSymbol[turn];
                break;
            case 5:
                if (gameBoard[1][1].equals(" "))
                    gameBoard[1][1] = playerSymbol[turn];
                break;
            case 6:
                if (gameBoard[1][2].equals(" "))
                    gameBoard[1][2] = playerSymbol[turn];
                break;
            case 7:
                if (gameBoard[2][0].equals(" "))
                    gameBoard[2][0] = playerSymbol[turn];
                break;
            case 8:
                if (gameBoard[2][1].equals(" "))
                    gameBoard[2][1] = playerSymbol[turn];
                break;
            case 9:
                if (gameBoard[2][2].equals(" "))
                    gameBoard[2][2] = playerSymbol[turn];
                break;
            default:
                System.out.print("Posição inválida. Escolha novamente.");
                pickPosition(turn, playerSymbol, players, gameBoard);
                break;
        }
   sc.close();

    }

    private static boolean roundWin(int turn, String[] players, int[] score, String[][] gameBoard) {
        if (winLineFound(gameBoard)) {
            win(players[turn]);  //aumenta score
            return true;
        }
        return false;
    }

    private static void play(int turn, String[] playerSymbol, int[] score, String[] players, String[][] gameBoard){
        //System.out.print("Digite o número da posição escolhida: ");
        Scanner sc = new Scanner(System.in);
        String position = sc.nextLine();
        System.out.println(position);

//        try{
//            String position = sc.nextLine();
//            System.out.println(position);
//        }
//        catch (InputMismatchException e){
//            System.out.print("Número inválido. Digite o número da posição escolhida: ");
//            //pickPosition(turn, playerSymbol, players, gameBoard);
//        }

        //pickPosition(turn, playerSymbol, players, gameBoard);
        writeBoard(turn, players, score, gameBoard);
        if (roundWin(turn, players, score, gameBoard)){
            score[turn]++;
            System.out.println(players[turn] + " ganhou esta rodada.");
            if (wishesToKeepPlaying(players[turn])) {
                resetGameBoard();
            } else {
                gameOver(players[turn]);
            }
        }
    }

    private static boolean winLineFound(String[][] gameBoard){
        return true;
    }

    private static void win(String player){

    }

    private static boolean wishesToKeepPlaying(String player){
        return true;
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




