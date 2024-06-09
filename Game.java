import java.util.HashMap;
import java.util.Scanner;

public class Game {
    static final int MAX_PARTICIPANTS = 4;
    private int numOfParticipants;
    private Player[] players;
    private Dealer dealer;

    private HashMap<String, PokerFamilyTree> scoreBoard;
    Game(int numOfParticipants) {
        this.numOfParticipants = numOfParticipants;
        players = new Player[numOfParticipants];
        dealer = new Dealer(players, this);
        scoreBoard = new HashMap<>();
    }

    public void initScoreBoard() {
        for (int i = 0; i < players.length; i++) {
            scoreBoard.put(players[i].getNickname(), PokerFamilyTree.NOPAIR);
        }
    }

    public Card[] getPlayerCards(String nickname) {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getNickname() == nickname) {
                return players[i].getCards();
            }
        }
        return null;
    }
    public void recordScore(String nickname, PokerFamilyTree pft) {
        scoreBoard.put(nickname, pft);
    }

    public void inputPlayerInfo() {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= numOfParticipants; i++) {
            String nickname = "";
            System.out.println(i + "번째 플레이어");
            System.out.print("닉네임 입력(20자 이내): ");
            nickname = sc.nextLine();
            while (nickname.length() > 20) {
                System.out.println("닉네임의 길이는 20자를 넘길 수 없습니다.");
                System.out.print("다시 입력하세요(20자 이내): ");
                nickname = sc.nextLine();
            }
            players[i - 1] = new Player(nickname);
        }
    }

    public Player[] getPlayers() {
        return players;
    }

    void printPlayersScore() {
        for (int i = 0; i < players.length; i++) {
            String nickname = players[i].getNickname();
            System.out.print(nickname + ": ");
            System.out.print(scoreBoard.get(nickname) + " ");
            Card tmp = dealer.getMaxCard(players[i].getNickname());
            if(tmp != null) System.out.println("[" + tmp + "]");
            else System.out.println();
        }
    }
    public void run() {
        // 게임 전 카드섞기
        dealer.shuffle();
        // 게임 참여 플레이어들에게 카드 5장씩 나눠주기
        dealer.giveCardToUser(players);
        // 족보 비교 후 맵에 기록
        dealer.recordScoreTable(this);
        // 게임 승자 닉네임 받아오기
        String winner = dealer.getWinner(scoreBoard);
        printPlayersScore();

        // 게임 승자에게 상금과 승수 부여
        int winnderIdx = 0;
        for (int i = 0; i < players.length; i++) {
            if ((players[i].getNickname()).equals(winner)) {
                winnderIdx = i;
                players[i].addWin();
            } else {
                players[i].addDefeat();
            }
        }
        System.out.print("승자: ");
        // 승자 출력
        System.out.println(players[winnderIdx]);
    }
}
