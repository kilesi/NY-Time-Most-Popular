package co.uk.newsapp.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.uk.newsapp.R

class News(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val icon: ImageView = itemView.findViewById(R.id.icon)
    val title: TextView = itemView.findViewById(R.id.title)
    val time: TextView = itemView.findViewById(R.id.time)
    val owner: TextView = itemView.findViewById(R.id.owner)
    val post: TextView = itemView.findViewById(R.id.post_preview)
    val item = itemView

}