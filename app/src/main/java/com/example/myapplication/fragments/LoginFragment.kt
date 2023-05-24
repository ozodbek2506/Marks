
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentLoginBinding
import com.example.myapplication.util.AppDatabase



class LoginFragment : Fragment() {


    val appDatabase: AppDatabase by lazy {
        AppDatabase.getInstanse(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.login.setOnClickListener {
            val login = binding.uLogin.text.toString()
            val password = binding.uPassword.text.toString()
            var user = appDatabase.getUserDao().login(login, password)
            for (u in appDatabase.getUserDao().getAllUsers()){
                u.status=false
                appDatabase.getUserDao().setActive(u)
            }
            if (user!=null){
                if (user.role=="Teacher"){
                    user.status=true
                    appDatabase.getUserDao().setActive(user)
                    findNavController().navigate(R.id.action_loginFragment_to_teacherHomeFragment)
                }else{
                    user.status=true
                    appDatabase.getUserDao().setActive(user)
                    findNavController().navigate(R.id.action_loginFragment_to_studentHomeFragment)
                }
            }
        }

        binding.registration.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        return binding.root
    }
}