package at.ac.fhcampuswien.fhmdb.factories;

import java.util.HashMap;
import java.util.Map;
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
        if (!controllers.containsKey(aClass)) {
            try {
                Object controller = aClass.getDeclaredConstructor().newInstance();
                controllers.put(aClass, controller);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return controllers.get(aClass);
    }
}