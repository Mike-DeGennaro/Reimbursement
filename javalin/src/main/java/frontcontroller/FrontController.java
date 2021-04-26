package frontcontroller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import org.apache.log4j.Logger;


public class FrontController {
    public static Logger loggy = Logger.getLogger(FrontController.class);
    Javalin app;
    Dispatcher dispatcher;

    //CONSTRUCTOR
    public FrontController(Javalin app){
        this.app = app;

        dispatcher = new Dispatcher(app);
    }

    //ROUTES
    public static void checkAllRequests(Context context){

    }

}
