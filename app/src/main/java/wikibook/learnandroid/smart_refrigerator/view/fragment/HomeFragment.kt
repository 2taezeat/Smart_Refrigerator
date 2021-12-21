package wikibook.learnandroid.smart_refrigerator.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebookfrenzy.carddemo.HomeAdapter
import com.google.firebase.firestore.FirebaseFirestore
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.FragmentHomeBinding
import wikibook.learnandroid.smart_refrigerator.repository.Contents
import wikibook.learnandroid.smart_refrigerator.utils.BottomDialogShow
import wikibook.learnandroid.smart_refrigerator.viewmodels.HomeViewModel




class HomeFragment() : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val lazyActivity by lazy {
        requireActivity()
    }

    private val fbFirestore = FirebaseFirestore.getInstance()
    lateinit var contentsList : ArrayList<Contents>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        contentsList = arrayListOf<Contents>()
        fbFirestore.collection("contents")
            .get()
            .addOnSuccessListener { result ->

                val tmpList = arrayListOf<Contents>()

                for (document in result) {
                    Log.d("fbFirestore", "${document.id} => ${document.data}")
                    val documentId = document.id
                    val documentData = document.data


                    tmpList.add(Contents(
                        kind = documentData["kind"].toString(),
                        location = documentData["location"].toString(),
                        updateTime = documentData["updataTime"].toString(),
                        count = documentData["count"].toString().toInt(),
                        shelfTime = documentData["shelfTime"].toString(),
                        purchaseDate = documentData["purchaseDate"].toString(),
                        image = documentData["image"].toString(),
                        memo = documentData["memo"].toString(),
                        useAi = documentData["useAi"].toString().toBoolean(),
                        id = documentData["id"].toString().toLong()
                    ))
                }
                Log.d("contentsList2", "${tmpList}")
                contentsList.addAll(tmpList)

            }
            .addOnFailureListener { exception ->
                Log.d("fbFirestore", "Error getting documents: ", exception)
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        val homeContentLayout = binding.homeContentCl

        val bundle = arguments
        val locationArrayBundle = bundle?.getStringArrayList("location")

        val homeRecyclerView = binding.homeRecyclerview
        homeRecyclerView.layoutManager = LinearLayoutManager(lazyActivity)

        if (locationArrayBundle == null) {
            homeRecyclerView.adapter = HomeAdapter("","", arrayListOf<String>("All","A","B","C","D","E","F","G","H"), contentsList)
        } else {
            homeRecyclerView.adapter = HomeAdapter("","", locationArrayBundle, contentsList)
        }





        Log.d("contentsList", "${contentsList}")

        binding.homeToolbar.inflateMenu(R.menu.home_toolbar_menu)
        binding.homeToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.home_menu_refrigerator -> {
                    val fragmentManager: FragmentManager = lazyActivity.supportFragmentManager
                    BottomDialogShow.refrigeratorBottomDialogFragmentShow(fragmentManager)
                    true
                }
                R.id.home_menu_search -> {
                    if (binding.homeSearchCl.visibility == View.VISIBLE) {
                        binding.homeSearchCl.visibility = View.GONE
                        //val homeContentLayout = binding.homeContentCl
                        val params: ViewGroup.LayoutParams = homeContentLayout.layoutParams
                        params.height = 1800
                        homeContentLayout.layoutParams = params
                    } else {
                        binding.homeSearchCl.visibility = View.VISIBLE
                        //val homeContentLayout = binding.homeContentCl
                        val params: ViewGroup.LayoutParams = homeContentLayout.layoutParams
                        params.height = 1300
                        homeContentLayout.layoutParams = params
                    }

                    true
                }

                R.id.home_menu_refresh -> {
                    true
                }

                else -> false
            }
        }



        val sortMenus = listOf("Shelf Life", "Update Time")
        val sortAdapter = ArrayAdapter(lazyActivity, R.layout.sort_item, sortMenus)
        binding.homeSortAutoTextview.setAdapter(sortAdapter)

        binding.homeSearchApplyButton.setOnClickListener{
            val itemSelect = binding.homeSearchEditText.text
            val sortSelect = binding.homeSortAutoTextview.text
            val locationSelectList = ArrayList<String>()

            if (binding.homeChipAll.isChecked) locationSelectList.add("All")
            if (binding.homeChipA.isChecked) locationSelectList.add("A")
            if (binding.homeChipB.isChecked) locationSelectList.add("B")
            if (binding.homeChipC.isChecked) locationSelectList.add("C")
            if (binding.homeChipD.isChecked) locationSelectList.add("D")
            if (binding.homeChipE.isChecked) locationSelectList.add("E")
            if (binding.homeChipF.isChecked) locationSelectList.add("F")
            if (binding.homeChipG.isChecked) locationSelectList.add("G")
            if (binding.homeChipH.isChecked) locationSelectList.add("H")


            Log.d("contentsList3", "${contentsList}")


            Log.d("select", "${itemSelect}, ${sortSelect}, ${locationSelectList}")

            //val dataArray = arrayOf( "2010/03/25", "2011/03/25", "2012/03/25", "2010/02/25", "2010/02/28", "2009/12/25", "2030/01/01", "1999/03/25" )
            //dataArray.sort()

            if (sortSelect.toString() == "Shelf Life") {

            } else if (sortSelect.toString() == "update Time") {

            } else {

            }




            homeRecyclerView.adapter = HomeAdapter(itemSelect.toString(), sortSelect.toString(), locationSelectList, contentsList)

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}