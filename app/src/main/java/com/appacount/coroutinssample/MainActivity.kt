package com.appacount.coroutinssample

import android.annotation.SuppressLint
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.math.log
import kotlin.system.measureTimeMillis


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    val TAG2 = "Coroutins Scop"
    private lateinit var txt: TextView

    @SuppressLint("MissingInflatedId")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt = findViewById(R.id.txt_main)
//#1
//        GlobalScope.launch {
//            delay(3000L)
//            Log.d(TAG,"Coroutines says hello from thread ${Thread.currentThread().name}")
//        }
//        Log.d(TAG,"Hello from thread ${Thread.currentThread().name}")
//

//        #2
//        GlobalScope.launch {
//            val netWorkCall = doNetWorkCall()
//            val newtWOrkCall2 = doNetWorkCall2()
//            Log.d(TAG, netWorkCall)
//            Log.d(TAG, doNetWorkCall2())
//
//        }

//    #3
//        GlobalScope.launch(Dispatchers.IO) {
//            Log.d(TAG, "Starting coroutine in thread ${Thread.currentThread().name}")
//            val answer = doNetWorkCall()
//            withContext(Dispatchers.Main) {
//                Log.d(TAG, "setting text in thread ${Thread.currentThread().name}")
//                txt.text = answer
//            }
//        }

//        #4
//        Log.d(TAG, "Before runBlocking")
//        runBlocking {
//            launch (Dispatchers.IO){
//                delay(3000L)
//                Log.d(TAG,"Finished IO Coroutine 1")
//            }
//            launch(Dispatchers.IO) {
//                delay(3000L)
//                Log.d(TAG,"Finished IO Coroutine 2")
//            }
//            Log.d(TAG, "start of runBlocking")
//            delay(5000L)
//            Log.d(TAG, "End of runBlocking")
//        }
//        Log.d(TAG, "After runBlocking")

//#5
//        val job = GlobalScope.launch(Dispatchers.Default) {
//            repeat(5) {
//                Log.d(TAG, "Coroutine is Still working...")
//                delay(1000L)
//            }
//        }
//        runBlocking {
//            delay(2000L)
//            job.cancel()
//            Log.d(TAG, "Main Thread is continuing...")
//        }
//#6

//        #7
//        val job = GlobalScope.launch(Dispatchers.Default) {
//            Log.d(TAG, "starting long running calculation....")
//
//            withTimeout(1000L) {
//                for (i in 30..40) {
//                    if (isActive) {
//                        Log.d(TAG, "Result for i=$i:${fib(i)}")
//                    }
//                }
//            }
//            Log.d(TAG, "Ending long running Calculation...")
//        }
//        runBlocking {
//            delay(2000L)
//            job.cancel()
//            Log.d(TAG, "cancled job!!")
//        }

//        #8
//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//                val answer1 = async { netWorkCall1() }
//                val answer2 = async { networkCall2() }
//                Log.d(TAG, "Answer1 is ${answer1.await()}")
//                Log.d(TAG, "Answer2 is ${answer2.await()}")
//            }
//            Log.d(TAG, "Request took $time ms.")
//        }


//#9
        CoroutineScope(Dispatchers.Main).launch {
            val result1 = async { doWork1() }
            val result2 = async { doWork2() }
            Log.d(TAG2, result1.await())
            Log.d(TAG2, result2.await())
        }

    }

    suspend fun doWork1(): String {
        delay(2000L)
        return "noobe"
    }

    suspend fun doWork2(): String {
        delay(300L)
        return "SAG"
    }

    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }

    suspend fun doNetWorkCall(): String {
        delay(3000L)
        return "NOOBE SAG (: "
    }

    suspend fun doNetWorkCall2(): String {
        delay(5000L)
        return "SAGE SAG"
    }

    suspend fun netWorkCall1(): String {
        delay(300L)
        return "answer 1"
    }

    suspend fun networkCall2(): String {
        delay(3000L)
        return "answer 2"
    }

}


