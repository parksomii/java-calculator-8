package calculator.controller;

import calculator.service.CalculatorService;
import calculator.view.ErrorView;
import calculator.view.InputView;
import calculator.view.OutputView;

/**
 * 계산기 애플리케이션의 컨트롤러 클래스
 */
public class CalculatorController {
    /**
     * 계산 서비스를 담당하는 객체
     */
    private final CalculatorService calculatorService;

    /**
     * CalculatorController 생성자 계산 서비스 인스턴스를 초기화한다.
     */
    public CalculatorController() {
        this.calculatorService = new CalculatorService();
    }

    /**
     * 계산기 애플리케이션의 메인 실행 메서드 사용자 입력을 받아 계산을 수행하고 결과를 출력한다.
     * 예외 발생 시 에러 메시지를 출력하고 예외를 다시 던진다.
     */
    public void run() {
        try {
            String input = InputView.readInput();
            int result = calculatorService.calculate(input);
            OutputView.printResult(result);
        } catch (IllegalArgumentException e) {
            ErrorView.printError(e.getMessage());
            throw e;
        }
    }
}