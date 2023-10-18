package edu.hw2.task4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CallingInfoTest {

    @Test
    void callingInfo() {
        CallingInfo callingInfo = CallingInfo.callingInfo();
        Assertions.assertThat(callingInfo.className()).isEqualTo(this.getClass().getName());
        Assertions.assertThat(callingInfo.methodName()).isEqualTo("callingInfo");
    }
}
