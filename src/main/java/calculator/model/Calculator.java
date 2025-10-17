package calculator.model;

/**
 * 계산기 도메인 클래스 Numbers 객체를 받아 합계를 계산하는 역할을 담당한다.
 */
public class Calculator {
    /**
     * 계산할 숫자들을 담고 있는 Numbers 객체
     */
    private final Numbers numbers;

    /**
     * Calculator 생성자
     *
     * @param numbers 계산할 숫자들을 담고 있는 Numbers 객체
     */
    public Calculator(Numbers numbers) {
        this.numbers = numbers;
    }

    /**
     * 숫자들의 합계를 계산한다
     *
     * @return 모든 숫자들의 합계
     */
    public int calculate() {
        return numbers.sum();
    }
}
