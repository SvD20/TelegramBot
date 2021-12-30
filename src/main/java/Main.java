import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();// инициализируем API на стороне телеги
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();//создаем объект API
        try {
            telegramBotsApi.registerBot(new Bot()); // регистрация бота
        }
        catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
