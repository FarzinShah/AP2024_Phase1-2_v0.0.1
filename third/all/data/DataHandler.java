package third.all.data;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//public class DataHandler {
//    public static void saveGameData(GameData gameData, String filePath) {
//        Gson gson = new Gson();
//        try (FileWriter writer = new FileWriter(filePath)) {
//            gson.toJson(gameData, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static GameData2 loadGameData(String filePath) {
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader(filePath)) {
//            return gson.fromJson(reader, GameData2.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}
