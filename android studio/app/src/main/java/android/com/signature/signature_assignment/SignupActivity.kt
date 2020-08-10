package android.com.signature.signature_assignment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*


class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        signup_createbtn.setOnClickListener {
            signUpUser()
        }


    }
    private fun signUpUser() {
        if (signup_username.text.toString().isEmpty()) {
            signup_username.error = "Please enter Email"
            signup_username.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(signup_username.text.toString()).matches()) {
            signup_username.error = "Please enter a Valid Email"
            signup_username.requestFocus()
            return
        }
        if (signup_password.text.toString().isEmpty()) {
            signup_password.error = "Please enter a Password"
            signup_password.requestFocus()
            return
        }
        if(signup_password_confirm.text.toString() != signup_password.text.toString()) {
            signup_password_confirm.error = " Passwords do not Match !!!"
            signup_password_confirm.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(signup_username.text.toString(), signup_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Sign-up Successful",
                        Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Sign-up failed. Try in sometime",
                        Toast.LENGTH_SHORT).show()
                }

            }
    }


}