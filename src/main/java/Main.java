import java.io.File;

public class Main {
    public static void main(String[] args) throws Exception {
        // создайте сервер, который отвечал бы на нужные запросы
        // слушать он должен порт 8989
        // отвечать на запросы /{word} -> возвращённое значение метода search(word) в JSON-формате
        BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs"));
        Server server = new Server(ServerConfig.PORT, engine);
        server.start();
    }
}