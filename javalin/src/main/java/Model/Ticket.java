package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ticket {
    private int id;
    private int amount;
    private String timeSubmitted;
    private String timeResolved;
    private String message;
    private int type;
    private int status;
    private int ticketID;
    private int statusID;

    public Ticket(int id, int amount, String message, int status, int type, String timeSubmitted, int ticketID, int statusID, String timeResolved) {
        this.id=id;
        this.amount = amount;
        this.message = message;
        this.type = type;
        this.status = status;
        this.timeSubmitted = timeSubmitted;
        this.ticketID = ticketID;
        this.statusID = statusID;
        this.timeResolved = timeResolved;
    }


    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted(String timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public String getTimeResolved() {
        return timeResolved;
    }

    public void setTimeResolved(String timeResolved) {
        this.timeResolved = timeResolved;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
