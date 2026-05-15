package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FelineTest {

    @Mock
    private Animal mockAnimal;

    @Test
    void testEatMeatReturnCorrectFoodList() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        Mockito.when(mockAnimal.getFood("Хищник")).thenReturn(expectedFood);

        Feline feline = new Feline() {
            @Override
            public String getFamily() {
                return "Кошачьи";
            }

            @Override
            public List<String> getFood(String animalKind) throws Exception {
                return mockAnimal.getFood(animalKind);
            }
        };


        Mockito.when(feline.getFood("Хищник")).thenReturn(expectedFood);

        List<String> actualFood = feline.eatMeat();

        assertEquals(expectedFood, actualFood);
    }

    @Test
    void testEatMeatWithThrowsException() throws Exception {

        Mockito.when(mockAnimal.getFood("Хищник")).thenThrow(new Exception("Неизвестный вид животного"));

        Feline feline = new Feline() {
            @Override
            public String getFamily() {
                return "Кошачьи";
            }

            @Override
            public List<String> getFood(String animalKind) throws Exception {
                return mockAnimal.getFood(animalKind);
            }
        };

        Exception exception = assertThrows(Exception.class, () -> feline.eatMeat());

        assertEquals("Неизвестный вид животного", exception.getMessage());
    }

    @Spy
    Feline spyFeline;

    @Test
    void testGetFamilyReturnsCorrectValue() {
        assertEquals("Кошачьи", spyFeline.getFamily());

    }
}