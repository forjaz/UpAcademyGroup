package io.altar.upacademy.dto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class IngredienteActualTest {

    @Test
    public void testRemoveReceitaIDDuplicatesWithIntegerList() {
        // Class tested
        Global tester = new Global();

        // Setup
        List<Long> integerList = new ArrayList<>();
        integerList.add(1L);
        integerList.add(1L);
        integerList.add(1L);
        integerList.add(1L);
        integerList.add(2L);
        integerList.add(3L);
        List<Long> resultList = new ArrayList<>();
        resultList.add(1L);
        resultList.add(2L);
        resultList.add(3L);

        // assert statements
        assertEquals("Long [1,1,1,1,2,3] must return Long [1,2,3]", resultList, tester.removeReceitaIDDuplicates(integerList));
    }
}
