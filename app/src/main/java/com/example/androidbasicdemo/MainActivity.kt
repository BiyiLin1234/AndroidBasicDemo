package com.example.androidbasicdemo

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val REQUEST_CODE_CAMERA = 666
    }

    private lateinit var uriTypes: Array<String>
    private lateinit var spinner: Spinner

    enum class URIPrefix(val s: String) {
        TEL("Telephone"),
        BROW("Browser"),
    }

    private val uriPrefixMap = hashMapOf(
        URIPrefix.TEL.s to "tel:",
        URIPrefix.BROW.s to "https://",
    )

    // Activity创建
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    // override's shortcut: CTRL + O
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item -> Toast.makeText(this, "add clicked", Toast.LENGTH_SHORT).show()
            R.id.remove_item -> Toast.makeText(this, "remove clicked", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun handleResult(activityResult: ActivityResult) {
        if (activityResult.resultCode == RESULT_OK) {
            val imageBitmap: Bitmap =
                (activityResult.data?.extras?.get("data") as? Bitmap?) ?: return
            findViewById<ImageView>(R.id.image_view).setImageBitmap(imageBitmap)
        }
    }

    private fun initView() {
        initGreet()
        initOpenSecondActivityBtn()
        initOpenSingleInstanceBtn()
    }

    private fun initOpenSingleInstanceBtn() {
        findViewById<Button>(R.id.open_single_instance_activity).setOnClickListener { view ->
            startActivity(Intent(this, SingleInstanceActivity::class.java))
        }
    }

    private fun initSpinner() {
        val types: Array<String> = arrayOf(URIPrefix.TEL.s, URIPrefix.BROW.s)
        spinner = findViewById(R.id.uri_type)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, types)
        spinner.setSelection(0) // 默认设置为0，telephone
    }

    private fun initGreet() {
        // click to switch greet content and greet target.
        findViewById<TextView>(R.id.greet_tv).let {
            it.setOnClickListener {
                (it as? TextView)?.let { tv ->
                    // Android Log Util
                    Log.d(TAG, "greet_tv onclick")
                    if (Objects.equals(tv.text, resources.getString(R.string.greet))) {
                        tv.text = resources.getString(R.string.default_user_name)
                    } else {
                        tv.text = resources.getString(R.string.greet)
                    }
                }
            }
            // LifecycleOwner xxx is attempting to register while current state is RESUMED. LifecycleOwners must call register before they are STARTED.
            // 不能在Activity Resume后调用
            val launcher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                    handleResult(activityResult)
                }
            it.setOnLongClickListener { tv ->
                // use new way to get Activity result(the old way startActivityForResult + onActivityResult is deprecated)
                // new way is registerForActivityResult + Lambda callback
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                launcher.launch(intent)
                true
            }
        }
    }

    private fun initOpenSecondActivityBtn() {
        findViewById<Button>(R.id.start_second_activity).setOnClickListener {
            // 显示Intent唤起其他Activity
            val intent = Intent(this, SecondActivity::class.java)
            this.startActivity(intent)
        }

        // 隐式Intent通过<category>标签和<action>标签匹配，不仅可以启动本程序的Activity
        // 还可以启动其他程序的Activity
        // 打开网页
        initSpinner()
        val openUriBtn = findViewById<Button>(R.id.open_uri_btn)
        val inputUri = findViewById<EditText>(R.id.input_uri)
        openUriBtn.setOnClickListener {
            val url = StringBuilder(inputUri.text.toString())
            val prefix = if (uriPrefixMap.containsKey(spinner.selectedItem.toString())) {
                uriPrefixMap[spinner.selectedItem.toString()]
            } else {
                ""
            }
            if (prefix == null) {
                return@setOnClickListener
            }
            if (!url.startsWith(prefix)) {
                url.insert(0, prefix)
            }

            // ActionView用于显示用户数据，根据用户类型打开相应的Activity，比如tel:xxx打开拨号程序
            // http://xxx打开浏览器等。
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url.toString())
                try {
                    startActivity(this)
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "url not correct", Toast.LENGTH_SHORT).show()
                    Log.e(TAG, e.toString())
                }
            }
            RESULT_OK
            inputUri.text.clear()
        }
    }
}