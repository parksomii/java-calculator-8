package calculator.util;

import calculator.common.ErrorMessage;
import calculator.model.Delimiters;
import java.util.ArrayList;
import java.util.List;

/**
 * 입력 문자열에서 구분자와 숫자 부분을 추출하는 유틸리티 클래스
 */
public class ExtractionUtil {

    /**
     * 입력 문자열에서 구분자를 추출하여 Delimiters 객체를 생성한다.
     * 커스텀 구분자가 있으면 해당 구분자를, 없으면 기본 구분자를 사용한다.
     *
     * @param input 구분자를 추출할 입력 문자열
     * @return 추출된 구분자 정보를 담은 Delimiters 객체
     * @throws IllegalArgumentException 구분자 형식이 잘못되었거나 음수 기호를 구분자로 사용한 경우
     */
    public static Delimiters extractDelimiters(String input) {
        if (input == null || !input.startsWith("//")) {
            return new Delimiters();
        }

        return extractCustomDelimiter(input);
    }

    /**
     * 커스텀 구분자를 추출하는 헬퍼 메서드
     *
     * @param input 입력 문자열
     * @return 커스텀 구분자를 포함한 Delimiters 객체
     * @throws IllegalArgumentException 구분자 형식이 잘못되었거나 음수 기호를 구분자로 사용한 경우
     */
    private static Delimiters extractCustomDelimiter(String input) {
        String delimiterPart = extractDelimiterPart(input);

        if (delimiterPart.length() < 3) {
            throw new IllegalArgumentException(ErrorMessage.MISSING_DELIMITER + input);
        }

        String customDelimiter = delimiterPart.substring(2);

        // 음수 기호(-)는 커스텀 구분자로 사용할 수 없음
        if ("-".equals(customDelimiter)) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_SIGN_DELIMITER_NOT_ALLOWED);
        }

        // 숫자는 커스텀 구분자로 사용할 수 없음
        if (customDelimiter.matches("\\d")) {
            throw new IllegalArgumentException(ErrorMessage.NUMERIC_DELIMITER_NOT_ALLOWED);
        }

        return new Delimiters(customDelimiter);
    }

    /**
     * 입력 문자열에서 구분자 부분을 추출하는 헬퍼 메서드
     * "//구분자\n" 부분을 분리하여 반환한다.
     *
     * @param input 입력 문자열
     * @return 구분자 부분 문자열
     * @throws IllegalArgumentException 구분자 형식이 잘못된 경우
     */
    private static String extractDelimiterPart(String input) {
        if (input.contains("\\n")) {
            return extractDelimiterWithEscapedNewline(input);
        }

        if (input.contains("\n")) {
            return extractDelimiterWithNewline(input);
        }

        throw new IllegalArgumentException(ErrorMessage.INVALID_DELIMITER_FORMAT + input);
    }

    /**
     * 이스케이프된 개행문자로 구분자를 추출하는 헬퍼 메서드
     *
     * @param input 입력 문자열
     * @return 구분자 부분 문자열
     * @throws IllegalArgumentException 구분자 형식이 잘못된 경우
     */
    private static String extractDelimiterWithEscapedNewline(String input) {
        String[] parts = input.split("\\\\n", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DELIMITER_FORMAT + input);
        }
        return parts[0];
    }

    /**
     * 개행문자로 구분자를 추출하는 헬퍼 메서드
     *
     * @param input 입력 문자열
     * @return 구분자 부분 문자열
     * @throws IllegalArgumentException 구분자 형식이 잘못된 경우
     */
    private static String extractDelimiterWithNewline(String input) {
        String[] parts = input.split("\n", 2);
        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DELIMITER_FORMAT + input);
        }
        return parts[0];
    }

    /**
     * 입력 문자열에서 숫자 부분을 추출한다.
     * 커스텀 구분자가 있으면 해당 부분을, 없으면 전체 문자열을 반환한다.
     *
     * @param input 숫자 부분을 추출할 입력 문자열
     * @return 추출된 숫자 부분 문자열
     */
    public static String extractNumbersPart(String input) {
        if (input == null || input.isEmpty()) {
            return "0";
        }

        if (!input.startsWith("//")) {
            return input;
        }

        // 커스텀 구분자에서 숫자 부분 추출
        return extractNumbersFromCustomDelimiter(input);
    }

    /**
     * 커스텀 구분자 형식에서 숫자 부분을 추출하는 헬퍼 메서드
     *
     * @param input 입력 문자열
     * @return 숫자 부분 문자열
     */
    private static String extractNumbersFromCustomDelimiter(String input) {
        if (input.contains("\\n")) {
            String[] parts = input.split("\\\\n", 2);
            return parts.length == 2 ? parts[1] : "0";
        }

        if (input.contains("\n")) {
            String[] parts = input.split("\n", 2);
            return parts.length == 2 ? parts[1] : "0";
        }

        return "0";
    }

    /**
     * 구분자를 사용하여 숫자 문자열을 분리한다.
     * 빈 토큰은 제거하고, 공백은 trim 처리한다.
     *
     * @param input      분리할 숫자 문자열
     * @param delimiters 사용할 구분자 정보
     * @return 분리된 숫자 문자열들의 리스트
     */
    public static List<String> splitNumbers(String input, Delimiters delimiters) {
        if (input == null || input.isEmpty()) {
            return List.of("0");
        }

        String delimiterPattern = delimiters.delimiterPattern();
        String[] tokens = input.split(delimiterPattern);
        List<String> result = new ArrayList<>();

        for (String token : tokens) {
            String trimmedToken = token.trim();
            if (!trimmedToken.isEmpty()) {
                result.add(trimmedToken);
            }
        }

        return result.isEmpty() ? List.of("0") : result;
    }
}
