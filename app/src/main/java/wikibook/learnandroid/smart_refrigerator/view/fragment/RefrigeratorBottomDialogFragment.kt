package wikibook.learnandroid.smart_refrigerator.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wikibook.learnandroid.smart_refrigerator.R


class RefrigeratorBottomDialogFragment : BottomSheetDialogFragment(){

    private val lazyActivity by lazy {
        requireActivity()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_refrigerator_bottom_dialog, container, false)

        val refrigeratorA = view.findViewById<ConstraintLayout>(R.id.bottom_cl_a)
        val refrigeratorB = view.findViewById<ConstraintLayout>(R.id.bottom_cl_b)
        val refrigeratorC = view.findViewById<ConstraintLayout>(R.id.bottom_cl_c)
        val refrigeratorD = view.findViewById<ConstraintLayout>(R.id.bottom_cl_d)
        val refrigeratorE = view.findViewById<ConstraintLayout>(R.id.bottom_cl_e)
        val refrigeratorF = view.findViewById<ConstraintLayout>(R.id.bottom_cl_f)
        val refrigeratorG = view.findViewById<ConstraintLayout>(R.id.bottom_cl_g)
        val refrigeratorH = view.findViewById<ConstraintLayout>(R.id.bottom_cl_h)


        //val homeRecyclerview = view.findViewById<RecyclerView>(R.id.home_recyclerview)
        //homeRecyclerview.layoutManager = LinearLayoutManager(lazyActivity)

        refrigeratorA.setOnClickListener {
            //homeRecyclerview.adapter = HomeAdapter("","", arrayListOf<String>("A"))
            dismiss()
            val navController = findNavController()
            navController.navigate(R.id.navigation_home)

        }




        return view
    }
    override fun getTheme(): Int = R.style.CustomBottomSheetDialog

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val refrigeratorA = view.findViewById<ConstraintLayout>(R.id.bottom_cl_a)
//
//        refrigeratorA.setOnClickListener {
//            // navigation call here
////            val action = RefrigeratorBottomDialogFragmentDirections.actionNavigationRefrigeratorToNavigationHome()
////            findNavController().navigate(action)
//        }
//    }


}