import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    private static final String NAME = "MudMud";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static String splitInput(String input) {
        int dividerPosition = input.indexOf(" ");
        return input.substring(dividerPosition + 1);
    }

    public static void addTask(String input) {
        tasks[tasksCount] = new Task(input);
        tasksCount++;
    }

    public static void addTodo(String input) {
        tasks[tasksCount] = new Todo(splitInput(input));
        tasksCount++;
    }

    public static void addDeadline(String input) {
        final String BY_KEYWORD = " /by ";

        input = splitInput(input);
        int byPosition = input.indexOf(BY_KEYWORD);

        tasks[tasksCount] = new Deadline(input.substring(0, byPosition),
                input.substring(byPosition + BY_KEYWORD.length()));
        tasksCount++;
    }

    public static void addEvent(String input) {
        final String FROM_KEYWORD = " /from ";
        final String TO_KEYWORD = " /to ";

        input = splitInput(input);
        int fromPosition = input.indexOf(FROM_KEYWORD);
        int toPosition = input.indexOf(TO_KEYWORD);

        tasks[tasksCount] = new Event(input.substring(0, fromPosition),
                input.substring(fromPosition + FROM_KEYWORD.length(), toPosition),
                input.substring(toPosition + TO_KEYWORD.length()));
        tasksCount++;
    }

    public static void setMarkAsDone(String input) {
        int index = Integer.parseInt(splitInput(input)) - 1;
        tasks[index].markAsDone();

        System.out.println("\tYay! You have completed this task:");
        System.out.println("\t\t" + tasks[index]);
    }

    public static void setUnmarkAsDone(String input) {
        int index = Integer.parseInt(splitInput(input)) - 1;
        tasks[index].unmarkAsDone();

        System.out.println("\tOh no! It seems that you haven't finish this task:");
        System.out.println("\t\t" + tasks[index]);
    }

    public static void printRecentTask(Task task) {
        System.out.println("\tI have added the following task into the list:");
        System.out.println("\t\t" + task);
        System.out.println("\tI took a peak at the list and you have " + tasksCount
                + (tasksCount == 1 ? " task" : " tasks") + " currently.");
    }

    public static void printTasks() {
        System.out.println("\tHere are your tasks you have inputted:");
        for (int i = 1; i <= tasksCount; i++) {
            System.out.println("\t" + i + "." + tasks[i - 1]);
        }
    }

    public static void executeCommand(String input, String command) {

        switch(command) {
        case "list":
            printTasks();
            break;
        case "mark":
            setMarkAsDone(input);
            break;
        case "unmark":
            setUnmarkAsDone(input);
            break;
        case "todo":
            addTodo(input);
            printRecentTask(tasks[tasksCount - 1]);
            break;
        case "deadline":
            addDeadline(input);
            printRecentTask(tasks[tasksCount - 1]);
            break;
        case "event":
            addEvent(input);
            printRecentTask(tasks[tasksCount - 1]);
            break;
        case "bye":
            System.out.println("\tGoodbye! I am going to sleep now.");
            System.out.println("\t" + HORIZONTAL_LINE);
            System.exit(0);
            break;
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String input = "";

        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\tOh hello! I'm " + NAME + ".");
        System.out.println("\tHow can I help you today?");
        System.out.println("\t" + HORIZONTAL_LINE);

        while (!input.equals("bye")) {
            input = in.nextLine();

            String[] parsedInput = input.split(" ", 2);
            String command = parsedInput[0];

            System.out.println("\t" + HORIZONTAL_LINE);
            executeCommand(input, command);
            System.out.println("\t" + HORIZONTAL_LINE);
        }
    }
}