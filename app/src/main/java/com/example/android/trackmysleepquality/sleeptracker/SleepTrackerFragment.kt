diff --git a/app/src/main/java/com/example/android/trackmysleepquality/sleeptracker/SleepTrackerFragment.kt b/app/src/main/java/com/example/android/trackmysleepquality/sleeptracker/SleepTrackerFragment.kt
index 7744abe..e69de29 100644
--- a/app/src/main/java/com/example/android/trackmysleepquality/sleeptracker/SleepTrackerFragment.kt
+++ b/app/src/main/java/com/example/android/trackmysleepquality/sleeptracker/SleepTrackerFragment.kt
@@ -1,111 +0,0 @@
-/*
- * Copyright 2018, The Android Open Source Project
- *
- * Licensed under the Apache License, Version 2.0 (the "License");
- * you may not use this file except in compliance with the License.
- * You may obtain a copy of the License at
- *
- *     http://www.apache.org/licenses/LICENSE-2.0
- *
- * Unless required by applicable law or agreed to in writing, software
- * distributed under the License is distributed on an "AS IS" BASIS,
- * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
- * See the License for the specific language governing permissions and
- * limitations under the License.
- */
-
-package com.example.android.trackmysleepquality.sleeptracker
-
-import android.os.Bundle
-import android.view.LayoutInflater
-import android.view.View
-import android.view.ViewGroup
-import androidx.databinding.DataBindingUtil
-import androidx.fragment.app.Fragment
-import androidx.lifecycle.Observer
-import androidx.lifecycle.ViewModelProvider
-import androidx.navigation.fragment.findNavController
-import com.example.android.trackmysleepquality.R
-import com.example.android.trackmysleepquality.database.SleepDatabase
-import com.example.android.trackmysleepquality.databinding.FragmentSleepTrackerBinding
-import com.google.android.material.snackbar.Snackbar
-
-/**
- * A fragment with buttons to record start and end times for sleep, which are saved in
- * a database. Cumulative data is displayed in a simple scrollable TextView.
- * (Because we have not learned about RecyclerView yet.)
- */
-class SleepTrackerFragment : Fragment() {
-
-    /**
-     * Called when the Fragment is ready to display content to the screen.
-     *
-     * This function uses DataBindingUtil to inflate R.layout.fragment_sleep_quality.
-     */
-    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
-                              savedInstanceState: Bundle?): View? {
-
-        // Get a reference to the binding object and inflate the fragment views.
-        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
-                inflater, R.layout.fragment_sleep_tracker, container, false)
-
-        val application = requireNotNull(this.activity).application
-
-        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
-
-        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)
-
-        val sleepTrackerViewModel =
-                ViewModelProvider(
-                        this, viewModelFactory).get(SleepTrackerViewModel::class.java)
-
-        binding.sleepTrackerViewModel = sleepTrackerViewModel
-
-        val adapter = SleepNightAdapter()
-        binding.sleepList.adapter = adapter
-
-        sleepTrackerViewModel.nights.observe(viewLifecycleOwner, Observer {
-            it?.let {
-                adapter.submitList(it)
-            }
-        })
-
-        binding.lifecycleOwner = this
-
-        // Add an Observer on the state variable for showing a Snackbar message
-        // when the CLEAR button is pressed.
-        sleepTrackerViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
-            if (it == true) { // Observed state is true.
-                Snackbar.make(
-                        requireActivity().findViewById(android.R.id.content),
-                        getString(R.string.cleared_message),
-                        Snackbar.LENGTH_SHORT // How long to display the message.
-                ).show()
-                // Reset state to make sure the snackbar is only shown once, even if the device
-                // has a configuration change.
-                sleepTrackerViewModel.doneShowingSnackbar()
-            }
-        })
-
-        // Add an Observer on the state variable for Navigating when STOP button is pressed.
-        sleepTrackerViewModel.navigateToSleepQuality.observe(viewLifecycleOwner, Observer { night ->
-            night?.let {
-                // We need to get the navController from this, because button is not ready, and it
-                // just has to be a view. For some reason, this only matters if we hit stop again
-                // after using the back button, not if we hit stop and choose a quality.
-                // Also, in the Navigation Editor, for Quality -> Tracker, check "Inclusive" for
-                // popping the stack to get the correct behavior if we press stop multiple times
-                // followed by back.
-                // Also: https://stackoverflow.com/questions/28929637/difference-and-uses-of-oncreate-oncreateview-and-onactivitycreated-in-fra
-                this.findNavController().navigate(
-                        SleepTrackerFragmentDirections
-                                .actionSleepTrackerFragmentToSleepQualityFragment(night.nightId))
-                // Reset state to make sure we only navigate once, even if the device
-                // has a configuration change.
-                sleepTrackerViewModel.doneNavigating()
-            }
-        })
-
-        return binding.root
-    }
-}
diff --git a/app/src/main/res/layout/fragment_sleep_tracker.xml b/app/src/main/res/layout/fragment_sleep_tracker.xml
index 8ee40ab..5c0f18f 100644
--- a/app/src/main/res/layout/fragment_sleep_tracker.xml
+++ b/app/src/main/res/layout/fragment_sleep_tracker.xml
@@ -46,8 +46,7 @@
             app:layout_constraintBottom_toTopOf="@+id/clear_button"
             app:layout_constraintEnd_toEndOf="parent"
             app:layout_constraintStart_toStartOf="parent"
-            app:layout_constraintTop_toBottomOf="@+id/stop_button"
-            app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"/>
+            app:layout_constraintTop_toBottomOf="@+id/stop_button"/>
 
         <!-- With data binding and LiveData, we can track the buttons' visibility states
              from the ViewModel. The click handler is in the ViewModel as well, and
diff --git a/app/src/main/res/layout/list_item_sleep_night.xml b/app/src/main/res/layout/list_item_sleep_night.xml
index 1e8f0d3..25c726f 100644
--- a/app/src/main/res/layout/list_item_sleep_night.xml
+++ b/app/src/main/res/layout/list_item_sleep_night.xml
@@ -27,28 +27,14 @@
             tools:srcCompat="@drawable/ic_sleep_5"
             app:sleepImage="@{sleep}"/>
 
-        <TextView
-            android:id="@+id/sleep_length"
-            android:layout_width="0dp"
-            android:layout_height="20dp"
-            android:layout_marginStart="8dp"
-            android:layout_marginTop="8dp"
-            android:layout_marginEnd="16dp"
-            app:layout_constraintEnd_toEndOf="parent"
-            app:layout_constraintStart_toEndOf="@+id/quality_image"
-            app:layout_constraintTop_toTopOf="@+id/quality_image"
-            tools:text="Wednesday"
-            app:sleepDurationFormatted="@{sleep}"/>
-
         <TextView
             android:id="@+id/quality_string"
             android:layout_width="0dp"
-            android:layout_height="20dp"
+            android:layout_height="wrap_content"
             android:layout_marginTop="8dp"
-            app:layout_constraintEnd_toEndOf="@+id/sleep_length"
-            app:layout_constraintStart_toStartOf="@+id/sleep_length"
-            app:layout_constraintTop_toBottomOf="@+id/sleep_length"
-            tools:text="Excellent!!!"
+            app:layout_constraintEnd_toEndOf="@+id/quality_image"
+            app:layout_constraintStart_toStartOf="@+id/quality_image"
+            app:layout_constraintTop_toBottomOf="@+id/quality_image"
             app:sleepQualityString="@{sleep}"/>
     </androidx.constraintlayout.widget.ConstraintLayout>
 </layout>
