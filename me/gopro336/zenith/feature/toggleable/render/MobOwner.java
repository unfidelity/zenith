//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.render;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractHorse;

@AnnotationHelper(name="MobOwner", description="displays the owner of mobs", category=Category.RENDER)
public class MobOwner
extends Feature {
    private List<AbstractHorse> mobs = new ArrayList<AbstractHorse>();
    private Map<String, String> uuidToName = new HashMap<String, String>();

    @Override
    public void onUpdate() {
        if (MobOwner.mc.world == null) {
            return;
        }
        for (Entity e : MobOwner.mc.world.loadedEntityList) {
            AbstractHorse horse;
            if (!(e instanceof AbstractHorse) || this.mobs.contains(horse = (AbstractHorse)e)) continue;
            this.mobs.add(horse);
            UUID uuid = horse.getOwnerUniqueId();
            if (uuid == null) {
                horse.setCustomNameTag("Not tamed");
                continue;
            }
            String uuidString = uuid.toString().replace("-", "");
            String name = "";
            if (this.uuidToName.get(name) != null) {
                name = this.uuidToName.get(name);
            } else {
                try {
                    String s = MobOwner.requestName(uuidString);
                    JsonElement element = new JsonParser().parse(s);
                    JsonArray array = element.getAsJsonArray();
                    if (array.size() == 0) continue;
                    name = array.get(array.size() - 1).getAsJsonObject().get("name").getAsString();
                    this.uuidToName.put(uuidString, name);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
            }
            horse.setCustomNameTag("Owner: " + name);
        }
    }

    private static String requestName(String uuid) {
        try {
            String query = "https://api.mojang.com/user/profiles/" + uuid + "/names";
            URL url = new URL(query);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
            String res = MobOwner.convertStreamToString(in);
            ((InputStream)in).close();
            conn.disconnect();
            return res;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        String r = s.hasNext() ? s.next() : "/";
        return r;
    }
}

