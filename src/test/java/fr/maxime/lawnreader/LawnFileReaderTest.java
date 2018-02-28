package fr.maxime.lawnreader;

import fr.maxime.lawnmower.Lawn;
import fr.maxime.lawnmower.motioncontrol.motion.Movement;
import fr.maxime.lawnmower.motioncontrol.motion.Orientation;
import fr.maxime.lawnmower.motioncontrol.motion.Rotation;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class LawnFileReaderTest {

    @Test
    public void fileInputOKTest() throws FileNotFoundException{
        String fileName = "inputOK1.txt";
        URL path = this.getClass().getClassLoader().getResource(fileName);
        if(path == null){
            throw new IllegalStateException("Missing file ["+ fileName + "] in resources directory");
        }
        Lawn lawn = LawnFileReader.read(path.getPath());

        Lawn expectedLawn = Lawn.builder().withMaxX(5).withMaxY(4)
                .withControllableMower().withInitialPosition().x(1).y(3).done()
                .withInitialOrientation(Orientation.N)
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Rotation.D).done()
                .withMotionController().withMotionControl(Rotation.G).done()
                .done()
                .withControllableMower().withInitialPosition().x(1).y(2).done()
                .withInitialOrientation(Orientation.W)
                .withMotionController().withMotionControl(Rotation.G).done()
                .withMotionController().withMotionControl(Rotation.D).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .done()
                .build();
        assertEquals(expectedLawn, lawn);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fileInputBadOrientationTest() throws FileNotFoundException{
        String fileName = "inputBadOrientation.txt";
        testInputFileRead(fileName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fileInputBadFirstLineTest() throws FileNotFoundException{
        String fileName = "inputBadFirstLine.txt";
        testInputFileRead(fileName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fileInputBadMotionControlLineTest() throws FileNotFoundException{
        String fileName = "inputBadMotionControl.txt";
        testInputFileRead(fileName);
    }

    private void testInputFileRead(String fileName) throws FileNotFoundException{
        URL path = this.getClass().getClassLoader().getResource(fileName);
        if(path == null){
            throw new IllegalStateException("Missing file ["+ fileName + "] in resources directory");
        }

        LawnFileReader.read(path.getPath());
    }
}
