package wikibook.learnandroid.smart_refrigerator.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wikibook.learnandroid.smart_refrigerator.R

class RefrigeratorBottomDialogFragment : BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_refrigerator_bottom_dialog, container, false)

    override fun getTheme(): Int = R.style.CustomBottomSheetDialog


}