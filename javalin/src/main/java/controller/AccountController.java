package controller;

import Model.Account;
import Model.Ticket;
import doa.Jdbc;
import doa.Service;
import io.javalin.http.Context;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class AccountController {
    public static Logger loggy = Logger.getLogger(AccountController.class);
    public static List<Account> myList = new ArrayList<>();
    public static Jdbc database = new Jdbc();
    public static Service service = new Service();

    //takes user input and passes to service layer, returns an object of the account we found (if found)
    //and tickets corresponding to that account
    public static void searchAccounts(Context context) {
        loggy.info("in search accounts controller");

        String firstParam = context.queryParam("applejacks");
        String secondParam = context.queryParam("humanwords");

        Account account = service.getAccount(firstParam, secondParam);
        context.json(account);
    }

    //takes user input from create ticket form and passes information down the lasagna, returns it
    public static void addTicket(Context context){
        loggy.info("in addTicket controller");

        String idParam = context.queryParam("id");
        String amountParam = context.queryParam("amount");
        String messageParam = context.queryParam("message");
        String typeParam = context.queryParam("type");

        System.out.println("im gona add some stuff");

        Ticket account = service.addTicket(Integer.parseInt(idParam), Integer.parseInt(amountParam),
                messageParam, Integer.parseInt(typeParam));

        context.json(account);

    }

    //takes user input and passes it down the lasagna to update a desired ticket
    public static void updateTicket(Context context){
        loggy.info("in updateTicket controller");

        String idParam = context.queryParam("id");
        String statusParam = context.queryParam("status");
        String resolvedParam = context.queryParam("resolvedby");

        service.updateTicket(Integer.parseInt(idParam), Integer.parseInt(statusParam), Integer.parseInt(resolvedParam));

    }
}
