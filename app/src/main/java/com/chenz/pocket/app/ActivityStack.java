package com.chenz.pocket.app;

import android.app.Activity;

import java.util.Stack;

/**
 * description: <Activity管理类>
 *
 * @author Chenz
 * @date 2017/10/31
 */
@Deprecated
public class ActivityStack {

    private static Stack<Activity> sActivityStack;
    private static ActivityStack instance;

    private ActivityStack() {
    }

    public static ActivityStack getInstance() {
        if (instance == null) {
            instance = new ActivityStack();
        }
        return instance;
    }

    public void addActivity(Activity activity) {
        if (sActivityStack == null) {
            sActivityStack = new Stack<>();
        }
        sActivityStack.add(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            sActivityStack.remove(activity);
        }
    }

    /**
     * 删除当前activity
     */
    public void finishActivity() {
        Activity activity = sActivityStack.lastElement();
        sActivityStack.remove(activity);
    }

    public void finishActivt(Class<?> cls) {
        for (Activity a : sActivityStack) {
            if (a.getClass().equals(cls)) {
                sActivityStack.remove(a);
            }
        }
    }

    public void finishAllActivity() {
        if (sActivityStack == null) return;
        for (Activity a : sActivityStack) {
            a.finish();
        }
        sActivityStack.clear();
    }

    /**
     * 获取当前activity
     */
    public Activity getLastActivity() {
        return sActivityStack.lastElement();
    }

    public void exitApp() {

        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
