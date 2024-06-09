enum PokerFamilyTree {
    NOPAIR, ONEPAIR, TWOPAIR, TRIPLE, STRAIGHT, MOUNTAIN, FLUSH, FULLHOUSE, FOURCARD, STRAIGHTFLUSH, ROYALSTRAIGHTFLUSH;

    private static final PokerFamilyTree[] PFT_ARR = PokerFamilyTree.values();

    public static PokerFamilyTree of(int idx) {
        if (idx < 0 || idx > 12) {
            throw new IllegalArgumentException("Invalid value:" + idx);
        }
        return PFT_ARR[idx];
    }
}
