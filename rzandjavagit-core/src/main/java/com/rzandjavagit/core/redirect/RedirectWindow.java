package com.rzandjavagit.core.redirect;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class RedirectWindow {
    private Activity f1134a;
    private Context f1135b;
    private static RedirectWindow instance = null;
    private C0375a f1136d;
    private String f1137e;

    public static RedirectWindow getInstance(Activity argActivity, Context argContext) {
        if (instance == null) {
            instance = new RedirectWindow(argActivity, argContext);
        }
        return instance;
    }

    public RedirectWindow(Activity argActivity, Context argContext) {
        this.f1134a = argActivity;
        this.f1135b = argContext;
        this.f1136d = new C0375a(this.f1134a, this.f1135b);
    }

    public RedirectWindow withBundle(Bundle argBundle) {
        this.f1136d.m1452a(argBundle);
        return this;
    }

    public RedirectWindow withFlag() {
        this.f1136d.m1451a();
        return this;
    }

    public RedirectWindow disposeWindow() {
        this.f1136d.m1456b();
        return this;
    }

    public void run(Class<?> argRedirectClass) {
        this.f1136d.m1453a((Class) argRedirectClass);
        return;
    }

    public void run(Class<?> argRedirectClass, int argTimeMilliseconds) {
        this.f1136d.m1454a((Class) argRedirectClass, argTimeMilliseconds);
        return;
    }

    public void run(Class<?> argRedirectClass, int argTimeMilliseconds, OnEventListener argOnEventListener) {
        this.f1136d.m1455a(argRedirectClass, argTimeMilliseconds, argOnEventListener);
        return;
    }

    public interface OnEventListener {
        public boolean onDependencyWait();
    }
}