package collectionmanager.business.types;

import java.util.Arrays;


public enum Rating {

    UNRATED(0, "unrated"),
    ONE(1, "1/10"),
    TWO(2, "2/10"),
    THREE(3, "3/10"),
    FOUR(4, "4/10"),
    FIFE(5, "5/10"),
    SIX(6, "6/10"),
    SEVEN(7, "7/10"),
    EIGHT(8, "8/10"),
    NINE(9, "9/10"),
    TEN(10, "10/10");

    private int intValue;
    private String stringValue;

    Rating(int intValue, String stringValue) {
        this.intValue = intValue;
        this.stringValue = stringValue;
    }

    public int toInt() {
        return intValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public static Rating of(Integer rating) {
        if (rating == null) {
            return Rating.UNRATED;
        }
        return Arrays.stream(values())
            .filter(r -> r.toInt() == rating)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("invalid value for rating: " + rating));
    }

}
