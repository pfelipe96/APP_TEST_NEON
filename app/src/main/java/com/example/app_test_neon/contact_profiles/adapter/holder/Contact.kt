package com.example.app_test_neon.contact_profiles.adapter.holder

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_test_neon.data.InfoContact
import com.example.app_test_neon.extensions.gone
import com.example.app_test_neon.extensions.loadImage
import com.example.app_test_neon.extensions.show
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.layout_contact.view.*

class Contact(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name = itemView.name_contact
    val phone = itemView.phone_contact
    val image = itemView.icon_profile
    val backgroundImage = itemView.background_icon_profile
    val containerViewContact = itemView.container_view_contact

    fun bind(item: InfoContact, dispatch: PublishSubject<InfoContact>) {
        name.text = item.name
        phone.text = item.phone
        handlerImage(name = item.name, imageProfile = item.imageProfile)
        containerViewContact.setOnClickListener { dispatch.onNext(item) }
    }

    @SuppressLint("SetTextI18n")
    private fun handlerImage(name: String, imageProfile: String) {
        if (imageProfile.isEmpty().not()) {
            image.loadImage(imageProfile)
            image.show()
        } else {
            image.gone()
            val firstWord = name[0]
            val secondWord = name.split(" ")[1][0]
            backgroundImage.text = "$firstWord$secondWord"
        }
    }
}