package client;

import java.io.IOException;
import client.Auth.*;
import client.facade.Facade;

public class Start {
    public static void main(String[] args)throws IOException {
        Facade facade = new Facade();
        new LoginFrame(facade);
    }
       // new LoginFrame();
      //  new Client().start();
}
