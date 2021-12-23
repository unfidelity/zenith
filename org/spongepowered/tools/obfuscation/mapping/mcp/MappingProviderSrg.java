/*
 * Decompiled with CFR 0.152.
 */
package org.spongepowered.tools.obfuscation.mapping.mcp;

import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import org.spongepowered.asm.mixin.throwables.MixinException;
import org.spongepowered.asm.obfuscation.mapping.common.MappingField;
import org.spongepowered.asm.obfuscation.mapping.common.MappingMethod;
import org.spongepowered.asm.obfuscation.mapping.mcp.MappingFieldSrg;
import org.spongepowered.tools.obfuscation.mapping.common.MappingProvider;

public class MappingProviderSrg
extends MappingProvider {
    public MappingProviderSrg(Messager messager, Filer filer) {
        super(messager, filer);
    }

    @Override
    public void read(final File input) throws IOException {
        final BiMap packageMap = this.packageMap;
        final BiMap classMap = this.classMap;
        final BiMap fieldMap = this.fieldMap;
        final BiMap methodMap = this.methodMap;
        Files.readLines((File)input, (Charset)Charset.defaultCharset(), (LineProcessor)new LineProcessor<String>(){

            public String getResult() {
                return null;
            }

            public boolean processLine(String line) throws IOException {
                if (Strings.isNullOrEmpty((String)line) || line.startsWith("#")) {
                    return true;
                }
                String type = line.substring(0, 2);
                String[] args2 = line.substring(4).split(" ");
                if (type.equals("PK")) {
                    packageMap.forcePut((Object)args2[0], (Object)args2[1]);
                } else if (type.equals("CL")) {
                    classMap.forcePut((Object)args2[0], (Object)args2[1]);
                } else if (type.equals("FD")) {
                    fieldMap.forcePut((Object)new MappingFieldSrg(args2[0]).copy(), (Object)new MappingFieldSrg(args2[1]).copy());
                } else if (type.equals("MD")) {
                    methodMap.forcePut((Object)new MappingMethod(args2[0], args2[1]), (Object)new MappingMethod(args2[2], args2[3]));
                } else {
                    throw new MixinException("Invalid SRG file: " + input);
                }
                return true;
            }
        });
    }

    @Override
    public MappingField getFieldMapping(MappingField field) {
        if (field.getDesc() != null) {
            field = new MappingFieldSrg(field);
        }
        return (MappingField)this.fieldMap.get((Object)field);
    }
}

