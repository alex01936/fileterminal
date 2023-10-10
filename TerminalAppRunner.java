package Tree;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;


public class TerminalAppRunner {

    public static void main(String[] args) {
        FileTerminal ft = new FileTerminal();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
           try{
               String input = scanner.nextLine();
               String[] splitted = input.split(" ");
               TerminalCommand command = TerminalCommand.valueOf(splitted[0].toUpperCase().trim());
               boolean c=ft.processCommand(command,splitted);
               if(!c){
                   break;
               }

           }
           catch (Exception e){
               System.out.println("wrong input!");
           }
        }
    }

}
