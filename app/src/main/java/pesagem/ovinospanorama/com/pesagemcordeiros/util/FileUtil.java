package pesagem.ovinospanorama.com.pesagemcordeiros.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;


public class FileUtil {

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static File getStorageDir(Context context, String dirName, String fileName) {
        // Get the directory for the app's private pictures directory.
        File path = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), dirName);
        if(!path.mkdirs()){
            Log.e("BalancaReader", "Directory not created");
        }
        File file = new File(path, fileName);
        return file;
    }



}
