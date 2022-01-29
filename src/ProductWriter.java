import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main (String args[]) {
        Boolean moreEntries = true;
        ArrayList<String> recs = new ArrayList<>();
        ArrayList <String>products = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String cont = "";
        String ID = "";
        String name = "";
        String desc = "";
        Integer cost = 0;

        //Gather Data
        while (moreEntries == true) {
            System.out.println("Please enter a product record.");
            ID = SafeInput.getNonZeroLenString(input, "please enter product ID");
            recs.add(ID);
            name = SafeInput.getNonZeroLenString(input, "Please enter product name");
            recs.add(name);
            desc = SafeInput.getNonZeroLenString(input, "Please enter a description");
            recs.add(desc);
            cost = SafeInput.getInt(input, "Please enter the cost");
            recs.add(cost.toString());
            products.add(String.valueOf(recs));
            cont = SafeInput.getNonZeroLenString(input, "More entries? Y/N");
            if (Objects.equals(cont, "Y")) {
                moreEntries = true;
            } else {
                moreEntries = false;
            }
            recs.clear();
        }

        //Write File
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        try {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : products)
            {
                writer.write(rec, 0, rec.length());

                writer.newLine();
            }
            writer.close();
            System.out.println("File written!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
