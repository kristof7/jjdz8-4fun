package pl.fourfun;


import pl.fourfun.menutypes.Menu;
import pl.fourfun.readwriteproducts.CheckProductFile;

import java.io.IOException;

class App
{

    public static void main( String[] args ) throws IOException, InterruptedException {

        CheckProductFile.checkingExistProductFile();
        CheckProductFile.checkingExistUserFile();
        Menu.showMainMenu();

    }
}
