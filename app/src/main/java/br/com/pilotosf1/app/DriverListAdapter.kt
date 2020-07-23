package br.com.pilotosf1.app

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_drive_list.view.*
import java.text.SimpleDateFormat
import java.util.*

class DriverListAdapter(private val listener: (String) -> Unit) : RecyclerView.Adapter<DriverListAdapter.DriverListViewHolder>() {

    var drivers= arrayListOf<Driver>()

    fun updatedrivers(drivers:List<Driver>) {
        this.drivers.addAll(drivers)
        notifyDataSetChanged()
    }

    inner class DriverListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SimpleDateFormat")
        fun define(driver: Driver, listener: (String) -> Unit) {
            with(itemView) {
                drive_name.text = driver.givenName
                val currentDate: String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                val date = SimpleDateFormat("yyyy-MM-dd").parse(driver.dateOfBirth)
                drive_birth.text = SimpleDateFormat("dd/MM/yyyy").format(date)
                drive_nationality.text = driver.nationality

                link_wikipedia.setOnClickListener {
                    listener.invoke(driver.url)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_drive_list, parent, false)
        return DriverListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return drivers.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DriverListViewHolder, position: Int) {
        holder.define(drivers[position], listener)
    }

}