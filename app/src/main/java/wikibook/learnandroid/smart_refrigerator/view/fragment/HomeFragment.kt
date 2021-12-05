package wikibook.learnandroid.smart_refrigerator.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebookfrenzy.carddemo.HomeAdapter
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.FragmentHomeBinding
import wikibook.learnandroid.smart_refrigerator.utils.BottomDialogShow
import wikibook.learnandroid.smart_refrigerator.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val lazyActivity by lazy {
        requireActivity()
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
        //val params: ViewGroup.LayoutParams = homeContentLayout.layoutParams


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

                else -> false
            }
        }



        val homeRecyclerview = binding.homeRecyclerview
        homeRecyclerview.layoutManager = LinearLayoutManager(lazyActivity)
        homeRecyclerview.adapter = HomeAdapter()


        return root
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}