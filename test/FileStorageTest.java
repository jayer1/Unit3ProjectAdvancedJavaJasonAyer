import jayer1.u2.parkingapp.FileStorage;
import jayer1.u2.parkingapp.Ticket;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileStorageTest {

    @Test
    public void testFileRead(){
        Ticket ticket1 = null;
        List<Ticket> myTestTicketList = new ArrayList();
        // Call FileRead method in FileStorage to read the file into the arrayList
        FileStorage fileReader = new FileStorage();
        try {
            myTestTicketList = fileReader.FileRead(ticket1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        boolean loaded = myTestTicketList.size()>0;
        assertTrue("Unable to load file to ArrayList", loaded);

    }

}