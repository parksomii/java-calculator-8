package calculator.model;

import java.util.regex.Pattern;

/**
 * 구분자를 관리하는 도메인 클래스
 */
public class Delimiters {
    /**
     * 기본 구분자 패턴 (쉼표와 콜론)
     */
    private static final String DEFAULT_DELIMITERS = "[,:]";

    /**
     * 현재 사용할 구분자 패턴
     */
    private final String delimiterPattern;

    /**
     * 기본 생성자 기본 구분자(쉼표, 콜론)를 사용하도록 설정한다.
     */
    public Delimiters() {
        this.delimiterPattern = DEFAULT_DELIMITERS;
    }

    /**
     * 커스텀 구분자를 사용하는 생성자
     *
     * @param customDelimiter 사용할 커스텀 구분자
     */
    public Delimiters(String customDelimiter) {
        this.delimiterPattern = Pattern.quote(customDelimiter);
    }

    /**
     * 현재 설정된 구분자 패턴을 반환한다
     *
     * @return 구분자 정규식 패턴
     */
    public String delimiterPattern() {
        return delimiterPattern;
    }
}