package lotto;

import lotto.domain.Lotto;
import lotto.service.LottoResult;
import lotto.service.NumberGenerator;
import lotto.util.NumberSeparator;
import lotto.util.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView(new InputValidator(new NumberSeparator()));
        int purchasePrice = inputView.inputPurchasePrice();

        OutputView outputView = new OutputView();
        NumberGenerator generator = new NumberGenerator(outputView);
        List<Lotto> lottoNums = generator.getLottos(purchasePrice);

        List<Integer> winningNums = inputView.inputWinningNums();
        int bonusNum = inputView.inputBonusNum(winningNums);

        LottoResult result = new LottoResult(outputView);
        result.compare(purchasePrice, lottoNums, winningNums, bonusNum);
    }
}
