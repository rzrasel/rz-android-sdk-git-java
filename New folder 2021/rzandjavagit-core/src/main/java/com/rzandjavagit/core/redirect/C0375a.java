package com.rzandjavagit.core.redirect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import com.rz.librarycore.redirect.RedirectWindow.OnEventListener;

class C0375a {
    protected Thread f1122a;
    private Activity f1123b;
    private Context f1124c;
    private Intent f1125d;
    private Bundle f1126e;
    private Class<?> f1127f;
    private List<Integer> f1128g;
    private Handler f1129h;
    private RedirectWindow.OnEventListener f1130i;
    private boolean f1131j;
    private String f1132k;

    protected C0375a(Activity activity, Context context) {
        this.f1128g = new ArrayList();
        this.f1131j = true;
        this.f1122a = new Thread(new C03732(this));
        this.f1123b = activity;
        this.f1124c = context;
        this.f1128g = new ArrayList();
        this.f1128g.clear();
    }

    protected C0375a m1452a(Bundle bundle) {
        m1446a(C0374a.BUNDLE);
        this.f1126e = bundle;
        return this;
    }

    protected C0375a m1451a() {
        m1446a(C0374a.FLAG);
        return this;
    }

    protected C0375a m1456b() {
        m1446a(C0374a.DISPOSE);
        return this;
    }

    protected void m1453a(Class<?> cls) {
        m1446a(C0374a.EXECUTE);
        this.f1127f = cls;
        m1457c();
    }

    protected void m1454a(Class<?> cls, int i) {
        m1446a(C0374a.EXECUTE);
        this.f1127f = cls;
        this.f1129h = new Handler();
        this.f1131j = true;
        this.f1129h.postDelayed(this.f1122a, (long) i);
    }

    protected void m1455a(Class<?> cls, int i, RedirectWindow.OnEventListener c0376a) {
        m1446a(C0374a.EXECUTE);
        this.f1127f = cls;
        this.f1130i = c0376a;
        this.f1129h = new Handler();
        this.f1131j = false;
        this.f1129h.postDelayed(this.f1122a, (long) i);
    }

    protected void m1457c() {
        Collections.sort(this.f1128g, new C03721(this));
        this.f1125d = new Intent(this.f1124c, this.f1127f);
        for (Integer intValue : this.f1128g) {
            int intValue2 = intValue.intValue();
            if (intValue2 != 1) {
                if (intValue2 == 2) {
                    this.f1125d.putExtras(this.f1126e);
                } else if (intValue2 == 3) {
                    this.f1125d.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                } else if (intValue2 == 4) {
                    this.f1123b.startActivity(this.f1125d);
                } else if (intValue2 == 5) {
                    this.f1123b.finish();
                }
            }
        }
    }

    private void m1446a(C0374a c0374a) {
        this.f1128g.add(Integer.valueOf(c0374a.f1121f));
    }

    class C03732 implements Runnable {
        final C0375a f1114a;

        C03732(C0375a c0375a) {
            this.f1114a = c0375a;
        }

        public void run() {
            if (this.f1114a.f1131j) {
                this.f1114a.m1457c();
                this.f1114a.f1129h.removeCallbacks(this.f1114a.f1122a);
                return;
            }
            this.f1114a.f1131j = this.f1114a.f1130i.onDependencyWait();
            this.f1114a.f1129h.postDelayed(this.f1114a.f1122a, 50);
        }
    }

    class C03721 implements Comparator<Integer> {
        final C0375a f1113a;

        C03721(C0375a c0375a) {
            this.f1113a = c0375a;
        }

        public int m1444a(Integer num, Integer num2) {
            return num.intValue() > num2.intValue() ? 1 : num.intValue() < num2.intValue() ? -1 : 0;
        }

        public int compare(Integer obj, Integer obj2) {
            return m1444a((Integer) obj, (Integer) obj2);
        }
    }

    private enum C0374a {
        INTENT(1),
        BUNDLE(2),
        FLAG(3),
        EXECUTE(4),
        DISPOSE(5);

        private int f1121f;

        private C0374a(int i) {
            this.f1121f = i;
        }
    }
}
