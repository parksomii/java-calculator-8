package calculator.model;

import calculator.common.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * 숫자들을 관리하는 도메인 클래스
 * 문자열로 된 숫자들을 실수로 변환하고 검증하며, 합계를 계산하는 기능을 제공한다.
 */
public class Numbers {
    /**
     * 실수 숫자들을 저장하는 리스트
     */
    private final List<Double> numbers;


    /**
     * 빈 입력 처리를 위한 생성자 빈 문자열인 경우 0을 추가하고, 그렇지 않으면 일반적인 숫자 변환을 수행한다.
     *
     * @param numberStrings 변환할 숫자 문자열들의 리스트
     * @param isEmptyInput  입력이 빈 문자열인지 여부
     */
    public Numbers(List<String> numberStrings, boolean isEmptyInput) {
        this.numbers = new ArrayList<>();
        if (isEmptyInput) {
            // 빈 문자열인 경우 0을 반환
            this.numbers.add(0.0);
            return;
        }

        for (String numberString : numberStrings) {
            addNumber(numberString);
        }
    }

    /**
     * 문자열을 실수로 변환하여 숫자 리스트에 추가한다.
     *
     * @param numberString 변환할 숫자 문자열
     * @throws IllegalArgumentException 숫자가 유효하지 않은 경우
     */
    public void addNumber(String numberString) {
        double number = parseNumber(numberString);
        validateNumber(number);
        numbers.add(number);
    }

    /**
     * 저장된 모든 숫자들의 합계를 계산한다
     *
     * @return 모든 숫자들의 합계
     */
    public double sum() {
        return numbers.stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }


    /**
     * 문자열을 실수로 변환하는 private 메서드
     * 정수와 실수 모두 지원하며, 정수 범위 초과 및 유효하지 않은 숫자 형식을 검증한다.
     *
     * @param numberString 변환할 숫자 문자열
     * @return 변환된 실수
     * @throws IllegalArgumentException 정수 범위를 초과하거나 유효하지 않은 숫자 형식인 경우
     */
    private double parseNumber(String numberString) {
        try {
            double result = Double.parseDouble(numberString);
            
            // 정수 범위 초과 체크 (Integer.MAX_VALUE를 초과하는 경우)
            if (result > Integer.MAX_VALUE) {
                throw new IllegalArgumentException(ErrorMessage.INTEGER_OVERFLOW);
            }
            
            return result;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER + numberString);
        }
    }

    /**
     * 숫자가 양수인지 검증하는 private 메서드
     *
     * @param number 검증할 숫자
     * @throws IllegalArgumentException 숫자가 0 이하인 경우
     */
    private void validateNumber(double number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER + number);
        }
    }
}