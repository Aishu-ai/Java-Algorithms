import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class BoggleBoard {
    // the 16 Boggle dice (1992 version)
    private static final String[] BOGGLE_1992 = {
        "LRYTTE", "VTHRWE", "EGHWNE", "SEOTIS",
        "ANAEEG", "IDSYTT", "OATTOW", "MTOICU",
        "AFPKFS", "XLDERI", "HCPOAS", "ENSIEU",
        "YLDEVR", "ZNRNHL", "NMIQHU", "OBBAOJ"
    };

    // the 16 Boggle dice (1983 version)
    private static final String[] BOGGLE_1983 = {
        "AACIOT", "ABILTY", "ABJMOQ", "ACDEMP",
        "ACELRS", "ADENVZ", "AHMORS", "BIFORX",
        "DENOSW", "DKNOTU", "EEFHIY", "EGINTV",
        "EGKLUY", "EHINPS", "ELPSTU", "GILRUW",
    };

    // the 25 Boggle Master / Boggle Deluxe dice
    private static final String[] BOGGLE_MASTER = {
        "AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM",
        "AEEGMU", "AEGMNN", "AFIRSY", "BJKQXZ", "CCNSTW",
        "CEIILT", "CEILPT", "CEIPST", "DDLNOR", "DHHLOR",
        "DHHNOT", "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU",
        "FIPRSY", "GORRVW", "HIPRRY", "NOOTUW", "OOOTTU"
    };

    // the 25 Big Boggle dice
    private static final String[] BOGGLE_BIG = {
        "AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM",
        "AEEGMU", "AEGMNN", "AFIRSY", "BJKQXZ", "CCENST",
        "CEIILT", "CEILPT", "CEIPST", "DDHNOT", "DHHLOR",
        "DHLNOR", "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU",
        "FIPRSY", "GORRVW", "IPRRRY", "NOOTUW", "OOOTTU"
    };
     // letters and frequencies of letters in the English alphabet
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /*private static final double[] FREQUENCIES = {
        0.08167, 0.01492, 0.02782, 0.04253, 0.12703, 0.02228,
        0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025,
        0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987,
        0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150,
        0.01974, 0.00074
    };*/
    
    //Random shuffle a string array
    private static void shuffleArray(String[] ar)
  {
    // If running on Java 6 or older, use `new Random()` on RHS here
    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      String a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }

    private final int m;        // number of rows
    private final int n;        // number of columns
    private char[][] board;     // the m-by-n array of characters

    /**
     * Initializes a random 4-by-4 board, by rolling the Hasbro dice.
     */
    public BoggleBoard() {
        m = 4;
        n = 4;
        shuffleArray(BOGGLE_1992);
        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String letters = BOGGLE_1992[n*i+j];
                int r = ThreadLocalRandom.current().nextInt(0, letters.length());
                board[i][j] = letters.charAt(r);
            }
        }
    }
    /**
     * Initializes a board from the given filename.
     * @param filename the name of the file containing the Boggle board
     */
    public BoggleBoard(String filename)throws IOException {
        File file = new File(filename);
        Scanner scanner=new Scanner(file);
        m = scanner.nextInt();
        n = scanner.nextInt();
        if (m <= 0) throw new IllegalArgumentException("number of rows must be a positive integer");
        if (n <= 0) throw new IllegalArgumentException("number of columns must be a positive integer");
        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String letter = scanner.nextLine().toUpperCase();
                if (letter.equals("QU"))
                    board[i][j] = 'Q';
                else if (letter.length() != 1)
                    throw new IllegalArgumentException("invalid character: " + letter);
                else if (!ALPHABET.contains(letter))
                    throw new IllegalArgumentException("invalid character: " + letter);
                else 
                    board[i][j] = letter.charAt(0);
            }
        }
    }
     /**
     * Initializes a board from the given 2d character array,
     * with 'Q' representing the two-letter sequence "Qu".
     * @param a the 2d character array
     */
    public BoggleBoard(char[][] a) {
        this.m = a.length;
        if (m == 0) throw new IllegalArgumentException("number of rows must be a positive integer");
        this.n = a[0].length;
        if (n == 0) throw new IllegalArgumentException("number of columns must be a positive integer");
        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            if (a[i].length != n)
                throw new IllegalArgumentException("char[][] array is ragged");
            for (int j = 0; j < n; j++) {
                if (ALPHABET.indexOf(a[i][j]) == -1)
                    throw new IllegalArgumentException("invalid character: " + a[i][j]);
                board[i][j] = a[i][j];
            }
        }
    }
    /**
     * Returns the number of rows.
     * @return number of rows
     */
    public int rows() {
        return m;
    }

    /**
     * Returns the number of columns.
     * @return number of columns
     */
    public int cols() {
        return n;
    }

    /**
     * Returns the letter in row i and column j,
     * with 'Q' representing the two-letter sequence "Qu".
     * @param i the row
     * @param j the column
     * @return the letter in row i and column j
     *    with 'Q' representing the two-letter sequence "Qu".
     */
    public char getLetter(int i, int j) {
        return board[i][j];
    }

    /**
     * Returns a string representation of the board, replacing 'Q' with "Qu".
     * @return a string representation of the board, replacing 'Q' with "Qu"
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(m + " " + n + "\n");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
                if (board[i][j] == 'Q') sb.append("u ");
                else sb.append("  ");
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }
     /**
     * Unit tests the BoggleBoard data type.
     */
    public static void main(String[] args) {

        // initialize a 4-by-4 board using Hasbro dice
        System.out.println("Hasbro board:");
        BoggleBoard board1 = new BoggleBoard();
        System.out.println(board1);
        System.out.println();

        /*// initialize a 4-by-4 board using letter frequencies in English language
        StdOut.println("Random 4-by-4 board:");
        BoggleBoard board2 = new BoggleBoard(4, 4);
        StdOut.println(board2);
        StdOut.println();*/

        // initialize a 4-by-4 board from a 2d char array
        System.out.println("4-by-4 board from 2D character array:");
        char[][] a =  {
            { 'D', 'O', 'T', 'Y' },
            { 'T', 'R', 'S', 'F' },
            { 'M', 'X', 'M', 'O' },
            { 'Z', 'A', 'B', 'W' }
        };
        BoggleBoard board3 = new BoggleBoard(a);
        System.out.println(board3);
        System.out.println();

        /*// initialize a 4-by-4 board from a file
        String filename = "board-quinquevalencies.txt";
        System.out.println("4-by-4 board from file " + filename + ":");
        BoggleBoard board4 = new BoggleBoard(filename);
        StdOut.println(board4);
        StdOut.println();*/
    }
    
}



    

       
        

