public class DataCorruptedException extends Throwable{
    public DataCorruptedException(String data) {
        System.out.println(data.concat(" data is corrupted."));
    }
}
