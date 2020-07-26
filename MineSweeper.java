import java.util.Scanner;

public class MineSweeper {

    public static void play() {
        MineSweeperGame game;
        Scanner sc = new Scanner(System.in);

        // 레벨 선택
        showLevelSelect();
        int level = sc.nextInt();

        switch (level) {
            case 1:
            default:
                game = new MineSweeperGame(5, 5);
                break;

            case 2:
                game = new MineSweeperGame(7, 7);
                break;

            case 3:
                game = new MineSweeperGame(10, 10);
                break;
        }

        // 게임 시작
        // 입력한 x, y값에 따라 보드를 보여 줌
        while (true) {
            int x, y;
            game.display();

            showCoordinatesSelect();
            x = sc.nextInt();
            y = sc.nextInt();

            // 체크하고 게임이 끝났을 때
            // 보드를 보여주고 게임을 끝낸다.
            if (game.CheckBoard(x, y)) {
                game.printBoardStatus();
                break;
            }
        }

        sc.close();
    }

    private static void showLevelSelect() {
        System.out.println("Select the level");
        System.out.println("1. Easy (5 x 5)");
        System.out.println("2. Moderate (7 x 7)");
        System.out.println("3. Hard (10 x 10)");
    }

    private static void showCoordinatesSelect() {
        System.out.println("Select x and y coordinates");
        System.out.print("X, Y: ");
    }
}