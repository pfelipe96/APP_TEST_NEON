package com.example.app_test_neon.contact_profiles.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.app_test_neon.R
import com.example.app_test_neon.data.InfoContact
import com.example.app_test_neon.extensions.loadImage
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.dialog_fragment_to_send_money.*

class DialogFragmentToSendMoney : DialogFragment() {

    private val dispatch: PublishSubject<InfoContact> = PublishSubject.create()

    companion object {
        private const val ARGUMENT_INFO_CONTACT = "INFO_CONTACT"

        fun newInstance(infoContact: InfoContact) =
            DialogFragmentToSendMoney().apply {
                arguments = Bundle().apply {
                    putSerializable(ARGUMENT_INFO_CONTACT, infoContact)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_fragment_to_send_money, container, false)
    }

    fun observableOnClick() : Observable<InfoContact> = dispatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ThemeDialogCustom)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name_contact_dialog?.text = getInfoContact().name
        phone_contact_dialog?.text = getInfoContact().phone
        handlerImage(getInfoContact().name, getInfoContact().imageProfile)

        send_money_in_dialog?.setOnClickListener { dispatch.onNext(getInfoContact()) }
        icon_close?.setOnClickListener { dismiss() }
    }

    private fun getInfoContact(): InfoContact =
        arguments?.getSerializable(ARGUMENT_INFO_CONTACT) as? InfoContact ?: InfoContact()


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