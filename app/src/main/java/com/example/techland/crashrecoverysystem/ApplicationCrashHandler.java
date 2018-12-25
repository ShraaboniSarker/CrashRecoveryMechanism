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
            showActivities();
        }

    //    public static void installHandler() {
    //        if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof ApplicationCrashHandler)) {
    //            Thread.setDefaultUncaughtExceptionHandler(new ApplicationCrashHandler());
    //        }
    //    }
    //
    //    private ApplicationCrashHandler() {
    //        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    //    }
    //
    //    @Override
    //    public void uncaughtException(Thread t, Throwable e) {
    //        Log.wtf(TAG, String.format("Exception: %s\n%s", e.toString(), getStackTrace(e)));
    //
    //        // Call the default handler
    //        defaultHandler.uncaughtException(t, e);
    //
    //    }
    //
    //    private String getStackTrace(Throwable e) {
    //        final Writer sw = new StringWriter();
    //        final PrintWriter pw = new PrintWriter(sw);
    //
    //        e.printStackTrace(pw);
    //        String stacktrace = sw.toString();
    //        pw.close();
    //
    //        return stacktrace;
    //    }
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
//        ActivityManager am = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
//        List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
//        Log.d("topActivity", "CURRENT Activity ::" + taskInfo.get(0).topActivity.getClassName());
//        ComponentName componentInfo = taskInfo.get(0).topActivity;
//        componentInfo.getPackageName();
        //Intent intent = new Intent(activity, taskInfo.get(0).topActivity.getClassName().getClass());
        Intent intent = new Intent(activity,topActivity);
        //intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        intent.putExtra("crash", true);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
//                | Intent.FLAG_ACTIVITY_CLEAR_TASK
//                | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(CrashRecoveryApplication.getInstance().getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) CrashRecoveryApplication.getInstance().getBaseContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, pendingIntent);
        activity.finish();
        System.exit(2);

    }

    public void showActivities(){
        ActivityManager m = (ActivityManager) activity.getSystemService( activity.ACTIVITY_SERVICE );
        List<ActivityManager.RunningTaskInfo> runningTaskInfoList =  m.getRunningTasks(10);
        Iterator<ActivityManager.RunningTaskInfo> itr = runningTaskInfoList.iterator();
        while(itr.hasNext()){
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo)itr.next();
            int id = runningTaskInfo.id;
            CharSequence desc= runningTaskInfo.description;
            int numOfActivities = runningTaskInfo.numActivities;
            topActivity = runningTaskInfo.topActivity.getClass();
            Log.i(TAG, "showActivities: ..................."+topActivity);
            break;
        }
    }
    }

