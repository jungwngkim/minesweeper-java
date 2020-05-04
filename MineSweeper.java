public class MineSweeper {
    private char[][] board; // size of ROWS + 2, COLUMNS + 2
    // private int ROWS;
    // private int COLUMNS;

    public MineSweeper() {
        board = createBoard(5, 5, 8);
    }

    public MineSweeper(int rows, int columns, int mines) {
        board = createBoard(rows, columns, mines);
    }

    private char[][] createBoard(int rows, int columns, int mines) {
        char[][] newBoard = new char[rows + 2][columns + 2];
        createTerrain(newBoard);
        createWall(newBoard);
        createMines(newBoard, mines);
        return newBoard;
    }

    private void createTerrain(char[][] newBoard) {
        // fill all board with 'O'
        for (int i = 0; i < newBoard.length; i++) {
            for (int j = 0; j < newBoard[0].length; j++) {
                newBoard[i][j] = 'O';
            }
        }
    }

    private void createWall(char[][] newBoard) {
        // create left and right wall
        for (int i = 0; i < newBoard.length; i++) {
            newBoard[i][0] = 'X';
            newBoard[i][newBoard[0].length - 1] = 'X';
        }
        // create top and bottom wall
        for (int i = 0; i < newBoard[0].length; i++) {
            newBoard[0][i] = 'X';
            newBoard[newBoard.length - 1][i] = 'X';
        }
    }

    private void createMines(char[][] newBoard, int mines) {
        // generate mines in terrain
        // row * column 사이의 랜덤한 숫자를 N이라고 하면
        // position(x, y) = (N / column, N % column)를
        // mines개 만큼 만들어서 (generatePosition로 만들기)
        // 그 위치에 '*'을 집어넣음
        generatePosition(newBoard.length * newBoard[0].length, mines);
    }

    private void generatePosition(int range, int number) {
        // generate mines in terrain
    }

    public void printBoardStatus(char[][] newBoard) {
        for (int i = 1; i < newBoard.length - 1; i++) {
            for (int j = 1; j < newBoard[0].length - 1; j++) {
                if (board[i][j] == '*') {
                    System.out.print('*' + " ");
                } else {
                    System.out.print(countMines(i, j) + " ");
                }
            }
            System.out.println();
        }
    }

    private int countMines(int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (board[i][j] == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    public void printBoard() {
        for (char[] row : board) {
            for (char el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}