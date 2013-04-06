package UI;

import controllers.LogicInterface;
import java.util.Scanner;

public class TUI {

    private LogicInterface logic;
    private Scanner scanner;
    private int choise;

    public TUI(LogicInterface l) {
        logic = l;
        scanner = new Scanner(System.in);
        choise = 0;
    }

    public void start() {
        System.out.println("Welcome. \n");

        while (choise != 9) {
            System.out.println("Choose an action:");
            System.out.println("1: Add an inproceedings.");
            System.out.println("9: Stop program");
            choise = Integer.parseInt(scanner.nextLine());

            if (choise == 1) {
                String[][] required = logic.getRequiredFields();
                String[][] optional = logic.getOptionalFields();
//                String[][] required = {{"Author", ""}, {"Title", ""}, {"Booktitle", ""}, {"Year", ""}};
//                String[][] optional = {{"Editor", ""}, {"Volume/Number", ""}, {"Series", ""}, {"Pages", ""}, {"Address", ""},
//                    {"Month", ""}, {"Organization", ""}, {"Publisher", ""}, {"Note", ""}, {"Key", ""}};
                
                System.out.println("Creating inproceedings.");
                
                System.out.println("\n Required fields:");
                
                for (int i = 0; i < required.length; i++) {
                    System.out.print(required[i][0] + "(required): ");
                    required[i][1] = scanner.nextLine();
                }
                
                System.out.println("\n Optional fields:");
                for (int i = 0; i < optional.length; i++) {
                    System.out.println(optional[i][0] + "(optional): ");
                    optional[i][1] = scanner.nextLine();
                }
                
                //logic.createInproceedings(required, optional);
                

            }




        }
    }
}
