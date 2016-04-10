import java.lang.reflect.Array;
import java.util.*;

/**
 * <b> CS 180 - Project 4 - Email Server Skeleton </b>
 * <p>
 *
 * This is the skeleton code for the EmailServer Class. This is a private email
 * server for you and your friends to communicate.
 *
 * @author Alex Durre <(adurre@purdue.edu)>
 *
 * @lab (01)
 *
 * @version (4/14/16)
 *
 */

public class EmailServer {
    // Useful constants
    public static final String FAILURE = "FAILURE";
    public static final String DELIMITER = "\t";
    public static final String SUCCESS = "SUCCESS";
    public static final String CRLF = "\r\n";
    User[] users = new User[2];
    public int numUsers = 1;



    // Used to print out extra information
    private boolean verbose = false;


    public void run() {
        Scanner in = new Scanner(System.in);
        users[0] = new User("root", "cs180"); // add the default user

        while (true) {
            System.out.printf("Input Server Request: ");
            String command = in.nextLine();

            command = replaceEscapeChars(command);

            if (command.equalsIgnoreCase("kill") || command.equalsIgnoreCase("kill\r\n"))
                break;

            if (command.equalsIgnoreCase("verbose") || command.equalsIgnoreCase("verbose\r\n")) {
                verbose = !verbose;
                System.out.printf("VERBOSE has been turned %s.\n\n", verbose ? "on" : "off");
                continue;
            }

            String response = null;
            try {
                response = parseRequest(command);
            } catch (Exception ex) {
                response = ErrorFactory.makeErrorMessage(ErrorFactory.UNKNOWN_ERROR,
                        String.format("An exception of %s occurred.", ex.getClass().toString()));
            }

            // change the formatting of the server response so it prints well on the terminal (for testing purposes only)
            //if (response.startsWith("SUCCESS" + DELIMITER))
            //	response = response.replace(DELIMITER, NEWLINE);
            if (response.startsWith(FAILURE) && !DELIMITER.equals("\t"))
                response = response.replace(DELIMITER, "\t");

            if (verbose)
                System.out.print("response: ");
            System.out.printf("\"%s\"\n\n", response);
        }

        in.close();
    }

    /**
     * Determines which client command the request is using and calls
     * the function associated with that command.
     *
     * @param request - the full line of the client request (CRLF included)
     * @return the server response
     */
    public String parseRequest(String request) {
        // TODO: Parse the client request (that means split the request into parameters).
        String[] elements = request.split("\t");
        String command = elements[0];
        String theReturn = "";
        // TODO: Check format validity (correct command and number of parameters).
        if (command.equals("ADD-USER")) {
            if (elements.length == 3) {
                String username = elements[1];
                String password = elements[2];
                int temp = 0;
                for (int i = 0; i < numUsers; i++) {
                    if (users[i].getName().equals(username)) {
                        if (users[i].checkPassword(password)) {
                            temp = 1;
                        }
                    }
                }
                if (temp == 1) {
                    theReturn = addUser(elements);
                    //todo return theReturn here?
                }
                else {
                    //todo return some sort of error because the username and password are not right
                }
            }
            else {
                //todo throw error/ or something
            }
        }
        else if (command.equals("DELETE-USER")) {
            if (elements.length == 3) {

            }
            else {
                //todo throw error/ or something
            }
        }
        else if (command.equals("GET-ALL-USERS")) {
            if (elements.length == 2) {

            }
            else {
                //todo throw error/ or something
            }
        }
        else if (command.equals("SEND-EMAIL")) {
            if (elements.length == 5) {

            }
            else {
                //todo throw error/ or something
            }
        }
        else if (command.equals("GET-EMAILS")) {
            if (elements.length == 4) {

            }
            else {
                //todo throw error/ or something
            }
        }
        else if (command.equals("DELETE-EMAIL")) {
            if (elements.length == 4) {

            }
            else {
                //todo throw error/ or something
            }
        }
        // TODO: Verify the username and password.
        // TODO: Invoke the appropriate protocol method specified in the below Protocol Methods section. Hint: this is a great time to use a switch statement.
        // TODO: Return the correct server response based on the success, failure, or improper format of the request.
        return null;
    }
    /**
     * Replaces "poorly formatted" escape characters with their proper
     * values. For some terminals, when escaped characters are
     * entered, the terminal includes the "\" as a character instead
     * of entering the escape character. This function replaces the
     * incorrectly inputed characters with their proper escaped
     * characters.
     *
     * @param str - the string to be edited
     * @return the properly escaped string
     */
    private static String replaceEscapeChars(String str) {
        str = str.replace("\\r\\n", "\r\n"); // may not be necessary, but just in case
        str = str.replace("\\r", "\r");
        str = str.replace("\\n", "\n");
        str = str.replace("\\t", "\t");
        str = str.replace("\\f", "\f");

        return str;
    }
    //protocal methods
    //todo add user
    public String addUser(String[] args) {
        String username = args[0];
        String password = args[1];
        if (username.length() > 1 && username.length() < 20 && password.length() > 4 && password.length() < 40) {
            return FAILURE;
        }
        else {
            User u = new User(args[0], args[1]);
            User[] usersTemp = Arrays.copyOf(users, users.length * 2);
            for (int i = 0; i < usersTemp.length; i++) {
                if (usersTemp[i] == null) {
                    usersTemp[i] = u;
                }
            }
            users = usersTemp;

            return SUCCESS;
        }
    }
    //todo get all users
    public String getAllUsers(String[] args) {

        return "";
    }
    //todo delete user
    public String deleteUser(String[] args) {

        return "";
    }
    //todo send email
    public String sendEmail(String[] args) {

        return "";
    }
    //todo get emails
    public String getEmails(String[] args) {

        return "";
    }
    //todo delete email
    public String deleteEmail(String[] args) {

        return "";
    }
    /**
     * This main method is for testing purposes only.
     * @param args - the command line arguments
     */
    public static void main(String[] args) {
        (new EmailServer()).run();
    }
}