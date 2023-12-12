import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void TestCreateEmptyGrid() {
        char[][] actualResult = new char[3][3];
        Main.createEmptyGrid(actualResult);
        char[][] expectedResult = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void TestCheckNumbers_whenHigherThanThree() {
        Main.checkNumbers(5, 5);
        String expectedResult = "Coordinates should be from 1 to 3!";
        assertEquals(expectedResult, outputStreamCaptor.toString()
                .trim());
    }
    @Test
    void TestCheckNumbers_whenLessThanOne() {
        Main.checkNumbers(0, 0);
        String expectedResult = "Coordinates should be from 1 to 3!";
        assertEquals(expectedResult, outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void TestIsPlaceIsEmpty_whenItIsOccupied() {
        char[][] state =  { { 'X', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
        Main.isPlaceIsEmpty(state, 1, 1);
        String expectedResult = "This cell is occupied! Choose another one!";
        assertEquals(expectedResult, outputStreamCaptor.toString()
                .trim());
    }

    @Test
    void TestUpdateGrid_whenDraw() {
        char[][] state =  { { 'X', 'O', 'X' }, { 'X', 'X', 'O' }, { 'O', 'X', 'O' } };
        Main.checkGrid(state);
        String expectedResult = "Draw";
        assertEquals(expectedResult, outputStreamCaptor.toString()
                .trim());
    }
    @Test
    void TestUpdateGrid_whenXWins() {
        char[][] state =  { { 'X', 'O', 'X' }, { 'O', 'X', 'O' }, { 'O', 'X', 'X' } };
        Main.checkGrid(state);
        String expectedResult = "X wins";
        assertEquals(expectedResult, outputStreamCaptor.toString()
                .trim());
    }
    @Test
    void TestUpdateGrid_whenOWins() {
        char[][] state =  { { 'X', 'O', 'X' }, { 'O', 'O', 'O' }, { 'O', 'X', 'X' } };
        Main.checkGrid(state);
        String expectedResult = "O wins";
        assertEquals(expectedResult, outputStreamCaptor.toString()
                .trim());
    }
    @Test
    void TestUpdateGrid_whenItHasPlaces() {
        char[][] state =  { { 'X', 'O', 'X' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
        boolean actualResult = Main.checkGrid(state);
        assertTrue(actualResult);
    }
}