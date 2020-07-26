public class MineSweeperGame {
    // size of ROWS + 2, COLUMNS + 2
    private boolean[][] board; // true if mine present
    private boolean[][] checked; // true if user checked

    private int ROWS;
    private int COLS;
    private int MINES;
    private int OPENED = 0;

    static final int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static final int[] dy = { 1, 0, -1, 1, -1, 1, 0, -1 };

    /**
     * Constructor with no arguments. Makes a game with board of 7 x 7.
     */
    public MineSweeperGame() {
        this(7, 7);
    }

    /**
     * Constructor with one argument. Makes a game with board of 5 x 5 and
     * numOfMines.
     * 
     * @param numOfMines
     */
    public MineSweeperGame(int numOfMines) {
        this(7, 7, numOfMines);
    }

    /**
     * 
     * @param rows
     * @param columns
     */
    public MineSweeperGame(int rows, int columns) {
        this(rows, columns, rows * columns / 7);
    }

    /**
     * 
     * @param rows
     * @param columns
     * @param mines
     */
    public MineSweeperGame(int rows, int columns, int mines) {
        ROWS = rows;
        COLS = columns;
        MINES = mines;
        createBoard();
    }

    public boolean CheckBoard(int x, int y) {
        if (x <= 0 || y <= 0 || x >= ROWS + 1 || y >= COLS + 1) {
            System.out.println("Cannot Check!");
            return false;
        }
        if (checked[x][y]) {
            System.out.println("Already Checked!");
            return false;
        }

        if (board[x][y]) {
            System.out.println("Boom!");
            return true;
        } else {
            CheckLeftMines(x, y);
            if ((OPENED + MINES) == (ROWS * COLS)) {
                System.out.println("You Win!");
                return true;
            }
            return false;
        }
    }

    private void CheckLeftMines(int x, int y) {
        OPENED++;
        checked[x][y] = true;
        for (int i = 0; i < 8; i++) {
            if (countMines(x, y) == 0 && !board[x + dx[i]][y + dy[i]] && !checked[x + dx[i]][y + dy[i]]) {
                CheckLeftMines(x + dx[i], y + dy[i]);
            }
        }
    }

    private void createBoard() {
        board = new boolean[ROWS + 2][COLS + 2];
        checked = new boolean[ROWS + 2][COLS + 2];
        generateWalls();
        generateMines();
    }

    private void generateWalls() {
        for (int i = 0; i < ROWS + 2; i++) {
            checked[0][i] = true;
            checked[COLS + 1][i] = true;
        }

        for (int i = 0; i < COLS + 2; i++) {
            checked[i][0] = true;
            checked[i][ROWS + 1] = true;
        }
    }

    private void generateMines() {
        int count = 0;
        while (count < MINES) {
            int x = (int) (Math.random() * ROWS + 1);
            int y = (int) (Math.random() * COLS + 1);
            if (!board[x][y]) {
                count++;
                board[x][y] = true;
            }
        }
    }

    // mine은 *로, 나머지 타일은 숫자로 표시하는 코드
    public void display() {
        for (int i = 1; i <= ROWS; i++) {
            for (int j = 1; j <= COLS; j++) {
                if (!checked[i][j]) {
                    System.out.print("@ ");
                } else {
                    System.out.print(countMines(i, j) + " ");
                }
            }
            System.out.println();
        }
    }

    // mine은 *로, 나머지 타일은 숫자로 표시하는 코드
    public void printBoardStatus() {
        for (int i = 1; i <= ROWS; i++) {
            for (int j = 1; j <= COLS; j++) {
                if (board[i][j]) {
                    System.out.print("* ");
                } else {
                    System.out.print(countMines(i, j) + " ");
                }
            }
            System.out.println();
        }
    }

    // 타일(x, y) 주변에 지뢰가 얼마나 있는지 세는 코드
    private int countMines(int x, int y) {
        int count = 0;
        for (int i = 0; i < 8; i++) {
            if (board[x + dx[i]][y + dy[i]]) {
                count++;
            }
        }
        return count;
    }

    // board의 전체 상태를 보여주는 코드
    public void printBoard() {
        for (boolean[] row : board) {
            for (boolean el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    public void printCheck() {
        for (boolean[] row : checked) {
            for (boolean el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}