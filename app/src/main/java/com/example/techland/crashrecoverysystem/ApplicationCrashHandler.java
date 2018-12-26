    package com.example.techland.crashrecoverysystem;

    import android.app.Activity;
    import android.app.ActivityManager;
    import android.app.AlarmManager;
    import android.app.PendingIntent;
    import android.content.ComponentName;
    import android.content.Context;
    import android.content.Intent;
    import android.util.Log;

    import java.io.PrintWriter;
    import java.io.StringWriter;
    import java.io.Writer;
    import java.util.Iterator;
    import java.util.List;

    import static android.content.Context.ACTIVITY_SERVICE;

    public class ApplicationCrashHandler implements Thread.UncaughtExceptionHandler {

        private Activity activity;
        private Thread.UncaughtExceptionHandler defaultHandler;
        public static Class topActivity;

        private static final String TAG = "ApplicationCrashHandler";

        public ApplicationCrashHandler(Activity activity) {
            this.activity = activity;
            //showActivities();
        }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        Intent intent = new Intent(activity, CustomErrorActivity.class);
        intent.putExtra("crash", true);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//                | Intent.FLAG_ACTIVITY_CLEAR_TASK
//                | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(CrashRecoveryApplication.getInstance().getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) CrashRecoveryApplication.getInstance().getBaseContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 10, pendingIntent);
        activity.finish();
        System.exit(2);


//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        activity.finish();
//        activity.startActivity(intent);
    }


    }

