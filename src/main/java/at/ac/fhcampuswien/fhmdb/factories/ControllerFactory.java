package at.ac.fhcampuswien.fhmdb.factories;

import java.util.HashMap;
import java.util.Map;

import at.ac.fhcampuswien.fhmdb.AboutController;
import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.MainController;
import at.ac.fhcampuswien.fhmdb.WatchlistController;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {

    private static final ControllerFactory INSTANCE = new ControllerFactory();
    //um Controller-Objekte zu speichern
    private Map<Class<?>, Object> controllers = new HashMap<>();

    private ControllerFactory() {}

    public static ControllerFactory getInstance() {
        return INSTANCE;
    }
    //die rückgabemethode
    @Override
    public Object call(Class<?> aClass) {

            try {
                if (aClass.equals(MainController.class)) {
                    return MainController.getInstance();
                } else if (aClass.equals(HomeController.class)) {
                    return HomeController.getInstance();
                } else if (aClass.equals(WatchlistController.class)) {
                    return WatchlistController.getInstance();
                } else if (aClass.equals(AboutController.class)) {
                    return AboutController.getInstance();
                } else return null;

                //controllers.put(aClass, controller);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        //return controllers.get(aClass);
    }
}