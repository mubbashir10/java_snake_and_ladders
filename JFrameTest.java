//importing libraries
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

//fileTest
public class JFrameTest{

    @Test
    public void unitTest() {

        //making object
        SnakeMania s = new SnakeMania();

        //testing
        assertEquals(true, s.checkPlayers(4));

    }
}