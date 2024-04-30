package com.example.opsc7311


import android.R
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class NotificationActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var textNoNotifications: TextView? = null
    private var imageNotification: ImageView? = null
}
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        recyclerView = findViewById(R.id.recycler_notifications)
        textNoNotifications = findViewById(R.id.noNotoficationsTxt)
        imageNotification = findViewById(R.id.imageView21)

        // Check if there are notifications available
        if (notificationsAvailable()) {
            // Hide "No Notifications" text and image
            textNoNotifications?.visibility = View.GONE
            imageNotification?.visibility = View.GONE

            // Show RecyclerView
            recyclerView?.visibility = View.VISIBLE

            // Set up RecyclerView adapter
            // recyclerView?.setAdapter(adapter);
        } else {
            // Show "No Notifications" text and image
            textNoNotifications?.visibility = View.VISIBLE
            imageNotification?.visibility = View.VISIBLE

            // Hide RecyclerView
            recyclerView?.visibility = View.GONE
        }
    }

    private fun notificationsAvailable(): Boolean {
        // Implement your logic to check if notifications are available
        // For example, check if the notifications list is not empty
        return !notificationList.isNullOrEmpty() // Assuming notificationList is a list of notifications
    }
}*/