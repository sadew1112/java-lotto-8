package lotto.util;

import java.util.Arrays;

public enum Rank {

    FIRST(6, false, 2_000_000_000L, "6개 일치 (%,d원) - %d개"),
    SECOND(5, true,   30_000_000L,  "5개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    THIRD(5, false,   1_500_000L,   "5개 일치 (%,d원) - %d개"),
    FOURTH(4, false,      50_000L,  "4개 일치 (%,d원) - %d개"),
    FIFTH(3, false,       5_000L,   "3개 일치 (%,d원) - %d개"),
    OUT_OF_RANK(0, false, 0L, "당첨 실패");

    private final int matchCount;
    private final boolean isMatchedBonus;
    private final long prize;
    private final String script;

    Rank(int matchCount,boolean isMatchedBonus, long prize, String script) {
        this.matchCount = matchCount;
        this.isMatchedBonus = isMatchedBonus;
        this.prize = prize;
        this.script = script;
    }

    public long getPrize() { return prize; }
    public String getScript() { return script; }
    public String format(int count) {
        return String.format(this.script, this.prize, count);
    }

    public static Rank from(int matchCount, boolean isMatchedBonus) {
        for (Rank r : values()) {
            if (r == OUT_OF_RANK) continue;
            if (r.matchCount != matchCount) continue;
            if (r.matchCount == 5) {
                if (r.isMatchedBonus == isMatchedBonus) return r;
                continue;
            }
            return r;
        }
        return OUT_OF_RANK;
    }


}
