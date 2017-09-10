package TestThread;

import java.util.ArrayDeque;
import java.util.Queue;

public final class ThreadPool
{
    public interface IThreadPool
    {
        void submitJob(final IJob job);
    }

    public interface IJob
    {
        void onThrowable(Throwable t);

        void run();
    }

    private static final class WorkerThread extends Thread
    {
        private final ThreadPoolImpl	threadPool;

        public WorkerThread(final ThreadPoolImpl threadPool,
                            final String threadName)
        {
            super(threadName);

            this.threadPool = threadPool;
        }

        @Override
        public void run()
        {
            IJob job = null;

            while (true)
            {
                synchronized (this.threadPool)
                {
                    job = this.threadPool.jobs.poll();
                }

                if (null != job)
                {
                    try
                    {
                        job.run();
                    }
                    catch (final Throwable t)
                    {
                        job.onThrowable(t);
                    }

                    job = null;
                }
                else
                {
                    synchronized (this.threadPool)
                    {
                        try
                        {
                            this.threadPool.wait();
                        }
                        catch (final InterruptedException exception)
                        {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }

    private static final class ThreadPoolImpl implements IThreadPool
    {
        private final Queue<IJob>	jobs;

        public ThreadPoolImpl(final int poolSize)
        {
            this.jobs = new ArrayDeque<>();

            for (int index = 0; index < poolSize; index++)
            {
                final WorkerThread workerThread = new WorkerThread(this,
                        "ThreadPool: Worker" + index);
                workerThread.start();
            }
        }

        @Override
        public final void submitJob(final IJob job)
        {
            if (null != job)
            {
                synchronized (this)
                {
                    this.jobs.add(job);
                    this.notifyAll();
                }
            }
        }
    }

    private static final int	THREAD_POOL_SIZE	= 5;

    private static IThreadPool	THREAD_POOL_IMPL;

    public static final IThreadPool getInstance()
    {
        if (null == ThreadPool.THREAD_POOL_IMPL)
        {
            THREAD_POOL_IMPL = new ThreadPoolImpl(THREAD_POOL_SIZE);
        }

        return THREAD_POOL_IMPL;
    }
}