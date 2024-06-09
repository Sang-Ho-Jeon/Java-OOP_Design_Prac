public class isPokerFamilyTree {
    private int[] valueCount = new int[Card.VALUE_MAX];
    private int[] kindCount = new int[Card.KIND_MAX];
    private Card[] cards;

    isPokerFamilyTree(Card[] cards) {
        this.cards = cards;
        for (int i = 0; i < cards.length; i++) {
            int valueIdx = cards[i].getValue().ordinal();
            int kindIdx = cards[i].getKind().ordinal();
            valueCount[valueIdx]++;
            kindCount[kindIdx]++;
        }
    }

    // wantCntNum : 원하는 같은 숫자의 개수
    // 페어 개수를 반환하는 메서드
    private int getNumOfPair(int wantCntNum) {
        int cnt = 0;
        for (int i = 0; i < valueCount.length; i++) {
            if(valueCount[i] == wantCntNum) cnt++;
        }
        return cnt;
    }

    private boolean isOnePair() {
        return getNumOfPair(2) == 1;
    }

    private boolean isTwoPair() {
        return getNumOfPair(2) == 2;
    }
    private boolean isTriple() {
        return getNumOfPair(3) == 1;
    }
    private boolean isStraight() {
        int pos = 0;
        for (int i = 0; i < valueCount.length; i++) {
            if (valueCount[i] >= 1) {
                pos = i;
                break;
            }
        }
        for (int i = pos; i < pos + 5; i++) {
            if(valueCount[i] != 1) return false;
        }
        return true;
    }
    private boolean isRoyalStraight() {
        for (int i = valueCount.length - 1; i >= valueCount.length - 5; i--) {
            if(valueCount[i] != 1) return false;
        }
        return true;
    }

    private boolean isFlush() {
        for (int i = 0; i < kindCount.length; i++) {
            if(kindCount[i] == 5) return true;
        }
        return false;
    }
    private boolean isFullHouse() {
        return isOnePair() && isTriple();
    }

    private boolean isFourCard() {
        return getNumOfPair(4) == 1;
    }

    public PokerFamilyTree getPokerFamilyTree() {
        if (isFlush() && isRoyalStraight()) {
            return PokerFamilyTree.ROYALSTRAIGHTFLUSH;
        } else if (isStraight() && isFlush()) {
            return PokerFamilyTree.STRAIGHTFLUSH;
        } else if (isFourCard()) {
            return PokerFamilyTree.FOURCARD;
        } else if (isFullHouse()) {
            return PokerFamilyTree.FULLHOUSE;
        } else if (isFlush()) {
            return PokerFamilyTree.FLUSH;
        } else if (isRoyalStraight()) {
            return PokerFamilyTree.MOUNTAIN;
        } else if (isStraight()) {
            return PokerFamilyTree.STRAIGHT;
        } else if (isTriple()) {
            return PokerFamilyTree.TRIPLE;
        } else if (isTwoPair()) {
            return PokerFamilyTree.TWOPAIR;
        } else if (isOnePair()) {
            return PokerFamilyTree.ONEPAIR;
        } else {
            return PokerFamilyTree.NOPAIR;
        }
    }
}