package com.example.retrofitapp.presentation.util

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.retrofitapp.domain.model.Word

@BindingAdapter("definitions")
fun setText(textView: TextView, words: List<Word>?){
    words?.let{
        words ->
        val stringBuilder = StringBuilder()
        for (word in words) {
            stringBuilder.append("word: ${word.word}\n")
            stringBuilder.append("meanings:\n\n")
            for (meaning in word.meanings) {
                stringBuilder.append("\tpart of speech: ${meaning.partOfSpeech}\n")
                stringBuilder.append("\tdefinitions:\n")
                for (definition in meaning.definitions) {
                    stringBuilder.append("\t${meaning.definitions.indexOf(definition) + 1}. ${definition.definition}\n")
                    // Display other fields from the definition object
                    stringBuilder.append("\t\texample: ${definition.example}\n")
                    stringBuilder.append("\t\tantonyms: ${definition.antonyms}\n")
                    stringBuilder.append("\t\tsynonyms: ${definition.synonyms}\n")

                    stringBuilder.append("\t\t// Add any other fields you want to display\n")
                }
                stringBuilder.append("\n")
            }
            stringBuilder.append("\n\n")
        }
        textView.text = stringBuilder.toString()
        stringBuilder.clear()
    }
}

@BindingAdapter("progressBarVisibility")
fun setProgressVisibility(progressBar: ProgressBar, status: Boolean?) {
    status?.let { loading ->
        if (loading) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("textViewVisibility")
fun setTextViewVisibility(textView: TextView, status: Boolean?) {
    status?.let { loading ->
        if (loading) {
            textView.visibility = View.GONE
        } else {
            textView.visibility = View.VISIBLE
        }
    }
}