import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.out;

public class Server {
    protected int port;
    protected BooleanSearchEngine engine;

    public Server(int port, BooleanSearchEngine engine) {
        this.port = port;
        this.engine = engine;
    }

    public void start() throws IOException {
        out.println("Starting server at " + port + " port.");
        try (ServerSocket server = new ServerSocket(ServerConfig.PORT)) {
            while (true) {
                try (Socket client = server.accept();
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                    if (client.isConnected()) {
                        out.println("Новое подключение, порт: " + client.getPort());
                        writer.println("Успешное подключение, введите искомое слово");
                    }
                    String word = reader.readLine();
                    word = word.toLowerCase();
                    List<PageEntry> pageEntryList = engine.search(word);
                    out.println(answerJson(pageEntryList));
                }
            }
        } catch (IOException e) {
            out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private String answerJson(List<PageEntry> pageEntryList) {
        var builder = new GsonBuilder();
        Gson gson = builder.create();
        List<String> jsonList = new ArrayList<>();

        if (pageEntryList.isEmpty()) {
            return "не найдено";
        }

        for (PageEntry pageEntry : pageEntryList) {
            jsonList.add(gson.toJson(pageEntry));
        }
        return jsonList.toString();
    }
}
