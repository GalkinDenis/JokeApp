package ru.denis.financeApp.presenter

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import ru.denis.financeApp.interfaces.JokeInterface
import ru.denis.financeApp.interfaces.ModelInterface
import ru.denis.financeApp.web_fragment.WebFragment

class Controller(private val modelView: ModelInterface) : Activity() {
    private var jokeView: JokeInterface? = null
    private var webViews: WebFragment? = null

    //Сохранение ссылки на JokeFragment.
    fun attachView(view: JokeInterface?) {
        jokeView = view
    }

    //Сохранение ссылки на WebFragment.
    fun attachView(view: WebFragment?) {
        webViews = view
    }

    //Восстановление сохраненного списка после смены конфигурации экрана/возврата из WebFragment.
    fun loadRestoreList(list: Array<String>) {
        jokeView?.setAdapter(list)
    }

    //Загрузка шуток через "API icndb.com".
    fun setList(s: Int?) {
        modelView.attachView(this)
        modelView.loadJokes(s!!)
    }

    //Функция вызываемая моделью после получения списка шуток для инициализации адаптера в JokeFragment.
    fun throwList(list: Array<String>) {
        jokeView?.setAdapter(list)
    }

    //Загрука страници "API icndb.com" в WebView WebFragment`а.
    fun loadUrl(web: WebView, link: String){
        webViews?.let { modelView.setPage(it, web, link) }
    }

    //Загрука сохраненного состояния страници "API icndb.com" в WebView WebFragment`а.
    fun restoreStateWebView(web: WebView, bundle: Bundle){
        webViews?.let { modelView.restorePage(it, web, bundle) }
    }
}