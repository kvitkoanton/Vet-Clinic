package main.java.com.magicvet;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static String PASSWORD = "default";
    static Scanner SCANNER = new Scanner(System.in);
    static String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._]+\\.[a-zA-Z]{2,}$";
    static String FIRST_LAST_NAME_PATTERN = "^[a-zA-Z-]{3,}$";
    /*
    Патерн для валідації імені та прізвища може бути однаковий
    (наприклад, перевірка, що рядок містить 3 і більше символів та лише латиницю
    без кирилиці, цифр чи спецсимволів, окрім дефісу, оскільки він використовується під час запису подвійного ім’я чи прізвища).
    * */

    public static void main(String[] args) {
        run();
    }
    static void run(){
        if (auth()){
            registerNewClient();
        }
    }
    static boolean auth() {
        boolean accepted = false;
        for (int i = 0; i < 3; i++) {
            System.out.print("Password: ");
            String input = SCANNER.nextLine();

            if (PASSWORD.equals (input)){
                accepted = true;
                break;
            } else {
                System.out.println("Access denied. Please check your password.");
            }
        }
        System.out.println(accepted ? "Welcome to the Magic Vet!" : "Application has been blocked");

        return accepted;
    }
    static void registerNewClient() {
        System.out.println("Please provide client details.");
        System.out.print("E-Mail: ");
        String email = SCANNER.nextLine(); //Введення E-Mail

        if (!isEmailValid(email)) { //Перевірка валідності Пошти
            System.out.println("Providet E-Mail is invalid");
            return; // Якщо Пошта не валідна, припиняємо виконувати метод
        }

        System.out.print("First name: ");
        String firstName = SCANNER.nextLine(); //Введення Ім'я

        if (!isFirstNamelValid(firstName)) { //Перевірка валідності Ім'я
            System.out.println("Providet First name is invalid");
            return; //Якщо Ім'я не валідно, припиняємо виконувати метод
        }

        System.out.print("Last name: ");
        String lastName = SCANNER.nextLine(); //Введення Прізвища

        if (!isLastNamelValid(lastName)){ // Перевірка валідності Прізвища
            System.out.println("Providet Last name is invalid");
            return; //Якщо Прізвище не валідно, припиняємо виконувати метод
        }
            Client client = buildClient(email, firstName, lastName);
            System.out.println("New client: " + client.FirstName + " " + client.LastName + " (" + client.email + ")");
    }

    private static Client buildClient(String email, String firstName, String lastName) {
        Client client = new Client();
        client.email = email;
        client.FirstName = firstName;
        client.LastName = lastName;

        return client;
    }

    static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    static boolean isFirstNamelValid(String firstName) {
        Pattern patternFirstName = Pattern.compile(FIRST_LAST_NAME_PATTERN);
        Matcher matcherFirstName = patternFirstName.matcher(firstName);
        return matcherFirstName.matches();
    }
    static boolean isLastNamelValid(String lastName) {
        Pattern patternLastName = Pattern.compile(FIRST_LAST_NAME_PATTERN);
        Matcher matcherLastName = patternLastName.matcher(lastName);
        return matcherLastName.matches();
    }
}
