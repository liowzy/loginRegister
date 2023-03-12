package my.edu.tarc.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import my.edu.tarc.loginregister.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView4.setOnClickListener{
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignIn4.setOnClickListener {
            val email = binding.emailEt2.editableText.toString()
            val pass = binding.passwordEt5.editableText.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() )
            {
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }else  {
                        Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT ).show()

                    }
                }


            }else{

                Toast.makeText(this,"Empty Fields Are Not Allowed!", Toast.LENGTH_SHORT ).show()
            }

        }

    }
}