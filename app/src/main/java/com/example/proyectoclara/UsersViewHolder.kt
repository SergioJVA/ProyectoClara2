package com.example.proyectoclara

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoclara.Data.ItemsUsuarios
import com.example.proyectoclara.databinding.ActivityInicioBinding

class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Crear variables para usar en el ViewHolder
    val imagen = itemView.findViewById<ImageView>(R.id.imagenUsuario)
    val email = itemView.findViewById<TextView>(R.id.email)
    val nombre = itemView.findViewById<TextView>(R.id.nombreUsuario)
    val apellidos = itemView.findViewById<TextView>(R.id.apellidosUsuario)


    fun bind(itemsUsuarios: ItemsUsuarios){

        email.text = itemsUsuarios.email
        nombre.text = itemsUsuarios.Nombre
        apellidos.text = itemsUsuarios.Apellidos




        // Al presionar sobre el usuario para borrarlo
        itemView.setOnLongClickListener() {
            // Ampliar Usuarios
            Log.d("Borrar Usuario", "Usuario pulsado")


                var usuario = Usuarios(itemsUsuarios.Nombre,itemsUsuarios.email,itemsUsuarios.Apellidos,itemsUsuarios.Privilegios)
                if (!usuario.BorrarUsuario(itemsUsuarios.email)) {
                    Log.d("Borrar Usuario", "Usuario borrado correctamente")


                } else {
                    Log.d("Borrar Usuario", "Error al borrar el usuario")
                }



            true
        }

        itemView.setOnClickListener() {
            // Ampliar Usuario
            Log.d("Ampliar Usuario", "Usuario Pulsado")


            true
        }




}
}