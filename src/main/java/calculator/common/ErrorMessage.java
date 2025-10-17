package calculator.common;

/**
 * 계산기 애플리케이션에서 사용되는 에러 메시지 상수들을 정의하는 클래스
 */
public class ErrorMessage {
    /**
     * 음수 입력 시 사용되는 에러 메시지
     */
    public static final String NEGATIVE_NUMBER = "양수만 입력 가능합니다: ";

    /**
     * 정수가 아닌 값(소수, 문자 등) 입력 시 사용되는 에러 메시지
     */
    public static final String INVALID_NUMBER = "정수만 입력 가능합니다: ";

    /**
     * 정수 범위를 초과하는 값 입력 시 사용되는 에러 메시지
     */
    public static final String INTEGER_OVERFLOW = "정수 범위를 초과했습니다.";

    /**
     * 잘못된 구분자 형식 입력 시 사용되는 에러 메시지
     */
    public static final String INVALID_DELIMITER_FORMAT = "잘못된 구분자 형식입니다: ";

    /**
     * 커스텀 구분자가 지정되지 않은 경우 사용되는 에러 메시지
     */
    public static final String MISSING_DELIMITER = "구분자가 지정되지 않았습니다: ";

    /**
     * 음수 기호(-)를 커스텀 구분자로 사용할 때 사용되는 에러 메시지
     */
    public static final String NEGATIVE_SIGN_DELIMITER_NOT_ALLOWED = "음수 기호(-)는 커스텀 구분자로 사용할 수 없습니다.";

    /**
     * 유틸리티 클래스이므로 인스턴스 생성을 방지하기 위한 private 생성자
     */
    private ErrorMessage() {
    }
}

