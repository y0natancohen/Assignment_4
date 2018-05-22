package src.Messages;

import src.Utils;
import java.util.Iterator;

public class Messages implements Iterable{


    // TODO: Java man make this iterator shit work
    // TODO: i hate it i want to shoot irena revayev and tzachi

    private int size;
    private int curretnIndex;
    private Message[] messages;

    public Messages(){
        this.size = 0;
        this.curretnIndex = 0;
        this.messages = new Message[0];
    }

    @Override
    public Iterator iterator() {

        return new Iterator() {

            @Override
            public boolean hasNext() {
                return (this.curretnIndex < this.size -1 );
            }

            @Override
            public Object next() {
                return null;
            }
        };
    }




    public void generateMessages(String filePath){
        String txt = Utils.readFile(filePath);
        String[] emails = txt.split("#");
        this.messages = new Message[emails.length];

        for (int i = 0; i < messages.length; i++){
            _parse(this.messages[i]);
        }
    }

    private static void _parse(Message msg){
        // TODO: all the fucking parsing
    }


}
