package wikibook.learnandroid.smart_refrigerator.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ebookfrenzy.carddemo.NotificationAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.FragmentNotificationsBinding
import wikibook.learnandroid.smart_refrigerator.repository.NotificationInfoDatabase
import wikibook.learnandroid.smart_refrigerator.utils.BottomDialogShow
import wikibook.learnandroid.smart_refrigerator.viewmodels.NotificationsViewModel

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

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
        notificationsViewModel = ViewModelProvider(this).get(NotificationsViewModel::class.java)
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications,container,false)
        val root: View = binding.root
        val notificationRecyclerview = binding.notificationRecyclerview
        var db = NotificationInfoDatabase.getInstance(lazyActivity)!!

        binding.notificationToolbar.inflateMenu(R.menu.notification_toolbar_menu)

        binding.notificationToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.notification_menu_refrigerator -> {
                    val fragmentManager: FragmentManager = lazyActivity.supportFragmentManager
                    BottomDialogShow.refrigeratorBottomDialogFragmentShow(fragmentManager)
                    true
                }
                R.id.notification_menu_refresh -> {
                    updateNotificationInfo(db, notificationRecyclerview)
                    true
                }

                else -> false
            }
        }


        updateNotificationInfo(db, notificationRecyclerview)


//        CoroutineScope(Dispatchers.Main).launch {
//            val notificationInfoList = CoroutineScope(Dispatchers.IO).async {
//                db.notificationInfoDao().getAll()
//            }.await()
//
//            notificationRecyclerview.layoutManager = LinearLayoutManager(lazyActivity)
//            notificationRecyclerview.adapter = NotificationAdapter(notificationInfoList.reversed())
//        }


//        val notificationInfoTmp : ArrayList<NotificationInfo> = arrayListOf(
//            NotificationInfo(
//                notificationCategory = "N1",
//                kind = "Apple",
//                location = "B",
//                notificationBody = "There is one \"apple\"(10) left in \"B\".",
//                notificationTime = "2021-11-22 09:12:34",
//                count = 1
//            ),
//            NotificationInfo(
//                notificationCategory = "N2",
//                kind = "meat",
//                location = "A",
//                notificationBody = "It has been 10 days, since the \"meat\"(1) in \"A\" was stored.",
//                notificationTime = "2021-10-22 09:12:34",
//                count = 2
//            ),
//            NotificationInfo(
//                notificationCategory = "N3",
//                kind = "milk",
//                location = "B",
//                notificationBody = "The shelf life of the \"milk\"(2) in \"D\" is imminent.",
//                notificationTime = "2021-09-22 09:12:34",
//                count = 3
//            ),
//            NotificationInfo(
//                notificationCategory = "N4",
//                kind = "bread",
//                location = "E",
//                notificationBody = "The state of \"bread\"(3) in \"E\" has been updated.",
//                notificationTime = "2021-08-22 09:12:34",
//                count = 4
//            )
//
//        )



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateNotificationInfo(db : NotificationInfoDatabase, recyclerView: RecyclerView) {
        CoroutineScope(Dispatchers.Main).launch {
            val notificationInfoList = CoroutineScope(Dispatchers.IO).async {
                db.notificationInfoDao().getAll()
            }.await()

            recyclerView.layoutManager = LinearLayoutManager(lazyActivity)
            recyclerView.adapter = NotificationAdapter(notificationInfoList.reversed())
        }
    }


}