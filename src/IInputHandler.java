

public interface IInputHandler<T> {
    /**
     * Reads a text file from given path
     *
     * @param pathToFile - path to file
     * @return T which is the moulde representing the specific converted raw file data
     */

    T readFile(String pathToFile);
}
