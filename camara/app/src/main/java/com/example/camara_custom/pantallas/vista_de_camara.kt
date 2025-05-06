package com.example.camara_custom.pantallas

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.Display.Mode
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random


@Composable
fun PantallaCamara() {
    val lente_a_usar = CameraSelector.LENS_FACING_BACK
    val ciclo_de_vida_dueño = LocalLifecycleOwner.current
    val contexto = LocalContext.current

    val prevista = Preview.Builder().build()
    val vista_prevista = remember { PreviewView(contexto) }
    val camarax_selector = CameraSelector.Builder().requireLensFacing(lente_a_usar).build()
    val capturador_de_imagen = remember { ImageCapture.Builder().build() }

    LaunchedEffect(lente_a_usar) {
        val proveedor_local_camara = contexto.obtenerProveedorDeCamara()
        proveedor_local_camara.unbindAll()
        proveedor_local_camara.bindToLifecycle(
            ciclo_de_vida_dueño,
            camarax_selector,
            prevista,
            capturador_de_imagen
        )
        prevista.setSurfaceProvider(vista_prevista.surfaceProvider)
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Vista de la cámara
        AndroidView(
            factory = { vista_prevista },
            modifier = Modifier.fillMaxSize()
        )

        // Filtro de contorno rosa
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .border(width = 4.dp, color = Color.Magenta.copy(alpha = 0.7f))
        )
        // Efecto de estrellas (Canvas)
        Canvas(modifier = Modifier.fillMaxSize()) {
            repeat(50) {
                val x = Random.nextFloat() * size.width
                val y = Random.nextFloat() * size.height
                drawCircle(
                    color = Color.Yellow,
                    radius = Random.nextFloat() * 5f,
                    center = Offset(x, y)
                )
            }
        }

        // Botón para capturar
        Button(
            onClick = { tomar_foto(capturador_de_imagen, contexto) },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("Capturar con Filtro")
        }
    }
}

// Resto del código (tomar_foto y obtenerProveedorDeCamara) sigue igual...
private suspend fun Context.obtenerProveedorDeCamara(): ProcessCameraProvider =
    suspendCoroutine { continuacion ->
        ProcessCameraProvider.getInstance(this).also { proveedor_camara ->
            proveedor_camara.addListener({
                continuacion.resume(proveedor_camara.get())
            }, ContextCompat.getMainExecutor(this))
        }

    }

private fun tomar_foto(capturador_imagen: ImageCapture, contexto: Context){
    val nombre_archivo = "CapturaFoto.jpeg"

    val valores_del_contenido = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, nombre_archivo)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/nuestra_app")
        }
    }

    val salida_foto = ImageCapture.OutputFileOptions.Builder(
        contexto.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        valores_del_contenido
    ).build()

    capturador_imagen.takePicture(
        salida_foto,
        ContextCompat.getMainExecutor(contexto),
        object: ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                Log.v("CAPTURA_EXITO", "Exito, no ha pasado nada")
            }

            override fun onError(exception: ImageCaptureException) {
                Log.v("CAPTURA_ERROR", "Se identifico el siguiente error: ${exception.message}")
            }

        }
    )


}


