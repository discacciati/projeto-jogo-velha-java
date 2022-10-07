import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        String[][] gameBoard = newGameBoard();
        String[] names = getNames();
        String[] playerSymbol = {"X", "O"};
        int[] score = {0, 0};
        boolean keepPlaying = true;

        instructions();

        while(keepPlaying){
            int[] turns = {0, 1};
            boolean endRound = false;
            for(int turn: turns){
                play(turn, playerSymbol, score, names, gameBoard);
                if (winLineFound(gameBoard)){
                    winRound(names, score, turn);
                    endRound = true;
                } else if(draw(gameBoard)){
                    System.out.println("Houve um empate, nenhum jogador ganhou a rodada.");
                    endRound = true;
                }
                if (endRound){
                    writeBoard(names, score, gameBoard);
                    gameBoard = newGameBoard();
                    if (wishesToKeepPlaying()){
                        clearConsole();
                        instructions();
                    }
                    else{
                        System.out.println("Obrigada por jogar!");
                        keepPlaying = false;
                        break;
                    }
                }
            }
        }
    }

    private static String[][] newGameBoard(){
        String[][] gameBoard = new String[3][3];
        for (String[] row: gameBoard)
            Arrays.fill(row, " ");
        return gameBoard;
    }

    private static String[] getNames(){
        System.out.println(" __________________________________________ ");
        System.out.println("|                                          |");
        System.out.println("|             JOGO DA VELHA                |");
        System.out.println("|__________________________________________|");
        System.out.println();

        String[] names = {"", ""};
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do jogador 1 : ");
        names[0]= sc.nextLine();
        System.out.println("Bem-vindo "+ names[0] + ", você joga como \"X\"\n");

        System.out.print("Digite o nome do jogador 2 : ");
        names[1] = sc.nextLine();
        System.out.println("Bem-vindo "+ names[1] + ", você joga como \"O\"\n");
        //sc.close();
        return names;
    };

    private static void instructions(){
        System.out.println("********** INSTRUÇÕES ***********");
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
        System.out.println("Você deverá escolher uma das posições acima para definir sua jogada no tabuleiro\n");
    }

    private static void writeBoard(String[] names, int[] score, String[][] gameBoard){
        System.out.println("     |     |     ");
        System.out.printf("  %s  |  %s  |  %s \n", gameBoard[0][0], gameBoard[0][1], gameBoard[0][2]);
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.printf("  %s  |  %s  |  %s \n", gameBoard[1][0], gameBoard[1][1], gameBoard[1][2]);
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.printf("  %s  |  %s  |  %s \n", gameBoard[2][0], gameBoard[2][1], gameBoard[2][2]);
        System.out.println("     |     |     ");
        System.out.println();

        System.out.println("_________________________________ ");
        System.out.println("             PLACAR             ");
        System.out.printf("     %s  %d  x  %d  %s    \n", names[0], score[0], score[1], names[1]);
        System.out.println("_________________________________");
        System.out.println();
    };

    private static int pickPosition(String name){
        System.out.print(name + ", digite o número da posição escolhida: ");
        Scanner sc = new Scanner(System.in);
        int position;
        try{
            position = sc.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Opção inválida, você deve digitar um número inteiro entre 1 e 9.");
            return pickPosition(name);
        }
        if(position < 1 || position > 9){
            System.out.println("Você não escolheu um número inteiro entre 1 e 9, por favor escolha novamente.");
            return pickPosition(name);
        }
        return position;
    }

    private static boolean winLineFound(String[][] gameBoard){
        if(gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2] && gameBoard[1][1] != " ") //check diagonal strike
            return true;
        if(gameBoard[2][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[0][2] && gameBoard[1][1] != " ") //check diagonal strike
            return true;
        for(int i = 0; i <= 2; i++){
            if(gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2] && gameBoard[i][1] != " ")  //check horizontal strike
                return true;
            if(gameBoard[0][i] == gameBoard[1][i] && gameBoard[1][i] == gameBoard[2][i] && gameBoard[1][i] != " ")  //check vertical strike
                return true;
        }
        return false;
    }

    private static void winRound(String[] names, int[] score, int turn){
        System.out.println("*********** " + names[turn] + " ganhou esta rodada!!! ***********");
        score[turn]++;
    }

    private static boolean wishesToKeepPlaying(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Deseja continuar o jogo? S ou N: ");
        String answer = sc.nextLine();
        if(answer.equalsIgnoreCase("s"))
            return true;
        else if(answer.equalsIgnoreCase("n")){
            return false;
        }
        else {
            System.out.println("Você precisa digitar a letra \"s\" ou a letra \"n\".");
            return wishesToKeepPlaying();
        }
    }

    private static void play(int turn, String[] playerSymbol, int[] score, String[] names, String[][] gameBoard){
        int position = pickPosition(names[turn]);
        while(!positionAvailable(position, gameBoard)){
            System.out.print("Posição não disponível, jogue novamente.");
            position = pickPosition(names[turn]);
        }
        String symbol = playerSymbol[turn];
        placePick(symbol, position, gameBoard);
        clearConsole();
        writeBoard(names, score, gameBoard);
    }

    private static boolean draw(String[][] gameBoard){
        for (int i = 0; i <= 2; i++){
            for (int j = 0; j <= 2; j++){
                if(gameBoard[i][j].equals(" "))
                    return false;
            }
        }
        return true;
    }

    private static boolean positionAvailable(int position, String[][] gameBoard){
        switch(position){
            case 1:
                if (!gameBoard[0][0].equals(" "))
                    return false;
                break;
            case 2:
                if (!gameBoard[0][1].equals(" "))
                    return false;
                break;
            case 3:
                if (!gameBoard[0][2].equals(" "))
                    return false;
                break;
            case 4:
                if (!gameBoard[1][0].equals(" "))
                    return false;
                break;
            case 5:
                if (!gameBoard[1][1].equals(" "))
                    return false;
                break;
            case 6:
                if (!gameBoard[1][2].equals(" "))
                    return false;
                break;
            case 7:
                if (!gameBoard[2][0].equals(" "))
                    return false;
                break;
            case 8:
                if (!gameBoard[2][1].equals(" "))
                    return false;
                break;
            case 9:
                if (!gameBoard[2][2].equals(" "))
                    return false;
                break;
            default:
                    return true;
        }
        return true;
    }

    private static void placePick(String symbol, int position, String[][] gameBoard) {
        switch(position){
            case 1:
                if (gameBoard[0][0].equals(" "))
                    gameBoard[0][0] = symbol;
                break;
            case 2:
                if (gameBoard[0][1].equals(" "))
                    gameBoard[0][1] = symbol;
                break;
            case 3:
                if (gameBoard[0][2].equals(" "))
                    gameBoard[0][2] = symbol;
                break;
            case 4:
                if (gameBoard[1][0].equals(" "))
                    gameBoard[1][0] = symbol;
                break;
            case 5:
                if (gameBoard[1][1].equals(" "))
                    gameBoard[1][1] = symbol;
                break;
            case 6:
                if (gameBoard[1][2].equals(" "))
                    gameBoard[1][2] = symbol;
                break;
            case 7:
                if (gameBoard[2][0].equals(" "))
                    gameBoard[2][0] = symbol;
                break;
            case 8:
                if (gameBoard[2][1].equals(" "))
                    gameBoard[2][1] = symbol;
                break;
            case 9:
                if (gameBoard[2][2].equals(" "))
                    gameBoard[2][2] = symbol;
                break;
            default:
                //no default, input mismatches were handled in the play method
                break;
        }
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





