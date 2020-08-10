package android.com.signature.signature_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        signupbtn.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
        }

        loginbtn.setOnClickListener {
            doLogin()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun doLogin() {
        if (login_username.text.toString().isEmpty()) {
            login_username.error = "Please enter Email"
            login_username.requestFocus()
            return
        }
        if (!(Patterns.EMAIL_ADDRESS.matcher(login_username.text.toString())).matches()) {
            login_username.error = "Please enter a Valid Email"
            login_username.requestFocus()
            return
        }
        if (login_password.text.toString().isEmpty()) {
            login_password.error = "Please enter a Password"
            login_password.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(login_username.text.toString(), login_password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(baseContext, "I updated User inside Sign in With Email and Password",
                        Toast.LENGTH_SHORT).show()
                    val user: FirebaseUser? = auth.currentUser
                    updateUI(user)
                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(baseContext, "Login failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
        }


    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if(currentUser != null ) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
        else {
            Toast.makeText(baseContext, "Credentials cannot be verified.",
                Toast.LENGTH_SHORT).show()
        }
    }

}