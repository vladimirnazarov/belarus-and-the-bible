package by.krokam.belarus_bible.fragments

import androidx.lifecycle.Observer
import com.github.terrakok.cicerone.androidx.FragmentScreen
import by.krokam.belarus_bible.data.pojo.Exposition
import by.krokam.belarus_bible.recycler.ExpositionAdapter
import by.krokam.belarus_bible.tools.provider
import io.reactivex.schedulers.Schedulers

class ExpositionsFragment : BaseListFragment<Exposition>() {
    private var exps = listOf<Exposition>()

    private  var liveExpositions  = provider.appDao.getExpositions(provider.secs!!.find {
        it.lang == provider.langManager.langNumber
    }!!.id)
    override val adapter = ExpositionAdapter(provider.selectedSection!!).apply {
        onClick = {
            provider.selectedExposition = it
            provider.expos = exps.filter { e -> e.idPoint == it.idPoint }
            provider.rootRouter.navigateTo(FragmentScreen{ExponateFragment()})
        }
    }

    override fun setupObservers() {
        liveExpositions.removeObservers(this)
        liveExpositions.observe(this, Observer { it ->
            startRefresh()
            adapter.items = it!!.filter { it.lang == provider.langManager.langNumber }
            exps = it
            stopRefresh()
        })

    }

    override fun onRefreshStarted() {
        subs.add(provider.apiService.getExpositions()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({
                    it!!
                    provider.appDao.insertExpositions(it)
                }, {
                    stopRefresh()
                    println(it.message)
                })
        )
    }
}