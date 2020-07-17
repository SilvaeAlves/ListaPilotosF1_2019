package br.com.pilotosf1.app

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.ShowableListMenu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.log

class DriverListActivity : AppCompatActivity() {

    private lateinit var listaPilotos:RecyclerView
    private lateinit var listaPilotosAdapter:DriverListAdapter
    private lateinit var listapilotosLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_list)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Atualizando......")
        progressDialog.setCancelable(false)
        progressDialog.show()

        Handler().postDelayed({progressDialog.dismiss()}, 3000)

        listaPilotos = findViewById(R.id.recycler_drivers)
        listapilotosLayoutManager = LinearLayoutManager(this)
        LinearLayoutManager.VERTICAL

        listaPilotos.layoutManager = listapilotosLayoutManager
        listaPilotosAdapter =
            DriverListAdapter{openWikipediaLink(it)}
        listaPilotos.adapter = listaPilotosAdapter

        getListaPilotos()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_driver, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    private fun openWikipediaLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.`package` = "com.android.chrome"
        startActivity(intent)
    }

    private fun getListaPilotos() {
        DrivesRespository.getListPilots(
            ::onListDriversFetched,
            ::onError
        )
    }

    private fun onListDriversFetched(driver: List<Driver>) {
        listaPilotosAdapter.updatedrivers(driver)
    }


    private fun onError(){
        Toast.makeText(this, "Por favor verifique sua conexão de internet", Toast.LENGTH_SHORT).show()
    }

}