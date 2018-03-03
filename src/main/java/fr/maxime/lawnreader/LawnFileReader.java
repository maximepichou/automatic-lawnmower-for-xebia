package fr.maxime.lawnreader;

import fr.maxime.lawnmower.*;
import fr.maxime.lawnmower.motioncontrol.MotionControl;
import fr.maxime.lawnmower.motioncontrol.motion.Movement;
import fr.maxime.lawnmower.motioncontrol.motion.Orientation;
import fr.maxime.lawnmower.motioncontrol.motion.Rotation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LawnFileReader {

    private static final String ERROR_MALFORMED_MESSAGE = "The input file is malformed";

    private LawnFileReader() {
    }

    /**
     * Read a file, construct a lawn and return it.
     * @param filePath the file path where there is the instruction to construct a Lawn.
     * @return a Lawn instance based on the file.
     * @throws FileNotFoundException if the file has been found.
     */
    public static Lawn read(String filePath) throws FileNotFoundException{
        Lawn lawn;
        try(Scanner in = new Scanner(new FileReader(filePath))) {
            Lawn.Builder lawnBuilder = constructLawn(in);
            lawn = constructControllableMowers(in, lawnBuilder).build();
        }catch (InputMismatchException ime){
            throw new IllegalArgumentException(ERROR_MALFORMED_MESSAGE);
        }
        return lawn;
    }

    /**
     * Construct the first part of the lawn
     * @param in the scanner of the file to construct
     * @return an instance of the Lawn.Builder to continue the construction
     */
    private static Lawn.Builder constructLawn(Scanner in) {
        int maxX = in.nextInt();
        int maxY = in.nextInt();
        if(!in.nextLine().isEmpty()){
            throw new IllegalArgumentException(ERROR_MALFORMED_MESSAGE);
        }
        return Lawn.builder().withMaxX(maxX).withMaxY(maxY);
    }

    /**
     * Construct the list of all mowers.
     * @param in the scanner of the file to construct.
     * @param lawnBuilder the instance of a Lawn.Builder where to put the list of mowers
     * @return an instance of a Lawn.Builder to continue the construction.
     */
    private static Lawn.Builder constructControllableMowers(Scanner in, Lawn.Builder lawnBuilder) {
        while(in.hasNextLine()){
            int initialX = in.nextInt();
            int initialY = in.nextInt();
            Orientation initialOrientation = Orientation.valueOf(in.next());
            if(!in.nextLine().isEmpty()){
                throw new IllegalArgumentException(ERROR_MALFORMED_MESSAGE);
            }
            ControllableMower.Builder controllableMowerBuilder = lawnBuilder.withControllableMower()
                    .withInitialPosition().x(initialX).y(initialY).done()
                    .withInitialOrientation(initialOrientation);
            constructMotionControllerList(in, controllableMowerBuilder);

        }
        return lawnBuilder;
    }

    /**
     * Construct a list of motion control in a builder.
     * @param in the scanner of the file to construct.
     * @param controllableMowerBuilder the Builder where to put the motion control list
     */
    private static void constructMotionControllerList(Scanner in, ControllableMower.Builder controllableMowerBuilder) {
        if(in.hasNextLine()) {
            String motionControls = in.next();
            String[] motionControlArray = motionControls.split("");
            for(String motionControlString : motionControlArray){
                constructMotionControl(controllableMowerBuilder, motionControlString);
            }
            controllableMowerBuilder.done();
        }
    }

    /**
     * Construct one motion control based on the representation of a string
     * @param controllableMowerBuilder the Builder where to put this motion control
     * @param motionControlString the representation of the motion control in string
     */
    private static void constructMotionControl(ControllableMower.Builder controllableMowerBuilder, String motionControlString) {
        MotionControl motionControl;

        if(Arrays.stream(Movement.values()).anyMatch(x -> x.name().equals(motionControlString))){
            motionControl = Movement.valueOf(motionControlString);
        }
        else if(Arrays.stream(Rotation.values()).anyMatch(x -> x.name().equals(motionControlString))){
            motionControl = Rotation.valueOf(motionControlString);
        }
        else{
            throw new IllegalArgumentException(ERROR_MALFORMED_MESSAGE);
        }
        controllableMowerBuilder.withMotionController()
                .withMotionControl(motionControl).done();
    }

}
