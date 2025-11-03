package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.view.OutputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

class LottoResultTest {

    static class CapturingOutputView extends OutputView {
        EnumMap<Rank, Integer> lastResult;
        BigDecimal lastRate;

        @Override
        public void winningResult(EnumMap<Rank, Integer> result) {
            this.lastResult = new EnumMap<>(result);
        }

        @Override
        public void profitRate(BigDecimal rate) {
            this.lastRate = rate;
        }
    }

    @Test
    @DisplayName("5등, 순위 밖 당첨 확인 테스트")
    void 당첨_확인_정상_테스트1() {
        CapturingOutputView view = new CapturingOutputView();
        LottoResult result = new LottoResult(view);

        Lotto l1 = new Lotto(List.of(2,8,10,25,31,32));
        Lotto l2 = new Lotto(List.of(4,9,15,18,35,37));

        List<Lotto> lottos = List.of(l1, l2);
        List<Integer> winning = List.of(2,8,15,25,33,43);
        int bonus = 45;
        int price = 2000;

        result.compare(price, lottos, winning, bonus);

        assertThat(view.lastResult.get(Rank.FIRST)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.SECOND)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.THIRD)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.FIFTH)).isEqualTo(1);
        assertThat(view.lastResult.get(Rank.OUT_OF_RANK)).isEqualTo(1);

        assertThat(view.lastRate).isEqualByComparingTo(new BigDecimal("250.0"));
    }

    @DisplayName("2,3등 당첨 테스트")
    @Test
    void 당첨_확인_정상_테스트2() {
        CapturingOutputView view = new CapturingOutputView();
        LottoResult result = new LottoResult(view);

        Lotto l1 = new Lotto(List.of(1,2,3,4,5,7));
        Lotto l2 = new Lotto(List.of(1,2,3,4,5,8));

        List<Lotto> lottos = List.of(l1,l2);
        List<Integer> winning = List.of(1,2,3,4,5,6);
        int bonus = 7;
        int price = 2000;

        result.compare(price, lottos, winning, bonus);

        assertThat(view.lastResult.get(Rank.FIRST)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.SECOND)).isEqualTo(1);
        assertThat(view.lastResult.get(Rank.THIRD)).isEqualTo(1);
        assertThat(view.lastResult.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.FIFTH)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.OUT_OF_RANK)).isEqualTo(0);

        assertThat(view.lastRate).isEqualByComparingTo(new BigDecimal("1575000.0"));
    }

    @DisplayName("모든 로또가 낙첨인 경우 테스트")
    @Test
    void 당첨_확인_정상_테스트3() {
        CapturingOutputView view = new CapturingOutputView();
        LottoResult result = new LottoResult(view);

        Lotto l1 = new Lotto(List.of(1,2,3,4,5,7));
        Lotto l2 = new Lotto(List.of(1,2,3,4,5,8));

        List<Lotto> lottos = List.of(l1,l2);
        List<Integer> winning = List.of(11,12,13,14,15,16);
        int bonus = 10;
        int price = 2000;

        result.compare(price, lottos, winning, bonus);

        assertThat(view.lastResult.get(Rank.FIRST)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.SECOND)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.THIRD)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.FOURTH)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.FIFTH)).isEqualTo(0);
        assertThat(view.lastResult.get(Rank.OUT_OF_RANK)).isEqualTo(2);

        assertThat(view.lastRate).isEqualByComparingTo(new BigDecimal("0.0"));
    }

    @DisplayName("로또가 여러 장인 일반 경우 테스트")
    @Test
    void 당첨_확인_정상_테스트4() {
        CapturingOutputView view = new CapturingOutputView();
        LottoResult sut = new LottoResult(view);

        Lotto l1 = new Lotto(List.of(1, 2, 3, 40, 41, 42));
        Lotto l2 = new Lotto(List.of(4, 5, 6, 7, 8, 9));
        Lotto l3 = new Lotto(List.of(13, 14, 15, 16, 17, 18));
        Lotto l4 = new Lotto(List.of(19, 20, 21, 22, 23, 24));
        Lotto l5 = new Lotto(List.of(25, 26, 27, 28, 29, 30));
        Lotto l6 = new Lotto(List.of(31, 32, 33, 34, 35, 36));
        Lotto l7 = new Lotto(List.of(37, 38, 39, 40, 41, 42));
        Lotto l8 = new Lotto(List.of(43, 44, 45, 9, 13, 14));

        List<Lotto> lottos = List.of(l1,l2,l3,l4,l5,l6,l7,l8);
        List<Integer> winning = List.of(1, 2, 3, 10, 11, 12);
        int bonus = 44;
        int price = 8000;

        sut.compare(8000, lottos, winning, bonus);

        assertThat(view.lastRate).isEqualByComparingTo(new BigDecimal("62.5"));
    }

}

