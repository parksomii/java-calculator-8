package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("빈 문자열은 0을 반환한다")
    void 빈_문자열_처리() {
        assertSimpleTest(() -> {
            run(" ");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    @DisplayName("기본 구분자로 쉼표를 사용한다")
    void 기본_구분자_쉼표() {
        assertSimpleTest(() -> {
            run("1,2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    @DisplayName("기본 구분자로 콜론을 사용한다")
    void 기본_구분자_콜론() {
        assertSimpleTest(() -> {
            run("1:2");
            assertThat(output()).contains("결과 : 3");
        });
    }

    @Test
    @DisplayName("기본 구분자로 쉼표와 콜론을 혼용한다")
    void 기본_구분자_혼용() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    @DisplayName("빈 값은 무시한다")
    void 빈_값_무시() {
        assertSimpleTest(() -> {
            run("1,,3");
            assertThat(output()).contains("결과 : 4");
        });
    }

    @Test
    @DisplayName("공백이 있는 값은 trim 처리한다")
    void 공백_trim_처리() {
        assertSimpleTest(() -> {
            run(" 1 , 2 , 3 ");
            assertThat(output()).contains("결과 : 6");
        });
    }
    
    @Test
    @DisplayName("0은 예외를 발생시킨다")
    void 영_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("0,1,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("숫자가 아닌 값은 예외를 발생시킨다")
    void 숫자_아닌_값_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,a,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("정수 범위를 초과하는 값은 예외를 발생시킨다")
    void 정수_범위_초과() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("9999999999999,1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("소수점이 포함된 값은 예외를 발생시킨다")
    void 소수점_포함_예외() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1.5,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
 
    @Test
    @DisplayName("점을 커스텀 구분자로 사용할 수 있다")
    void 점_커스텀_구분자() {
        assertSimpleTest(() -> {
            run("//.\\n1.2.3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    @DisplayName("다른 특수문자도 커스텀 구분자로 사용할 수 있다")
    void 특수문자_커스텀_구분자() {
        assertSimpleTest(() -> {
            run("//@\\n1@2@3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    @DisplayName("다중 문자 커스텀 구분자를 사용할 수 있다")
    void 다중_문자_커스텀_구분자() {
        assertSimpleTest(() -> {
            run("//ab\\n1ab2ab3");
            assertThat(output()).contains("결과 : 6");
        });
    }

    @Test
    @DisplayName("음수 기호를 커스텀 구분자로 사용할 수 없다")
    void 음수_기호_커스텀_구분자_금지() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//-\\n-1-2-3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }
    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
