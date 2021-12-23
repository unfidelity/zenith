/*
 * Decompiled with CFR 0.152.
 */
package org.reflections.scanners;

import java.util.List;
import java.util.Map;
import javassist.bytecode.ClassFile;
import org.reflections.scanners.Scanner;

@Deprecated
class AbstractScanner
implements Scanner {
    protected final Scanner scanner;

    AbstractScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String index() {
        return this.scanner.index();
    }

    @Override
    public List<Map.Entry<String, String>> scan(ClassFile cls) {
        return this.scanner.scan(cls);
    }
}

