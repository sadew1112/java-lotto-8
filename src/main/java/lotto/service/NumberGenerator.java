package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.domain.Lotto;
import lotto.util.InputValidator;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class NumberGenerator {
    private static final int LOTTO_PRICE = 1000;
    private final OutputView outputView;

    public NumberGenerator(OutputView outputView){
        this.outputView = outputView;
    }

    public List<Lotto> getLottos(Integer purchasePrice){
        int count = purchasePrice / LOTTO_PRICE;
        outputView.purchaseCount(count);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++){
            Lotto lotto = generateLotto();
            lottos.add(lotto);
            outputView.lottoNumber(lotto);
        }

        return lottos;
    }

    public Lotto generateLotto(){
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
}
