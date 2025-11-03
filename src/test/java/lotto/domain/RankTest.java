package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RankTest {

    @Test
    @DisplayName("매칭 규칙: 6개 일치 → FIRST")
    void fromFirst() {
        assertThat(Rank.from(6, false)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("매칭 규칙: 5개+보너스 → SECOND")
    void fromSecond() {
        assertThat(Rank.from(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("매칭 규칙: 5개 일치 → THIRD")
    void fromThird() {
        assertThat(Rank.from(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("매칭 규칙: 4/3개 → FOURTH/FIFTH")
    void fromFourthFifth() {
        assertThat(Rank.from(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.from(3, false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("매칭 규칙: 그 외 → OUT_OF_RANK")
    void fromOutOfRank() {
        assertThat(Rank.from(2, false)).isEqualTo(Rank.OUT_OF_RANK);
    }

    @Test
    @DisplayName("출력 포맷 확인")
    void format() {
        String s = Rank.FIFTH.format(1);
        assertThat(s).isEqualTo("3개 일치 (5,000원) - 1개");
    }
}


