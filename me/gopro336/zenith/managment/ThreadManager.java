/*
 * Decompiled with CFR 0.152.
 */
package me.gopro336.zenith.managment;

import me.gopro336.zenith.api.util.IGlobals;
import me.gopro336.zenith.feature.FeatureManager;

public class ThreadManager
implements IGlobals {
    ModuleService moduleService = new ModuleService();

    public ThreadManager() {
        this.moduleService.setDaemon(true);
        this.moduleService.start();
    }

    public ModuleService getService() {
        return this.moduleService;
    }

    public static class ModuleService
    extends Thread
    implements IGlobals {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    if (!this.nullCheck()) {
                        ModuleService.yield();
                        continue;
                    }
                    FeatureManager.getToggledFeatures().forEach(module -> {
                        try {
                            module.onThread();
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                    });
                }
                catch (Exception exception) {}
            }
        }
    }
}

