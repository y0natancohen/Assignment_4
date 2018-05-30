package src.InputHandlers;

import src.LinkedList.LinkedList;
import src.Spam.Spam;
import src.Spam.Spams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpamInputHandler<T> implements IInputHandler<T> {

    public T readFile(String pathToFile) {
        LinkedList<Spam> spamWords = new LinkedList<>();
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(pathToFile));
            while ((bufferReader.readLine()) != null) {
                String[] line = bufferReader.readLine().split(" ");
                spamWords.add(new Spam(line[0], Integer.parseInt(line[1])));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Spams spams = new Spams(spamWords.getSize());

        // todo:elad understand why casting is necessary here
        return (T) spams;
    }
}
