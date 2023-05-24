
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentTeacherHomeBinding
import uz.itteacher.lessonroom.util.entity.User


class TeacherHomeFragment : Fragment(), ClassAdapter.OnItemClickListener,
    StudentsAdapter.OnItemStudentClickListener {
    var students = mutableListOf<User>()
    var classes_s = mutableListOf<String>("All")
    lateinit var adapter_c:ClassAdapter
    lateinit var me:User
    lateinit var binding: FragmentTeacherHomeBinding
    val appDatabase: AppDatabase by lazy {
        AppDatabase.getInstanse(requireContext())
    }
    lateinit var users:MutableList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeacherHomeBinding.inflate(inflater,container,false)
        users = appDatabase.getUserDao().getAllUsers() as MutableList<User>
        if (users!=null) {
            students = users.filter { item -> (item.role == "Student")} as MutableList<User>
            Log.d("st", students.toString())
            for (u in users){
                if (u.status){
                    me=u
                }
            }
        }
        for (c in students){
            classes_s.add(c.group.toString())
        }
        adapter_c = ClassAdapter(requireContext(), classes_s, this@TeacherHomeFragment)
        binding.studentClasses.adapter = adapter_c
        val layoutManager_c =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.studentClasses.setLayoutManager(layoutManager_c)



        var adapter = StudentsAdapter(requireContext(),
            students,
            this@TeacherHomeFragment
        )
        binding.studentsMarks.adapter = adapter
        return binding.root
    }
    override fun onItemClick(position: Int) {
        var got = classes_s[position]
        if (got != "All"){
            var st = students.filter { item -> (item.group==got) } as MutableList<User>
            var adapter = StudentsAdapter(requireContext(),
                st,
                this@TeacherHomeFragment
            )
            binding.studentsMarks.adapter = adapter
        }else{
            var adapter = StudentsAdapter(requireContext(),
                students,
                this@TeacherHomeFragment
            )
            binding.studentsMarks.adapter = adapter
        }
        adapter_c.notifyItemChanged(position)
    }

    override fun onItemClickStudent(position: Int) {
        Log.d("put a ", "mark")
    }

}