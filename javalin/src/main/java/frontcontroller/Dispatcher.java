package frontcontroller;

import controller.AccountController;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Dispatcher {

    public Dispatcher(Javalin app) {
        setupAllPaths(app);
    }

    public static void setupAllPaths(Javalin app) {
        setupAccountPaths(app);
    }

    private static void setupAccountPaths(Javalin app) {
        //route for logging in, gets user and password info
        app.routes(() -> {
            path("/api/queryparam-demo/", () -> {
                System.out.println("in dispatcher search");
                get(AccountController::searchAccounts);
            });
        });
        //route for adding a ticket, gathers required information
        app.routes(() -> {
            path("/api/queryparam-add/", () -> {
                System.out.println("in dispatcher add list");
                get(AccountController::addTicket);
            });
        });

        app.routes(() -> {
            path("/api/queryparam-update/", () -> {
                System.out.println("in dispatcher add item");
                get(AccountController::updateTicket);
            });
        });

    }


}
