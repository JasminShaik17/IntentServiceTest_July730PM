package cubex.mahesh.intentservicetest

import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ifilter = IntentFilter( )
        ifilter.addAction("image_loading_done")
        registerReceiver(MyReceiver(),ifilter)

        getImage.setOnClickListener {
      var i = Intent(this@MainActivity,MyService::class.java)
      startService(i)
        }
    }

    class  MyService:IntentService("MyService") {
        override fun onHandleIntent(p0: Intent?) {
 var u = URL("https://images.techhive.com/images/article/2016/09/android-old-habits-100682662-primary.idge.jpg")
 var isr = u.openStream()
 var bmp:Bitmap = BitmapFactory.decodeStream(isr)

            var i = Intent( )
            i.setAction("image_loading_done")
            i.putExtra("image",bmp)
            sendBroadcast(i)
          //  unregisterReceiver(MyReceiver())
       }
    }

    class MyReceiver:BroadcastReceiver()
    {
        override fun onReceive(p0: Context?, p1: Intent?) {
   var mActivity = p0 as MainActivity
 var iview = mActivity.findViewById<ImageView>(R.id.iview)
 var bmp = p1!!.getParcelableExtra<Bitmap>("image")
  iview.setImageBitmap(bmp)

        }
    }


} // MainActivity
