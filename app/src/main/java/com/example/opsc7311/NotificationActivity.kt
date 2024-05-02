package com.example.opsc7311

import android.app.Notification
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class NotificationActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var textNoNotifications: TextView
    private lateinit var imageNotification: ImageView
    private val notificationList: List<String> = listOf(

    ) // Add  dummy notification messages here

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        recyclerView = findViewById(R.id.recycler_notifications)
        textNoNotifications = findViewById(R.id.noNotificationsTxt)
        imageNotification = findViewById(R.id.imageView21)

        // Check if there are notifications available
        if (notificationsAvailable()) {
            // Hide "No Notifications" text and image
            textNoNotifications.visibility = View.GONE
            imageNotification.visibility = View.GONE

            // Show RecyclerView
            recyclerView.visibility = View.VISIBLE

            // Set up RecyclerView adapter
            val adapter = NotificationAdapter(notificationList)
            recyclerView.adapter = adapter
        } else {
            // Show "No Notifications" text and image
            textNoNotifications.visibility = View.VISIBLE
            imageNotification.visibility = View.VISIBLE

            // Hide RecyclerView
            recyclerView.visibility = View.GONE
        }
    }

    private fun notificationsAvailable(): Boolean {
        return notificationList.isNotEmpty()
    }
}
