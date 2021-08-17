package ru.denis.financeApp.interfaces

import android.os.Bundle
import android.webkit.WebView
import ru.denis.financeApp.presenter.Controller
import ru.denis.financeApp.web_fragment.WebFragment

interface ModelInterface {
    fun attachView(controller: Controller?)
    fun loadJokes(count: Int)
    fun setPage(webFragment: WebFragment, webView: WebView,  link: String)
    fun restorePage(webFragment: WebFragment, webView: WebView, bundle: Bundle)
}