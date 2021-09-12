package reading;

import dto.State;
import exceptions.WrongFormatException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

public class ReaderTest {

    @Test(
            description = "Проверка обработки инвалидных карт и путей.",
            dataProvider = "invalid_paths"
    )
    public void shouldValidateInput(String path, Class<? extends Exception> exceptionType, int expectedSize) throws Exception {
        State state = null;

        try {
            state = Reader.readInput(path);
        } catch (Exception e) {
            Assert.assertEquals(e.getClass(), exceptionType);
            return;
        }
        if (exceptionType != null) {
            throw new Exception("Валидация пропустила невалидный тест.");
        }
        Assert.assertEquals(state.matrix.length, expectedSize);
    }


    @DataProvider(name = "invalid_paths")
    public Object[][] getInvalidPath() {
        return new Object[][]{
                {"invalid_path.txt", FileNotFoundException.class, 0},
                {"src/test/resources/maps/invalid/empty_npuzzle-3-3.txt", WrongFormatException.class, 0},
                {"src/test/resources/maps/invalid/wrong_format_npuzzle-3-1.txt", WrongFormatException.class, 0},
                {"src/test/resources/maps/invalid/wrong_format_npuzzle-3-2.txt", WrongFormatException.class, 0},
                {"src/test/resources/maps/invalid/wrong_format_npuzzle-3-3.txt", WrongFormatException.class, 0},
//                {"src/test/resources/maps/invalid/wrong_cell_value_npuzzle-3.txt", WrongFormatException.class, 0},
                {"src/test/resources/maps/valid/npuzzle-3-1.txt", null, 3},
                {"src/test/resources/maps/valid/npuzzle-3-2.txt", null, 3},
                {"src/test/resources/maps/valid/npuzzle-3-3.txt", null, 3}
        };
    }
}