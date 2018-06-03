package src.InputHandlers;

import src.LinkedList.LinkedList;
import src.Messages.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MessageInputHandler<T> implements IInputHandler<T> {

    public T readFile(String pathToFile) {
        LinkedList<Message> rawMessages = new LinkedList<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(pathToFile));
            scanner.useDelimiter("#");
            while (scanner.hasNext()) {
                String[] singleMessageDetails = scanner.next().split("");
                rawMessages.add(new Message(singleMessageDetails[0], singleMessageDetails[1], singleMessageDetails[2]));
            }
            return (T) rawMessages;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (T) rawMessages;
    }
}
