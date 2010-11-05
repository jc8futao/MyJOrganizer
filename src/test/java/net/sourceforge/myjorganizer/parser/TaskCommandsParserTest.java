package net.sourceforge.myjorganizer.parser;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

import net.sourceforge.myjorganizer.parser.syntaxtree.TaskInsertCommand;

import org.junit.Test;

public class TaskCommandsParserTest {
    @Test
    public void testSimpleTask() throws FileNotFoundException, ParseException {
        FileReader reader = new FileReader("src/test/java/testsource1.mjo");
        TaskCommandsParser parser = new TaskCommandsParser(reader);

        TaskInsertCommand command = parser.TaskInsertCommand();
        
        assertEquals("insert", command.f0.tokenImage);
        assertEquals("task", command.f1.f0.tokenImage);
        assertEquals("test1", command.f1.f1.tokenImage);
        assertEquals("Test task #1", command.f1.f4.f2.tokenImage);
    }
}
