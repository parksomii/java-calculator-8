package calculator.util;

import calculator.common.ErrorMessage;
import calculator.view.ErrorView;

/**
 * 입력 문자열의 유효성을 검증하는 유틸리티 클래스
 * 커스텀 구분자 형식과 기본 구분자 사용에 대한 검증을 수행한다.
 */
public class InputValidator {

    /**
     * 입력 문자열의 전체적인 유효성을 검증한다
     * null 체크, 커스텀 구분자 형식 검증, 기본 구분자 검증을 순차적으로 수행한다.
     *
     * @param input 검증할 입력 문자열
     * @throws IllegalArgumentException 입력이 유효하지 않은 경우
     */
    public static void validateInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException(
                    ErrorView.ERROR_MESSAGE + ErrorMessage.INVALID_DELIMITER_FORMAT + "null");
        }

        validateCustomDelimiterFormat(input);
        validateBasicDelimiters(input);
    }

    /**
     * 커스텀 구분자 형식의 유효성을 검증한다
     * "//구분자\n숫자들" 형식이 올바른지 확인한다.
     *
     * @param input 검증할 입력 문자열
     * @throws IllegalArgumentException 커스텀 구분자 형식이 잘못된 경우
     */
    private static void validateCustomDelimiterFormat(String input) {
        if (!input.startsWith("//")) {
            return;
        }

        if (!input.contains("\\n") && !input.contains("\n")) {
            throw new IllegalArgumentException(ErrorView.ERROR_MESSAGE + ErrorMessage.INVALID_DELIMITER_FORMAT + input);
        }

        String delimiterPart = extractDelimiterPartForValidation(input);

        if (delimiterPart.length() < 3) {
            throw new IllegalArgumentException(ErrorView.ERROR_MESSAGE + ErrorMessage.MISSING_DELIMITER + input);
        }

    }

    /**
     * 기본 구분자 사용의 유효성을 검증한다
     * 기본 구분자(쉼표, 콜론) 외의 구분자가 사용되었는지 확인한다.
     *
     * @param input 검증할 입력 문자열
     * @throws IllegalArgumentException 잘못된 구분자가 사용된 경우
     */
    private static void validateBasicDelimiters(String input) {
        // 커스텀 구분자가 아닌 경우에만 기본 구분자 검증
        if (input.startsWith("//")) {
            return;
        }
        // 숫자, 문자, 공백, 음수 기호, 소수점을 제거한 후 구분자만 남김
        String withoutNumbers = input.replaceAll("[0-9a-zA-Z\\s.-]", "");

        // 구분자가 기본 구분자(, :)만 있는지 확인
        if (!withoutNumbers.matches("^[,:]*$")) {
            throw new IllegalArgumentException(ErrorView.ERROR_MESSAGE + ErrorMessage.INVALID_DELIMITER_FORMAT + input);
        }
    }

    /**
     * 커스텀 구분자 부분을 추출하는 헬퍼 메서드
     * "//구분자\n" 부분을 분리하여 반환한다.
     *
     * @param input 입력 문자열
     * @return 구분자 부분 문자열
     * @throws IllegalArgumentException 구분자 형식이 잘못된 경우
     */
    private static String extractDelimiterPartForValidation(String input) {
        if (input.contains("\\n")) {
            String[] parts = input.split("\\\\n", 2);
            if (parts.length != 2) {
                throw new IllegalArgumentException(
                        ErrorView.ERROR_MESSAGE + ErrorMessage.INVALID_DELIMITER_FORMAT + input);
            }
            return parts[0];
        }

        String[] parts = input.split("\n", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    ErrorView.ERROR_MESSAGE + ErrorMessage.INVALID_DELIMITER_FORMAT + input);
        }
        return parts[0];
    }

    /**
     * 유틸리티 클래스이므로 인스턴스 생성을 방지하기 위한 private 생성자
     */
    private InputValidator() {
    }
}