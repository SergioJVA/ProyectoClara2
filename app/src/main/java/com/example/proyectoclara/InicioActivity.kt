package com.example.proyectoclara


import android.content.ClipData.Item
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.proyectoclara.Adapter.UsuarioAdapter
import com.example.proyectoclara.Data.ItemsUsuarios
import com.example.proyectoclara.databinding.ActivityInicioBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class InicioActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInicioBinding
    private lateinit var adapterUsuarios : Adapter
    private lateinit var listaUsuarios : ArrayList<ItemsUsuarios>
    var recycler : RecyclerView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // Inicializamos la lista
        listaUsuarios = ArrayList()
        // Inflamos el binding
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.registro.setOnClickListener{
            startActivity(Intent(this,RegistroActivity::class.java))
        }


        // Boton mostrar todos los usuarios
        binding.mostrarUsuario.setOnClickListener{
            cargarDatos()
        }

        // Acceder a la galeria
        binding.photo.setOnClickListener{
            startActivity(Intent(this,Galeria::class.java))
        }




    }


    fun cargarDatos() {
        // Limpiamos la lista de usuarios
        listaUsuarios.clear()
        val db = FirebaseFirestore.getInstance()
        // Obtengo los datos de la base de datos
        db.collection("usuarios")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("A??adiendo Usuarios", "${document.id} => ${document.data}")
                    val usuario = document.toObject(ItemsUsuarios::class.java)
                    listaUsuarios.add(usuario)
                    // Muestro el recyclerView
                    binding.mostrarTodos.layoutManager = LinearLayoutManager(this)
                    binding.mostrarTodos.adapter = UsuarioAdapter(listaUsuarios)
                    // Vuelvo a actualizar el adapter para el borrado
                    var adapter = UsuarioAdapter(listaUsuarios)
                    recycler?.adapter = adapter
                    val position = listaUsuarios.indexOf(ItemsUsuarios())
                    adapter.notifyItemRemoved(position)
                    adapter.notifyDataSetChanged()


                }


            }
            .addOnFailureListener { exception ->
                Log.w("A??adiendo Usuarios", "Error al obtener los usuarios.", exception)
            }


        // Boton para cerrar la sesion de usuario
        binding.cerrarSesion.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            // Volvemos a nuestro Main Activity
            startActivity(Intent(this,MainActivity::class.java))
        }


}



}