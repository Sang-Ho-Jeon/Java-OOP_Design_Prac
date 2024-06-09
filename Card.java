public class Card implements Comparable<Card>{
    static final int KIND_MAX = 4;
    static final int VALUE_MAX = 13;
    enum Kind { CLOVER, HEART, DIAMOND, SPADE } // 카드 무늬
    enum Value { TWO, THREE, FOUR, Five, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE } // 카드 숫자

    private final Kind kind;
    private final Value value;

    Card(Kind kind, Value value) {
        this.kind = kind;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    public Kind getKind() {
        return kind;
    }

    public String toString() {
        return "kind : " + this.kind + ", number : " + this.value;
    }

    @Override
    public int compareTo(Card o) {
        if(o.value.compareTo(this.value) == 0)
            return o.kind.compareTo(this.kind);
        else return o.value.compareTo(this.value);
    }
}
