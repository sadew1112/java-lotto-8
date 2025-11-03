package lotto.util;

import lotto.exception.ErrorCode;

import java.util.List;

public class InputValidator {
    private static final int LOTTO_PRICE = 1000;
    private final NumberSeparator separator;

    public InputValidator(NumberSeparator separator){
        this.separator = separator;
    }

    public Integer priceValidate(String inputPrice){
        inputValidate(inputPrice);

        if(!inputPrice.matches("^[0-9]+$")){
            throw new IllegalArgumentException(ErrorCode.INVALID_PRICE_NUM_INPUT.message());
        }

        int price = Integer.parseInt(inputPrice);
        if (price < LOTTO_PRICE) {
            throw new IllegalArgumentException(ErrorCode.INVALID_PRICE_OVER_INPUT.message());
        }
        if (price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorCode.INVALID_PRICE_UNIT_INPUT.message());
        }
        return price;
    }

    public List<Integer> winningNumsValidate(String inputWinningNums){
        inputValidate(inputWinningNums);

        if (!inputWinningNums.matches("^[0-9,]+$")) {
            throw new IllegalArgumentException(ErrorCode.INVALID_WINNING_NUM_INPUT.message());
        }

        List<Integer> winningNums = separator.separator(inputWinningNums);

        if(winningNums.size() != 6){
            throw new IllegalArgumentException(ErrorCode.INVALID_LOTTO_SIZE_INPUT.message());
        }

        return winningNums;
    }

    public Integer bonusValidate(String inputBonusNum, List<Integer> winningNums){
        inputValidate(inputBonusNum);
        if (!inputBonusNum.matches("^[0-9]+$")) {
            throw new IllegalArgumentException(ErrorCode.BONUS_NUM_INPUT.message());
        }

        int bonus = Integer.parseInt(inputBonusNum);
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException(ErrorCode.OUT_OF_RANGE_NUM.message());
        }

        if (winningNums.contains(bonus)){
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_BONUS_NUM.message());
        }
        return bonus;
    }

    private void inputValidate(String input){
        if(input == null || input.isBlank()){
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_ERROR.message());
        }
    }

}
