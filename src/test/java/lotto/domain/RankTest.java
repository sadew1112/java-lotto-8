package lotto.domain;

import lotto.domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RankTest {



    @Test
    @DisplayName("from 메서드를 통한 매칭 테스트")
    void 등수_매칭_정상_테스트() {
        assertThat(Rank.from(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.from(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.from(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.from(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.from(3, false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("그 외 등수 테스트")
    void fromOutOfRank() {
        assertThat(Rank.from(1, false)).isEqualTo(Rank.OUT_OF_RANK);
    }

    @Test
    @DisplayName("등수 별 상금 확인")
    void 상금_반환_테스트() {
        assertThat(Rank.FIRST.getPrize()).isEqualTo(2_000_000_000L);
    }

    @Test
    @DisplayName("등수 별 출력 스크립트 확인")
    void 스크립트_출력_테스트() {
        assertThat(Rank.SECOND.getScript()).isEqualTo("5개 일치, 보너스 볼 일치 (%,d원) - %d개");
    }
    @Test
    @DisplayName("등수 별 출력 포맷 확인")
    void 출력_포맷_테스트() {
        assertThat(Rank.SECOND.format(1)).isEqualTo("5개 일치, 보너스 볼 일치 (30,000,000원) - 1개");
    }
}


