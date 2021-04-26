//import example.frontcontroller.FrontController;
import frontcontroller.FrontController;
import io.javalin.Javalin;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class Main {
    public static Logger loggy = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        loggy.setLevel(Level.ALL);
        if(loggy.isInfoEnabled()) {
        }
        loggy.info("Starting application");

        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.addStaticFiles("/front-end");
        }).start(8002);

        FrontController fc = new FrontController(app);

    }
}
