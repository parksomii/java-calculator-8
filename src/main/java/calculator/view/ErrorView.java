package calculator.view;

/**
 * 에러 메시지를 출력하는 뷰 클래스
 * 에러 메시지의 일관된 형식을 제공한다.
 */
public class ErrorView {
    /**
     * 에러 메시지 접두사
     */
    public static final String ERROR_MESSAGE = "[ERROR] ";

    /**
     * 에러 메시지를 출력한다
     * 
     * @param message 출력할 에러 메시지
     */
    public static void printError(String message) {
        System.out.println(message);
    }

    private ErrorView() {
        // 유틸리티 클래스
    }
}

