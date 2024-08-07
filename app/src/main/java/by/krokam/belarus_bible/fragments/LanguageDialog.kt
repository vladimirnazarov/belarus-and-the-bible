package by.krokam.belarus_bible.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import by.krokam.belarus_bible.app.LangManager
import by.krokam.belarus_bible.databinding.DialogLangBinding
import by.krokam.belarus_bible.tools.provider

class LanguageDialog : AppCompatDialogFragment(){

    private lateinit var mBinding: DialogLangBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DialogLangBinding.inflate(layoutInflater)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.btnBel.setOnClickListener {
            provider.langManager.language = LangManager.Language.BEL
            dismiss()
        }

        mBinding.btnEng.setOnClickListener {
            provider.langManager.language = LangManager.Language.ENG
            dismiss()
        }

        mBinding.btnRus.setOnClickListener {
            provider.langManager.language = LangManager.Language.RUS
            dismiss()
        }
    }
}