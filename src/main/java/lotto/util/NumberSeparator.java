package lotto.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberSeparator {
    public List<Integer> separator(String input){
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(num -> !num.isEmpty())
                .map(num -> Integer.parseInt(num))
                .distinct()
                .collect(Collectors.toList());
    }
}
