package com.borntodev.todolist

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.content.IntentSender
import android.content.res.Configuration
import android.content.res.Resources
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.DatePicker
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.annotation.RequiresApi
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.loader.app.LoaderManager
import androidx.savedstate.SavedStateRegistry
import java.io.FileDescriptor
import java.io.PrintWriter

class CreateTaskActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {
    override fun onConfigurationChanged(newConfig: Configuration) {
        TODO("Not yet implemented")
    }

    override fun onLowMemory() {
        TODO("Not yet implemented")
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        TODO("Not yet implemented")
    }

    override fun getLifecycle(): Lifecycle {
        TODO("Not yet implemented")
    }

    override fun getViewModelStore(): ViewModelStore {
        TODO("Not yet implemented")
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        TODO("Not yet implemented")
    }


class CreateTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super<AppCompatActivity>.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_task)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStart() {
        TODO("Not yet implemented")
    }

    override fun onResume() {
        TODO("Not yet implemented")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        TODO("Not yet implemented")
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onPause() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        TODO("Not yet implemented")
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        TODO("Not yet implemented")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        TODO("Not yet implemented")
    }

    override fun registerForContextMenu(view: View) {
        TODO("Not yet implemented")
    }

    override fun unregisterForContextMenu(view: View) {
        TODO("Not yet implemented")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        TODO("Not yet implemented")
    }

    override fun setEnterSharedElementCallback(callback: SharedElementCallback?) {
        TODO("Not yet implemented")
    }

    override fun setExitSharedElementCallback(callback: SharedElementCallback?) {
        TODO("Not yet implemented")
    }

    override fun postponeEnterTransition() {
        TODO("Not yet implemented")
    }

    override fun startPostponedEnterTransition() {
        TODO("Not yet implemented")
    }

    override fun dump(
        prefix: String,
        fd: FileDescriptor?,
        writer: PrintWriter,
        args: Array<out String>?
    ) {
        TODO("Not yet implemented")
    }

    override fun getTheme(): Int {
        TODO("Not yet implemented")
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(id: Int): Dialog? {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
    }
}