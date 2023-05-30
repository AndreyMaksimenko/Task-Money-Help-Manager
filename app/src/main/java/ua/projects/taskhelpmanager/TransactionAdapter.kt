package ua.projects.taskhelpmanager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class TransactionAdapter (private val transactionList: ArrayList<TransactionModel>) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTransaction = transactionList[position]
        holder.tvTransactionTitle.text = currentTransaction.title

        if (currentTransaction.type == 1){
            holder.tvTransactionAmount.setTextColor(Color.parseColor("#FFBB86FC"))
        }else{
            holder.tvTransactionAmount.setTextColor(Color.parseColor("#2ec4b6"))
        }
        holder.tvTransactionAmount.text = currentTransaction.amount.toString()

        holder.tvCategory.text = currentTransaction.category

        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val result = Date(currentTransaction.date!!)
        holder.tvDate.text = simpleDateFormat.format(result)

        if (currentTransaction.type == 1){
            holder.typeIcon.setImageResource(R.drawable.ic_moneyout_svgrepo_com)
        }else{
            holder.typeIcon.setImageResource(R.drawable.ic_moneyin_svgrepo_com)
        }
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    class ViewHolder(itemView: View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvTransactionTitle: TextView = itemView.findViewById(R.id.tvTransactionTitle)
        val tvTransactionAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
        val typeIcon: ImageView = itemView.findViewById(R.id.typeIcon)

        init {
            itemView.setOnClickListener {
                @Suppress("DEPRECATION")
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}
