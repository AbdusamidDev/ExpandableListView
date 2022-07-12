package developer.abdusamid.expendlistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import developer.abdusamid.expendlistview.cache.MySharedPreference
import developer.abdusamid.expendlistview.models.ArrayProduct
import developer.abdusamid.expendlistview.models.Product
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        val type = intent.getIntExtra("type", 0)
        tv_add.text = ArrayProduct.titleArray[type]
        MySharedPreference.init(this)
        card_add.setOnClickListener {
            val edtName = edt_add_name.text.toString().trim()
            val edtData = edt_add_data.text.toString().trim()
            if (edtData != "" && edtName != "") {
                Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show()
                val product = Product(ArrayProduct.titleArray[type], edtName, edtData)
                val array = MySharedPreference.objectString
                array.add(product)
                MySharedPreference.objectString = array
                finish()
            } else {
                Toast.makeText(this, "Write To The Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}