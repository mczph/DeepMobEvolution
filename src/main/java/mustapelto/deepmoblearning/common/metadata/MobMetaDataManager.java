package mustapelto.deepmoblearning.common.metadata;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import mustapelto.deepmoblearning.DMLRelearned;
import mustapelto.deepmoblearning.common.util.FileHelper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MobMetaDataManager {
    private static final LinkedHashMap<String, MobMetaData> dataStore = new LinkedHashMap<>();
    private static final File configFile = new File(FileHelper.configDML, "DataModels.json");

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
            dataObject = FileHelper.readObject("/settings/DataModels.json");
        } catch (IOException e) {
            DMLRelearned.logger.error("Could not read default Data Model config file! This will cause the mod to malfunction.");
            return;
        }

        populateDataStore(dataObject);
    }

    private static void readConfigFile() {
        JsonObject dataObject;
        try {
            dataObject = FileHelper.readObject(configFile);
        } catch (IOException e) {
            DMLRelearned.logger.error("Could not read Data Model config file! This will cause the mod to malfunction.");
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
                MobMetaData mobData = new MobMetaData(entry.getKey(), contents.get(i).getAsJsonObject());
                dataStore.put(mobData.itemID, mobData);
            }
        }
    }

    private static void writeConfigFile() {
        JsonObject data = new JsonObject();

        for (MobMetaData entry : dataStore.values()) {
            String modID = entry.getModID();
            if (!data.has(modID))
                data.add(modID, new JsonArray());
            data.get(modID).getAsJsonArray().add(entry.toJsonObject());
        }

        try {
            FileHelper.writeObject(data, configFile);
        } catch (IOException e) {
            DMLRelearned.logger.error("Could not write Data Model config file!");
        }
    }

    public static LinkedHashMap<String, MobMetaData> getDataStore() {
        return dataStore;
    }

    public static MobMetaData getMetaData(String id) {
        return dataStore.get(id);
    }
}
