package com.example.relativapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        /*
       val tvHello = findViewById<TextView>(R.id.tvHello)

       // 1: Recuperer l'email envoyé par l'activityMain


      // 2: afficher l'email dans le tvHello

       tvHello.text = "Bienvenue : $email"
       */
        val email = intent.getStringExtra("email")

        val listPosts = findViewById<ListView>(R.id.listPosts)
        val postsArray = arrayListOf(
            Post("Post 1", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image1),
            Post("Post 2", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image2),
            Post("Post 3", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image3),
            Post("Post 4", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image4),
            Post("Post 5", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image5),
        )
        val adapter = PostsAdapter(this, R.layout.item_post, postsArray)
        listPosts.adapter = adapter

        listPosts.setOnItemClickListener { adapterView, view, position, id  ->
          //Toast.makeText(this, "Position: $position", Toast.LENGTH_LONG).show()
            val clickedPost = postsArray[position]
            Intent(this, PostDetailsActivity::class.java).also {
                it.putExtra("title", clickedPost.title)
                startActivity(it)
            }
      }
    } // fin onCreate

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemAdd -> {
                Toast.makeText(this, "Add new Post", Toast.LENGTH_SHORT).show()
            }
            R.id.itemConfig -> {
                Toast.makeText(this, "Add Configuration", Toast.LENGTH_SHORT).show()

            }
            R.id.itemLogout -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}