package br.com.pilotosf1.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_drive_list.view.*

class DriverListAdapter(private val listener: (String) -> Unit) : RecyclerView.Adapter<DriverListAdapter.DriverListViewHolder>() {

    var drivers= arrayListOf<Driver>()

    fun updatedrivers(drivers:List<Driver>) {
        this.drivers.addAll(drivers)
        notifyDataSetChanged()
    }

    inner class DriverListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun define(driver: Driver, listener: (String) -> Unit) {
            with(itemView) {
                drive_name.text = driver.givenName
                drive_birth.text = driver.dateOfBirth
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

    override fun onBindViewHolder(holder: DriverListViewHolder, position: Int) {
        holder.define(drivers[position], listener)
    }

}