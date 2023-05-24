
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import uz.itteacher.lessonroom.util.entity.User

class StudentsAdapter(val context:Context, val users_list: MutableList<User>, private var listener: OnItemStudentClickListener): RecyclerView.Adapter<StudentsAdapter.MyHolder>() {
    inner class MyHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var text1: TextView = itemView.findViewById<TextView>(R.id.student_name)
        var text2: TextView = itemView.findViewById<TextView>(R.id.student_group)
        var text3: EditText = itemView.findViewById<EditText>(R.id.mark)
        init{
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position!=RecyclerView.NO_POSITION){
                listener.onItemClickStudent(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var myHolder = MyHolder(LayoutInflater.from(parent.context).inflate( R.layout.student_item, parent, false))
        return myHolder
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var product = users_list.get(position)
        holder.text2.text = product.group.toString()
        holder.text1.text = product.userName

    }

    override fun getItemCount(): Int {
        return users_list.size
    }
    interface OnItemStudentClickListener {
        fun onItemClickStudent(position: Int)
    }
}