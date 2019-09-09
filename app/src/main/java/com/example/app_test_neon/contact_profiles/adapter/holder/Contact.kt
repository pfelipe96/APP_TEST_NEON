package com.example.app_test_neon.contact_profiles.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_test_neon.data.InfoContact
import com.example.app_test_neon.extensions.loadImage
import kotlinx.android.synthetic.main.layout_contact.view.*

class Contact(itemView: View) : RecyclerView.ViewHolder(itemView){
    val name = itemView.name_contact
    val phone = itemView.phone_contact
    val image = itemView.icon_profile

    fun bind(item: InfoContact){
        name.text = item.name
        phone.text = item.phone
        image.loadImage(item.imageProfile)
    }
}