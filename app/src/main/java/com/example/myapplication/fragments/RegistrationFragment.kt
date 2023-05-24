

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRegistrationBinding
import com.example.myapplication.util.AppDatabase
import uz.itteacher.lessonroom.util.entity.User


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrationFragment : Fragment() {

    private lateinit var db: AppDatabase
    var user_role: String =""
    var user_grade: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = AppDatabase.getInstanse(requireContext())
        val binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val roles = resources.getStringArray(R.array.roles)
        val grades = resources.getStringArray(R.array.grades)


        binding.regRole.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                user_role = roles[position]
                if (user_role == "Teacher"){
                    binding.subject.visibility = View.VISIBLE
                }else if (user_role=="Student"){
                    binding.regGrade.visibility = View.VISIBLE
                    binding.groupName.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            }
        binding.regGrade.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                user_grade = grades[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }



        binding.regRegistration.setOnClickListener {
            val userName = binding.regUserName.text.toString()
            val login = binding.regLogin.text.toString()
            val password = binding.regPassword.text.toString()
            val rePassword = binding.regRepassword.text.toString()
            val subject = binding.subject.text.toString()
            var group_name = binding.groupName.text.toString()

            if (validate(userName, login, password, rePassword, subject, user_grade, group_name)) {
                db.getUserDao().addUser(
                    User(
                        userName = userName,
                        login = login,
                        password = password,
                        role = user_role,
                        grade = user_grade,
                        subject = subject,
                        group = group_name,
//                        mark = mutableListOf(Marks(1,1, 2), Marks(2,2, 3))
                    )
                )
                findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
            }
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrationFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun validate(u:String,l:String,p:String,r:String, u_subject:String, user_grade:String, st_class:String):Boolean{
        var isValidate = true
        var message = "Ro'yhatdan muofaqqatli o'tdingiz"
        if (u.isEmpty() || l.isEmpty() || p.isEmpty()){
            isValidate = false
            message = "Ma'lumotlarni to'liq kiriting"
        }
        if (db.getUserDao().isLogin(l)!=null){
            isValidate = false
            message = "Bunday login tizimda mavjud"
        }

        if (r!=p){
            isValidate = false
            message = "Takroriy parol xato kiritildi"
        }

        if (user_role=="Tanlang"){
            isValidate = false
            message = "Tizimdagi rolni tanlang"
        }else{
            if (user_role=="Student" && (st_class=="" || user_grade=="Sinfingiz")){
                isValidate = false
                message = "Sinfingizni kiriting"
            }
            if (user_role=="Teacher" && u_subject==""){
                isValidate = false
                message = "Qaysi fandan o'tishingizni kiriting"
            }
        }
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG).show()
        return isValidate
    }
}