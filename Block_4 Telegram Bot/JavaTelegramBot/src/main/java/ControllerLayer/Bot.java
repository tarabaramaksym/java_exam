package ControllerLayer;

import DataLayer.DataBase;
import DataLayer.DataFile;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;

import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Random;

public class Bot extends TelegramLongPollingBot {

  public static LongPollingBot getBot() {

    return new Bot();
  }

  DataFile dataFile;
  DataBase dataBase;

  public Bot() {
    super();
    this.dataFile = new DataFile();
    this.dataBase = new DataBase();
  }

  /**
   * Метод возвращает имя бота, указанное при регистрации.
   * 
   * @return имя бота
   */
  @Override
  public String getBotUsername() {
    return "JavaBot_Tarabara_bot";
  }

  /**
   * Метод возвращает token бота для связи с сервером Telegram
   * 
   * @return token для бота
   */
  @Override
  public String getBotToken() {
    return "5309432028:AAFyCsodKyFEXnnMaz-01GSD80O3AfRVdV4";
  }

  private boolean inPlay = false;
  private boolean inStart = false;
  private boolean inDecision = false;

  private HashMap<Integer, String> dic;

  @Override
  public void onUpdateReceived(Update update) {
    //
    if (update.hasMessage() && update.getMessage().hasText()) {
      String message_text = update.getMessage().getText();
      String chat_id = update.getMessage().getChatId().toString();

      if (message_text.equals("/start")) {
        String messageSend = "Привет, " + update.getMessage().getFrom().getFirstName() + ". Сколько тебе лет?";
        inStart = true;
        inDecision = false;
        inPlay = false;
        sendMsg(chat_id, messageSend);
      } else if (message_text.equals("/help")) {
        sendMsg(chat_id, "Введите /start, чтобы начать");

      } else if (inPlay) {
        try {
          int rand = new Random().nextInt(3);
          int num = 0;
          if (message_text.equals("Камень")) {
            num = 1;
          } else if (message_text.equals("Ножницы")) {
            num = 2;
          } else if (message_text.equals("Бумага")) {
            num = 3;
          }

          dic = new HashMap<Integer, String>();
          dic.put(1, "Камень");
          dic.put(2, "Ножницы");
          dic.put(3, "Бумага");

          sendMsg(chat_id, dic.get(rand) + "!");
          if (num == rand) {
            sendMsg(chat_id, "Ничья!");
          } else if (num == 1 && rand == 2 || num == 2 && rand == 3 || num == 3 && rand == 1) {
            sendMsg(chat_id, "Ты победил!");
          } else {
            sendMsg(chat_id, "Ты проиграл!");
          }
          sendMsg(chat_id, "Еще раз? Да / Нет");
          inDecision = true;
        } catch (Exception ex) {
          sendMsg(chat_id, "Неправильный ввод");
        }

      } else if (inDecision) {
        if (update.getMessage().getText().equals("Да") || update.getMessage().getText().equals("да")) {
          inPlay = true;
          sendMsg(chat_id, "Камень - Ножницы - Бумага ?");
        } else {
          inDecision = false;
        }
      } else if (inStart) {
        try {
          int age = Integer.parseInt(update.getMessage().getText());
          if (age <= 13) {
            inStart = false;
            sendMsg(chat_id, "С детьми не играю");
          } else {
            if (age <= 20) {
              sendMsg(chat_id, "(Деньги есть?)");
            }
            sendMsg(chat_id, "Сыграем в игру камень-ножницы-бумага? Да/Нет");
            inStart = false;
            inDecision = true;
          }
        } catch (Exception ex) {

        }

      }
    }
  }

  //
  /**
   * Метод для настройки сообщения и его отправки.
   * 
   * @param chatId id чата
   * @param s      Строка, которую необходимот отправить в качестве сообщения.
   */
  public synchronized void sendMsg(String chatId, String s) {
    SendMessage sendMessage = new SendMessage();
    sendMessage.enableMarkdown(true);
    sendMessage.setChatId(chatId);
    sendMessage.setText(s);
    try {
      sendMessage(sendMessage);
    } catch (TelegramApiException e) {
      // log.log(Level.SEVERE, "Exception: ", e.toString());
    }
  }
  //
}
