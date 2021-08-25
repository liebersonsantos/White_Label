package br.com.liebersonsantos.whitelabel

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by lieberson on 8/11/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltAndroidApp
class CustomApp: Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

    }
}