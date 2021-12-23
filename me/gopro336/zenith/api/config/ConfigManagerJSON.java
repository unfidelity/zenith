/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.feature.FeatureManager;
import me.gopro336.zenith.feature.hudElement.Element;
import me.gopro336.zenith.property.Property;

public class ConfigManagerJSON {
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void createDirectory() throws IOException {
        if (!Files.exists(Paths.get("zenith/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("zenith/", new String[0]), new FileAttribute[0]);
        }
        if (!Files.exists(Paths.get("zenith/features", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("zenith/features", new String[0]), new FileAttribute[0]);
        }
        if (!Files.exists(Paths.get("zenith/gui", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("zenith/gui", new String[0]), new FileAttribute[0]);
        }
        if (!Files.exists(Paths.get("zenith/social", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("zenith/social", new String[0]), new FileAttribute[0]);
        }
    }

    public static void registerFiles(String name, String path) throws IOException {
        if (!Files.exists(Paths.get("zenith/" + path + "/" + name + ".json", new String[0]), new LinkOption[0])) {
            Files.createFile(Paths.get("zenith/" + path + "/" + name + ".json", new String[0]), new FileAttribute[0]);
        } else {
            File file = new File("zenith/" + path + "/" + name + ".json");
            file.delete();
            Files.createFile(Paths.get("zenith/" + path + "/" + name + ".json", new String[0]), new FileAttribute[0]);
        }
    }

    public static void saveConfig() {
        try {
            ConfigManagerJSON.saveModules();
            ConfigManagerJSON.saveHUD();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static void loadConfig() {
        try {
            ConfigManagerJSON.createDirectory();
            ConfigManagerJSON.loadModules();
            ConfigManagerJSON.loadHUD();
        }
        catch (IOException iOException) {
            // empty catch block
        }
    }

    public static void saveModules() throws IOException {
        for (Feature feature : FeatureManager.getModulesStatic()) {
            ConfigManagerJSON.registerFiles(feature.getName(), "features");
            OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter((OutputStream)new FileOutputStream("zenith/features/" + feature.getName() + ".json"), StandardCharsets.UTF_8);
            JsonObject featureObject = new JsonObject();
            JsonObject propertyObject = new JsonObject();
            JsonObject subPropertyObject = new JsonObject();
            featureObject.add("Name", (JsonElement)new JsonPrimitive(feature.getName()));
            featureObject.add("Enabled", (JsonElement)new JsonPrimitive(Boolean.valueOf(feature.isEnabled())));
            featureObject.add("Bind", (JsonElement)new JsonPrimitive((Number)feature.getKey()));
            for (Property<?> property : FeatureManager.getOldPropertiesFromFeature(feature)) {
                if (property.getValue() instanceof Boolean) {
                    propertyObject.add(property.getName(), (JsonElement)new JsonPrimitive((Boolean)property.getValue()));
                }
                if (property.getValue() instanceof Number) {
                    propertyObject.add(property.getName(), (JsonElement)new JsonPrimitive((Number)property.getValue()));
                }
                if (!(property.getValue() instanceof Enum)) continue;
                propertyObject.add(property.getName(), (JsonElement)new JsonPrimitive(String.valueOf(property.getValue())));
            }
            propertyObject.add("SubProperties", (JsonElement)subPropertyObject);
            featureObject.add("Properties", (JsonElement)propertyObject);
            String jsonString = gson.toJson(new JsonParser().parse(featureObject.toString()));
            fileOutputStreamWriter.write(jsonString);
            fileOutputStreamWriter.close();
        }
    }

    public static void loadModules() {
        for (Feature feature : FeatureManager.getModulesStatic()) {
            if (!Files.exists(Paths.get("zenith/features/" + feature.getName() + ".json", new String[0]), new LinkOption[0])) continue;
            JsonObject featureObject = null;
            try {
                InputStream inputStream = Files.newInputStream(Paths.get("zenith/features/" + feature.getName() + ".json", new String[0]), new OpenOption[0]);
                featureObject = new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject();
            }
            catch (Exception inputStream) {
                // empty catch block
            }
            assert (featureObject != null);
            if (featureObject.get("Name") == null || featureObject.get("Enabled") == null || featureObject.get("Bind") == null) continue;
            JsonObject propertyObject = featureObject.get("Properties").getAsJsonObject();
            JsonObject subPropertyObject = propertyObject.get("SubProperties").getAsJsonObject();
            for (Property<?> property : FeatureManager.getOldPropertiesFromFeature(feature)) {
                JsonElement propertyValueObject = null;
                if (property.getValue() instanceof Boolean) {
                    propertyValueObject = propertyObject.get(property.getName());
                }
                if (property.getValue() instanceof Number) {
                    propertyValueObject = propertyObject.get(property.getName());
                }
                if (property.getValue() instanceof Enum) {
                    propertyValueObject = propertyObject.get(property.getName());
                }
                if (propertyValueObject == null) continue;
                if (property.getValue() instanceof Boolean) {
                    property.setValue(propertyValueObject.getAsBoolean());
                }
                if (property.getValue() instanceof Number) {
                    property.setValue(propertyValueObject.getAsNumber());
                }
                if (!(property.getValue() instanceof Enum)) continue;
                property.setValue(propertyValueObject.getAsInt());
            }
            feature.setEnabled(featureObject.get("Enabled").getAsBoolean());
            feature.setKey(featureObject.get("Bind").getAsInt());
        }
    }

    public static void saveHUD() throws IOException {
        ConfigManagerJSON.registerFiles("HUD", "gui");
        OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter((OutputStream)new FileOutputStream("zenith/gui/HUD.json"), StandardCharsets.UTF_8);
        JsonObject guiObject = new JsonObject();
        JsonObject hudObject = new JsonObject();
        for (Element component : FeatureManager.getHudElements()) {
            JsonObject positionObject = new JsonObject();
            positionObject.add("x", (JsonElement)new JsonPrimitive((Number)Float.valueOf(component.x)));
            positionObject.add("y", (JsonElement)new JsonPrimitive((Number)Float.valueOf(component.y)));
            positionObject.add("enabled", (JsonElement)new JsonPrimitive(Boolean.valueOf(component.isEnabled())));
            hudObject.add(component.getName(), (JsonElement)positionObject);
        }
        guiObject.add("Components", (JsonElement)hudObject);
        String jsonString = gson.toJson(new JsonParser().parse(guiObject.toString()));
        fileOutputStreamWriter.write(jsonString);
        fileOutputStreamWriter.close();
    }

    public static void loadHUD() throws IOException {
        if (!Files.exists(Paths.get("zenith/gui/HUD.json", new String[0]), new LinkOption[0])) {
            return;
        }
        InputStream inputStream = Files.newInputStream(Paths.get("zenith/gui/HUD.json", new String[0]), new OpenOption[0]);
        JsonObject guiObject = new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject();
        if (guiObject.get("Components") == null) {
            return;
        }
        JsonObject windowObject = guiObject.get("Components").getAsJsonObject();
        for (Element component : FeatureManager.getHudElements()) {
            JsonElement hudEnabledObject;
            JsonElement hudYObject;
            if (windowObject.get(component.getName()) == null) {
                return;
            }
            JsonObject categoryObject = windowObject.get(component.getName()).getAsJsonObject();
            JsonElement hudXObject = categoryObject.get("x");
            if (hudXObject != null && hudXObject.isJsonPrimitive()) {
                component.x = hudXObject.getAsInt();
            }
            if ((hudYObject = categoryObject.get("y")) != null && hudYObject.isJsonPrimitive()) {
                component.y = hudYObject.getAsInt();
            }
            if ((hudEnabledObject = categoryObject.get("enabled")) == null || !hudEnabledObject.isJsonPrimitive() || !hudEnabledObject.getAsBoolean()) continue;
            component.toggle();
        }
        inputStream.close();
    }
}

