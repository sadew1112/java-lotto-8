package lotto.util;

import lotto.exception.ErrorCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class NumberSeparator {
    public List<Integer> separator(String input){
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(this::parseAndValidate)
                .collect(Collectors.toList());

        duplicateValidate(numbers);
        return numbers;
    }

    private int parseAndValidate(String numStr) {
        blankValidate(numStr);
        int num = parseInt(numStr);
        rangeValidate(num);
        return num;
    }

    private void blankValidate(String numStr) {
        if (numStr.isBlank()) {
            throw new IllegalArgumentException(ErrorCode.INVALID_INPUT_ERROR.message());
        }
    }

    private int parseInt(String numStr) {
        try {
            return Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorCode.IMPOSSIBLE_TRANS_NUM_INPUT.message());
        }
    }

    public void rangeValidate(int num) {
        if (num < 1 || num > 45) {
            throw new IllegalArgumentException(ErrorCode.OUT_OF_RANGE_NUM.message());
        }
    }

    private void duplicateValidate(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorCode.DUPLICATE_NUM_INPUT.message());
        }
    }
}

