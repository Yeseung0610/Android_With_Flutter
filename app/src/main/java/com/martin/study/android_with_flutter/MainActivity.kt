package com.martin.study.android_with_flutter

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        flutterEngine = FlutterEngine(this)
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        FlutterEngineCache.getInstance().put(CACHED_ENGINE, flutterEngine)

        val createFlutterEngineButton: Button = findViewById(R.id.create_flutter_engine_button)
        createFlutterEngineButton.setOnClickListener {
            startActivity(
                FlutterActivity.createDefaultIntent(this)
            )
        }

        val cacheFlutterEngineButton: Button = findViewById(R.id.cache_flutter_engine_button)
        cacheFlutterEngineButton.setOnClickListener {
            startActivity(
                FlutterActivity.withCachedEngine(CACHED_ENGINE).build(this)
            )
        }
    }

    companion object {
        private const val CACHED_ENGINE = "cached_engine"

        private lateinit var flutterEngine: FlutterEngine
    }
}