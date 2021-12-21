package wikibook.learnandroid.smart_refrigerator.utils

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wikibook.learnandroid.smart_refrigerator.repository.Contents
import wikibook.learnandroid.smart_refrigerator.repository.ImageInfo


object ContentsObject{
    var contentsObjectList = ArrayList<Contents>()
    var imageList = ArrayList<ImageInfo>()

    fun getContents(fbFirestore : FirebaseFirestore) {
        val tmpList = arrayListOf<Contents>()
        val tmpImage = arrayListOf<ImageInfo>()

        CoroutineScope(Dispatchers.IO).launch {
            fbFirestore.collection("contents")
                .get()
                .addOnSuccessListener { result ->
                    val resultReverse = result.reversed()
                    val storageRef = Firebase.storage.reference
                    for (document in resultReverse) {
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

                        Log.d("url_id", "${documentData["id"].toString()}")
                        CoroutineScope(Dispatchers.IO).launch {
                            val job1 = launch {
                                val idString = documentData["id"].toString()
                                storageRef.child("images/${idString}.jpg").downloadUrl
                                    .addOnSuccessListener { url ->
                                        Log.d("url", "${url}")
                                        tmpImage.add(ImageInfo(id = idString ,imageUrl = url.toString()))
                                        tmpImage.sortByDescending { it.id }
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.e("storageReference", "Exception: ${exception.message}")
                                    }
                            }
                            job1.join()
                        }

                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("fbFirestore", "Error getting documents: ", exception)
                }
            contentsObjectList = tmpList
            imageList = tmpImage
        }

    }

}