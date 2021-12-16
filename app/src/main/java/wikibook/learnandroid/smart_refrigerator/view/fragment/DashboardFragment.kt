package wikibook.learnandroid.smart_refrigerator.view.fragment

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebookfrenzy.carddemo.DashboardEditingAdapter
import com.google.android.material.tabs.TabLayout
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.FragmentDashboardBinding
import wikibook.learnandroid.smart_refrigerator.utils.BottomDialogShow
import wikibook.learnandroid.smart_refrigerator.view.activity.MainActivity
import wikibook.learnandroid.smart_refrigerator.viewmodels.DashboardViewModel
import java.util.*


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val lazyActivity by lazy {
        requireActivity()
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {
            var imageUrl = it.data?.data
            binding.dashboardFragmentIV1.setImageURI(imageUrl)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        binding.dashboardToolbar.inflateMenu(R.menu.dashboard_toolbar_menu)
        binding.dashboardToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.dashboard_menu_refrigerator -> {
                    val fragmentManager: FragmentManager = lazyActivity.supportFragmentManager
                    BottomDialogShow.refrigeratorBottomDialogFragmentShow(fragmentManager)
                    true
                }
                R.id.dashboard_menu_image_append -> {
                    var photoPickerIntent = Intent(Intent.ACTION_PICK)
                    photoPickerIntent.type = "image/*"
                    startForResult.launch(Intent.createChooser(photoPickerIntent,"photoPicker"))
                    true
                }
                R.id.dashboard_menu_camera -> {
                    (lazyActivity as MainActivity).takeCapture()
                    true
                }

                else -> false
            }
        }


        binding.dashboardEdittextShelflife.setOnClickListener{
            val cal = Calendar.getInstance()
            val dateSetListener = OnDateSetListener { view, year, month, dayOfMonth ->
                val shelfLifeDateString = "${year}/${month+1}/${dayOfMonth}"
                binding.dashboardEdittextShelflife.setText(shelfLifeDateString)
            }
            DatePickerDialog(lazyActivity, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }


        binding.dashboardEdittextPurchasingtime.setOnClickListener{
            val cal = Calendar.getInstance()
            val dateSetListener = OnDateSetListener { view, year, month, dayOfMonth ->
                val purchasingTimeDateString = "${year}/${month+1}/${dayOfMonth}"
                binding.dashboardEdittextPurchasingtime.setText(purchasingTimeDateString)
            }
            DatePickerDialog(lazyActivity, dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }




        binding.dashboardTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("onTabSelected","${tab}")
                val pos = tab!!.position
                changeView(pos)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("onTabReselected","${tab}")
                val pos = tab!!.position
                changeView(pos)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.d("onTabUnselected","${tab}")
                val pos = tab!!.position
                changeView(pos)
            }
        })



        val dashBoardEditingRecyclerview = binding.dashboardEditingRecyclerview
        dashBoardEditingRecyclerview.layoutManager = LinearLayoutManager(lazyActivity)
        dashBoardEditingRecyclerview.adapter = DashboardEditingAdapter()



        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeView(index: Int) {
        when (index) {
            0 -> {
                binding.dashboardMainAddCl.visibility = View.VISIBLE
                binding.dashboardMainEditCl.visibility = View.INVISIBLE
            }
            1 -> {
                binding.dashboardMainAddCl.visibility = View.INVISIBLE
                binding.dashboardMainEditCl.visibility = View.VISIBLE
            }
        }
    }
}