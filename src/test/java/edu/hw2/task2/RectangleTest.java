package edu.hw2.task2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RectangleTest {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect = rect.setWidth(20);
        rect = rect.setHeight(10);
        assertThat(rect.area()).isEqualTo(200.0);

        rect = rect.setHeight(30);
        rect = rect.setWidth(40);
        assertThat(rect.area()).isEqualTo(1200.0);

        rect = rect.setHeight(40);
        rect = rect.setWidth(30);
        rect = rect.setHeight(30);
        assertThat(rect.area()).isEqualTo(900.0);
    }

    @Test
    void instanceOfTests() {
        Square square = new Square();
        assertThat(square).isInstanceOf(Square.class);
        assertThat(square.setSides(10, 10)).isInstanceOf(Square.class);
        assertThat(square.setSides(20, 10)).isNotInstanceOf(Square.class);

        Rectangle rectangle = new Rectangle();
        assertThat(rectangle).isInstanceOf(Rectangle.class);
        assertThat(rectangle.setSides(10, 20)).isInstanceOf(Rectangle.class);
        assertThat(rectangle.setSides(20, 10)).isInstanceOf(Rectangle.class);
    }
}
