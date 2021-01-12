diff --git a/app/src/main/java/com/example/android/trackmysleepquality/sleeptracker/SleepTrackerFragment.kt b/app/src/main/java/com/example/android/trackmysleepquality/sleeptracker/SleepTrackerFragment.kt
index 7744abe..3c26835 100644
--- a/app/src/main/java/com/example/android/trackmysleepquality/sleeptracker/SleepTrackerFragment.kt
+++ b/app/src/main/java/com/example/android/trackmysleepquality/sleeptracker/SleepTrackerFragment.kt
@@ -25,6 +25,7 @@ import androidx.fragment.app.Fragment
 import androidx.lifecycle.Observer
 import androidx.lifecycle.ViewModelProvider
 import androidx.navigation.fragment.findNavController
+import androidx.recyclerview.widget.GridLayoutManager
 import com.example.android.trackmysleepquality.R
 import com.example.android.trackmysleepquality.database.SleepDatabase
 import com.example.android.trackmysleepquality.databinding.FragmentSleepTrackerBinding
@@ -61,6 +62,9 @@ class SleepTrackerFragment : Fragment() {
 
         binding.sleepTrackerViewModel = sleepTrackerViewModel
 
+        val manager = GridLayoutManager(activity, 3)
+        binding.sleepList.layoutManager = manager
+
         val adapter = SleepNightAdapter()
         binding.sleepList.adapter = adapter
 
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
