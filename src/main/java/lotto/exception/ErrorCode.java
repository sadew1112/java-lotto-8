package lotto.exception;

public enum ErrorCode {
    INVALID_INPUT_ERROR("[ERROR] 입력 값은 null 이나 빈 문자열일 수 없습니다."),
    INVALID_PRICE_NUM_INPUT("[ERROR] 로또 구입 금액은 숫자만 가능합니다."),
    IMPOSSIBLE_TRANS_NUM_INPUT("[ERROR] 숫자 변환이 불가능한 값이 포함되어 있습니다"),
    INVALID_PRICE_UNIT_INPUT("[ERROR] 로또 구입 금액은 1,000원 단위로만 입력 가능합니다."),
    INVALID_PRICE_OVER_INPUT("[ERROR] 로또 구입 금액은 1,000원 이상부터 가능합니다."),
    INVALID_WINNING_NUM_INPUT("[ERROR] 로또 당첨 번호는 숫자와 쉼표로만 입력할 수 있습니다."),
    INVALID_LOTTO_SIZE_INPUT("[ERROR] 로또 번호는 6개여야 합니다."),
    DUPLICATE_NUM_INPUT("[ERROR] 로또 번호에 중복된 숫자가 포함되어 있습니다."),
    OUT_OF_RANGE_NUM("[ERROR] 로또 번호는 1~45 사이의 숫자여야 합니다."),
    BONUS_NUM_INPUT("[ERROR] 보너스 번호는 숫자만 가능합니다."),
    DUPLICATE_BONUS_NUM("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");

    private final String message;
    ErrorCode(String message){
        this.message = message;
    }
    public String message(){
        return message;
    }
}

