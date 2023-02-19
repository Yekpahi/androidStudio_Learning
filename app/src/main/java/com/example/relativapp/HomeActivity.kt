package com.example.relativapp
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.stream.DoubleStream.builder
import java.util.stream.IntStream.builder
import java.util.stream.Stream.builder

class HomeActivity : AppCompatActivity() {

    lateinit var listPosts: ListView
    var postsArray = ArrayList<Post>()
    lateinit var adapter: PostsAdapter

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

            listPosts = findViewById<ListView>(R.id.listPosts)
            postsArray = arrayListOf(
            Post("Mon post 1", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image1),
            Post("Post 2", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image2),
            Post("Post 3", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image3),
            Post("Post 4", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image4),
            Post("Post 5", "Les enfants sont fort dans vaurienh dès. Maos ils sont fouts!", R.drawable.image5),
        )
        adapter = PostsAdapter(this, R.layout.item_post, postsArray)
        listPosts.adapter = adapter

        listPosts.setOnItemClickListener { adapterView, view, position, id  ->
          //Toast.makeText(this, "Position: $position", Toast.LENGTH_LONG).show()
            val clickedPost = postsArray[position]
            Intent(this, PostDetailsActivity::class.java).also {
                it.putExtra("titre", clickedPost.title)
                startActivity(it)
            }
      }
        registerForContextMenu(listPosts)
    } // fin onCreate

    //Optiob menu
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
                //Afficher un dialog de confirmation
                showLogoutConfigurationDialog()
                //finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //Fonction du dialog de confirmation
    fun showLogoutConfigurationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Ëtes vous sûrs de vouloir quitter l'appli")
        builder.setPositiveButton("Oui") { dialogInterface, id ->

            //SharePreference remove after deconnection
            val editor = this.getSharedPreferences("app_state", Context.MODE_PRIVATE).edit()
            editor.remove("is_authentificated")
            editor.apply()
            //fin
            finish()
        }
        builder.setNegativeButton("Non") { dialogInterface, id ->
            dialogInterface.dismiss()

        }
        builder.setNeutralButton("Annuler") { dialogInterface, id ->
        dialogInterface.dismiss()
        }
        val alertDialog : AlertDialog = builder.create()
        alertDialog.show()
    }

    // Option menu Fin

    // Context Menu
   override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.list_context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info: AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo
        val position: Int = info.position
        when(item.itemId) {
            R.id.itemShow -> {

            Intent(this, PostDetailsActivity::class.java).also {
                it.putExtra("titre", postsArray[position].title)
                startActivity(it)
            }
            //Toast.makeText(this, "show", Toast.LENGTH_SHORT).show()
            }
            R.id.itemDelete -> {
                postsArray.removeAt(position)
                adapter.notifyDataSetChanged()
                //Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onContextItemSelected(item)
    }

    //Fin Context Menu


}