package fr.maxime.lawnmower;


import fr.maxime.lawnmower.service.LawnService;
import fr.maxime.lawnmower.motioncontrol.*;
import fr.maxime.lawnmower.motioncontrol.motion.Movement;
import fr.maxime.lawnmower.motioncontrol.motion.Orientation;
import fr.maxime.lawnmower.motioncontrol.motion.Position;
import fr.maxime.lawnmower.motioncontrol.motion.Rotation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LawnTest {

    // Please see the associate image example1.png (in resources/img_for_test_example folder) to understand this test
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
        LawnService lawnService = new LawnService(lawn);
        List<Maneuver> maneuverList = lawnService.moveMowersAndGetFinalPosition();


        // Verify
        List<Maneuver> expectedManeuverList = new ArrayList<>();
        expectedManeuverList.add(new Maneuver(new Position(1, 3), Orientation.N));

        assertEquals(expectedManeuverList, maneuverList);
    }


    // Please see the associate image example2.png (in resources/img_for_test_example folder) to understand this test
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
        LawnService lawnService = new LawnService(lawn);
        List<Maneuver> maneuverList = lawnService.moveMowersAndGetFinalPosition();


        // Verify
        List<Maneuver> expectedManeuverList = new ArrayList<>();
        expectedManeuverList.add(new Maneuver(new Position(5, 1), Orientation.E));

        assertEquals(expectedManeuverList, maneuverList);
    }


    // Please see the associate image example3.png (in resources/img_for_test_example folder) to understand this test
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
        LawnService lawnService = new LawnService(lawn);
        List<Maneuver> maneuverList = lawnService.moveMowersAndGetFinalPosition();


        // Verify
        List<Maneuver> expectedManeuverList = new ArrayList<>();
        expectedManeuverList.add(new Maneuver(new Position(1, 1), Orientation.W));

        assertEquals(expectedManeuverList, maneuverList);
    }


    // Please see the associate image example4.png (in resources/img_for_test_example folder) to understand this test
    @Test
    public void example4Test(){
        // Given
        Lawn lawn = Lawn.builder()
                .withMaxX(3).withMaxY(3)
                .withControllableMower()
                .withInitialOrientation(Orientation.N)
                .withInitialPosition().x(3).y(0).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Rotation.G).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Rotation.G).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .withMotionController().withMotionControl(Movement.A).done()
                .done()
                .build();

        // Execute
        LawnService lawnService = new LawnService(lawn);
        List<Maneuver> maneuverList = lawnService.moveMowersAndGetFinalPosition();


        // Verify
        List<Maneuver> expectedManeuverList = new ArrayList<>();
        expectedManeuverList.add(new Maneuver(new Position(0, 0), Orientation.S));

        assertEquals(expectedManeuverList, maneuverList);
    }
}


