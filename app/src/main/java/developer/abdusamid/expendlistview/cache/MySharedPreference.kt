package developer.abdusamid.expendlistview.cache

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import developer.abdusamid.expendlistview.models.Product

object MySharedPreference {
    private const val NAME = "cache_file"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var objectString: ArrayList<Product>
        get() = gsonStringToArray(sharedPreferences.getString("object", "[]"))
        set(value) = sharedPreferences.edit {
            it.putString("object", arrayToGsonString(value))
        }

    private fun arrayToGsonString(arrayList: ArrayList<Product>): String {
        return Gson().toJson(arrayList)
    }

    private fun gsonStringToArray(gsonString: String?): java.util.ArrayList<Product> {
        val typeToken = object : TypeToken<ArrayList<Product>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}