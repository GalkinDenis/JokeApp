package ru.denis.financeApp.model

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import kotlinx.android.synthetic.main.web_page.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.denis.financeApp.presenter.Controller
import ru.denis.financeApp.interfaces.ModelInterface
import ru.denis.financeApp.interfaces.WebApi
import ru.denis.financeApp.web_fragment.WebFragment

class Model: ModelInterface {
    private var controller: Controller? = null

    //Сохранение ссылки на Controller.
    override fun attachView(controller: Controller?) {
        this.controller = controller
    }

    //Загрука страници "API icndb.com" в WebView WebFragment`а.
    override fun setPage(webFragment: WebFragment, webView: WebView, link: String) {
        webFragment.webView.loadUrl(link)
    }

    //Загрука сохраненного состояния страници "API icndb.com" в WebView WebFragment`а.
    override fun restorePage(webFragment: WebFragment, webView: WebView, bundle: Bundle) {
        webFragment.webView.restoreState(bundle)
    }

    //Асинхронный запрос на сервер "icndb.com" за получением необходимого количества шуток.
    override fun loadJokes(count: Int) {
        val list = Array(count){""}
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.icndb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(WebApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getJokes(count)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    val items = response.body()?.value
                    if (items != null) {
                        for (i in 0 until items.count()) {
                            list[i] = response.body()?.value?.get(i)?.joke.toString()
                        }
                    }else{
                        Log.e("RETROFIT_ERROR", response.code().toString())

                    }
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
                controller?.throwList(list)
            }
        }
    }
}
