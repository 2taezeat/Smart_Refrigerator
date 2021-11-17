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
import com.ebookfrenzy.carddemo.NotificationAdapter
import wikibook.learnandroid.smart_refrigerator.R
import wikibook.learnandroid.smart_refrigerator.databinding.FragmentNotificationsBinding
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
        //_binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notifications,container,false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

//        binding.notificationToolbar.setNavigationIcon(R.drawable.ic_baseline_kitchen_24)
//
//        binding.notificationToolbar.setNavigationOnClickListener { view ->
//            // Navigate somewhere
//        }

        binding.notificationToolbar.inflateMenu(R.menu.notification_toolbar_menu)

        binding.notificationToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.notification_menu_refrigerator -> {
                    val fragmentManager: FragmentManager = lazyActivity.supportFragmentManager
                    BottomDialogShow.refrigeratorBottomDialogFragmentShow(fragmentManager)
                    true
                }
                else -> false
            }
        }

        val notificationRecyclerview = binding.notificationRecyclerview
        notificationRecyclerview.layoutManager = LinearLayoutManager(lazyActivity)
        notificationRecyclerview.adapter = NotificationAdapter()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}