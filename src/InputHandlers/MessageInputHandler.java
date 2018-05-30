package src.InputHandlers;

import src.LinkedList.LinkedList;
import src.Messages.Message;

import java.util.Scanner;

public class MessageInputHandler<T> implements IInputHandler<T> {

    public T readFile(String pathToFile) {
        LinkedList<Message> rawMessages = new LinkedList<>();
        Scanner scanner = new Scanner(pathToFile);
        scanner.useDelimiter("#");
        while (scanner.hasNext()) {
            String[] singleMessageDetails = scanner.next().split(" ");
            rawMessages.add(new Message(singleMessageDetails[0], singleMessageDetails[1], singleMessageDetails[2]));
        }
        return (T) rawMessages;
    }
}
