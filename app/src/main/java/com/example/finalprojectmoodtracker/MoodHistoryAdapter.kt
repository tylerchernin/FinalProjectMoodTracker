package com.example.finalprojectmoodtracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MoodHistoryAdapter(private var entries: List<SharedPreferencesHelper.MoodEntry>) :
    RecyclerView.Adapter<MoodHistoryAdapter.MoodHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodHistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mood_entry, parent, false)
        return MoodHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoodHistoryViewHolder, position: Int) {
        val entry = entries[position]
        holder.bind(entry)
    }

    override fun getItemCount(): Int = entries.size

    class MoodHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moodTextView: TextView = itemView.findViewById(R.id.textViewMood)
        private val noteTextView: TextView = itemView.findViewById(R.id.textViewNote)
        private val dateTextView: TextView = itemView.findViewById(R.id.textViewDate)

        fun bind(entry: SharedPreferencesHelper.MoodEntry) {
            moodTextView.text = entry.mood
            noteTextView.text = entry.note
            dateTextView.text = formatTimestamp(entry.timestamp)
        }

        private fun formatTimestamp(timestamp: Long): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            return sdf.format(Date(timestamp))
        }
    }

    fun updateEntries(newEntries: List<SharedPreferencesHelper.MoodEntry>) {
        entries = newEntries
        notifyDataSetChanged()
    }
}
