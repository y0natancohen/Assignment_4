package src;

//import src.LinkedList.LinkedList;
//import src.Messages.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MessageInputHandler<T> implements IInputHandler<T> {

    public T readFile(String pathToFile) {
        LinkedList<Message> rawMessages = new LinkedList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(pathToFile));
            scanner.useDelimiter("\n#\n");
            while (scanner.hasNext()) {
                rawMessages.add(extractSingleMessage(scanner.next()));
            }
            return (T) rawMessages;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (T) rawMessages;
    }

    private Message extractSingleMessage(String rawMessage) {
        String from, to, content;
        String[] first = rawMessage.split(":", 3);
        String[] second = first[1].split("\nTo");
        from = second[0];
        String[] third = first[2].split("\n", 2);
        to = third[0];
        content = third[1];
        return new Message(from, to, content);
    }
}
