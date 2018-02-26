package fr.maxime.reader;

import fr.maxime.lawnmower.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class LawnFileReader {

    public static Lawn read(String filePath){
        Lawn lawn = null;
        try(Scanner in = new Scanner(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            Lawn.Builder lawnBuilder = constructLawn(in);
            lawn = constructControllableMowers(in, lawnBuilder).build();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lawn;
    }

    private static Lawn.Builder constructLawn(Scanner in){
        int maxX = in.nextInt();
        int maxY = in.nextInt();
        return Lawn.builder().withMaxX(maxX).withMaxY(maxY);
    }

    private static Lawn.Builder constructControllableMowers(Scanner in, Lawn.Builder lawnBuilder){
        while(in.hasNextLine()){
            int initialX = in.nextInt();
            int initialY = in.nextInt();
            Orientation initialOrientation = Orientation.valueOf(in.next());
            ControllableMower.Builder controllableMowerBuilder = lawnBuilder.withControllableMower()
                    .withInitialPosition()
                    .x(initialX)
                    .y(initialY)
                    .done()
                    .withInitialOrientation(initialOrientation);
            if(in.hasNextLine()) {
                String motionControls = in.next();
                String[] motionControlArray = motionControls.split("");
                for(String motionControlString : motionControlArray){
                    MotionControl motionControl;

                    if(Arrays.stream(Movement.values()).anyMatch(x -> x.name().equals(motionControlString))){
                        motionControl = Movement.valueOf(motionControlString);
                    }
                    else if(Arrays.stream(Rotation.values()).anyMatch(x -> x.name().equals(motionControlString))){
                        motionControl = Rotation.valueOf(motionControlString);
                    }
                    else{
                        throw new IllegalStateException("Can't find any motion control to associate");
                    }
                    controllableMowerBuilder.withMotionController()
                            .withMotionControl(motionControl).done();

                }
                controllableMowerBuilder.done();
            }

        }
        return lawnBuilder;
    }

}
