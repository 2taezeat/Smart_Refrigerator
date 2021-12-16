package wikibook.learnandroid.smart_refrigerator

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wikibook.learnandroid.smart_refrigerator.repository.NotificationInfo
import wikibook.learnandroid.smart_refrigerator.repository.NotificationInfoDatabase
import wikibook.learnandroid.smart_refrigerator.view.activity.MainActivity
import java.text.SimpleDateFormat
import java.util.*


class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) { // FCM 등록 토큰이 업데이트 되면 호출, 토큰이 처음 생성될때 토큰을 검
        Log.d(TAG, "Refreshed token: $token")
        super.onNewToken(token)
        sendRegistrationToServer(token) // FCM 등록 토큰을 앱서버에 추가합니다.
    }


    var db = NotificationInfoDatabase.getInstance(this)!!

    // 메세지를 수신할때 호출한다. remoteMessage는 수신한 메시지이다.
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")
        Log.d(TAG, "remoteMessage: ${remoteMessage.data}")

        // Check if message contains a data payload.
        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")

            val data = remoteMessage.data
            val notificationCategory = data.getValue("body")
            val kind = data.getValue("kind")
            val notificationBody = data.getValue("title")
            val count = data.getValue("count").toInt()
            val location = data.getValue("location")

            val nowTime: Long = System.currentTimeMillis()
            val date = Date(nowTime)             // 현재 시간을 Date 타입으로 변환
            val dateFormat = SimpleDateFormat("yy-MM-dd, hh:mm", Locale("ko", "KR")) // 날짜, 시간을 가져오고 싶은 형태 선언
            val notificationTime = dateFormat.format(date) // 현재 시간을 dateFormat 에 선언한 형태의 String 으로 변환
            CoroutineScope(Dispatchers.IO).launch {
                db.notificationInfoDao().insert(NotificationInfo(notificationCategory, kind, location, notificationBody, notificationTime, count))
            }

            sendNotification(remoteMessage)
            if (true) { /* Check if data needs to be processed by long running job */
                scheduleJob()
            } else {
                handleNow()
            }
        }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Body: ${it.body}")
        }

    }


    private fun scheduleJob() { // 'data'를 처리하는데 10초 이상 걸릴경우에 호출, WorkManager를 사용하여 비동기 작업 예
        //val work = OneTimeWorkRequest.Builder(Myw)
    }

    private fun handleNow() { // 'data'를 처리하는데 10초 이내에 걸릴경우 호출, BroadcastReceiveres에 할당된 시간을 처리
        Log.d(TAG, "Short Lived task is done.")
    }


    private fun sendRegistrationToServer(token: String) {
        Log.d(TAG, "sendRegistrationTokenTOServer($token)")
        // 타사 서버에 토큰을 유지해주는 메서드
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        // RemoteCode, ID를 고유값으로 지정하여 알림이 개별 표시 되도록 함
        val uniId: Int = (System.currentTimeMillis() / 7).toInt()

        // PendingIntent : Intent 의 실행 권한을 외부의 어플리케이션에게 위임
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Activity Stack을 경로만 남김, A-B-C-D-B => A-B
        val pendingIntent =
            PendingIntent.getActivity(this, uniId, intent, PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.firebase_notification_channel_id)
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // 알림에 대한 UI 정보와 작업을 지정한다.
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher) // 아이콘 설정
            .setContentTitle(remoteMessage.data["body"].toString()) // 제목
            .setContentText(remoteMessage.data["title"].toString()) // 메시지 내용
            .setAutoCancel(true)
            .setSound(soundUri) // 알림 소리
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 이후에는 채널이 필요하다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        // 알림 생성
        notificationManager.notify(uniId, notificationBuilder.build())
    }


//    private fun addUser(){
//        //var name = binding.etName.text.toString()
//        //var age = binding.etAge.text.toString()
//        //var phone = binding.etPhone.text.toString()
//
//        CoroutineScope(Dispatchers.IO).launch {
//            db.notificationDao().insert(User(name, age, phone))
//        }
//    }
//
//    private fun refreshNotificationInfo(){
//        var userList = "유저 리스트\n"
//
//        CoroutineScope(Dispatchers.Main).launch {
//            val users = CoroutineScope(Dispatchers.IO).async {
//                db.notificationDao().getAll()
//            }.await()
//
//            for(user in users){
//                userList += "이름: ${user.name}, 나이: ${user.age}, 번호: ${user.phone}\n"
//            }
//            //binding.tvPerson.text = userList
//        }
//    }


}