package com.example.nikita.profitmanager.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.nikita.profitmanager.components.MyViewHolder
import com.example.nikita.profitmanager.R
import com.example.nikita.profitmanager.models.CoinCap
import kotlinx.android.synthetic.main.item_coin.view.*
import java.text.DecimalFormat

class CoinAdapter: RecyclerView.Adapter<MyViewHolder>() {
    private var df : DecimalFormat = DecimalFormat("0.00")
    //устанавливаем в список поля через сеттер
    var mData:List<CoinCap>?=null
        set(value) {
            field=value
            notifyDataSetChanged()
        }

    //используем вьюхолдер для держания ссылок списка вместо FVBI
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false))
    }


    override fun getItemCount(): Int = mData?.size?:0

    //получаем количество элементов в списке
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //присваиваем полям списка значения из JACKSONE
        mData?.let {
            val coin=it[position]
            holder.view.tvSymbol.text=coin.symbol
            holder.view.tvName.text=coin.name
//            holder.view.tvPrice.text="$${coin.price_usd}"
            holder.view.tvPrice.text= "$${df.format(coin.price_usd)}"
            holder.view.tvPriceChange_24h.text = if (coin.percent_change_24h > 0) "▲ +${coin.percent_change_24h}%"  else "▼  ${coin.percent_change_24h}%"
            holder.view.tvPriceChange_7d.text = if (coin.percent_change_7d > 0) "▲ +${coin.percent_change_7d}%" else "▼  ${coin.percent_change_7d}%"
            if (coin.percent_change_24h > 0) {
                //holder.view.tvPriceChange_24h.setBackgroundResource(R.color.Green)
                holder.view.tvPriceChange_24h.setTextColor(Color.GREEN)

            }
            else {
                //holder.view.tvPriceChange_24h.setBackgroundResource(R.color.colorAccent)
                holder.view.tvPriceChange_24h.setTextColor(Color.RED)
            }

            if (coin.percent_change_7d > 0) {
                //holder.view.tvPriceChange_24h.setBackgroundResource(R.color.Green)
                holder.view.tvPriceChange_7d.setTextColor(Color.GREEN)

            }
            else {
                //holder.view.tvPriceChange_24h.setBackgroundResource(R.color.colorAccent)
                holder.view.tvPriceChange_7d.setTextColor(Color.RED)
            }
        }
    }
}
