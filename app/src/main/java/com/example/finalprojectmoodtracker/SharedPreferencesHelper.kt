package com.example.finalprojectmoodtracker

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHelper {

    private const val PREFERENCES_FILE_KEY = "com.example.finalprojectmoodtracker.PREFERENCE_FILE_KEY"
    private const val MOOD_ENTRIES_KEY = "MOOD_ENTRIES_KEY"

    data class MoodEntry(val mood: String, val note: String, val timestamp: Long)

    fun saveMoodEntry(context: Context, moodEntry: MoodEntry) {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val newEntryKey = System.currentTimeMillis().toString()
        editor.putString(newEntryKey, "${moodEntry.mood}|${moodEntry.note}|${moodEntry.timestamp}")
        editor.apply()
    }

    fun getAllMoodEntries(context: Context): List<MoodEntry> {
        val sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
        val entries = mutableListOf<MoodEntry>()
        sharedPreferences.all.forEach { (key, value) ->
            if (value is String) {
                val parts = value.split("|")
                if (parts.size == 3) {
                    val mood = parts[0]
                    val note = parts[1]
                    val timestamp = parts[2].toLongOrNull()
                    if (timestamp != null) {
                        entries.add(MoodEntry(mood, note, timestamp))
                    }
                }
            }
        }
        return entries.sortedBy { it.timestamp }
    }
}
