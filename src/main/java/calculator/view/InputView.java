package calculator.view;

import camp.nextstep.edu.missionutils.Console;

/**
 * 사용자 입력을 처리하는 뷰 클래스
 * 계산기에 필요한 입력을 받는 기능을 제공한다.
 */
public class InputView {

    /**
     * 사용자로부터 덧셈할 문자열을 입력받는다
     *
     * @return 사용자가 입력한 문자열
     */
    public static String readInput() {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        return Console.readLine();
    }

    /**
     * 유틸리티 클래스이므로 인스턴스 생성을 방지하기 위한 private 생성자
     */
    private InputView() {
    }
}