package calculator.service;

import calculator.model.Calculator;
import calculator.model.Delimiters;
import calculator.model.Numbers;
import calculator.util.ExtractionUtil;
import calculator.util.InputValidator;

/**
 * 입력 문자열을 검증하고 파싱하여 계산을 수행하는 비즈니스 로직을 담당한다.
 */
public class CalculatorService {

    /**
     * 입력 문자열을 받아 계산을 수행하는 메인 메서드
     *
     * @param input 계산할 문자열 (구분자와 숫자로 구성)
     * @return 계산 결과 (숫자들의 합)
     * @throws IllegalArgumentException 입력이 유효하지 않은 경우
     */
    public int calculate(String input) {
        InputValidator.validateInput(input);

        Numbers numbers = createNumbersFromInput(input);
        Calculator calculator = new Calculator(numbers);
        return calculator.calculate();
    }

    /**
     * 입력 문자열로부터 Numbers 객체를 생성하는 헬퍼 메서드 구분자를 추출하고 숫자 부분을 파싱하여 Numbers 객체를 만든다.
     *
     * @param input 입력 문자열
     * @return 파싱된 Numbers 객체
     */
    private Numbers createNumbersFromInput(String input) {
        Delimiters delimiters = ExtractionUtil.extractDelimiters(input);
        String numbersPart = ExtractionUtil.extractNumbersPart(input);
        boolean isEmptyInput = (input == null || input.trim().isEmpty());
        return new Numbers(ExtractionUtil.splitNumbers(numbersPart, delimiters), isEmptyInput);
    }
}