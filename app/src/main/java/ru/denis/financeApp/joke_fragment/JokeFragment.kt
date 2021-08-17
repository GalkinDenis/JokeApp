package ru.denis.financeApp.joke_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.denis.financeApp.MainActivity.Companion.list
import ru.denis.financeApp.app.App
import ru.denis.financeApp.databinding.JokeListBinding
import ru.denis.financeApp.interfaces.JokeInterface
import ru.denis.financeApp.presenter.Controller
import javax.inject.Inject

class JokeFragment : Fragment(), JokeInterface {
    @Inject lateinit var controller: Controller
    private var _binding: JokeListBinding? = null
    private val binding get() = _binding!!

    //Инициализация адаптера.
    override fun setAdapter(s: Array<String>) {
        list = s
        binding.recyclerView.adapter = JokeAdapter(s)
    }

    //Сохранение данных списка перед сменой конфигурации экрана.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        when {
            !list.isNullOrEmpty() -> {
                //Сохранение размера списка.
                outState.putString("listSize", list?.size.toString())
                for (i in list!!.indices) {
                    outState.putString("jokeList$i", list!![i])
                }
            }
            else -> return
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)

        //Инициализыция контроллера и модели.
        App.joke?.injectJokeList(this)

        //Передача ссылки на View контроллеру.
        controller.attachView(this)
    }

    //Раздувание макета.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = JokeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        //Восстановление данных списка смены конфигурации экрана.
        if (savedInstanceState != null) {
            //Определение размера списка.
            if(savedInstanceState.getString("listSize", "-") != "-") {
                list = Array(savedInstanceState.getString("listSize", "-").toInt()) { "" }
                for (i in list!!.indices) {
                    //Инициализация списка.
                    list!![i] = savedInstanceState.getString("jokeList$i", "-")
                }
                //Обращение в контроллеру за установкой списка.
                controller.loadRestoreList(list!!)
            }
        }else if(!list.isNullOrEmpty()){
            //Обращение в контроллеру за установкой списка после возврата из фрагмента WebView.
            controller.loadRestoreList(list!!)
        }


        binding.buttonCount.setOnClickListener {
            val count = binding.inputCount.text.toString()
                when{
                    //Обращение в контроллеру за установкой списка в соответствии с
                        //запрашиваемым количеством шуток.
                    count != "" -> controller.setList(count.toInt())
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
