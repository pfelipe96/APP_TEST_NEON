package com.example.app_test_neon.contact_profiles.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_test_neon.R
import com.example.app_test_neon.contact_profiles.adapter.holder.Contact
import com.example.app_test_neon.data.InfoContact
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AdapterContacts : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listData: List<InfoContact> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return Contact(inflater.inflate(R.layout.layout_contact, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val contact = listData[position]
        if (holder is Contact) holder.bind(contact)
    }

    override fun getItemCount(): Int = listData.size

    fun setAdapter(single: Single<List<InfoContact>>) {
        single
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.isEmpty().not() }
            .doOnSuccess {
                this.listData = it
                notifyDataSetChanged()
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}