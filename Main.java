import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    //숫자 아닌 값 입력됐을때 예외처리하기
    public static int inputNumOfParticipants() throws InputMismatchException{
        Scanner sc = new Scanner(System.in);
        System.out.print("참가 인원수를 입력하세요(2~4): ");
        int numOfParticipants = 0;
        try {
            numOfParticipants = sc.nextInt();
            if(numOfParticipants > 4 || numOfParticipants < 2) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("다시입력해주세요");
            numOfParticipants = inputNumOfParticipants();
        } finally {
            return numOfParticipants;
        }
    }

    public static void main(String[] args) {
        System.out.println("-------- 카드게임 시작 --------");
        // game 참여자수 입력받고 게임 생성
        Game game = null;
        try {
            game = new Game(inputNumOfParticipants());
        } catch (InputMismatchException e) {
            System.out.println("잘못입력했습니다. 양식을 지켜주세요. 프로그램을 종료합니다.");
            return;
        }
        // 플레이어 닉네임 입력받기
        game.inputPlayerInfo();
        // 게임 100번 자동 실행
        for (int i = 1; i <= 100; i++) {
            System.out.println("------------------------");
            System.out.println(i + "게임");
            game.run();
            game.initScoreBoard();
        }

        System.out.println("-------- 최종 결과 ---------");
        Player[] players = game.getPlayers();
        Arrays.sort(players);
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i]);
        }
    }
}
