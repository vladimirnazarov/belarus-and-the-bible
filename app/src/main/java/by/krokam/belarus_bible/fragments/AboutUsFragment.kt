package by.krokam.belarus_bible.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.krokam.belarus_bible.R
import by.krokam.belarus_bible.databinding.FragmentAboutBinding

class AboutUsFragment : Fragment(){

    private lateinit var mBinding: FragmentAboutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentAboutBinding.inflate(layoutInflater)

        mBinding.text.text = Html.fromHtml(resources.getString(R.string.about_us_text))
        mBinding.mailText.setOnClickListener {
            startActivity(Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ssrlab221@gmail.com")))
        }

        return mBinding.root
    }
}