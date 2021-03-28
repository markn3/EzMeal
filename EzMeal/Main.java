// CSCI 3326 Project    Members:
// Main program

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Menu menu = new Menu();

        while (true) {
            menu.displayUserMenu();
            int ans = in.nextInt();
            if (ans == 3) {
                System.out.println("Yee haw");
                break;
            } else if (ans == 2) {
                System.out.println("Yee haw");
                break;
            } else if (ans == 1) {
                System.out.println("Yee haw");
                break;
            } else {
                System.out.println("Yee haw");
                break;
            }

        }

        in.close();
    }
}
