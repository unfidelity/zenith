/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.api.widget.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardUtil {
    public static String get() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable content = clipboard.getContents(null);
        if (content != null && content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                return content.getTransferData(DataFlavor.stringFlavor).toString();
            }
            catch (UnsupportedFlavorException | IOException exc) {
                return null;
            }
        }
        return null;
    }

    public static void set(String string) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(string), null);
    }
}

