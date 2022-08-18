package by.bhl.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PersonalComputerTest
{

    private PersonalComputer personalComputer = new PersonalComputer();

    @BeforeEach
    public void resetObject() {
        personalComputer.setId(0l);
        personalComputer.setName("");
        personalComputer.setLevel(0);
        personalComputer.setStatus("0");
    }

    @Test
    public void getIdTest()
    {
        personalComputer.setId(15l);
        assertTrue(personalComputer.getId() == 15l);
    }

    @Test
    public void levelFullTest() {
        personalComputer.setLevel(22);
        assertTrue(personalComputer.getLevel().equals(22));
        personalComputer.setLevel(-5);
        assertTrue(personalComputer.getLevel().equals(0));
        personalComputer.setLevel(129);
        assertTrue(personalComputer.getLevel().equals(100));
    }

    @Test
    public void assertsTest() {
        int a = 17;
        assertEquals(17, a);
        assertNotEquals(18, a);
    }

    @Test
    public void nullTest() {
        Integer i = 0;
        assertNotNull(i);

        if(i == 1) {
            fail("0 value for i is also not applicable!");
        }
    }

    @Test
    public void berforeEachTest() {
        System.out.println(personalComputer);
        assertTrue(personalComputer.getLevel().equals(0));
    }

}
