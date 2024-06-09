public class Deck {
    final int CARD_NUM = 52; // 카드의 개수
    private Card[] cardArr = new Card[CARD_NUM];

    Deck() {
        int i = 0;
        Card.Kind[] kindArr = Card.Kind.values();
        Card.Value[] valueArr = Card.Value.values();
        for (int k = 0; k < Card.KIND_MAX; k++) {
            for (int n = 0; n < Card.VALUE_MAX; n++) {
                cardArr[i++] = new Card(kindArr[k], valueArr[n]);
            }
        }
    }

    Card pick(int index) {
        return cardArr[index];
    }

    Card pick() {
        int index = (int)(Math.random() * CARD_NUM);
        return pick(index);
    }

    void shuffle() {
        for (int i = 0; i < cardArr.length; i++) {
            int r = (int) (Math.random() * CARD_NUM);

            Card temp = cardArr[i];
            cardArr[i] = cardArr[r];
            cardArr[r] = temp;
        }
    }
}
