package wikibook.learnandroid.smart_refrigerator.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
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

        val view2: View = inflater.inflate(R.layout.fragment_home, container, true)
        val hv = view2.findViewById<RecyclerView>(R.id.home_recyclerview)

        refrigeratorA.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("location",arrayListOf<String>("A"))
            dismiss()
            val navController = findNavController()
            navController.navigate(R.id.navigation_home,bundle)

        }

        refrigeratorB.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("location",arrayListOf<String>("B"))
            dismiss()
            val navController = findNavController()
            navController.navigate(R.id.navigation_home,bundle)
        }

        refrigeratorC.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("location",arrayListOf<String>("C"))
            dismiss()
            val navController = findNavController()
            navController.navigate(R.id.navigation_home,bundle)
        }

        refrigeratorD.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("location",arrayListOf<String>("D"))
            dismiss()
            val navController = findNavController()
            navController.navigate(R.id.navigation_home,bundle)
        }

        refrigeratorE.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("location",arrayListOf<String>("E"))
            dismiss()
            val navController = findNavController()
            navController.navigate(R.id.navigation_home,bundle)
        }

        refrigeratorF.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("location",arrayListOf<String>("F"))
            dismiss()
            val navController = findNavController()
            navController.navigate(R.id.navigation_home,bundle)
        }

        refrigeratorG.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("location",arrayListOf<String>("G"))
            dismiss()
            val navController = findNavController()
            navController.navigate(R.id.navigation_home,bundle)
        }

        refrigeratorH.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("location",arrayListOf<String>("H"))
            dismiss()
            val navController = findNavController()
            navController.navigate(R.id.navigation_home,bundle)
        }


        return view
    }
    override fun getTheme(): Int = R.style.CustomBottomSheetDialog


}