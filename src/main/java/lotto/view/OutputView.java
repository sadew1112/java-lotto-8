package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Rank;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;

public class OutputView {
    public void purchaseCount(Integer count){
        System.out.println();
        System.out.printf("%d개를 구매했습니다.%n", count);
    }

    public void lottoNumber(Lotto lotto){
        System.out.println(lotto.getNumbers().stream().sorted().toList());
        System.out.println();
    }

    public void winningResult(EnumMap<Rank, Integer> result){
        System.out.println("당첨 통계\n---");
        System.out.println();

        List<Rank> order = List.of(
                Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST
        );

        for (Rank rank : order){
            int matchCount = result.get(rank);
            System.out.println(rank.format(matchCount));
        }
    }

    public void profitRate(BigDecimal rate) {
        System.out.printf("총 수익률은 %s%%입니다.%n", rate.toPlainString());
    }

}
