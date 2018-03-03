package fr.maxime;

import fr.maxime.lawnmower.service.LawnService;
import fr.maxime.lawnmower.Lawn;
import fr.maxime.lawnmower.motioncontrol.Maneuver;
import fr.maxime.lawnreader.LawnFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.util.List;


public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        if(args.length < 1){
            log.error("Usage : application.jar <input_file_path>");
            System.exit(1);
        }

        Lawn lawn = null;
        try {
            lawn = LawnFileReader.read(args[0]);
        } catch (FileNotFoundException e) {
            log.error("The file has not been found");
            System.exit(2);
        }

        LawnService lawnService = new LawnService(lawn);
        List<Maneuver> maneuvers = lawnService.moveMowersAndGetFinalPosition();
        maneuvers.forEach(maneuver -> log.info(maneuver.toString()));
    }

}
