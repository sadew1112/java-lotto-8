package lotto.domain;

import lotto.exception.ErrorCode;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        validateDuplicate(numbers);
        validateRange(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> transNumbers = numbers.stream().collect(Collectors.toSet());
        if (transNumbers.size() != 6) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_NUM_INPUT.message());
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (Integer num : numbers){
            if (num < 1 || num > 45) {
                throw new IllegalArgumentException(ErrorCode.OUT_OF_RANGE_NUM.message());
            }
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
