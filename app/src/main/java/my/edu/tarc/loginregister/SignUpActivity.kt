package my.edu.tarc.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import my.edu.tarc.loginregister.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var  firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth=FirebaseAuth.getInstance()

        binding.textViewMessage3.setOnClickListener{
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp3.setOnClickListener {
            val pass = binding.passEt.text.toString()
            val confirmPass = binding.confirmEt.text.toString()
            val email = binding.emailEt.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty())
            {
                if (pass.equals(confirmPass)){
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener {
                        if(it.isSuccessful){
                            val intent = Intent(this,SignInActivity::class.java)
                            startActivity(intent)
                        }else  {
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT ).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT ).show()
                }
            }else{

                Toast.makeText(this,"Empty Fields Are Not Allowed!", Toast.LENGTH_SHORT ).show()
            }

        }
    }
}