package calculator.model;

import calculator.common.ErrorMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * 숫자들을 관리하는 도메인 클래스
 */
public class Numbers {
    /**
     * 정수 숫자들을 저장하는 리스트
     */
    private final List<Integer> numbers;


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
            this.numbers.add(0);
        } else {
            for (String numberString : numberStrings) {
                addNumber(numberString);
            }
        }
    }

    /**
     * 문자열을 정수로 변환하여 숫자 리스트에 추가한다.
     *
     * @param numberString 변환할 숫자 문자열
     * @throws IllegalArgumentException 숫자가 유효하지 않은 경우
     */
    public void addNumber(String numberString) {
        int number = parseNumber(numberString);
        validateNumber(number);
        numbers.add(number);
    }

    /**
     * 저장된 모든 숫자들의 합계를 계산한다
     *
     * @return 모든 숫자들의 합계
     */
    public int sum() {
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }


    /**
     * 문자열을 정수로 변환하는 private 메서드 정수 범위 초과 및 소수점 포함 여부를 검증한다.
     *
     * @param numberString 변환할 숫자 문자열
     * @return 변환된 정수
     * @throws IllegalArgumentException 정수 범위를 초과하거나 소수점이 포함된 경우
     */
    private int parseNumber(String numberString) {
        try {
            // 정수 범위 초과 체크
            long longValue = Long.parseLong(numberString);
            if (longValue > Integer.MAX_VALUE) {
                throw new IllegalArgumentException(ErrorMessage.INTEGER_OVERFLOW);
            }
            return (int) longValue;
        } catch (NumberFormatException e) {
            // 소수점이 포함된 경우 (정수만 허용)
            if (numberString.contains(".")) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER + numberString);
            }
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER + numberString);
        }
    }

    /**
     * 숫자가 양수인지 검증하는 private 메서드
     *
     * @param number 검증할 숫자
     * @throws IllegalArgumentException 숫자가 0 이하인 경우
     */
    private void validateNumber(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER + number);
        }
    }
}