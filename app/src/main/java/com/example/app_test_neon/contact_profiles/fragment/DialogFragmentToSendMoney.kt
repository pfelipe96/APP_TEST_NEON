package com.example.app_test_neon.contact_profiles.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.app_test_neon.R
import com.example.app_test_neon.extensions.loadImage
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.dialog_fragment_to_send_money.*

class DialogFragmentToSendMoney : DialogFragment() {

    private val dispatch = PublishSubject.create<Bundle>()

    companion object {
        private const val ARGUMENT_NAME = "ARGUMENT_NAME"
        private const val ARGUMENT_PHONE = "ARGUMENT_PHONE"
        private const val ARGUMENT_ID = "ARGUMENT_ID"
        private const val ARGUMENT_IMAGE = "ARGUMENT_IMAGE"

        fun newInstance(name: String, phone: String, id: Int, image: String) =
            DialogFragmentToSendMoney().apply {
                arguments = Bundle().apply {
                    putString(ARGUMENT_NAME, name)
                    putString(ARGUMENT_PHONE, phone)
                    putInt(ARGUMENT_ID, id)
                    putString(ARGUMENT_IMAGE, image)
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_to_send_money, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ThemeDialogCustom)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name_contact_dialog.text = getName()
        phone_contact_dialog.text = getPhone()
        handlerImage(getName(), getImage())

        send_money_in_dialog.setOnClickListener { }
        icon_close.setOnClickListener { dismiss() }
    }

    private fun getName(): String = arguments?.getString(ARGUMENT_NAME) ?: ""
    private fun getPhone(): String = arguments?.getString(ARGUMENT_PHONE) ?: ""
    private fun getImage(): String = arguments?.getString(ARGUMENT_IMAGE) ?: ""
    private fun getID(): Int = arguments?.getInt(ARGUMENT_ID) ?: -1


    @SuppressLint("SetTextI18n")
    private fun handlerImage(name: String, imageProfile: String) {
        if (imageProfile.isEmpty().not())
            icon_profile_dialog.loadImage(imageProfile)
        else {
            val firstWord = name[0]
            val secondWord = name.split(" ")[1][0]
            background_icon_profile_dialog.text = "$firstWord$secondWord"
        }
    }
}