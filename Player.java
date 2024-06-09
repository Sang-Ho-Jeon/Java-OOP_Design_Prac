public class Player implements Comparable<Player> {
    static final int PLAYER_CARD_NUM = 5;
    private Card[] cards = new Card[PLAYER_CARD_NUM];
    private String nickname;
    private int money = 10000;
    private int win = 0;
    private int defeat = 0;

    Player(String nickname) {
        this.nickname = nickname;
    }
    void setCard(int index, Card card) {
        cards[index] = card;
    }

    public Card[] getCards() {
        return cards;
    }
    public void addWin() {
        win++;
        money += 100;
    }

    public void addDefeat() {
        defeat++;
    }

    public String getNickname() {
        return nickname;
    }
    public String toString() {
        return nickname + " >> " + win + "승 " + defeat + "패 " + money + "원 보유";
    }

    @Override
    public int compareTo(Player o) {
        return o.win - this.win;
    }
}
