package lotto.util;

import lotto.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputValidatorTest {

    private final InputValidator validator = new InputValidator(new NumberSeparator());

    // 금액 검증
    @Test
    @DisplayName("정상 금액 입력은 그대로 정수 반환")
    void 구매금액_입력_정상_테스트() {
        assertThat(validator.priceValidate("5000")).isEqualTo(5000);
    }

    @Test
    @DisplayName("null or 빈 문자열 입력 예외")
    void 금액_null_빈문자열_입력_예외() {
        assertThatThrownBy(() -> validator.priceValidate(" "))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("금액에 숫자 외 문자가 있으면 예외")
    void 숫자_외_입력_예외() {
        assertThatThrownBy(() -> validator.priceValidate("3000!k"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("금액이 1000 미만이면 예외")
    void 금액_부족_예외() {
        assertThatThrownBy(() -> validator.priceValidate("800"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("금액이 1000 단위가 아니면 예외")
    void 금액_단위_예외() {
        assertThatThrownBy(() -> validator.priceValidate("1500"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호는 숫자와 쉼표만 허용")
    void 당첨_번호_입력_문자_예외() {
        assertThatThrownBy(() -> validator.winningNumsValidate("1,2,3,4,5,j"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("당첨 번호 6개가 아니면 예외")
    void 당첨_번호_개수_6개_불일치_예외() {
        assertThatThrownBy(() -> validator.winningNumsValidate("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("null or 빈 문자열 입력 예외")
    void 당첨_번호_null_빈문자열_입력_예외() {
        assertThatThrownBy(() -> validator.winningNumsValidate(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정상 당첨 번호 테스트")
    void 당첨_번호_정상_테스트() {
        assertThat(validator.winningNumsValidate("1,2,3,4,5,6"))
                .containsExactly(1,2,3,4,5,6);
    }

    @Test
    @DisplayName("보너스 번호 정상")
    void 보너스_번호_정상_테스트() {
        assertThat(validator.bonusValidate("7", List.of(1,2,3,4,5,6))).isEqualTo(7);
    }

    @Test
    @DisplayName("보너스 번호에 숫자 외 문자가 있으면 예외")
    void 보너스_번호_숫자_외_입력_예외() {
        assertThatThrownBy(() -> validator.bonusValidate("j", List.of(1,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호에 포함되면 예외")
    void 보너스_번호_중복_예외() {
        assertThatThrownBy(() -> validator.bonusValidate("6", List.of(1,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("보너스 번호 범위 벗어나면 예외")
    void 보너스_번호_범위_밖_예외() {
        assertThatThrownBy(() -> validator.bonusValidate("-3", List.of(1,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("null or 빈 문자열 입력 예외")
    void 보너스_null_빈문자열_입력_예외() {
        assertThatThrownBy(() -> validator.bonusValidate(" ", List.of(1,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}


