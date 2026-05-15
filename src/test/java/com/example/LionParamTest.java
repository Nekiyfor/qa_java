package com.example;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LionParamTest {

    @Spy
    Feline spyFeline = new Feline();

    @ParameterizedTest
    @CsvSource({"2", "3", "5"})
    void testGetKittensReturnsExpectedKittensCount(int kittens) throws Exception {
        Mockito.when(spyFeline.getKittens()).thenReturn(kittens);
        Lion lion = new Lion("Самец", spyFeline);
        assertEquals(kittens, lion.getKittens());
    }

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void testDoesHaveManeReturnsExpectedValue(String sex, boolean expectedHasMane) throws Exception {
        Lion lion = new Lion(sex, spyFeline);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

}
