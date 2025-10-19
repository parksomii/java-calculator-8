package calculator.view;

/**
 * 계산 결과를 출력하는 뷰 클래스
 * 계산 결과를 사용자에게 표시하는 기능을 제공한다.
 */
public class OutputView {

    /**
     * 계산 결과를 출력한다
     * 
     * @param result 출력할 계산 결과
     */
    public static void printResult(double result) {
        // 정수인 경우 소수점 없이 출력, 실수인 경우 소수점 포함 출력
        if (result == (int) result) {
            System.out.println("결과 : " + (int) result);
        } else {
            System.out.println("결과 : " + result);
        }
    }

    /**
     * 유틸리티 클래스이므로 인스턴스 생성을 방지하기 위한 private 생성자
     */
    private OutputView() {
    }
}

