package ru.denis.financeApp.joke_fragment;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.denis.financeApp.presenter.Controller
import ru.denis.financeApp.databinding.ItemBinding

//Адаптер списка шуток.
class JokeAdapter(private var s: Array<String>) :
    RecyclerView.Adapter<JokeAdapter.TrendViewHolder>() {

    //Биндинг элементов списка.
    class TrendViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    //Определение длины списка.
    override fun getItemCount(): Int {
        return s.size
    }

    //Раздутие макета.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendViewHolder {
        return TrendViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    //Инициализация элементов списка.
    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.binding.joke.text = s[position]
    }
    }
