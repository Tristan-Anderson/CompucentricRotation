package com.example.myapplication

//import android.R
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import java.text.DecimalFormat


//import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val NewX : TextView = findViewById(R.id.NewX)
            val NewY : TextView = findViewById(R.id.NewY)
            val oX1 : TextView = findViewById(R.id.oldX)
            val oY1 : TextView = findViewById(R.id.oldY)
            val img : ImageView = findViewById(R.id.imageView)

            // Do something in response to button click
            val X : EditText = findViewById(R.id.OriginX)
            val Y : EditText = findViewById(R.id.OriginY)
            val X1 : EditText = findViewById(R.id.CurrentX)
            val Y1 : EditText = findViewById(R.id.CurrentY)

            val T : EditText = findViewById(R.id.RotDeg)


            val ox1 : Float = X.text.toString().toFloat()
            val oy1 : Float = Y.text.toString().toFloat()
            val x1 : Float = X1.text.toString().toFloat()
            val y1 : Float = Y1.text.toString().toFloat()
            val t : Float = T.text.toString().toFloat()
            val irot : Float = img.rotation.toString().toFloat()
            val origin = arrayOf<Float>(ox1, oy1)
            Log.i("DEBUG", origin[0].toString())
            Log.i("DEBUG", origin[1].toString())


            val absv = arrayOf<Float>(x1,y1)
            Log.i("DEBUG",absv[0].toString())
            Log.i("DEBUG", absv[1].toString())
            val theta: Float = (t * PI/180).toFloat()

            val l = diff2v(absv, origin)
            Log.i("DEBUG",l[0].toString())
            Log.i("DEBUG", l[1].toString())
            val newv_r = arrayOf<Float>(newx(l,theta), newy(l,theta))
            Log.i("DEBUG",newv_r[0].toString())
            Log.i("DEBUG", newv_r[1].toString())
            val newv = add2v(newv_r, origin)
            Log.i("DEBUG",newv[0].toString())
            Log.i("DEBUG", newv[1].toString())

            val relv = diff2v(newv,absv)

            NewX.setText(DecimalFormat("#.##").format(newv[0]).toString())
            NewY.setText(DecimalFormat("#.##").format(newv[1]).toString())
            X1.setText(DecimalFormat("#.##").format(newv[0]).toString())
            Y1.setText(DecimalFormat("#.##").format(newv[1]).toString())

            oX1.setText(DecimalFormat("#.##").format(absv[0]).toString())
            oY1.setText(DecimalFormat("#.##").format(absv[1]).toString())
            img.setRotation(irot+t)
        }

    }

    fun newx(l:Array<Float>, theta: Float): Float {
        val x1 : Float = l[0]
        val y1 : Float = l[1]
        return cos(theta)*x1 + sin(theta)*y1
    }

    fun newy(l: Array<Float>, theta: Float): Float {
        val x1 : Float = l[0]
        val y1 : Float = l[1]
        return -sin(theta) * x1 + cos(theta) * y1
    }

    fun diff2v(v1: Array<Float>, v2: Array<Float>): Array<Float> {
        val x1 : Float = v1[0]
        val y1 : Float = v1[1]
        val x2 : Float = v2[0]
        val y2 : Float = v2[1]
        return arrayOf<Float>(x1 - x2, y1 - y2)

    }

    fun add2v(v1: Array<Float>, v2: Array<Float>): Array<Float> {
        val x1 : Float = v1[0]
        val y1 : Float = v1[1]
        val x2 : Float = v2[0]
        val y2 : Float = v2[1]
        return arrayOf<Float>(x1 + x2,y1 + y2)
    }

    fun relRotate(): Unit {
        setContentView(R.layout.activity_main)
        val NewX : TextView = findViewById(R.id.NewX)
        val NewY : TextView = findViewById(R.id.NewY)

        // Do something in response to button click
        val X : EditText = findViewById(R.id.OriginX)
        val Y : EditText = findViewById(R.id.OriginY)
        val X1 : EditText = findViewById(R.id.CurrentX)
        val Y1 : EditText = findViewById(R.id.CurrentY)
        val T : EditText = findViewById(R.id.RotDeg)


        val ox1 : Float = X.text.toString().toFloat()
        val oy1 : Float = Y.text.toString().toFloat()
        val x1 : Float = X1.text.toString().toFloat()
        val y1 : Float = Y1.text.toString().toFloat()
        val t : Float = T.text.toString().toFloat()
        val origin = arrayOf<Float>(ox1, oy1)
        Log.i("DEBUG", origin[0].toString())
        Log.i("DEBUG", origin[1].toString())


        val absv = arrayOf<Float>(x1,y1)
        Log.i("DEBUG",absv[0].toString())
        Log.i("DEBUG", absv[1].toString())
        val theta: Float = (t * PI/180).toFloat()

        val l = diff2v(absv, origin)
        Log.i("DEBUG",l[0].toString())
        Log.i("DEBUG", l[1].toString())
        val newv_r = arrayOf<Float>(newx(l,theta), newy(l,theta))
        Log.i("DEBUG",newv_r[0].toString())
        Log.i("DEBUG", newv_r[1].toString())
        val newv = add2v(newv_r, origin)
        Log.i("DEBUG",newv[0].toString())
        Log.i("DEBUG", newv[1].toString())

        val relv = diff2v(newv,absv)

        NewX.setText((newv[0]).toString())
        NewY.setText((newv[1]).toString())
    }

}