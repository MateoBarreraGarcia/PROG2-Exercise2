package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.factories.ControllerFactory;


public class AboutController {
    private static AboutController instance;

    private AboutController() {}

    public static AboutController getInstance() {
        if (instance == null) {
            instance = new AboutController();
        }
        return instance;
    }
}
