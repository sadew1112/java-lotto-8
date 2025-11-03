package lotto;

import lotto.domain.Lotto;
import lotto.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("범위를 벗어난 숫자 포함 시 예외")
    void outOfRange() {
        assertThatThrownBy(() -> new Lotto(List.of(0,2,3,4,5,6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorCode.OUT_OF_RANGE_NUM.message());
    }

    @Test
    @DisplayName("불변 리스트 보장 (수정 시도 시 UnsupportedOperationException)")
    void immutableNumbers() {
        Lotto lotto = new Lotto(List.of(1,2,3,4,5,6));
        assertThatThrownBy(() -> lotto.getNumbers().add(7))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
