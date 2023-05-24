
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class ClassAdapter(val context:Context, val users_list: MutableList<String>, private val listener: OnItemClickListener): RecyclerView.Adapter<ClassAdapter.MyHolder>() {
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var myHolder = MyHolder(LayoutInflater.from(parent.context).inflate( R.layout.classes_item, parent, false))
        return myHolder
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var product = users_list.get(position)
        holder.text1.text = product
    }

    override fun getItemCount(): Int {
        return users_list.size
    }
    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var text1: TextView = itemView.findViewById<TextView>(R.id.group_names)
        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position!=RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}