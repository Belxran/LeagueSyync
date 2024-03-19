package com.example.leaguesyync

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*

class RegisterUsuarioManager(private val context: Context) {

    companion object {
        private const val JSON_FILE_NAME = "usuarios.json"
        private const val ASSETS_JSON_FILE_NAME = "usuarios.json"
        private const val TAG = "RegistroUsuarioManager"
    }

    private var usuariosRegistrados: MutableList<Usuario> = mutableListOf()

    init {
        cargarUsuariosDesdeAssets()
        cargarUsuariosDesdeArchivo()
    }

    private fun cargarUsuariosDesdeAssets() {
        try {
            val inputStream: InputStream = context.assets.open(ASSETS_JSON_FILE_NAME)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)
            val gson = Gson()
            val tipoListaUsuario = object : TypeToken<List<Usuario>>() {}.type
            usuariosRegistrados = gson.fromJson(json, tipoListaUsuario)
        } catch (e: IOException) {
            Log.e(TAG, "Error al cargar usuarios desde assets: ${e.message}")
        }
    }

    private fun cargarUsuariosDesdeArchivo() {
        try {
            val jsonFile = File(context.filesDir, JSON_FILE_NAME)
            if (jsonFile.exists()) {
                val bufferedReader = BufferedReader(FileReader(jsonFile))
                val gson = Gson()
                val tipoListaUsuario = object : TypeToken<List<Usuario>>() {}.type
                val usuariosDesdeArchivo: List<Usuario> = gson.fromJson(bufferedReader, tipoListaUsuario)
                usuariosRegistrados.addAll(usuariosDesdeArchivo)
            } else {
                guardarUsuariosEnArchivo()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error al cargar usuarios desde archivo: ${e.message}")
        }
    }

    private fun guardarUsuariosEnArchivo() {
        try {
            val jsonUsuarios = Gson().toJson(usuariosRegistrados)
            val jsonFile = File(context.filesDir, JSON_FILE_NAME)
            val fileWriter = FileWriter(jsonFile)
            fileWriter.write(jsonUsuarios)
            fileWriter.close()
        } catch (e: Exception) {
            Log.e(TAG, "Error al guardar usuarios en archivo: ${e.message}")
        }
    }

    fun getUsuariosRegistrados(): List<Usuario> {
        return usuariosRegistrados
    }

    fun registrarNuevoUsuario(nuevoUsuario: Usuario) {
        usuariosRegistrados.add(nuevoUsuario)
        guardarUsuariosEnArchivo()
    }
}
