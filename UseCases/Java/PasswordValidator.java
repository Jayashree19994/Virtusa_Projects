
import java.util.*;

class PasswordValidator {

    public static void pvalidator() {
        Scanner sc = new Scanner(System.in);
        String str;

        while (true) {
            System.out.print("Enter password: ");
            str = sc.nextLine();

            boolean hasUpper = false;
            boolean hasLower = false;
            boolean hasDigit = false;

            if (str.length() < 8) {
                System.out.println("Too short, retry\n");
                continue;
            }

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                if (Character.isUpperCase(ch)) hasUpper = true;
                if (Character.isLowerCase(ch)) hasLower = true;
                if (Character.isDigit(ch)) hasDigit = true;

                if (hasUpper && hasLower && hasDigit) break;
            }

            if (!hasUpper) System.out.println("Missing an uppercase letter\n");
            if (!hasLower) System.out.println("Missing a lowercase letter\n");
            if (!hasDigit) System.out.println("Missing a digit\n");

            if (hasUpper && hasLower && hasDigit) {
                System.out.println("Password is valid");
                break;
            }
        }
    }

    public static void main(String[] args) {
        pvalidator();
    }
}
