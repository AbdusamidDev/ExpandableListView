package developer.abdusamid.expendlistview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import developer.abdusamid.expendlistview.R
import kotlinx.android.synthetic.main.child_group.view.*
import kotlinx.android.synthetic.main.item_group.view.*

class ExpandedAdapter(private var titleList: List<String>, var map: HashMap<String, List<String>>) :
    BaseExpandableListAdapter() {
    override fun getGroupCount(): Int = titleList.size
    override fun getChildrenCount(groupPosition: Int): Int = map[titleList[groupPosition]]!!.size
    override fun getGroup(groupPosition: Int): Any = titleList[groupPosition]
    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        map[titleList[groupPosition]]!![childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()
    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()
    override fun hasStableIds(): Boolean = true
    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val groupItemView: View = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_group, parent, false)
        groupItemView.txt_name.text = titleList[groupPosition]
        return groupItemView
    }


    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val childItemView: View = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.child_group, parent, false)
        val list = map[titleList[groupPosition]]
        val childItem = list?.get(childPosition)
        childItemView.tv_child.text = childItem
        return childItemView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
}