package com.example;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LionParamTest {

    @Mock
    Feline mockFeline;


    @ParameterizedTest
    @CsvSource({"2", "3", "5"})
    void testGetKittensReturnsExpectedKittensCount(int kittens) throws Exception {
        Mockito.doReturn(kittens).when(mockFeline).getKittens();
        Lion lion = new Lion("Самец", mockFeline);
        assertEquals(kittens, lion.getKittens());
    }

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void testDoesHaveManeReturnsExpectedValue(String sex, boolean expectedHasMane) throws Exception {
        Lion lion = new Lion(sex, mockFeline);
        assertEquals(expectedHasMane, lion.doesHaveMane());
    }

}
