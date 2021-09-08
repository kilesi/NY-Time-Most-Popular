package co.uk.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import co.uk.newsapp.adapters.News
import com.koushikdutta.ion.Ion
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private var dataset = arrayListOf<co.uk.newsapp.models.News>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        val newsRecyclerView = findViewById<RecyclerView>(R.id.news)
        newsRecyclerView.layoutManager = LinearLayoutManager(this)

        newsRecyclerView.adapter = News(dataset, this)
        findViewById<SwipeRefreshLayout>(R.id.refresh).setOnRefreshListener {
            fetchData()
        }
        fetchData()
    }

    private fun fetchData() {
        findViewById<SwipeRefreshLayout>(R.id.refresh).isRefreshing = true
        Ion.with(this)
            .load(
                "http://api.nytimes.com/svc/mostpopular/v2/viewed/7.json?api-key=" + getString(
                    R.string.api_key
                )
            )
            .asString()
            .setCallback { e, result ->
                findViewById<SwipeRefreshLayout>(R.id.refresh).isRefreshing = false
                if ((e == null).and(result != "")) {
                    val news = JSONObject(result).getJSONArray("results")
                    for (i in 0 until news.length()) {
                        val post = news.getJSONObject(i)
                        val icon = try {
                            post.getJSONArray("media").getJSONObject(0)
                                .getJSONArray("media-metadata").getJSONObject(0)
                                .getString("url")
                        } catch (ex: Exception) {
                            "https://cdn0.iconfinder.com/data/icons/shift-free/32/Error-512.png"
                        }
                        dataset.add(
                            co.uk.newsapp.models.News(
                                post.getString("published_date"),
                                icon,
                                post.getString("title"),
                                post.getString("abstract"),
                                post.getString("byline")
                            )
                        )
                    }
                    findViewById<RecyclerView>(R.id.news).adapter?.notifyDataSetChanged()
                }

            }
    }
}