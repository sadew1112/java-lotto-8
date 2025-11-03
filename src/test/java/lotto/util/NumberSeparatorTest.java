package lotto.util;

import lotto.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class NumberSeparatorTest {

    private final NumberSeparator separator = new NumberSeparator();

    @Test
    @DisplayName("쉼표로 분리된 숫자 문자열을 정수 리스트로 변환")
    void 쉼표_구분자_리스트_변환_정상_테스트() {
        assertThat(separator.separator("1,2,3,4,5,6"))
                .containsExactly(1,2,3,4,5,6);
    }

    @Test
    @DisplayName("공백은 무시 테스트")
    void 공백_제거_후_변환_정상_테스트() {
        assertThat(separator.separator("1, 2, 3,     4, 5, 6"))
                .containsExactly(1,2,3,4,5,6);
    }

    @Test
    @DisplayName("빈 문자열을 요소로 받을 시 예외")
    void 빈_문자열_요소_예외() {
        assertThatThrownBy(() -> separator.separator("1,,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복 숫자 존재 시 예외")
    void 중복_숫자_예외() {
        assertThatThrownBy(() -> separator.separator("1,2,2,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("범위를 벗어난 숫자 존재 시 예외")
    void 범위_밖_숫자_예외() {
        assertThatThrownBy(() -> separator.separator("0,2,3,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("숫자 타입 변환 불가 문자 포함 시 예외")
    void 숫자_변환_불가_예외() {
        assertThatThrownBy(() -> separator.separator("1,a,3,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
