package com.example.opsc7311

import android.content.Context
import android.content.Intent
import android.os.Bundle

fun openIntent(context: Context, activityToOpen: Class<*>)
{
    val intent = Intent(context, activityToOpen)
    //creates an intenet object that will request that a page be opened

    //put "parameter" value instead of "order"
    //intent.putExtra("parameter", parameter)

    context.startActivity(intent)
}

fun shareIntent(context: Context, parameter: String)
{
    var sendIntent = Intent()
    //an intent is a code object that is responsible for >
    //>requesting that and action be done by the application

    sendIntent.setAction(Intent.ACTION_SEND)
    //this line sets the action of the intent as sending [something?] to [somewhere?]

    sendIntent.setType("text/plain")

    var shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}