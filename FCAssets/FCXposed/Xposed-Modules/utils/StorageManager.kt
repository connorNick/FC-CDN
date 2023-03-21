import android.os.Environment
import java.io.File

object StorageManager {
    private val saveDir = File(Environment.getExternalStorageDirectory(), "${Environment.DIRECTORY_DOCUMENTS}/hideapps")
        .apply { mkdirs() }

    fun isHideApp(packageName: String): Boolean {
        return File(saveDir, packageName).exists()
    }

    fun setHideApp(packageName: String, e: Boolean): Boolean {
        return if (e) {
            File(saveDir, packageName).createNewFile()
        } else {
            File(saveDir, packageName).delete()
        }
    }
}
