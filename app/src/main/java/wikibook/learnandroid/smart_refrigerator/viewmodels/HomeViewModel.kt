package wikibook.learnandroid.smart_refrigerator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import wikibook.learnandroid.smart_refrigerator.repository.Contents

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private var _contentsArrayList = MutableLiveData<ArrayList<Contents>>()
    var contentsArrayList: LiveData<ArrayList<Contents>> = _contentsArrayList

}