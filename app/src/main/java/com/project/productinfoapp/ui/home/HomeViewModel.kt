package com.project.productinfoapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.project.productinfoapp.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: HomeRepository) : ViewModel() {

    val notesLiveData get() = noteRepository.notesLiveData
    val statusLiveData get() = noteRepository.statusLiveData



    fun getAllNotes() {
        viewModelScope.launch {
            noteRepository.getNotes()
        }
    }

}