package lotto.service;

import lotto.domain.Lotto;
import lotto.exception.ErrorCode;
import lotto.domain.Rank;
import lotto.view.OutputView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.List;

public class LottoResult {
    private final OutputView outputView;

    public LottoResult(OutputView outputView){
        this.outputView = outputView;
    }

    public EnumMap<Rank, Integer> compare(int price, List<Lotto> lottos, List<Integer> winningNums, int bonusNum) {
        EnumMap<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        long prize = 0L;
        for (Lotto lotto : lottos) {
            Rank rank = matchRank(lotto, winningNums, bonusNum);
            result.merge(rank, 1, Integer::sum);
            prize += rank.getPrize();
        }
        outputView.winningResult(result);
        profitRate(prize, price);

        return result;
    }

    private Rank matchRank(Lotto lotto, List<Integer> winningNums, int bonusNum){
        List<Integer> lottoNums = lotto.getNumbers();
        int matchCount = 0;

        for(int num : lottoNums){
            if(winningNums.contains(num)){ matchCount++; }
        }

        boolean isMatchedBonus = lottoNums.contains(bonusNum);

        return Rank.from(matchCount, isMatchedBonus);
    }

    public void profitRate(long prize, int price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ErrorCode.INVALID_PRICE_OVER_INPUT.message());
        }
        BigDecimal rate = BigDecimal.valueOf(prize)
                .divide(BigDecimal.valueOf(price), 10, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(1, RoundingMode.HALF_UP);

        outputView.profitRate(rate);
    }
}
