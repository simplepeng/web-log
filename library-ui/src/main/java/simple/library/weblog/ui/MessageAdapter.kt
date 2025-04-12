package simple.library.weblog.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class MessageAdapter(
    private val messageList: List<String>
) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    override fun getItemCount() = messageList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvItem.text = messageList[position]
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItem by lazy { itemView.findViewById<TextView>(R.id.tvItem) }
    }
}