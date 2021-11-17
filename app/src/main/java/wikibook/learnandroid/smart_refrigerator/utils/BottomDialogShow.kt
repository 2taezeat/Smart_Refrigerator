package wikibook.learnandroid.smart_refrigerator.utils

import androidx.fragment.app.FragmentManager
import wikibook.learnandroid.smart_refrigerator.view.fragment.RefrigeratorBottomDialogFragment


class BottomDialogShow{
    companion object {
        private val refrigeratorBottomSheet = RefrigeratorBottomDialogFragment()
        fun refrigeratorBottomDialogFragmentShow(fragmentManager: FragmentManager) {
            refrigeratorBottomSheet.show(fragmentManager, refrigeratorBottomSheet.tag)
        }
    }
}