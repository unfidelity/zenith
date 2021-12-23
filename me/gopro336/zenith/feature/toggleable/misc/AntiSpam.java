//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Aestheticall\Documents\decomp\1.12 stable mappings"!

/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.feature.toggleable.misc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import me.gopro336.zenith.feature.AnnotationHelper;
import me.gopro336.zenith.feature.Category;
import me.gopro336.zenith.feature.Feature;
import me.gopro336.zenith.property.NumberProperty;
import me.gopro336.zenith.property.Property;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import team.stiff.pomelo.impl.annotated.handler.annotation.Listener;

@AnnotationHelper(name="AntiSpam", description="Hides spam", category=Category.MISC)
public class AntiSpam
extends Feature {
    private final Property<Boolean> greenText = new Property<Boolean>(this, "Green Text", "", true);
    private final Property<Boolean> discordLinks = new Property<Boolean>(this, "Discord Links", "", true);
    private final Property<Boolean> webLinks = new Property<Boolean>(this, "Web Links", "", true);
    private final Property<Boolean> announcers = new Property<Boolean>(this, "Announcers", "", true);
    private final Property<Boolean> spammers = new Property<Boolean>(this, "Spammers", "", true);
    private final Property<Boolean> insulters = new Property<Boolean>(this, "Insulters", "", true);
    private final Property<Boolean> tradeChat = new Property<Boolean>(this, "Trade Chat", "", true);
    private final Property<Boolean> duplicates = new Property<Boolean>(this, "Duplicates", "", true);
    private final NumberProperty<Integer> duplicatesTimeout = new NumberProperty<Integer>((Feature)this, "DuplicatesTimeout", "", 10, Integer.valueOf(1), 600);
    private final Property<Boolean> filterOwn = new Property<Boolean>(this, "Filter Own", "", true);
    private final Property<Boolean> debug = new Property<Boolean>(this, "Debug Messages", "", true);
    private ConcurrentHashMap<String, Long> messageHistory;
    SPacketChat sPacketChat;

    @Listener
    public void onPacket(ClientChatReceivedEvent event) {
        if (AntiSpam.mc.player != null && this.isEnabled() && this.detectSpam(event.getMessage().getUnformattedText())) {
            event.setCanceled(true);
        }
    }

    private boolean detectSpam(String message) {
        if (!this.filterOwn.getValue().booleanValue() && this.findPatterns(FilterPatterns.ownMessage, message)) {
            return false;
        }
        if (this.greenText.getValue().booleanValue() && this.findPatterns(FilterPatterns.greenText, message)) {
            if (this.debug.getValue().booleanValue()) {
                System.out.println("Green Text: " + message);
            }
            return true;
        }
        if (this.discordLinks.getValue().booleanValue() && this.findPatterns(FilterPatterns.discord, message)) {
            if (this.debug.getValue().booleanValue()) {
                System.out.println("Discord Link: " + message);
            }
            return true;
        }
        if (this.webLinks.getValue().booleanValue() && this.findPatterns(FilterPatterns.webLink, message)) {
            if (this.debug.getValue().booleanValue()) {
                System.out.println("Web Link: " + message);
            }
            return true;
        }
        if (this.tradeChat.getValue().booleanValue() && this.findPatterns(FilterPatterns.tradeChat, message)) {
            if (this.debug.getValue().booleanValue()) {
                System.out.println("Trade Chat: " + message);
            }
            return true;
        }
        if (this.announcers.getValue().booleanValue() && this.findPatterns(FilterPatterns.announcer, message)) {
            if (this.debug.getValue().booleanValue()) {
                System.out.println("Announcer: " + message);
            }
            return true;
        }
        if (this.spammers.getValue().booleanValue() && this.findPatterns(FilterPatterns.spammer, message)) {
            if (this.debug.getValue().booleanValue()) {
                System.out.println("Spammers: " + message);
            }
            return true;
        }
        if (this.insulters.getValue().booleanValue() && this.findPatterns(FilterPatterns.insulter, message)) {
            if (this.debug.getValue().booleanValue()) {
                System.out.println("Insulter: " + message);
            }
            return true;
        }
        if (this.duplicates.getValue().booleanValue()) {
            if (this.messageHistory == null) {
                this.messageHistory = new ConcurrentHashMap();
            }
            boolean isDuplicate = false;
            if (this.messageHistory.containsKey(message) && (System.currentTimeMillis() - this.messageHistory.get(message)) / 1000L < (long)((Integer)this.duplicatesTimeout.getValue()).intValue()) {
                isDuplicate = true;
            }
            this.messageHistory.put(message, System.currentTimeMillis());
            if (isDuplicate) {
                if (this.debug.getValue().booleanValue()) {
                    System.out.println("Duplicate: " + message);
                }
                return true;
            }
        }
        return false;
    }

    private boolean findPatterns(String[] patterns, String string) {
        for (String pattern : patterns) {
            if (!Pattern.compile(pattern).matcher(string).find()) continue;
            return true;
        }
        return false;
    }

    private static class FilterPatterns {
        private static final String[] announcer = new String[]{"I just walked .+ feet!", "I just placed a .+!", "I just attacked .+ with a .+!", "I just dropped a .+!", "I just opened chat!", "I just opened my console!", "I just opened my GUI!", "I just went into full screen mode!", "I just paused my game!", "I just opened my inventory!", "I just looked at the player list!", "I just took a screen shot!", "I just swaped hands!", "I just ducked!", "I just changed perspectives!", "I just jumped!", "I just ate a .+!", "I just crafted .+ .+!", "I just picked up a .+!", "I just smelted .+ .+!", "I just respawned!", "I just attacked .+ with my hands", "I just broke a .+!", "I recently walked .+ blocks", "I just droped a .+ called, .+!", "I just placed a block called, .+!", "Im currently breaking a block called, .+!", "I just broke a block called, .+!", "I just opened chat!", "I just opened chat and typed a slash!", "I just paused my game!", "I just opened my inventory!", "I just looked at the player list!", "I just changed perspectives, now im in .+!", "I just crouched!", "I just jumped!", "I just attacked a entity called, .+ with a .+", "Im currently eatting a peice of food called, .+!", "Im currently using a item called, .+!", "I just toggled full screen mode!", "I just took a screen shot!", "I just swaped hands and now theres a .+ in my main hand and a .+ in my off hand!", "I just used pick block on a block called, .+!", "Ra just completed his blazing ark", "Its a new day yes it is"};
        private static final String[] spammer = new String[]{"WWE Client's spammer", "Lol get gud", "Future client is bad", "WWE > Future", "WWE > Impact", "Default Message", "IKnowImEZ is a god", "THEREALWWEFAN231 is a god", "WWE Client made by IKnowImEZ/THEREALWWEFAN231", "WWE Client was the first public client to have Path Finder/New Chunks", "WWE Client was the first public client to have color signs", "WWE Client was the first client to have Teleport Finder", "WWE Client was the first client to have Tunneller & Tunneller Back Fill"};
        private static final String[] insulter = new String[]{".+ Download WWE utility mod, Its free!", ".+ 4b4t is da best mintscreft serber", ".+ dont abouse", ".+ you cuck", ".+ https://www.youtube.com/channel/UCJGCNPEjvsCn0FKw3zso0TA", ".+ is my step dad", ".+ again daddy!", "dont worry .+ it happens to every one", ".+ dont buy future it's crap, compared to WWE!", "What are you, fucking gay, .+?", "Did you know? .+ hates you, .+", "You are literally 10, .+", ".+ finally lost their virginity, sadly they lost it to .+... yeah, that's unfortunate.", ".+, don't be upset, it's not like anyone cares about you, fag.", ".+, see that rubbish bin over there? Get your ass in it, or I'll get .+ to whoop your ass.", ".+, may I borrow that dirt block? that guy named .+ needs it...", "Yo, .+, btfo you virgin", "Hey .+ want to play some High School RP with me and .+?", ".+ is an Archon player. Why is he on here? Fucking factions player.", "Did you know? .+ just joined The Vortex Coalition!", ".+ has successfully conducted the cactus dupe and duped a itemhand!", ".+, are you even human? You act like my dog, holy shit.", ".+, you were never loved by your family.", "Come on .+, you hurt .+'s feelings. You meany.", "Stop trying to meme .+, you can't do that. kek", ".+, .+ is gay. Don't go near him.", "Whoa .+ didn't mean to offend you, .+.", ".+ im not pvping .+, im WWE'ing .+.", "Did you know? .+ just joined The Vortex Coalition!", ".+, are you even human? You act like my dog, holy shit."};
        private static final String[] discord = new String[]{"discord.gg"};
        private static final String[] greenText = new String[]{"^<.+> >"};
        private static final String[] tradeChat = new String[]{"buy", "sell"};
        private static final String[] webLink = new String[]{"http:\\/\\/", "https:\\/\\/"};
        private static final String[] ownMessage = new String[]{"^<" + AntiSpam.mc.player.getName() + "> ", "^To .+: "};

        private FilterPatterns() {
        }
    }
}

