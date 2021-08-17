package ru.denis.financeApp.web_fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.webkit.WebView
import androidx.fragment.app.Fragment
import ru.denis.financeApp.app.App
import ru.denis.financeApp.databinding.WebPageBinding
import ru.denis.financeApp.interfaces.interfaceFavorites
import ru.denis.financeApp.presenter.Controller
import javax.inject.Inject

//Фрагмент страницы используемого API.
@SuppressLint("SetJavaScriptEnabled")
class WebFragment : Fragment() {
    @Inject lateinit var controller: Controller
    private var _binding: WebPageBinding? = null
    private val binding get() = _binding!!

    //Сохранение состояния страници WebView.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        binding.webView.saveState(bundle)
        outState.putBundle("webViewState", bundle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
            //WebView.setWebContentsDebuggingEnabled(false)

        //Инициализыция контроллера и модели.
        App.web?.injectWebFragment(this)

        //Передача ссылки на View контроллеру.
        controller.attachView(this)
    }

    //Раздувание макета.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WebPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            //Загрука сохраненного состояния страници "API icndb.com" в WebView WebFragment`а.
            savedInstanceState.getBundle("webViewState")?.let { controller.restoreStateWebView(binding.webView, it) }
        }else {
            //Загрука страници "API icndb.com" в WebView WebFragment`а.
            controller.loadUrl(binding.webView, "http://www.icndb.com/api/")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
