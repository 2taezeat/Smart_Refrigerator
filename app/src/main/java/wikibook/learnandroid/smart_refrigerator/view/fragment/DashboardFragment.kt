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
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebookfrenzy.carddemo.DashboardEditingAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.FragmentDashboardBinding
import wikibook.learnandroid.smart_refrigerator.repository.Contents
import wikibook.learnandroid.smart_refrigerator.utils.BottomDialogShow
import wikibook.learnandroid.smart_refrigerator.view.activity.MainActivity
import wikibook.learnandroid.smart_refrigerator.viewmodels.DashboardViewModel
import java.text.SimpleDateFormat
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
    var fbAuth : FirebaseAuth? = null
    var fbFirestore : FirebaseFirestore? = null
    var fbStorage : FirebaseStorage? = null

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

        fbStorage = FirebaseStorage.getInstance()
        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

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
                R.id.dashboard_menu_upload -> {
                    val nowTime: Long = System.currentTimeMillis() // content ID
                    val date = Date(nowTime)             // 현재 시간을 Date 타입으로 변환
                    val dateFormat = SimpleDateFormat("yy-MM-dd, hh:mm", Locale("ko", "KR")) // 날짜, 시간을 가져오고 싶은 형태 선언

                    val kind = binding.dashboardEdittextKind.text.toString()
                    val location = binding.dashboardEdittextLocation.text.toString()
                    val updateTime = dateFormat.format(date).toString() // 현재 시간을 dateFormat 에 선언한 형태의 String 으로 변환
                    val count = binding.dashboardEdittextCount.text.toString().toInt()
                    val shelfTime = binding.dashboardEdittextShelflife.text.toString()
                    val purchaseDate = binding.dashboardEdittextPurchasingtime.text.toString()
                    val image = "tmpimage"
                    val memo = binding.dashboardEdittextMemo.text.toString()
                    val useAi = binding.dashboardUseAiCheckbox.isChecked

                    val contentAdd = Contents(
                        kind = kind,
                        location = location,
                        updateTime = updateTime,
                        count = count,
                        shelfTime = shelfTime,
                        purchaseDate = purchaseDate,
                        image = image,
                        memo = memo,
                        useAi = useAi,
                        id = nowTime
                    )
                    fbFirestore?.collection("contents")?.document(nowTime.toString())?.set(contentAdd)

                    clearValue(binding.dashboardEdittextKind)
                    clearValue(binding.dashboardEdittextLocation)
                    clearValue(binding.dashboardEdittextCount)
                    clearValue(binding.dashboardEdittextShelflife)
                    clearValue(binding.dashboardEdittextPurchasingtime)
                    clearValue(binding.dashboardEdittextMemo)
                    binding.dashboardUseAiCheckbox.isChecked = false
                    binding.dashboardFragmentIV1.setImageDrawable(null)



                    Toast.makeText(lazyActivity,"Upload Success!",Toast.LENGTH_SHORT).show()
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

    fun clearValue(textInputEditText: TextInputEditText) {
        textInputEditText.setText("")
    }

}