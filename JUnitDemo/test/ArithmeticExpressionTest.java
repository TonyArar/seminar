import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class ArithmeticExpressionTest {

    // (1 + (2 + 2))
    ArithmeticExpression testExpression_0 = new Addition(new Number(1), new Addition(new Number(2), new Number(2)));
    // (2 + (2 + 2))
    ArithmeticExpression testExpression_1 = new Addition(new Number(2), new Addition(new Number(2), new Number(2)));

    // flache Listen der Baeume der erwarteten Ergebnisse von evaluate()
    List<Integer> expecteds_0 = List.of(5, 1, 4, 2, 2);
    List<Integer> expecteds_1 = List.of(5, 1, 4, 2, 2);

    // aktuelle Position im Baum/in der Liste
    int currentIndex = 0;

    // der hierarchische Test
    @TestFactory
    public Stream<DynamicNode> test(){
        DynamicNode dn_0 = buildTestTree(testExpression_0, expecteds_0);
        // Position zuruecksetzen
        currentIndex = 0;
        DynamicNode dn_1 = buildTestTree(testExpression_1, expecteds_1);
        return Stream.of(dn_0,dn_1);
    }

    // baut den test Baum
    public DynamicNode buildTestTree(ArithmeticExpression ae, List<Integer> expecteds) {
        int expected = expecteds.get(currentIndex++);
        if (ae.getClass() == Addition.class) {
            return DynamicContainer.dynamicContainer("addition",
                    Stream.of(DynamicTest.dynamicTest("result should be " + expected, () -> {
                                int actual = ae.evaluate();
                                assertEquals(expected, actual);
                            }),
                            buildTestTree(((Addition) ae).getExp1(), expecteds),
                            buildTestTree(((Addition) ae).getExp2(), expecteds)));
        }
        return DynamicTest.dynamicTest("number should be " + expected, () -> {
            int actual = ae.evaluate();
            assertEquals(expected, actual);
        });
    }

}