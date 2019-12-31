import java.util.*;
import java.io.*;

class CreateNum800 {
    public static void main(String args[]) throws IOException {
        PrintWriter file = new PrintWriter(new File("num800"));
	Random random = new Random();

	for (int i = 0; i < 800; i++) {
	    file.print(random.nextInt(1000));
	    file.println();
	}
	file.close();
    }
}
