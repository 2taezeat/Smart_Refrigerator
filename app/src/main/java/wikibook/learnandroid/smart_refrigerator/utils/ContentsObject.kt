package wikibook.learnandroid.smart_refrigerator.utils

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wikibook.learnandroid.smart_refrigerator.repository.Contents


object ContentsObject{
    var contentsObjectList = ArrayList<Contents>()

    fun qwe(fbFirestore : FirebaseFirestore) {
        val tmpList = arrayListOf<Contents>()
        CoroutineScope(Dispatchers.IO).launch {
            fbFirestore.collection("contents")
                .get()
                .addOnSuccessListener { result ->
                    //val resultReverse = result.reversed()
                    for (document in result) {
                        Log.d("fbFirestore", "${document.id} => ${document.data}")
                        val documentId = document.id
                        val documentData = document.data

                        tmpList.add(
                            Contents(
                                kind = documentData["kind"].toString(),
                                location = documentData["location"].toString(),
                                updateTime = documentData["updateTime"].toString(),
                                count = documentData["count"].toString().toInt(),
                                shelfTime = documentData["shelfTime"].toString(),
                                purchaseDate = documentData["purchaseDate"].toString(),
                                image = documentData["image"].toString(),
                                memo = documentData["memo"].toString(),
                                useAi = documentData["useAi"].toString().toBoolean(),
                                id = documentData["id"].toString().toLong()
                            )
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("fbFirestore", "Error getting documents: ", exception)
                }
        }
        contentsObjectList = tmpList

    }


}