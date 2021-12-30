
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot { //наследуем класс

   Login login = new Login();

    public void onUpdateReceived(Update update) { //Метод для приема сообщений, update - класс, описывающий сообщение
       update.getUpdateId();// обновляем информацию о пользователе

       SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());


       if (update.getMessage().getText().equals("/start")){
           sendMessage.setText("Привет,я могу предоставить тебе расписание занятий учебных групп и преподавателей БГУИР");
           try {
               setButtons(sendMessage); //предоставляем клавиатуру пользователю
               sendMessage(sendMessage); //устаревший метод отправки сообщения пользователю
           } catch (TelegramApiException e) {
               e.printStackTrace();
           }
       }
        if (update.getMessage().getText().equals("/help")){
            sendMessage.setText("Хозяин не вписывался в дедлайны, поэтому я знаю только расписание группы 914303 и преподавателя ГорбачаАнтонаПетровича");
            try {
                setButtons(sendMessage); //предоставляем клавиатуру пользователю
                sendMessage(sendMessage); //устаревший метод отправки сообщения пользователю
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (update.getMessage().getText().equals("914303")){
            sendMessage.setText(get_shedule_of_group ());
            try {
                setButtons(sendMessage); //предоставляем клавиатуру пользователю
                sendMessage(sendMessage); //устаревший метод отправки сообщения пользователю
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (update.getMessage().getText().equals("ГорбачАнтонПетрович")){
            sendMessage.setText(get_shedule_of_teacher ());
            try {
                setButtons(sendMessage); //предоставляем клавиатуру пользователю
                sendMessage(sendMessage); //устаревший метод отправки сообщения пользователю
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String get_shedule_of_teacher (){
    return login.getSchedule_of_teacher ().toString();
    }

    public String get_shedule_of_group (){
        return login.getSchedule_of_group().toString();
    }

    public void setButtons(SendMessage sendMessage) { //метод для появления у пользователя кнопок для взаимодействия с ботом
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();//создаем клавиатуру
        sendMessage.setReplyMarkup(replyKeyboardMarkup); // разметка клавиатуры
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList(); // лист кнопок
        KeyboardRow keyboardFirstRow = new KeyboardRow(); // первая строка кнопок
        KeyboardRow keyboardSecondRow = new KeyboardRow(); // вторая строка кнопок

        keyboardFirstRow.add(new KeyboardButton("/start")); // название кнопки
        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardSecondRow.add(new KeyboardButton("914303"));
        keyboardSecondRow.add(new KeyboardButton("ГорбачАнтонПетрович"));

        keyboardRowList.add(keyboardFirstRow); //добавляем первую строку клавиатуры в список
        keyboardRowList.add(keyboardSecondRow); //добавляем вторую строку клавиатуры в список
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public String getBotUsername() {
        return "BSUIR_Test_SchedBot";
    } // возвращает имя бота

    public String getBotToken() {
        return "1724032748:AAHo3ROCg4_ujOt_SOETZUXgxzdSxnB4rl0";
    }//возвращает токен для доступа к API телеги
}


