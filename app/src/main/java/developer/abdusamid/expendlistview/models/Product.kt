package developer.abdusamid.expendlistview.models

class Product(var type: String, var name: String, var data: String) {
    override fun toString(): String {
        return "Product(type='$type', name='$name', data='$data')"
    }
}