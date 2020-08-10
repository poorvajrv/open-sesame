package android.com.signature.signature_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_dashboard.view.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_dashboard)

    var counter: Int = 0
    markButtonEnable(slot1_in)
    markButtonEnable(slot2_in)
    markButtonDisable(slot1_out)
    markButtonDisable(slot2_out)

    registerbtn.setOnClickListener {
        startActivity(Intent(this, Register::class.java))
        finish()
    }

    slot1_in.setOnClickListener{
        counter +=1
        markButtonDisable(slot1_in)
        markButtonEnable(slot1_out)
        Toast.makeText(this, "Slot 1 is now Filled !", Toast.LENGTH_SHORT).show()

    }
    slot2_in.setOnClickListener{
        counter +=1
        markButtonDisable(slot2_in)
        markButtonEnable(slot2_out)
        Toast.makeText(this, "Slot 2 is now Filled !", Toast.LENGTH_SHORT).show()
    }
    slot1_out.setOnClickListener{
        counter -=1
        markButtonDisable(slot1_out)
        markButtonEnable(slot1_in)
        Toast.makeText(this, "Slot 1 is now Empty !", Toast.LENGTH_SHORT).show()
    }
    slot2_out.setOnClickListener{
        counter -=1
        markButtonDisable(slot2_out)
        markButtonEnable(slot2_in)
        Toast.makeText(this, "Slot 2 is now Empty !", Toast.LENGTH_SHORT).show()
    }


    }

    private fun markButtonDisable(button: Button) {
        button.isEnabled = false
        button.isClickable = false

    }
    private fun markButtonEnable(button: Button) {
        button.isEnabled = true
        button.isClickable = true

    }

    fun onGoButtonClicked(view: View) {
        if (radioGroup.ownerradio.isChecked)
        {
            Toast.makeText(this, "Welcome Home ! Choose the Slot", Toast.LENGTH_SHORT).show()
        }
        if(radioGroup.guestradio.isChecked)
        {
            Toast.makeText(this, "Welcome. Register your License Plate if this is your first time.", Toast.LENGTH_SHORT).show()
        }
    }


}


