package co.uk.newsapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import org.json.JSONObject

class ReadNews : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_news)
        initialize()
    }

    private fun initialize() {
        val data = JSONObject(intent!!.extras!!.getString("data")!!)

        findViewById<TextView>(R.id.title).text = data.getString("title")
        findViewById<TextView>(R.id.post_preview).text = data.getString("post")
        findViewById<TextView>(R.id.time).text = data.getString("time")
        findViewById<TextView>(R.id.owner).text = data.getString("owner")
        Glide.with(this).load(data.getString("icon")).into(findViewById(R.id.icon))

    }

}