package mustapelto.deepmoblearning.common.metadata;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mustapelto.deepmoblearning.DMLConstants;
import mustapelto.deepmoblearning.DMLRelearned;
import mustapelto.deepmoblearning.common.util.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LivingMatterDataManager {
    private static final LinkedHashMap<String, LivingMatterData> dataStore = new LinkedHashMap<>();
    private static final File configFile = new File(FileHelper.configDML, "LivingMatter.json");

    public static void init() {
        if (!configFile.exists()) {
            readDefaultFile();
            writeConfigFile();
            return;
        }

        readConfigFile();
    }

    private static void readDefaultFile() {
        JsonObject dataObject;
        try {
            dataObject = FileHelper.readObject("/settings/LivingMatter.json");
        } catch (IOException e) {
            DMLRelearned.logger.error("Could not read default Living Matter config file! This will cause the mod to malfunction.");
            return;
        }

        populateDataStore(dataObject);
    }

    private static void readConfigFile() {
        JsonObject dataObject;
        try {
            dataObject = FileHelper.readObject(configFile);
        } catch (IOException e) {
            DMLRelearned.logger.error("Could not read Living Matter config file! This will cause the mod to malfunction.");
            return;
        }

        populateDataStore(dataObject);
    }

    private static void populateDataStore(JsonObject data) {
        Set<Map.Entry<String, JsonElement>> entrySet = data.entrySet();
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            if (!(entry.getValue() instanceof JsonArray))
                continue;
            JsonArray contents = (JsonArray) entry.getValue();
            for (int i = 0; i < contents.size(); i++) {
                LivingMatterData livingMatterData = new LivingMatterData(entry.getKey(), contents.get(i).getAsJsonObject());
                dataStore.put(livingMatterData.itemID, livingMatterData);
            }
        }
    }

    private static void writeConfigFile() {
        JsonObject data = new JsonObject();

        for (LivingMatterData entry : dataStore.values()) {
            String modID = entry.getModID();
            if (!data.has(modID))
                data.add(modID, new JsonArray());
            data.get(modID).getAsJsonArray().add(entry.toJsonObject());
        }

        try {
            FileHelper.writeObject(data, configFile);
        } catch (IOException e) {
            DMLRelearned.logger.error("Could not write Living Matter config file!");
        }
    }

    public static LivingMatterData getByID(String id) {
        return dataStore.get(id);
    }

    public static LinkedHashMap<String, LivingMatterData> getDataStore() {
        return dataStore;
    }
}
