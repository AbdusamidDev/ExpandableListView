package developer.abdusamid.expendlistview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import developer.abdusamid.expendlistview.adapter.ExpandedAdapter
import developer.abdusamid.expendlistview.cache.MySharedPreference
import developer.abdusamid.expendlistview.models.ArrayProduct
import developer.abdusamid.expendlistview.models.Product
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ArrayProduct.titleArray.add(resources.getString(R.string.mobiles))
        ArrayProduct.titleArray.add(resources.getString(R.string.laptops))
        ArrayProduct.titleArray.add(resources.getString(R.string.com_access))
        ArrayProduct.titleArray.add(resources.getString(R.string.home_enter))
        ArrayProduct.titleArray.add(resources.getString(R.string.tvs_by_brand))
        ArrayProduct.titleArray.add(resources.getString(R.string.kitchen))
        val expandedAdapter = ExpandedAdapter(ArrayProduct.titleArray, ArrayProduct.map)
        expanded_menu.setAdapter(expandedAdapter)
        expanded_menu.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            if (ArrayProduct.map[ArrayProduct.titleArray[groupPosition]]?.get(childPosition) == "add") {
                startActivity(Intent(this, AddActivity::class.java).putExtra("type", groupPosition))
            } else {
                startActivity(
                    Intent(this, DataActivity::class.java).putExtra(
                        "type",
                        groupPosition
                    ).putExtra("name", childPosition)
                )
            }
            true
        }
    }

    override fun onStart() {
        super.onStart()
        MySharedPreference.init(this)
        val arrayProduct: ArrayList<Product> = MySharedPreference.objectString
        loadData(arrayProduct)
        for (i in 0..5) {
            expanded_menu.collapseGroup(i)
        }
    }

    private fun loadData(list: List<Product>) {
        for (i in ArrayProduct.titleArray) {
            ArrayProduct.map[i] = listOf("add")
            val listPosition = ArrayList<String>()
            if (listPosition.size == 0) {
                listPosition.add("add")
            }
            for (product in list) {
                if (product.type == i) {
                    listPosition.add(product.name)
                }
            }
            ArrayProduct.map[i] == listPosition
        }
        println(ArrayProduct.map)
    }
}