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
    public static void printResult(int result) {
        System.out.println("결과 : " + result);
    }

    /**
     * 유틸리티 클래스이므로 인스턴스 생성을 방지하기 위한 private 생성자
     */
    private OutputView() {
    }
}

