package com.appacount.coroutinssample

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    val TAG2 = "Coroutine Scope"
    val RUNBLOCKINGTAG = "Coroutine RUN BLOCKING "
    private lateinit var txt: TextView
    lateinit var job: Job

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
//        CoroutineScope(Dispatchers.Main).launch {
//            val result1 = async { doWork1() }
//            val result2 = async { doWork2() }
//            Log.d(TAG2, result1.await())
//            Log.d(TAG2, result2.await())
//        }
//#10 coroutineScope with async builder and time measurement
//        CoroutineScope(Dispatchers.Main).launch {
//            val result1 = async { scopeWork1() }
//            val result2 = async { scopeWork2() }
//            var finalResult = ""
//            val time = measureTimeMillis {
//                finalResult = "${result1.await()} ${result2.await()}"
//            }
//            Log.d(TAG2, "$finalResult => $time")
//
//        }
//#11
//        CoroutineScope(Dispatchers.Main).launch {
//            runBlockingDoWork()
//            runBlockingDoWork()
//            runBlockingDoWork()
//        }

//        #12 change thread
//        CoroutineScope(Dispatchers.IO).launch {
//            doWork1()
//            withContext(Dispatchers.Main) {
//                txt.text = "Coroutine Changed Thread from IO to Main"
//            }
//        }

//        #13 TimeOut in coroutine
//        CoroutineScope(Dispatchers.IO).launch {
//            withTimeoutOrNull(3000L) {
//                for (i in 1000..1100) {
//                    Log.d(TAG2, i.toString())
//                    delay(1000)
//                }
//            }
//        }

//        job = CoroutineScope(Dispatchers.Main).launch {
//            doWork1()
//        }

//        CoroutineScope(Dispatchers.Main).launch {
//            delay(3000L)
//            Log.d(TAG, job.isActive.toString())
//            Log.d(TAG, job.isCompleted.toString())
//            Log.d(TAG, job.isCancelled.toString())
//        }

//#14 coroutine with child coroutine and job with join
//        CoroutineScope(Dispatchers.Main).launch {
//            val job = CoroutineScope(Dispatchers.Main).launch {
//                repeat(3) {
//                    delay(1000L)
//                    Log.d(TAG, "Coroutine is working ")
//                }
//            }
//            job.join()
//            Log.d(TAG, "Coroutine is Completed")
//        }


//        #15 coroutine with child coroutine and job with cancel


//        CoroutineScope(Dispatchers.Main).launch {
//            val job = CoroutineScope(Dispatchers.Main).launch {
//                repeat(3) {
//                    delay(1000L)
//                    Log.d(TAG, "Coroutine is working ")
//                }
//            }
//            delay(2000L)
//            job.cancel()
//            Log.d(TAG, "Coroutine is Completed")
//        }


//        #16 Coroutine with lifecycle

        lifecycleScope.launch {
            while (true) {
                delay(1000)
                Log.d(TAG, "Coroutine is working...")
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            Intent(this@MainActivity, SecondActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }

    }


    private suspend fun runBlockingDoWork() {
        delay(1000L)
        Log.d(RUNBLOCKINGTAG, "runBlocking is started")
    }


    private suspend fun scopeWork1(): String {
        delay(3000L)
        return "Result 1"
    }

    private suspend fun scopeWork2(): String {
        delay(4000L)
        return "Result 2"
    }

    suspend fun doWork1(): String {
        delay(2000L)
        return "Newbie"
    }

    suspend fun doWork2(): String {
        delay(300L)
        return "Pro_Newbie"
    }

    fun fib(n: Int): Long {
        return if (n == 0) 0
        else if (n == 1) 1
        else fib(n - 1) + fib(n - 2)
    }

    suspend fun doNetWorkCall(): String {
        delay(3000L)
        return "You are may sweet heart (: "
    }

    suspend fun doNetWorkCall2(): String {
        delay(5000L)
        return "Newbie (: (8"
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


