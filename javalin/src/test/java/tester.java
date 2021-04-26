
import doa.*;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class tester {
    Jdbc test = new Jdbc();
    Service serv = new Service();

    @BeforeEach
    void setUp() {

        try(Connection conn = DriverManager.getConnection(test.url,test.username,test.password)){

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println("--------------------BEFORE EACH--------------------");
    }


    @Test
    public void test1(){
        System.out.println("In create ticket test type");
        assertTrue(test.addTicketType(320, 1));
        assertTrue(test.addTicketStatus(54,23));
    }

    @Test
    public void test2(){
        System.out.println("In create ticket status");

        assertTrue(test.addTicketStatus(54,23));
    }

}
