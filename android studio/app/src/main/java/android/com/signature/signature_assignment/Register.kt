package android.com.signature.signature_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_register.*
import java.lang.StringBuilder

class Register : AppCompatActivity() {

    var database = FirebaseDatabase.getInstance().reference
    //var id: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        database.addValueEventListener(retrieveData)
        database.addListenerForSingleValueEvent(retrieveData)
        insertbtn.setOnClickListener{
            register_lp()
        }

        var getdata = object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    private fun register_lp() {
        var license_plate = edit_lp.text.toString()

        if (license_plate.isEmpty()) {
            edit_lp.error = "Please Enter your License Plate to Register"
            edit_lp.requestFocus()
            return
        }
        else {
            //id += 1
            //database.child(id.toString()).setValue(licenseplate(license_plate)).addOnCompleteListener {
            database.push().setValue(licenseplate(license_plate)).addOnCompleteListener {
                edit_lp.setText("")
                Toast.makeText(baseContext, "License Plate Registered !",
                    Toast.LENGTH_SHORT).show()
                Toast.makeText(baseContext, "Press Back to go to Garage Opener",
                    Toast.LENGTH_LONG).show()
            }

        }
    }

    var retrieveData = object: ValueEventListener {
        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            var sb = StringBuilder()
            for (i in snapshot.children) {
                var plateno = i.child("plate").value
                sb.append("$plateno\n")
            }
            showingdb.setText(sb)
        }
    }


}