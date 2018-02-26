package fr.maxime.lawnmower;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LawnTest {

    @Test
    public void example1Test(){
        // Given
        Lawn lawn = Lawn.builder().withMaxX(5).withMaxY(5)
                .withControllableMower()
                    .withInitialPosition().x(1).y(2).done()
                    .withInitialOrientation(Orientation.N)
                    .withMotionController().withMotionControl(Rotation.G).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.G).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.G).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.G).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                .done()
                .build();



        // Execute
        List<Maneuver> maneuverList = lawn.performFinalPositionOfControllableMower();


        // Verify
        List<Maneuver> expectedManeuverList = new ArrayList<>();
        expectedManeuverList.add(new Maneuver(new Position(1, 3), Orientation.N));

        assertEquals(expectedManeuverList, maneuverList);
    }

    @Test
    public void example2Test(){

        // Given
        Lawn lawn = Lawn.builder().withMaxX(5).withMaxY(5)
                .withControllableMower()
                    .withInitialPosition().x(3).y(3).done()
                    .withInitialOrientation(Orientation.E)
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                .done()
                .build();


        // Execute
        List<Maneuver> maneuverList = lawn.performFinalPositionOfControllableMower();


        // Verify
        List<Maneuver> expectedManeuverList = new ArrayList<>();
        expectedManeuverList.add(new Maneuver(new Position(5, 1), Orientation.E));

        assertEquals(expectedManeuverList, maneuverList);
    }

    @Test
    public void example3Test(){

        // Given
        Lawn lawn = Lawn.builder()
                .withMaxX(3).withMaxY(3)
                .withControllableMower()
                    .withInitialOrientation(Orientation.E)
                    .withInitialPosition().x(0).y(3).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                    .withMotionController().withMotionControl(Rotation.D).done()
                    .withMotionController().withMotionControl(Movement.A).done()
                .done()
                .build();

        // Execute
        List<Maneuver> maneuverList = lawn.performFinalPositionOfControllableMower();


        // Verify
        List<Maneuver> expectedManeuverList = new ArrayList<>();
        expectedManeuverList.add(new Maneuver(new Position(1, 1), Orientation.W));

        assertEquals(expectedManeuverList, maneuverList);
    }

}


