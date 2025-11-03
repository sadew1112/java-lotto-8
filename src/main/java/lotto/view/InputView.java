package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.util.InputValidator;

import java.util.List;

public class InputView {
    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator){
        this.inputValidator = inputValidator;
    }
    public int inputPurchasePrice() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String input = Console.readLine();
                return inputValidator.priceValidate(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Integer> inputWinningNums() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String input = Console.readLine();
                return inputValidator.winningNumsValidate(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public int inputBonusNum(List<Integer> winningNums) {
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String input = Console.readLine();
                return inputValidator.bonusValidate(input, winningNums);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
