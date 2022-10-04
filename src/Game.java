import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        int[] score = {1, 0};
        String[] playerSymbol = {"X", "O"};
        String[] names = {"", ""};
        int turn = 0;   //player 1 starts the game
        boolean keepPlaying = true;
        String[][] gameBoard = createGameBoard();

        for(String[] mark: gameBoard){
            System.out.println(mark);
        }

        //getNames(names);
        //instructions();

        while(keepPlaying){
            turn = 0;
            play(turn, playerSymbol, score, names, gameBoard);
            if (winLineFound(gameBoard)){
                winRound(names, score, turn);
                resetGameBoard(gameBoard);
                if (wishesToKeepPlaying())
                    resetGameBoard(gameBoard);
                else
                    keepPlaying = false;
            }

            turn = 1;
            play(turn, playerSymbol, score, names, gameBoard);
            if (winLineFound(gameBoard)){
                winRound(names, score, turn);
                resetGameBoard(gameBoard);
                if (wishesToKeepPlaying())
                    resetGameBoard(gameBoard);
                else
                    keepPlaying = false;
            }
        }
    }

    private static String[][] createGameBoard(){
        String[][] gameBoard = new String[3][3];
        for (String[] row: gameBoard)
            Arrays.fill(row, " ");
        return gameBoard;
    }

    private static void getNames(String[] names){
        System.out.println(" __________________________________________ ");
        System.out.println("|                                          |");
        System.out.println("|             JOGO DA VELHA                |");
        System.out.println("|__________________________________________|");
        System.out.println();

        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do jogador 1: ");
        names[0]= sc.nextLine();
        System.out.println("Bem-vinde "+ names[0] + ", você será o X .");
        System.out.println();
        System.out.print("Digite o nome do jogador 2: ");
        names[1] = sc.nextLine();
        System.out.println("Bem-vinde "+ names[1] + ", você será o 0 .");
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

    private static void writeBoard(int turn, String[] names, int[] score, String[][] gameBoard){
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
        System.out.printf("   %s  %d  x  %d  %s    \n", names[0], score[0], score[1], names[1]);
        System.out.println("_________________________________");
        System.out.println();

    };

    private static void pickPosition(int turn, String[] playerSymbol, String[] names, String[][] gameBoard){
        Scanner sc = new Scanner(System.in);
        System.out.print(names[turn] + ", digite o número da posição escolhida: ");
        int position = 0;
        try{
            position = sc.nextInt();
        }
        catch (NoSuchElementException e){
            System.out.println("Opção inválida, você deve digitar um número inteiro entre 1 e 9.");
            pickPosition(turn, playerSymbol, names, gameBoard);
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
                System.out.print("Posição inválida, escolha um número inteiro entre 1 e 9.");
                pickPosition(turn, playerSymbol, names, gameBoard);
                break;
        }
   sc.close();

    }

    private static boolean winLineFound(String[][] gameBoard){
        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]) //check diagonal strike
            return true;
        if(gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2]) //check diagonal strike
            return true;
        for(int i = 0; i <= 2; i++){
            if(gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2])  //check horizontal strike
                return true;
            if(gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][1] == gameBoard[2][i])  //check vertical strike
                return true;
        }
        return false;
    }

    private static void winRound(String[] names, int[] score, int turn){
        System.out.println(names[turn] + " ganhou esta rodada!!!");
        score[turn]++;
    }

    private static void resetGameBoard(String[][] gameBoard){
        gameBoard = createGameBoard();
    }

    private static boolean wishesToKeepPlaying(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Deseja continuar o jogo? S ou N: ");
        String answer = sc.nextLine();
        sc.close();
        if(answer.toLowerCase().equals("s"))
            return true;
        else if(answer.toLowerCase().equals("n"))
            return false;
        else {
            System.out.println("Você precisa digitar a letra \"s\" ou a letra \"n\".");
            return wishesToKeepPlaying();
        }
    }


    private static void play(int turn, String[] playerSymbol, int[] score, String[] names, String[][] gameBoard){
        pickPosition(turn, playerSymbol, names, gameBoard);
        clearConsole();
        writeBoard(turn, names, score, gameBoard);
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




