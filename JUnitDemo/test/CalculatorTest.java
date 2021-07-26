import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private static Calculator calculator;

    // initialisiert calculator und meldet den Start der Tests
    @BeforeAll
    public static void start(){
        calculator = new Calculator();
        System.out.println("started testing...");
    }

    // meldet das Ende der Tests
    @AfterAll
    public static void finish(){
        System.out.println("ended testing...");
    }

    // vergisst das Ergebnis der letzten Operation und meldet den Wert
    @BeforeEach
    public void preparation(){
        System.out.println("forgot: " + calculator.memory);
        calculator.forget();
    }

    // merkt sich das Ergebnis der letzten Operation und meldet den Wert
    @AfterEach
    public void finalization(){
        calculator.memorize();
        System.out.println("remembered: " + calculator.memory);
    }


    @Test
    @DisplayName("Subtraction Test")
    public void subTest(){
        assertEquals(0,calculator.sub(1,1));
    }


    @ParameterizedTest(name = "result of {0} x {1} should be {2}")
    @MethodSource("createMulArgs")
    public void mulTest(double a, double b, double expectedAnswer) {
        assertEquals(expectedAnswer,calculator.mul(a,b));
        assertEquals(expectedAnswer, calculator.ans);
    }

    // stellt die Parameter fuer den parametrisierten Test mulTest bereit
    private static Stream<Arguments> createMulArgs() {
        return Stream.of(
                Arguments.of(5, 0, 0),
                Arguments.of(7, 4, 28),
                Arguments.of(3, 2, 6)
        );
    }


    @RepeatedTest(5)
    public void addTest() {
        assertEquals(2,calculator.add(1,1));
        assertEquals(2, calculator.ans);
    }


    // asserting exception
    @Test
    public void divTest() throws Exception {
        assertThrows(ArithmeticException.class, () -> { calculator.div(0,0); });
        assertThrows(ArithmeticException.class, () -> { calculator.div(1,0); });
        assertEquals(1,calculator.div(1,1));
    }


    // gutes Beispiel für schlechtes Design
    @Test
    @Disabled("fails due to design flaws")
    public void powTest(){
        // korrekt
        assertEquals(1448.15,calculator.pow(2,10.5), 0.01);
        // falsches Ergebnis, negative Exponente nicht betrachtet im Code
        assertEquals(0.5, calculator.pow(2,-1));
    }


    // gutes Beispiel für einen White-Box Test
    @Test
    @Tag("slow")
    public void log2Test(){
        // typische Fälle
        assertEquals(3,calculator.log2(8));
        assertEquals(3, calculator.ans);
        assertEquals(10, calculator.log2(1024));
        assertEquals(10, calculator.ans);
        // Randfälle
        assertEquals(0, calculator.log2(1));
        assertEquals(0, calculator.ans);
        assertThrows(ArithmeticException.class, () -> calculator.log2(0));
        assertThrows(ArithmeticException.class, () -> calculator.log2(-8));
    }


    @Test
    @Tag("fast")
    @Timeout(value = 5, unit = TimeUnit.MILLISECONDS)
    public void fastFibTest(){
        assertEquals(832040, calculator.fastFib(30));
        assertEquals(832040, calculator.ans);
        // sehr schnell
        assertEquals(102334155, calculator.fastFib(40));
        assertEquals(102334155, calculator.ans);
    }


    // gutes Beispiel für einen Black-Box Test
    @Test
    @Tag("very_slow")
    public void fibTest(){
        // oder assertTimeoutPreemptively nutzen
        assertTimeout(Duration.ofMillis(10),() -> {
            assertEquals(832040, calculator.fib(30));
            assertEquals(832040, calculator.ans);
            // sehr langsam
            assertEquals(102334155, calculator.fib(40));
            assertEquals(102334155, calculator.ans);
        });
    }

}

