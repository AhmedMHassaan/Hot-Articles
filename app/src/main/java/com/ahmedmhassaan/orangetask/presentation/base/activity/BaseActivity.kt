package com.ahmedmhassaan.orangetask.presentation.base.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmedmhassaan.core.activity.CoreActivity

open class BaseActivity : CoreActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        TransformationConfig.activityReceiverConfig(this);

    }


    /**
     * this method for finishing an current activity and start new activity with new task and clear top of stack
     *
     * @param destinationClass the class of a class to be open
     */
    fun startNewActivity(destinationClass: Class<out AppCompatActivity?>?) {

        try {

            if ((this as Activity).isDestroyed || (this as Activity).isFinishing) {
//                ToastMessage.error(context.applicationContext, "Finished")
                return
            }
            val intent = Intent(this, destinationClass)
            intent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        } catch (e: Exception) {
//            Tools.error(e.message)
        }
    }


}