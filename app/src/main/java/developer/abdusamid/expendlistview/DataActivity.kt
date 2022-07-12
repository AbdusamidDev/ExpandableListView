package developer.abdusamid.expendlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import developer.abdusamid.expendlistview.cache.MySharedPreference
import developer.abdusamid.expendlistview.models.ArrayProduct
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.item_group.*

class DataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)
        val type = intent.getIntExtra("type", 0)
        val name = intent.getIntExtra("name", 0)
        MySharedPreference.init(this)
        val arrayProduct = MySharedPreference.objectString
        for (product in arrayProduct) {
            if (product.type == ArrayProduct.titleArray[type] && product.name == ArrayProduct.map[ArrayProduct.titleArray[type]]?.get(
                    name
                )
            ) {
                txt_name_data.text = product.name
                txt_data.text = product.data
            }
        }
    }
}