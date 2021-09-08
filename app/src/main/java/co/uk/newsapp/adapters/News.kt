package co.uk.newsapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.uk.newsapp.MainActivity
import co.uk.newsapp.R
import co.uk.newsapp.ReadNews
import co.uk.newsapp.models.News
import com.bumptech.glide.Glide
import org.json.JSONObject

class News(private val dataset: List<News>, private val activity: MainActivity) :
    RecyclerView.Adapter<co.uk.newsapp.viewholders.News>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): co.uk.newsapp.viewholders.News {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.unit_post, parent, false)
        return co.uk.newsapp.viewholders.News(view)
    }

    override fun onBindViewHolder(holder: co.uk.newsapp.viewholders.News, position: Int) {

        holder.title.text = dataset[position].title
        holder.post.text = dataset[position].post
        holder.time.text = dataset[position].time
        holder.owner.text = dataset[position].owner
        holder.item.setOnClickListener {
            val intent = Intent(it.context, ReadNews::class.java)


            val data = JSONObject()

            data.put("title", dataset[position].title)
            data.put("post", dataset[position].post)
            data.put("time", dataset[position].time)
            data.put("owner", dataset[position].owner)
            data.put("icon", dataset[position].icon)

            intent.putExtra("data", data.toString())
            activity.startActivity(intent)
        }

        Glide.with(holder.icon.context).load(dataset[position].icon).into(holder.icon)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}