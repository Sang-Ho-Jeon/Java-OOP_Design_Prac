import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dealer {
    private Deck deck = new Deck();
    private Player[] players;
    private Game game;
    private HashMap<String, Card> playerCards = new HashMap<>();
    private ArrayList<String> sameScoreNickname = new ArrayList<>(Game.MAX_PARTICIPANTS);
    Dealer(Player[] players, Game game) {
        shuffle();
        this.players = players;
        this.game = game;
    }

    public void shuffle() {
        deck.shuffle();
    }

    public void giveCardToUser(Player[] players) {
        int card_idx = 0;
        for (int i = 0; i < players.length; i++) {
            for (int j = 0; j < Player.PLAYER_CARD_NUM; j++) {
                players[i].setCard(j, deck.pick(card_idx++));
            }
        }
    }

    Card getMaxCard(String nickname) {
        return playerCards.get(nickname);
    }
    void recordScoreTable(Game game) {
        for (int i = 0; i < players.length; i++) {
            isPokerFamilyTree pft = new isPokerFamilyTree(players[i].getCards());
            game.recordScore(players[i].getNickname(), pft.getPokerFamilyTree());
        }
    }

    // 가장 높은 족보 구하기
    public int getHighPokerFamilyTree(HashMap<String, PokerFamilyTree> scoreBoard) {
        int max = Integer.MIN_VALUE;
        for (String str : scoreBoard.keySet()) {
            int value = scoreBoard.get(str).ordinal();
            max = Math.max(max, value);
        }
        return max;
    }

    String getWinner(HashMap<String, PokerFamilyTree> scoreBoard) {
        int max = getHighPokerFamilyTree(scoreBoard);

        // 가장 높은 족보가 중복되면 배열에 넣기
        sameScoreNickname.clear();
        for (String str : scoreBoard.keySet()) {
            if (scoreBoard.get(str).ordinal() == max) {
                sameScoreNickname.add(str);
            }
        }

        // 가장 높은 족보가 1명이면 승리, 여러명이면 각자 높은 숫자 비교
        playerCards.clear();
        if (sameScoreNickname.size() == 1) {
            return sameScoreNickname.get(0);
        } else {
            return evaluateSamePlayer();
        }
    }

    // 카드 숫자값 내림차순 정렬해서 <닉네임 : 카드패> 쌍을 맵에 저장
    public void putSortingCardToPlayerCardsHashMap() {
        for (String nickname : sameScoreNickname) {
            Card[] cards = game.getPlayerCards(nickname);
            Arrays.sort(cards);
            playerCards.put(nickname, cards[0]);
        }
    }

    String evaluateSamePlayer() {
        putSortingCardToPlayerCardsHashMap();
        String winnerNickname = "";
        Card maxCard = new Card(Card.Kind.CLOVER, Card.Value.TWO);
        for (String nickname : playerCards.keySet()) {
            int compareValue = playerCards.get(nickname).getValue().compareTo(maxCard.getValue());
            if (compareValue == 0) {
                int compareKind = playerCards.get(nickname).getKind().compareTo(maxCard.getKind());
                if (compareKind > 0) {
                    maxCard = playerCards.get(nickname);
                    winnerNickname = nickname;
                }
            } else if (compareValue > 0) {
                maxCard = playerCards.get(nickname);
                winnerNickname = nickname;
            }
        }
        return winnerNickname;
    }
}
